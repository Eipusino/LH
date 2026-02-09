package endfield.android;

import arc.Core;
import endfield.util.ClassHelper;
import mindustry.android.AndroidRhinoContext;
import mindustry.android.AndroidRhinoContext.AndroidContextFactory;
import rhino.ContextFactory;
import rhino.GeneratedClassLoader;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Objects;

import static endfield.android.AndroidImpl.exceptionHandler;
import static endfield.android.Unsafer.unsafe;

public class AndroidClassHelper implements ClassHelper {
	static Field accessFlags;

	@Override
	public Class<?> defineClass(String name, byte[] bytes, ClassLoader loader) {
		if (!(ContextFactory.getGlobal() instanceof AndroidContextFactory)) {
			AndroidRhinoContext.enter(new File(Core.settings.getDataDirectory() + "/rhino/"));
		}
		return ((GeneratedClassLoader) ((AndroidContextFactory) ContextFactory.getGlobal())
				.createClassLoader(loader))
				.defineClass(name, bytes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T allocateInstance(Class<? extends T> clazz) {
		Objects.requireNonNull(clazz);

		try {
			return (T) unsafe.allocateInstance(clazz);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setPublic(Class<?> obj) {
		try {
			if (accessFlags == null) {
				accessFlags = Class.class.getDeclaredField("accessFlags");
				accessFlags.setAccessible(true);
			}

			int flags = accessFlags.getInt(obj);
			accessFlags.setInt(obj, 65535 & ((flags & 65535 & (-17) & (-3)) | 1));
		} catch (IllegalAccessException | NoSuchFieldException e) {
			exceptionHandler.get(e);
		}
	}
}
