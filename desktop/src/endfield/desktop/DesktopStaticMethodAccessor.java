package endfield.desktop;

import endfield.util.MethodAccessor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Arrays;

import static endfield.desktop.DesktopImpl.lookup;

public class DesktopStaticMethodAccessor implements MethodAccessor {
	final Method method;
	final MethodHandle spreadHandle;

	int hash;

	public DesktopStaticMethodAccessor(Method met) {
		try {
			method = met;
			MethodHandle target = lookup.unreflect(met);

			int paramCount = target.type().parameterCount();

			spreadHandle = target.asSpreader(Object[].class, paramCount)
					.asType(MethodType.methodType(Object.class, Object[].class));
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invoke(Object obj, Object... args) {
		try {
			return (T) spreadHandle.invokeExact(args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this || obj instanceof DesktopStaticMethodAccessor other && other.getMethod().equals(method);
	}

	@Override
	public int hashCode() {
		int hc = hash;

		if (hc == 0) {
			hc = hash = method.getDeclaringClass().getName().hashCode() ^
					method.getName().hashCode() ^
					Arrays.hashCode(method.getParameterTypes());
		}

		return hc;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + method.toString();
	}
}
