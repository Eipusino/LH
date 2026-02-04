package endfield.desktop;

import arc.func.Prov;
import dynamilize.FunctionType;
import endfield.util.CollectionObjectMap;
import endfield.util.MethodInvokeHelper;
import endfield.util.Reflects;
import endfield.util.holder.ObjectHolder;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static endfield.Vars2.classHelper;
import static endfield.desktop.DesktopClassHelper.ctypes;
import static endfield.desktop.DesktopClassHelper.mtypes;
import static endfield.desktop.DesktopClassHelper.ptypes;
import static endfield.desktop.DesktopImpl.lookup;

public class DesktopMethodInvokeHelper implements MethodInvokeHelper {
	protected static final CollectionObjectMap<Class<?>, CollectionObjectMap<String, CollectionObjectMap<FunctionType, MethodHandle>>> methodPool = new CollectionObjectMap<>(Class.class, CollectionObjectMap.class);

	protected static final Prov<CollectionObjectMap<String, CollectionObjectMap<FunctionType, MethodHandle>>> prov1 = () -> new CollectionObjectMap<>(String.class, CollectionObjectMap.class);
	protected static final Prov<CollectionObjectMap<FunctionType, MethodHandle>> prov2 = () -> new CollectionObjectMap<>(FunctionType.class, MethodHandle.class);

	protected MethodHandle getMethod(Class<?> clazz, String name, FunctionType argTypes) throws NoSuchMethodException, IllegalAccessException {
		CollectionObjectMap<FunctionType, MethodHandle> map = methodPool.get(clazz, prov1).get(name, prov2);

		FunctionType type = FunctionType.inst(argTypes);
		MethodHandle res = map.get(type);

		if (res != null) return res;

		for (ObjectHolder<FunctionType, MethodHandle> entry : map) {
			if (entry.key.match(argTypes.getTypes())) return entry.value;
		}

		Class<?> curr = clazz;

		while (curr != null) {
			try {
				Method method = classHelper.getMethod(curr, name, argTypes.getTypes());

				if (method != null) {
					method.setAccessible(true);
					res = lookup.unreflect(method);
				}
			} catch (IllegalAccessException ignored) {}

			if (res != null) {
				map.put(inst(res.type()), res);
				break;
			}

			curr = curr.getSuperclass();
		}

		if (res != null) return res;

		curr = clazz;
		a:
		while (curr != null) {
			for (Method method : classHelper.getMethods(curr)) {
				if (!method.getName().equals(name)) continue;
				Class<?>[] methodArgs = method.getParameterTypes();

				FunctionType t;
				if ((t = from(method)).match(methodArgs)) {
					method.setAccessible(true);

					res = lookup.unreflect(method);
					map.put(t, res);
					break a;
				}
				t.recycle();
			}

			curr = curr.getSuperclass();
		}

		if (res == null)
			throw new NoSuchMethodException("no such method " + name + " in class: " + clazz + " with assignable parameter: " + argTypes);

		return res;
	}

	protected MethodHandle getConstructor(Class<?> clazz, FunctionType argsType) throws IllegalAccessException {
		CollectionObjectMap<FunctionType, MethodHandle> map = methodPool.get(clazz, prov1).get("<init>", prov2);

		MethodHandle res = map.get(argsType);
		if (res != null) return res;

		for (ObjectHolder<FunctionType, MethodHandle> entry : map) {
			if (entry.key.match(argsType.getTypes())) return entry.value;
		}

		try {
			Constructor<?> met = clazz.getConstructor(argsType.getTypes());

			met.setAccessible(true);

			res = lookup.unreflectConstructor(met);
		} catch (NoSuchMethodException | IllegalAccessException ignored) {}

		if (res != null) return res;

		for (Constructor<?> constructor : classHelper.getConstructors(clazz)) {
			FunctionType functionType;
			if ((functionType = from(constructor)).match(argsType.getTypes())) {
				constructor.setAccessible(true);

				res = lookup.unreflectConstructor(constructor);
				map.put(functionType, res);

				break;
			}
			functionType.recycle();
		}

		if (res != null) return res;

		throw new NoSuchMethodError("no such constructor in class: " + clazz + " with assignable parameter: " + argsType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invoke(Object object, String name, Object... args) {
		FunctionType type = FunctionType.inst(args);
		try {
			return (T) Reflects.invokeVirtual(object, getMethod(object.getClass(), name, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invokeStatic(Class<?> clazz, String name, Object... args) {
		FunctionType type = FunctionType.inst(args);
		try {
			return (T) Reflects.invokeStatic(getMethod(clazz, name, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T newInstance(Class<T> clazz, Object... args) {
		FunctionType type = FunctionType.inst(args);
		try {
			return (T) Reflects.invokeStatic(getConstructor(clazz, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invoke(Object object, String name, Class<?>[] parameterTypes, Object... args) {
		FunctionType type = FunctionType.inst(parameterTypes);
		try {
			return (T) Reflects.invokeVirtual(object, getMethod(object.getClass(), name, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invokeStatic(Class<?> clazz, String name, Class<?>[] parameterTypes, Object... args) {
		FunctionType type = FunctionType.inst(parameterTypes);
		try {
			return (T) Reflects.invokeStatic(getMethod(clazz, name, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T newInstance(Class<T> clazz, Class<?>[] parameterTypes, Object... args) {
		FunctionType type = FunctionType.inst(parameterTypes);
		try {
			return (T) Reflects.invokeStatic(getConstructor(clazz, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	public static FunctionType inst(MethodType methodType) {
		return FunctionType.inst((Class<?>[]) ptypes.get(methodType));
	}

	public static FunctionType from(Method method) {
		return FunctionType.inst((Class<?>[]) mtypes.get(method));
	}

	public static FunctionType from(Constructor<?> constructor) {
		return FunctionType.inst((Class<?>[]) ctypes.get(constructor));
	}
}
