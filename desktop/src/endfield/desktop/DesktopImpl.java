package endfield.desktop;

import endfield.util.PlatformImpl;
import sun.reflect.ReflectionFactory;

import java.lang.invoke.MethodHandles.Lookup;

import static endfield.Vars2.accessibleHelper;
import static endfield.Vars2.classHelper;
import static endfield.Vars2.fieldAccessHelper;
import static endfield.Vars2.methodInvokeHelper;

public class DesktopImpl implements PlatformImpl {
	static final Lookup lookup;

	static {
		try {
			lookup = (Lookup) ReflectionFactory.getReflectionFactory()
					.newConstructorForSerialization(Lookup.class, Lookup.class.getDeclaredConstructor(Class.class, Class.class, int.class))
					.newInstance(Object.class, null, -1);

			Demodulator.init();
			Demodulator.openModules();

			DesktopClassHelper.init();

			classHelper = new DesktopClassHelper();
			fieldAccessHelper = new DesktopFieldAccessHelper();
			methodInvokeHelper = new DesktopMethodInvokeHelper();
			accessibleHelper = new DesktopAccessibleHelper();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
