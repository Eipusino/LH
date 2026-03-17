package template;

import arc.util.Time;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main implements Cloneable {
	static int number;

	static void main(String... args) {
		try {
			Lookup lookup = MethodHandles.lookup();
			MethodHandle adder = lookup.findStatic(Main.class, "adder", MethodType.methodType(void.class, int.class));
			Object[] objects = {1};
			Time.mark();
			for (int i = 0; i < 100000; i++) {
				//adder.asSpreader(0, Object[].class, 1).invokeExact(objects);
				//adder.invokeWithArguments(objects);
				adder.invoke(1);
			}
			System.out.println(Time.elapsed());
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

	public static void adder(int add) {
		number += add;
	}
}
