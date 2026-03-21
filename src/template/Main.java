package template;

import sun.reflect.ReflectionFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;

public class Main {
	static final Status status = Status.A;

	static void main(String... args) {
		try {
			ReflectionFactory factory = ReflectionFactory.getReflectionFactory();
			Lookup lookup = (Lookup) factory.newConstructorForSerialization(Lookup.class, Lookup.class.getDeclaredConstructor(Class.class, Class.class, int.class)).newInstance(Object.class, null, -1);

			MethodHandle setStatus = lookup.findStaticSetter(Main.class, "status", Status.class);

			setStatus.invokeExact(Status.B);

			System.out.println(status);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public enum Status {
		A, B;
	}
}
