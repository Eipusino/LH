package endfield.desktop;

import endfield.util.MethodAccessor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static endfield.desktop.DesktopImpl.lookup;

public class DesktopMethodAccessor implements MethodAccessor {
	final Method method;
	final MethodHandle spreadHandle;
	final boolean isStatic;

	int hash;

	public DesktopMethodAccessor(Method met) {
		try {
			method = met;
			isStatic = Modifier.isStatic(met.getModifiers());
			MethodHandle target = lookup.unreflect(met);

			int paramCount = target.type().parameterCount();

			if (isStatic) {
				spreadHandle = target.asSpreader(Object[].class, paramCount)
						.asType(MethodType.methodType(Object.class, Object[].class));
			} else {
				if (paramCount < 1)
					throw new IllegalArgumentException("Instance method must have e receiver");
				MethodHandle spread = target.asSpreader(Object[].class, paramCount -1);
				MethodType newType = spread.type()
						.changeParameterType(0, Object.class)
						.changeReturnType(Object.class);
				spreadHandle = spread.asType(newType);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invoke(Object obj, Object... args) {
		try {
			return (T) spreadHandle.invokeExact(obj, args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invokeStatic(Object... args) {
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
		return obj == this || obj instanceof MethodAccessor other && other.getMethod().equals(method);
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
