package heavyindustry.desktop;

import heavyindustry.core.HeavyIndustryMod;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.Map;
import java.util.Set;

import static heavyindustry.util.Reflects.lookup;

/**
 * The anti modularity tool only provides one main method {@link Demodulator#openModule(Module, String, Module)}
 * to force software packages that open modules to the required modules.
 * <p>This class behavior may completely break the modular access protection and is inherently insecure. If it is
 * not necessary, please try to avoid using this class.
 * <p><strong>This class is only available after Java 9 to avoid referencing methods of this class in earlier versions,
 * and it is only available on the desktop platform. Any behavior of this class is not allowed on the
 * Android platform.</strong>
 *
 * @author EBwilson
 */
public final class Demodulator {
	public static Map<Class<?>, Set<String>> fieldFilterMap, methodFilterMap;

	private static MethodHandle implAddOpens;

	private Demodulator() {}

	// The exceptions thrown during initialization are collectively handled in a try-catch block.
	public static void init() throws NoSuchMethodException, IllegalAccessException {
		implAddOpens = lookup.findVirtual(Module.class, "implAddOpens", MethodType.methodType(void.class, String.class, Module.class));
	}

	/**
	 * @param from To open the module of the package
	 * @param pn The package name of the module to export the package
	 * @param to The module to be exported to.
	 */
	public static void openModule(Module from, String pn, Module to) throws Throwable {
		implAddOpens.invokeExact(from, pn, to);
	}

	static void openModules() throws Throwable {
		Module base = Object.class.getModule(), impl = Demodulator.class.getModule(), main = HeavyIndustryMod.class.getModule();

		openModule(base, "jdk.internal.misc", impl);

		openModule(base, "java.lang", main);
		openModule(base, "java.lang.reflect", main);
		openModule(base, "jdk.internal.misc", main);

		//MethodHandle addReads = Reflects.lookup.findStatic(Module.class, "addReads0", MethodType.methodType(void.class, Module.class, Module.class));
	}

	@SuppressWarnings("unchecked")
	static void ensureFieldOpen() throws Throwable {
		Class<?> reflection = Class.forName("jdk.internal.reflect.Reflection");

		fieldFilterMap = (Map<Class<?>, Set<String>>) lookup.findStaticGetter(reflection, "fieldFilterMap", Map.class).invokeExact();
		fieldFilterMap.clear();

		methodFilterMap = (Map<Class<?>, Set<String>>) lookup.findStaticGetter(reflection, "methodFilterMap", Map.class).invokeExact();
		methodFilterMap.clear();
	}
}
