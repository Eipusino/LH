package heavyindustry.android;

import arc.util.Log;
import dalvik.system.VMStack;
import heavyindustry.util.PlatformImpl;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;

import static heavyindustry.util.Objects2.run;
import static heavyindustry.util.Reflects.lookup;
import static heavyindustry.util.Unsafer.unsafe;
import static heavyindustry.util.Unsafer2.internalUnsafe;

public class AndroidImpl implements PlatformImpl {
	static Field accessFlags;

	static {
		init();
	}

	static void init() {
		try {
			Log.info("Use @", Class.forName("sun.misc.Unsafe"));

			Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (sun.misc.Unsafe) field.get(null);
		} catch (Throwable e) {
			Log.err(e);

			return;
		}

		run(HiddenApi::setHiddenApiExemptions);
		run(() -> {
			Log.info("Use @", Class.forName("jdk.internal.misc.Unsafe"));

			Field field = jdk.internal.misc.Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			internalUnsafe = (jdk.internal.misc.Unsafe) field.get(null);
		});
		run(() -> {
			accessFlags = Class.class.getDeclaredField("accessFlags");
			accessFlags.setAccessible(true);
		});
		run(() -> {
			Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
			field.setAccessible(true);
			lookup = (Lookup) field.get(null);
		});
	}

	@Override
	public void setPublic(Class<?> obj) {
		try {
			if (accessFlags != null) {
				int flags = accessFlags.getInt(obj);
				accessFlags.setInt(obj, 65535 & ((flags & 65535 & (-17) & (-3)) | 1));
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Class<?> callerClass() {
		return VMStack.getStackClass2();
	}
}
