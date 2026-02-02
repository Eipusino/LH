package endfield.desktop;

import arc.util.Log;
import endfield.util.DefaultAccessibleHelper;
import endfield.util.DefaultClassHelper;
import endfield.util.DefaultFieldAccessHelper;
import endfield.util.DefaultMethodInvokeHelper;
import endfield.util.PlatformImpl;
import sun.reflect.ReflectionFactory;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.AccessibleObject;

import static endfield.Vars2.accessibleHelper;
import static endfield.Vars2.classHelper;
import static endfield.Vars2.fieldAccessHelper;
import static endfield.Vars2.methodInvokeHelper;

public class DesktopImpl implements PlatformImpl {
	static Lookup lookup;

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
			Log.err("It seems you platform is special. (But don't worry)", e);

			lookup = MethodHandles.publicLookup();

			classHelper = new DefaultClassHelper();
			fieldAccessHelper = new DefaultFieldAccessHelper();
			methodInvokeHelper = new DefaultMethodInvokeHelper();
			accessibleHelper = new DefaultAccessibleHelper() {
				@Override
				public void makeAccessible(AccessibleObject object) {
					object.trySetAccessible();
				}
			};
		}
	}
}
