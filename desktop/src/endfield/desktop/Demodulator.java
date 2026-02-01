package endfield.desktop;

import endfield.core.EndFieldMod;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static endfield.desktop.DesktopImpl.lookup;

/**
 * The anti modularity tool only provides one main method {@link Demodulator#openModule(Module, String, Module)}
 * to force software packages that open modules to the required modules.
 * <p>This class behavior may completely break the modular access protection and is inherently insecure. If it is
 * not necessary, please try to avoid using this class.
 * <p><strong>This class is only available after Java 9 to avoid referencing methods of this class in earlier versions,
 * and it is only available on the desktop platform. Any behavior of this class is not allowed on the
 * Android platform.</strong>
 *
 * @author Eipusino
 */
public final class Demodulator {
	//public static Map<Class<?>, Set<String>> fieldFilterMap;

	static MethodHandle implAddOpens;

	private Demodulator() {}

	// The exceptions thrown during initialization are collectively handled in a try-catch block.
	static void init() throws NoSuchMethodException, IllegalAccessException {
		implAddOpens = lookup.findVirtual(Module.class, "implAddOpens", MethodType.methodType(void.class, String.class, Module.class));
	}

	public static void makeModuleOpen(Module from, Class<?> clazz, Module to) {
		if (clazz.isArray()) {
			makeModuleOpen(from, clazz.getComponentType(), to);
		} else makeModuleOpen(from, clazz.getPackage(), to);
	}

	public static void makeModuleOpen(Module from, Package pac, Module to) {
		if (checkOpenModule(from, pac, to)) return;

		makeModuleOpen(from, pac.getName(), to);
	}

	/**
	 * @param from To open the module of the package
	 * @param pac The package name of the module to export the package
	 * @param to The module to be exported to.
	 */
	public static void makeModuleOpen(Module from, String pac, Module to) {
		try {
			openModule(from, pac, to);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean checkOpenModule(Module from, Package pac, Module to) {
		if (pac == null) return true;

		return from.isOpen(pac.getName(), to);
	}

	static void openModule(Module from, String pn, Module to) throws Throwable {
		implAddOpens.invokeExact(from, pn, to);
	}

	static void openModules() throws Throwable {
		Module base = Object.class.getModule(), main = EndFieldMod.class.getModule();

		openModule(base, "java.lang", main);
		openModule(base, "java.lang.reflect", main);
		openModule(base, "jdk.internal.misc", main);
		openModule(base, "jdk.internal.reflect", main);
	}

	// We directly call the private native method within Class to bypass filtering, so there is no need to do so.
	/*@SuppressWarnings("unchecked")
	static void ensureFieldOpen() {
		try {
			Class<?> reflection = Class.forName("jdk.internal.reflect.Reflection");

			fieldFilterMap = (Map<Class<?>, Set<String>>) lookup.findStaticGetter(reflection, "fieldFilterMap", Map.class).invokeExact();
			fieldFilterMap.clear();
		} catch (Throwable e) {
			Log.err(e);
		}
	}*/
}
