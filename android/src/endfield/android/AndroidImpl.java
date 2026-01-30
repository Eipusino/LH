package endfield.android;

import arc.Core;
import dalvik.system.VMStack;
import endfield.android.field.AndroidField;
import endfield.util.PlatformImpl;
import endfield.util.Unsafer;
import endfield.util.Unsafer2;
import libcore.io.Memory;
import mindustry.android.AndroidRhinoContext;
import mindustry.android.AndroidRhinoContext.AndroidContextFactory;
import rhino.ContextFactory;
import rhino.GeneratedClassLoader;

import java.io.File;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;

import static endfield.util.Objects2.run;
import static endfield.util.Reflects.lookup;

public class AndroidImpl implements PlatformImpl {
	static Field accessFlagsField;

	static {
		run(() -> {
			Class.forName("sun.misc.Unsafe", true, null);

			Unsafer.init();

			run(() -> {
				HiddenApi.setHiddenApiExemptions();

				run(() -> {
					Class.forName("jdk.internal.misc.Unsafe", true, null);

					Unsafer2.init();
				});

				run(() -> {
					Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
					field.setAccessible(true);
					lookup = (Lookup) field.get(null);
				});
			});
		});

		run(() -> {
			accessFlagsField = Class.class.getDeclaredField("accessFlags");
			accessFlagsField.setAccessible(true);
		});
	}

	@Override
	public void setPublic(Class<?> type) {
		try {
			if (accessFlagsField != null) {
				int flags = accessFlagsField.getInt(type);
				accessFlagsField.setInt(type, 65535 & ((flags & 65535 & (-17) & (-3)) | 1));
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long offset(Field field) {
		return AndroidField.fieldOffset(field);
	}

	@Override
	public long staticOffset(Field field) {
		return AndroidField.fieldOffset(field);
	}

	@Override
	public long objectOffset(Field field) {
		return AndroidField.fieldOffset(field);
	}

	@Override
	public void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes) {
		Memory.memmove(destBase, (int) destOffset, srcBase, (int) srcOffset, bytes);
	}

	@Override
	public Class<?> callerClass() {
		return VMStack.getStackClass2();
	}

	@Override
	public Class<?> defineClass(String name, byte[] bytes, ClassLoader loader) throws ClassFormatError {
		if (!(ContextFactory.getGlobal() instanceof AndroidContextFactory)) {
			AndroidRhinoContext.enter(new File(Core.settings.getDataDirectory() + "/rhino/"));
		}
		return ((GeneratedClassLoader) ((AndroidContextFactory) ContextFactory.getGlobal())
				.createClassLoader(loader))
				.defineClass(name, bytes);
	}
}
