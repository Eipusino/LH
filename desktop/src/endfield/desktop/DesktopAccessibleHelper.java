package endfield.desktop;

import arc.util.Log;
import endfield.util.AccessibleHelper;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

import static endfield.Vars2.classHelper;

public class DesktopAccessibleHelper implements AccessibleHelper {
	static Field override, modifiers;

	@Override
	public void makeAccessible(AccessibleObject object) {
		try {
			if (override == null) {
				override = AccessibleObject.class.getDeclaredField("override");
				override.setAccessible(true);
			}
			override.setBoolean(object, true);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void makeClassAccessible(Class<?> clazz) {
		try {
			if (modifiers == null) {
				modifiers = Objects.requireNonNull(classHelper.getField(Class.class, "modifiers"));
				modifiers.setAccessible(true);
			}

			char flags = modifiers.getChar(clazz);

			flags &= ~(Modifier.PRIVATE | Modifier.PROTECTED | Modifier.FINAL);
			flags |= Modifier.PUBLIC;

			modifiers.setChar(clazz, flags);
		} catch (Exception e) {
			Log.err("The currently running JVM's java.lang.Class does not contain a modifiers field, so modifiers cannot be modified", e);
		}
	}
}
