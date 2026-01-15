package template;

import arc.util.Time;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
	private static final Unsafe unsafe = getUnsafe();
	private static final Lookup lookup = getLookup();

	private static MethodHandle clone;
	private static MethodHandle implAddOpens;
	private static MethodHandle load;
	private static Method loadm;

	public static void main(String... arg) {
		try {
			MethodHandle method = lookup.unreflectConstructor(Main.class.getConstructor(int.class));
			Object[] params = {1114};
			System.out.println(Handles.invokeStatic(method, params));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T clone(T object) {
		try {
			return (T) clone.invokeExact(object);
		} catch (Throwable e) {
			e.printStackTrace();

			return null;
		}
	}

	public Main(int i) {

	}

	static class CA implements Cloneable {
		static final CA INSTANCE = new CA();

		int number;

		private CA() {}

		public void load() {
			number++;
		}

		@Override
		public CA clone() {
			try {
				return null;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static Unsafe getUnsafe() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}

	private static Lookup getLookup() {
		try {
			Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
			return (Lookup) unsafe.getObject(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}
}
