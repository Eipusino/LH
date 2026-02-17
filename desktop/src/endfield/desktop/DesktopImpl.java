package endfield.desktop;

import arc.util.Log;
import endfield.core.EndFieldMod;
import endfield.util.AccessibleHelper;
import endfield.util.ClassHelper;
import endfield.util.DefaultFieldAccessHelper;
import endfield.util.DefaultMethodInvokeHelper;
import endfield.util.PlatformImpl;
import sun.reflect.ReflectionFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.AccessibleObject;
import java.nio.Buffer;

import static endfield.Vars2.accessibleHelper;
import static endfield.Vars2.classHelper;
import static endfield.Vars2.fieldAccessHelper;
import static endfield.Vars2.methodInvokeHelper;
import static endfield.desktop.Unsafer.unsafe;

public class DesktopImpl implements PlatformImpl {
	static Lookup lookup;
	static MethodHandle clone;

	static {
		try {
			lookup = (Lookup) ReflectionFactory.getReflectionFactory()
					.newConstructorForSerialization(Lookup.class, Lookup.class.getDeclaredConstructor(Class.class, Class.class, int.class))
					.newInstance(EndFieldMod.class, null, -1);

			Demodulator.init();
			Demodulator.openModules();

			DesktopClassHelper.init();

			classHelper = new DesktopClassHelper();
			try {
				Log.infoTag("Unsafe", "getUnsafe: " + Unsafer.unsafe);

				fieldAccessHelper = new UnsafeFieldAccessHelper();
			} catch (Throwable e) {
				fieldAccessHelper = new MethodHandleFieldAccessHelper();
			}
			methodInvokeHelper = new DesktopMethodInvokeHelper();
			accessibleHelper = new DesktopAccessibleHelper();
		} catch (Throwable e) {
			Log.err("It seems you platform is special. (But don't worry)", e);

			lookup = MethodHandles.publicLookup();

			classHelper = new ClassHelper() {
				@Override
				public <T> T allocateInstance(Class<? extends T> clazz) {
					throw new UnsupportedOperationException();
				}

				@Override
				public Class<?> defineClass(String name, byte[] bytes, ClassLoader loader) {
					throw new UnsupportedOperationException();
				}
			};
			fieldAccessHelper = new DefaultFieldAccessHelper();
			methodInvokeHelper = new DefaultMethodInvokeHelper();
			accessibleHelper = new AccessibleHelper() {
				@Override
				public void makeAccessible(AccessibleObject object) {
					object.trySetAccessible();
				}
			};
		}
	}

	@Override
	public Lookup lookup(Class<?> clazz) {
		return lookup;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T clone(T object) {
		try {
			if (clone == null) {
				clone = lookup.findVirtual(Object.class, "clone", MethodType.methodType(Object.class));
			}

			return (T) clone.invokeExact(object);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void putBuffer(Buffer src, int srcOffset, Buffer dst, int dstOffset, int numBytes) {
		long srcAddress = addressOf(src);
		long dstAddress = addressOf(dst);
		unsafe.copyMemory(srcAddress + srcOffset, dstAddress + dstOffset, numBytes);
	}
}
