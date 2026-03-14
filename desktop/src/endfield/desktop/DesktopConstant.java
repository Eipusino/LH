package endfield.desktop;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static endfield.desktop.DesktopImpl.lookup;

public final class DesktopConstant {
	static final MethodHandle clone, getPrimitiveClass;

	static {
		try {
			clone = lookup.findVirtual(Object.class, "clone", MethodType.methodType(Object.class));

			getPrimitiveClass = lookup.findStatic(Class.class, "getPrimitiveClass", MethodType.methodType(Class.class, String.class));
		} catch (NoSuchMethodException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
