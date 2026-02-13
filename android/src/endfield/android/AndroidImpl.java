package endfield.android;

import arc.func.Cons;
import arc.util.Log;
import endfield.util.AccessibleHelper;
import endfield.util.CollectionObjectMap;
import endfield.util.PlatformImpl;
import libcore.io.Memory;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.Buffer;
import java.util.Objects;
import java.util.function.Function;

import static endfield.Vars2.accessibleHelper;
import static endfield.Vars2.classHelper;
import static endfield.Vars2.fieldAccessHelper;
import static endfield.Vars2.methodInvokeHelper;
import static endfield.android.Unsafer.unsafe;

@SuppressWarnings("removal")
public class AndroidImpl implements PlatformImpl {
	static final Cons<Throwable> exceptionHandler = e -> {};

	static Constructor<Lookup> constructor;

	static final CollectionObjectMap<Class<?>, Lookup> lookupMap = new CollectionObjectMap<>(Class.class, Lookup.class);
	static final Function<Class<?>, Lookup> lookupBuilder = clazz -> {
		try {
			return constructor.newInstance(clazz, 15);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	};

	static {
		Log.infoTag("Unsafe", "getUnsafe: " + unsafe);

		try {
			HiddenApi.load();
		} catch (Throwable e) {
			Log.err("It seems you platform is special. (But don't worry)", e);
		}

		accessibleHelper = new AndroidAccessibleHelper();
		classHelper = new AndroidClassHelper();
		fieldAccessHelper = new AndroidFieldAccessHelper();
		methodInvokeHelper = new AndroidMethodInvokeHelper();

		try {
			constructor = Lookup.class.getDeclaredConstructor(Class.class, int.class);
			constructor.setAccessible(true);
		} catch (Throwable e) {
			Log.err(e);
		}
	}

	// Due to the lack of TRUSTED lookup in Android, each class needs to create an ALL_MODES lookup.
	@Override
	public Lookup lookup(Class<?> clazz) {
		return lookupMap.computeIfAbsent(clazz, lookupBuilder);
	}

	@Override
	public void putBuffer(Buffer src, int srcOffset, Buffer dst, int dstOffset, int numBytes) {
		Objects.requireNonNull(src);
		Objects.requireNonNull(dst);

		Memory.memmove(dst, dstOffset, src, srcOffset, numBytes);
	}
}
