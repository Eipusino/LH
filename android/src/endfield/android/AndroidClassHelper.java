package endfield.android;

import arc.Core;
import endfield.util.ClassHelper;
import mindustry.android.AndroidRhinoContext;
import mindustry.android.AndroidRhinoContext.AndroidContextFactory;
import rhino.ContextFactory;
import rhino.GeneratedClassLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static endfield.android.AndroidImpl.getPrimitiveClass;
import static endfield.android.Unsafer.unsafe;

public class AndroidClassHelper implements ClassHelper {
	@SuppressWarnings("unchecked")
	@Override
	public <T> Class<T> getPrimitiveClass(String name) {
		try {
			return (Class<T>) getPrimitiveClass.invoke(null, name);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

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
}
