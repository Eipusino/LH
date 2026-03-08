package endfield.desktop;

import arc.util.Structs;
import dynamilize.FunctionType;
import endfield.util.CollectionObjectMap;
import endfield.util.DefaultMethodInvokeHelper;
import endfield.util.Reflects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Objects;

import static endfield.Vars2.classHelper;
import static endfield.desktop.DesktopImpl.lookup;
import static endfield.desktop.DesktopMethodInvokeHelper.from;

/** @deprecated High performance overhead. Experimental API. */
@Deprecated
public class DirectMethodInvokeHelper extends DefaultMethodInvokeHelper {
	static final MethodHandle invoke, newInstance;

	static {
		try {
			Class<?> refc = Objects.requireNonNullElseGet(Reflects.findClass("jdk.internal.reflect.DirectMethodHandleAccessor$NativeAccessor"),
					() -> Reflects.findClass("jdk.internal.reflect.NativeMethodAccessorImpl")),
					refc2 = Objects.requireNonNullElseGet(Reflects.findClass("jdk.internal.reflect.DirectConstructorHandleAccessor$NativeAccessor"),
							() -> Reflects.findClass("jdk.internal.reflect.NativeConstructorAccessorImpl"));

			invoke = lookup.findStatic(refc, "invoke0", MethodType.methodType(Object.class, Method.class, Object.class, Object[].class));
			newInstance = lookup.findStatic(refc2, "newInstance0", MethodType.methodType(Object.class, Constructor.class, Object[].class));
		} catch (NoSuchMethodException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T invoke(Object object, Method method, Object... args) throws Throwable {
		return (T) invoke.invokeExact(method, object, args);
	}

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Constructor<T> constructor, Object... args) throws Throwable {
		return (T) newInstance.invokeExact(constructor, args);
	}

	@Override
	public <T> T invoke(Object object, String name, Object... args) {
		FunctionType type = FunctionType.inst(args);
		try {
			return invoke(object, getMethod(object.getClass(), name, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@Override
	public <T> T invokeStatic(Class<?> clazz, String name, Object... args) {
		FunctionType type = FunctionType.inst(args);
		try {
			return invoke(null, getMethod(clazz, name, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@Override
	public <T> T newInstance(Class<T> type, Object... args) {
		FunctionType funcType = FunctionType.inst(args);
		try {
			return newInstance(getConstructor(type, funcType), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			funcType.recycle();
		}
	}

	@Override
	public <T> T invokeWithAsType(Object object, String name, Class<?>[] parameterTypes, Object... args) {
		FunctionType type = FunctionType.inst(parameterTypes);
		try {
			return invoke(object, getMethod(object.getClass(), name, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@Override
	public <T> T invokeStaticWithAsType(Class<?> clazz, String name, Class<?>[] parameterTypes, Object... args) {
		FunctionType type = FunctionType.inst(parameterTypes);
		try {
			return invoke(null, getMethod(clazz, name, type), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			type.recycle();
		}
	}

	@Override
	public <T> T newInstanceWithAsType(Class<T> type, Class<?>[] parameterTypes, Object... args) {
		FunctionType funcType = FunctionType.inst(parameterTypes);
		try {
			return newInstance(getConstructor(type, funcType), args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			funcType.recycle();
		}
	}

	@Override
	public <T> T invoke(Method method, Object object, boolean access, Object... args) {
		try {
			return invoke(object, method, args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T invokeStatic(Method method, boolean access, Object... args) {
		try {
			return invoke(null, method, args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T newInstance(Constructor<T> constructor, boolean access, Object... args) {
		try {
			return newInstance(constructor, args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Method getMethod(Class<?> clazz, String name, FunctionType argTypes) throws NoSuchMethodException {
		CollectionObjectMap<FunctionType, Method> map = methodPool.get(clazz, prov1).get(name, prov2);

		FunctionType type = FunctionType.inst(argTypes);
		Method res = map.get(type);

		if (res != null) return res;

		for (var entry : map) {
			if (entry.key.match(argTypes.getTypes())) return entry.value;
		}

		Class<?> curr = clazz;

		if (!Structs.contains(argTypes.getTypes(), void.class)) {
			while (curr != null) {
				Method method = classHelper.getMethod(curr, name, argTypes.getTypes());

				if (method != null) {
					map.put(from(method), method);
					res = method;

					break;
				}

				curr = curr.getSuperclass();
			}

			if (res != null) return res;
		}

		curr = clazz;
		a:
		while (curr != null) {
			for (Method method : classHelper.getMethods(curr)) {
				if (!method.getName().equals(name)) continue;

				FunctionType t;
				if ((t = from(method)).match(argTypes.getTypes())) {
					res = method;
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

	@SuppressWarnings("unchecked")
	@Override
	protected <T> Constructor<T> getConstructor(Class<T> clazz, FunctionType argsType) throws NoSuchMethodException {
		CollectionObjectMap<FunctionType, Constructor<?>> map = cstrMap.get(clazz, prov3);

		Constructor<T> res = (Constructor<T>) map.get(argsType);
		if (res != null) return res;

		for (var entry : map) {
			if (entry.key.match(argsType.getTypes())) return (Constructor<T>) entry.value;
		}

		Constructor<T> cstr = classHelper.getConstructor(clazz, argsType.getTypes());
		if (cstr != null) {
			res = cstr;
		}

		if (res != null) return res;

		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			FunctionType t;
			if ((t = from(constructor)).match(argsType.getTypes())) {
				map.put(t, constructor);
				res = (Constructor<T>) constructor;

				break;
			}
			t.recycle();
		}

		if (res != null) return res;

		throw new NoSuchMethodException("no such constructor in class: " + clazz + " with assignable parameter: " + argsType);
	}
}
