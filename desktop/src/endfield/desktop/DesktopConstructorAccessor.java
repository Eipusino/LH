package endfield.desktop;

import endfield.util.ConstructorAccessor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;

import static endfield.desktop.DesktopImpl.lookup;

public class DesktopConstructorAccessor implements ConstructorAccessor {
	final Constructor<?> constructor;
	final MethodHandle spreadHandle;

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
}
