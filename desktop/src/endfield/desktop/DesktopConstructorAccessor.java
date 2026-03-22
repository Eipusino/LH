package endfield.desktop;

import endfield.util.ConstructorAccessor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.util.Arrays;

import static endfield.desktop.DesktopImpl.lookup;

public class DesktopConstructorAccessor implements ConstructorAccessor {
	final Constructor<?> constructor;
	final MethodHandle spreadHandle;

	int hash;

	public DesktopConstructorAccessor(Constructor<?> cons) {
		try {
			constructor = cons;
			MethodHandle target = lookup.unreflectConstructor(cons);

			int paramCount = target.type().parameterCount();
			MethodHandle spread = target.asSpreader(Object[].class, paramCount);
			spreadHandle = spread.asType(MethodType.methodType(Object.class, Object[].class));
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T newInstance(Object... args) {
		try {
			return (T) spreadHandle.invokeExact(args);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Constructor<?> getConstructor() {
		return constructor;
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this || obj instanceof DesktopConstructorAccessor other && other.getConstructor().equals(constructor);
	}

	@Override
	public int hashCode() {
		int hc = hash;

		if (hc == 0) {
			hc = hash = constructor.getDeclaringClass().getName().hashCode() ^
					Arrays.hashCode(constructor.getParameterTypes());
		}

		return hc;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + constructor.toString();
	}
}
