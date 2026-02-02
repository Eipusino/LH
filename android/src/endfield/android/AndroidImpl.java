package endfield.android;

import arc.func.Cons;
import arc.util.Log;
import endfield.util.DefaultAccessibleHelper;
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
			Log.err("It seems you platform is special. (But don't worry)", e);
		}

		accessibleHelper = new DefaultAccessibleHelper();
		classHelper = new AndroidClassHelper();
		fieldAccessHelper = new AndroidFieldAccessHelper();
		methodInvokeHelper = new AndroidMethodInvokeHelper();
	}
}
