package template;

import jdk.internal.module.Modules;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static template.Reflect.lookup;

public class Demodulator {
	static MethodHandle implAddOpens;

	private Demodulator() {}

	// The exceptions thrown during initialization are collectively handled in a try-catch block.
	static void init() throws NoSuchMethodException, IllegalAccessException {
		implAddOpens = lookup.findVirtual(Module.class, "implAddOpens", MethodType.methodType(void.class, String.class, Module.class));
	}

	public static void makeOpenModule(Module from, Class<?> clazz, Module to) {
		if (clazz.isArray()) {
			makeOpenModule(from, clazz.getComponentType(), to);
		} else {
			makeOpenModule(from, clazz.getPackage(), to);
		}
	}

	public static void makeOpenModule(Module from, Package pac, Module to) {
		if (pac == null) return;

		makeOpenModule(from, pac.getName(), to);
	}

	/**
	 * @param from To open the module of the package
	 * @param pac The package name of the module to export the package
	 * @param to The module to be exported to.
	 */
	public static void makeOpenModule(Module from, String pac, Module to) {
		if (from.isOpen(pac, to)) return;

		Modules.addExports(from, pac, to);
	}

	public static void makeOpenModule(Module from, String pac) {
		if (from.isOpen(pac)) return;

		Modules.addExports(from, pac);
	}

	static void openModule(Module from, String pn, Module to) throws Throwable {
		implAddOpens.invokeExact(from, pn, to);
	}

	static void openModules() throws Throwable {
		Module base = Object.class.getModule(), main = Main.class.getModule();

		openModule(base, "java.lang", main);
		openModule(base, "java.lang.reflect", main);
		openModule(base, "jdk.internal.misc", main);
		openModule(base, "jdk.internal.module", main);
		openModule(base, "jdk.internal.reflect", main);
		openModule(base, "sun.nio.ch", main);
	}
}
