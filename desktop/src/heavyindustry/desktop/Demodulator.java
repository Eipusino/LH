package heavyindustry.desktop;

import heavyindustry.core.HeavyIndustryMod;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.Map;

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
	static MethodHandle implAddOpens;

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

		openModule(base, "java.lang", main);
		openModule(base, "jdk.internal.misc", impl);
		openModule(base, "jdk.internal.misc", main);
		openModule(base, "jdk.internal.reflect", impl);
		openModule(base, "jdk.internal.reflect", main);

		//MethodHandle addReads = Reflects.lookup.findStatic(Module.class, "addReads0", MethodType.methodType(void.class, Module.class, Module.class));
	}

	public static void ensureFieldOpen() throws Throwable {
		Class<?> reflection = Class.forName("jdk.internal.reflect.Reflection");

		Map<?, ?> fieldFilterMap = (Map<?, ?>) lookup.findStaticGetter(reflection, "fieldFilterMap", Map.class).invokeExact();
		if (fieldFilterMap != null) {
			fieldFilterMap.clear();
		}

		Map<?, ?> methodFilterMap = (Map<?, ?>) lookup.findStaticGetter(reflection, "methodFilterMap", Map.class).invokeExact();
		if (methodFilterMap != null) {
			methodFilterMap.clear();
		}
	}

	/*private static final long fieldFilterOffset = 112l;

	private static final Field opensField;
	private static final Field exportField;

	private static final Method exportNative;

	static {
		try {
			ensureFieldOpen();

			opensField = Module.class.getDeclaredField("openPackages");
			exportField = Module.class.getDeclaredField("exportedPackages");

			makeModuleOpen(Module.class.getModule(), "java.lang", Demodulator.class.getModule());

			exportNative = Module.class.getDeclaredMethod("addExports0", Module.class, String.class, Module.class);
			exportNative.setAccessible(true);
			exportNative.invoke(null, Module.class.getModule(), "java.lang", Demodulator.class.getModule());
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
		         NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	private Demodulator() {}

	public static void makeModuleOpen(Module from, Class<?> clazz, Module to) {
		if (clazz.isArray()) {
			makeModuleOpen(from, clazz.getComponentType(), to);
		} else {
			makeModuleOpen(from, clazz.getPackage(), to);
		}
	}

	public static void makeModuleOpen(Module from, Package pac, Module to) {
		if (checkModuleOpen(from, pac, to)) return;

		makeModuleOpen(from, pac.getName(), to);
	}

	@SuppressWarnings("unchecked")
	public static void makeModuleOpen(Module from, String pac, Module to) {
		try {
			if (exportNative != null) exportNative.invoke(null, from, pac, to);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		Map<String, Set<Module>> opensMap = (Map<String, Set<Module>>) unsafe.getObjectVolatile(from, unsafe.objectFieldOffset(opensField));
		if (opensMap == null) {
			opensMap = new HashMap<>();
			unsafe.putObjectVolatile(from, unsafe.objectFieldOffset(opensField), opensMap);
		}

		Map<String, Set<Module>> exportsMap = (Map<String, Set<Module>>) unsafe.getObjectVolatile(from, unsafe.objectFieldOffset(exportField));
		if (exportsMap == null) {
			exportsMap = new HashMap<>();
			unsafe.putObjectVolatile(from, unsafe.objectFieldOffset(exportField), exportsMap);
		}

		Set<Module> opens = opensMap.computeIfAbsent(pac, e -> new HashSet<>());
		Set<Module> exports = exportsMap.computeIfAbsent(pac, e -> new HashSet<>());

		try {
			opens.add(to);
		} catch (UnsupportedOperationException e) {
			ArrayList<Module> lis = new ArrayList<>(opens);
			lis.add(to);
			opensMap.put(pac, new HashSet<>(lis));
		}

		try {
			exports.add(to);
		} catch (UnsupportedOperationException e) {
			ArrayList<Module> lis = new ArrayList<>(exports);
			lis.add(to);
			exportsMap.put(pac, new HashSet<>(lis));
		}
	}

	public static boolean checkModuleOpen(Module from, Package pac, Module to) {
		Objects.requireNonNull(from);
		Objects.requireNonNull(to);

		if (pac == null) return true;

		return from.isOpen(pac.getName(), to);
	}

	@SuppressWarnings("unchecked")
	public static void ensureFieldOpen() {
		try {
			Class<?> clazz = Class.forName("jdk.internal.reflect.Reflection");

			if (HVars.hasImplLookup) {
				Map<Class<?>, Set<String>> fieldFilterMap = (Map<Class<?>, Set<String>>) lookup.findStaticGetter(clazz, "fieldFilterMap", Map.class).invokeExact();
				if (fieldFilterMap != null) {
					fieldFilterMap.clear();
				}

				Map<Class<?>, Set<String>> methodFilterMap = (Map<Class<?>, Set<String>>) lookup.findStaticGetter(clazz, "methodFilterMap", Map.class).invokeExact();
				if (methodFilterMap != null) {
					methodFilterMap.clear();
				}
			} else {
				Map<Class<?>, Set<String>> fieldFilterMap = (Map<Class<?>, Set<String>>) unsafe.getObject(clazz, fieldFilterOffset);
				fieldFilterMap.clear();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}*/
}
