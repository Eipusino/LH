package heavyindustry.desktop;

import arc.util.Log;
import heavyindustry.util.PlatformImpl;

import java.lang.StackWalker.Option;
import java.lang.StackWalker.StackFrame;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import static heavyindustry.util.Objects2.run;
import static heavyindustry.util.Reflects.lookup;
import static heavyindustry.util.Unsafer.unsafe;
import static heavyindustry.util.Unsafer2.internalUnsafe;

public class DesktopImpl implements PlatformImpl {
	static MethodHandle getFieldsHandle, getMethodsHandle, getConstructorsHandle;

	static StackWalker walker;

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

		run(() -> {
			lookup = (Lookup) unsafe.getObject(Lookup.class, unsafe.staticFieldOffset(Lookup.class.getDeclaredField("IMPL_LOOKUP")));
		});
		run(() -> {
			Demodulator.init();
			Demodulator.openModules();
		});
		run(Demodulator::ensureFieldOpen);
		run(() -> {
			Log.info("Use @", Class.forName("jdk.internal.misc.Unsafe"));

			internalUnsafe = jdk.internal.misc.Unsafe.getUnsafe();
		});
		run(() -> {
			getFieldsHandle = lookup.findVirtual(Class.class, "getDeclaredFields0", MethodType.methodType(Field[].class, boolean.class));
			getMethodsHandle = lookup.findVirtual(Class.class, "getDeclaredMethods0", MethodType.methodType(Method[].class, boolean.class));
			getConstructorsHandle = lookup.findVirtual(Class.class, "getDeclaredConstructors0", MethodType.methodType(Constructor[].class, boolean.class));
		});

		walker = StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE);
	}

	@Override
	public Class<?> callerClass() {
		try {
			Optional<String> callerClassName = walker.walk(frames -> frames
					.skip(1)
					.findFirst()
					.map(StackFrame::getClassName));
			return callerClassName.isPresent() ? Class.forName(callerClassName.get()) : null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	@Override
	public Field[] getFields(Class<?> cls) {
		try {
			return (Field[]) getFieldsHandle.invokeExact(cls, false);
		} catch (Throwable e) {
			return cls.getDeclaredFields();
		}
	}

	@Override
	public Method[] getMethods(Class<?> cls) {
		try {
			return (Method[]) getMethodsHandle.invokeExact(cls, false);
		} catch (Throwable e) {
			return cls.getDeclaredMethods();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Constructor<T>[] getConstructors(Class<T> type) {
		try {
			return (Constructor<T>[]) getConstructorsHandle.invokeExact(type, false);
		} catch (Throwable e) {
			return PlatformImpl.super.getConstructors(type);
		}
	}
}
