package endfield.desktop;

import arc.util.Log;
import endfield.util.PlatformImpl;

import java.lang.StackWalker.Option;
import java.lang.StackWalker.StackFrame;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import static endfield.util.Objects2.run;
import static endfield.util.Reflects.lookup;
import static endfield.util.Unsafer.unsafe;
import static endfield.util.Unsafer2.internalUnsafe;

public class DesktopImpl implements PlatformImpl {
	private static MethodHandle getFieldsMethod, getMethodsMethod, getConstructorsMethod;
	private static VarHandle methodParameterTypes, constructorParameterTypes;

	private static StackWalker walker;

	static {
		init();
	}

	private static void init() {
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
			getFieldsMethod = lookup.findVirtual(Class.class, "getDeclaredFields0", MethodType.methodType(Field[].class, boolean.class));
			getMethodsMethod = lookup.findVirtual(Class.class, "getDeclaredMethods0", MethodType.methodType(Method[].class, boolean.class));
			getConstructorsMethod = lookup.findVirtual(Class.class, "getDeclaredConstructors0", MethodType.methodType(Constructor[].class, boolean.class));

			methodParameterTypes = lookup.findVarHandle(Method.class, "parameterTypes", Class[].class);
			constructorParameterTypes = lookup.findVarHandle(Constructor.class, "parameterTypes", Class[].class);
		});

		walker = StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE);
	}

	@Override
	public Field getField(Class<?> type, String name) {
		try {
			Field[] fields = (Field[]) getFieldsMethod.invokeExact(type, false);
			for (Field field : fields) {
				if (field.getName().equals(name)) return field;
			}
			return null;
		} catch (Throwable e) {
			return PlatformImpl.super.getField(type, name);
		}
	}

	@Override
	public Method getMethod(Class<?> type, String name, Class<?>... parameterTypes) {
		try {
			Method[] methods = (Method[]) getMethodsMethod.invokeExact(type, false);
			for (Method method : methods) {
				if (method.getName().equals(name) && Arrays.equals((Class<?>[]) methodParameterTypes.get(method), parameterTypes)) return method;
			}
			return null;
		} catch (Throwable e) {
			return PlatformImpl.super.getMethod(type, name, parameterTypes);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Constructor<T> getConstructor(Class<T> type, Class<?>... parameterTypes) {
		try {
			Constructor<T>[] constructors = (Constructor<T>[]) getConstructorsMethod.invokeExact(type, false);
			for (Constructor<T> constructor : constructors) {
				if (Arrays.equals((Class<?>[]) constructorParameterTypes.get(constructor), parameterTypes)) return constructor;
			}
			return null;
		} catch (Throwable e) {
			return PlatformImpl.super.getConstructor(type, parameterTypes);
		}
	}

	@Override
	public Field[] getFields(Class<?> type) {
		try {
			return (Field[]) getFieldsMethod.invokeExact(type, false);
		} catch (Throwable e) {
			return type.getDeclaredFields();
		}
	}

	@Override
	public Method[] getMethods(Class<?> type) {
		try {
			return (Method[]) getMethodsMethod.invokeExact(type, false);
		} catch (Throwable e) {
			return type.getDeclaredMethods();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Constructor<T>[] getConstructors(Class<T> type) {
		try {
			return (Constructor<T>[]) getConstructorsMethod.invokeExact(type, false);
		} catch (Throwable e) {
			return PlatformImpl.super.getConstructors(type);
		}
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
	public Class<?> defineClass(String name, byte[] bytes, ClassLoader loader) throws ClassFormatError {
		return internalUnsafe.defineClass(name, bytes, 0, bytes.length, loader, null);
	}
}
