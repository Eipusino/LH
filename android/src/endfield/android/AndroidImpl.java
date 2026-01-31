package endfield.android;

import arc.func.Cons;
import arc.util.Log;
import endfield.util.AccessibleHelper;
import endfield.util.PlatformImpl;

import java.lang.reflect.AccessibleObject;

import static endfield.Vars2.accessibleHelper;
import static endfield.Vars2.classHelper;
import static endfield.Vars2.fieldAccessHelper;
import static endfield.Vars2.methodInvokeHelper;

public class AndroidImpl implements PlatformImpl {
	static final Cons<Throwable> exceptionHandler = e -> {};

	static {
		try {
			Log.infoTag("Unsafe", "getUnsafe: " + Unsafer.unsafe);

			try {
				HiddenApi.setHiddenApiExemptions();
			} catch (Throwable e) {
				Log.err(e);
			}
		} catch (Throwable e) {
			Log.err(e);
		}

		accessibleHelper = new AccessibleHelper() {
			@Override
			public void makeAccessible(AccessibleObject object) {
				object.setAccessible(true);
			}

			@Override
			public void makeClassAccessible(Class<?> clazz) {
				//no action
			}
		};
		classHelper = new AndroidClassHelper();
		fieldAccessHelper = new AndroidFieldAccessHelper();
		methodInvokeHelper = new AndroidMethodInvokeHelper();
	}
}
