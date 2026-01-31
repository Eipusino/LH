package endfield.android;

import arc.func.Prov;
import dynamilize.FunctionType;
import endfield.util.CollectionObjectMap;
import endfield.util.MethodInvokeHelper;
import endfield.util.misc.ObjectHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AndroidMethodInvokeHelper implements MethodInvokeHelper {
	protected static final CollectionObjectMap<Class<?>, CollectionObjectMap<String, CollectionObjectMap<FunctionType, Method>>> methodPool = new CollectionObjectMap<>(Class.class, CollectionObjectMap.class);
	protected static final CollectionObjectMap<Class<?>, CollectionObjectMap<FunctionType, Constructor<?>>> cstrMap = new CollectionObjectMap<>(Class.class, CollectionObjectMap.class);

	protected static final Prov<CollectionObjectMap<String, CollectionObjectMap<FunctionType, Method>>> prov1 = () -> new CollectionObjectMap<>(String.class, CollectionObjectMap.class);
	protected static final Prov<CollectionObjectMap<FunctionType, Method>> prov2 = () -> new CollectionObjectMap<>(FunctionType.class, Method.class);
	protected static final Prov<CollectionObjectMap<FunctionType, Constructor<?>>> prov3 = () -> new CollectionObjectMap<>(FunctionType.class, Constructor.class);

	protected Method getMethod(Class<?> clazz, String name, FunctionType argTypes) {
		CollectionObjectMap<FunctionType, Method> map = methodPool.getDefault2(clazz, prov1).getDefault2(name, prov2);

		FunctionType type = FunctionType.inst(argTypes);
		Method res = map.get(type);

		if (res != null) return res;

		for (ObjectHolder<FunctionType, Method> entry : map) {
			if (entry.key.match(argTypes.getTypes())) return entry.value;
		}

		Class<?> curr = clazz;

		while (curr != null) {
			try {
				res = curr.getDeclaredMethod(name, argTypes.getTypes());
			} catch (Throwable ignored) {
			}

			if (res != null) {
				res.setAccessible(true);
				map.put(FunctionType.from(res), res);
				break;
			}

			curr = curr.getSuperclass();
		}

		if (res != null) return res;

		curr = clazz;
		a:
		while (curr != null) {
			for (Method method : curr.getDeclaredMethods()) {
				if (!method.getName().equals(name)) continue;

				FunctionType t;
				if ((t = FunctionType.from(method)).match(argTypes.getTypes())) {
					method.setAccessible(true);
					res = method;
					map.put(t, res);
					break a;
				}
				t.recycle();
			}

			curr = curr.getSuperclass();
		}

		if (res == null)
			throw new RuntimeException("no such method " + name + " in class: " + clazz + " with assignable parameter: " + argTypes);

		return res;
	}

	@SuppressWarnings("unchecked")
	protected <T> Constructor<T> getConstructor(Class<T> type, FunctionType argsType) {
		CollectionObjectMap<FunctionType, Constructor<?>> map = cstrMap.getDefault2(type, prov3);

		Constructor<T> res = (Constructor<T>) map.get(argsType);
		if (res != null) return res;

		for (ObjectHolder<FunctionType, Constructor<?>> entry : map) {
			if (entry.key.match(argsType.getTypes())) return (Constructor<T>) entry.value;
		}

		try {
			res = type.getConstructor(argsType.getTypes());
			res.setAccessible(true);
		} catch (NoSuchMethodException ignored) {
		}

		if (res != null) return res;

		for (Constructor<?> constructor : type.getDeclaredConstructors()) {
			FunctionType functionType;
			if ((functionType = FunctionType.from(constructor)).match(argsType.getTypes())) {
				map.put(functionType, constructor);
				res = (Constructor<T>) constructor;
				res.setAccessible(true);

				break;
			}
			functionType.recycle();
		}

		if (res != null) return res;

		throw new RuntimeException("no such constructor in class: " + type + " with assignable parameter: " + argsType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invoke(Object owner, String method, Object... args) {
		FunctionType type = FunctionType.inst(args);
		try {
			return (T) getMethod(owner.getClass(), method, type).invoke(owner, args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invokeStatic(Class<?> clazz, String method, Object... args) {
		FunctionType type = FunctionType.inst(args);
		try {
			return (T) getMethod(clazz, method, type).invoke(null, args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@Override
	public <T> T newInstance(Class<T> type, Object... args) {
		FunctionType funcType = FunctionType.inst(args);
		try {
			return getConstructor(type, funcType).newInstance(args);
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			throw new RuntimeException(e);
		} finally {
			funcType.recycle();
		}
	}
}
