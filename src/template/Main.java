package template;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main implements Cloneable {
	static Object ol;

	static void main(String... args) {
		try {
			Field f1 = Main.class.getDeclaredField("ol"), f2 = Main.class.getDeclaredField("ol");
			System.out.println(f1 == f2);
			System.out.println(f1.equals(f2));
			System.out.println(f1.hashCode() + ":" + f2.hashCode());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void load(Object o) {
		System.out.println(o);
	}

	public Main copy() throws CloneNotSupportedException {
		return (Main) super.clone();
	}
}
