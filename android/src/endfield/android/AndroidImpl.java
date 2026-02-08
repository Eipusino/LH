package endfield.android;

import arc.func.Cons;
import arc.util.Log;
import endfield.util.CollectionObjectMap;
import endfield.util.DefaultAccessibleHelper;
import endfield.util.PlatformImpl;
import libcore.io.Memory;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import static endfield.Vars2.accessibleHelper;
import static endfield.Vars2.classHelper;
import static endfield.Vars2.fieldAccessHelper;
import static endfield.Vars2.methodInvokeHelper;
import static endfield.android.Unsafer.unsafe;

public class AndroidImpl implements PlatformImpl {
	public static final int ALL_MODES = Lookup.PUBLIC | Lookup.PRIVATE | Lookup.PROTECTED | Lookup.PACKAGE;

	static final Cons<Throwable> exceptionHandler = e -> {};

	static final CollectionObjectMap<Class<?>, Lookup> lookupMap = new CollectionObjectMap<>(Class.class, Lookup.class);

	static Constructor<Lookup> constructor;

	static final Function<Class<?>, Lookup> initialer = clazz -> {
		try {
			return constructor.newInstance(clazz, ALL_MODES);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	};

	static {
		try {
			Log.infoTag("Unsafe", "getUnsafe: " + unsafe);

			try {
				HiddenApi.setHiddenApiExemptions();
			} catch (Throwable e) {
				Log.err(e);
			}
		} catch (Throwable e) {
			Log.err("It seems you platform is special. (But don't worry)", e);
		}

		accessibleHelper = new DefaultAccessibleHelper();
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
		return lookupMap.computeIfAbsent(clazz, initialer);
	}

	@Override
	public void copyMemory(long srcAddr, long dstAddr, long bytes) {
		unsafe.copyMemory(srcAddr, dstAddr, bytes);
	}

	@Override
	public void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes) {
		Memory.memmove(destBase, (int) destOffset, srcBase, (int) srcOffset, bytes);
	}
}
