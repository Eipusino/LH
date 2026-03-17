package endfield.android;

import java.lang.reflect.Field;

import static endfield.android.Propertys.fields;

public class PropertyFieldAccessHelper extends AndroidFieldAccessHelper {
	@Override
	protected Field getField(Class<?> clazz, String name) throws NoSuchFieldException {
		for (Field field : fields.get(clazz)) {
			if (field.getName().equals(name)) {
				field.setAccessible(true);

				setAccessFlags(field);

				return field;
			}
		}

		throw new NoSuchFieldException(name);
	}
}
