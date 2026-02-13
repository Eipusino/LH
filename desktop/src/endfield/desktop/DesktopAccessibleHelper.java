package endfield.desktop;

import endfield.util.AccessibleHelper;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public class DesktopAccessibleHelper implements AccessibleHelper {
	static Field overrideField, modifiersField;

	@Override
	public void makeAccessible(AccessibleObject object) {
		try {
			if (overrideField == null) {
				overrideField = AccessibleObject.class.getDeclaredField("override");
				overrideField.setAccessible(true);
			}
			overrideField.setBoolean(object, true);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void makeClassAccessible(Class<?> clazz) {
		try {
			if (modifiersField == null) {
				modifiersField = Class.class.getDeclaredField("modifiers");
				modifiersField.setAccessible(true);
			}
			char flags = modifiersField.getChar(clazz);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
