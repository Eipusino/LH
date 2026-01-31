package endfield.desktop;

import arc.util.Log;
import endfield.util.CollectionObjectMap;
import endfield.util.CollectionObjectSet;
import endfield.util.FieldAccessHelper;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static endfield.Vars2.classHelper;
import static endfield.desktop.DesktopImpl.lookup;

public class DesktopFieldAccessHelper implements FieldAccessHelper {
	protected static final CollectionObjectMap<Class<?>, CollectionObjectMap<String, Field>> fieldMap = new CollectionObjectMap<>(Class.class, CollectionObjectMap.class);
	protected static final CollectionObjectMap<String, Field> empty = new CollectionObjectMap<>(String.class, Field.class);

	protected static final CollectionObjectSet<Field> finalFields = new CollectionObjectSet<>(Field.class);

	protected static final CollectionObjectMap<Field, MethodHandle> getters = new CollectionObjectMap<>(Field.class, MethodHandle.class);
	protected static final CollectionObjectMap<Field, MethodHandle> setters = new CollectionObjectMap<>(Field.class, MethodHandle.class);

	protected static final boolean useUnsafe;

	static {
		boolean tmp;

		try {
			Log.infoTag("Unsafe", "getUnsafe: " + Unsafer.unsafe);
			tmp = true;
		} catch (Throwable e) {
			Log.err(e);

			tmp = false;
		}
		useUnsafe = tmp;
	}

	public Field getField(Class<?> clazz, String name, boolean isStatic) throws NoSuchFieldException {
		Field field = fieldMap.getDefault(clazz, empty).get(name);
		if (field != null) return field;

		if (isStatic) {
			Field f = classHelper.getField(clazz, name);
			if (f != null && Modifier.isStatic(f.getModifiers())) {
				f.setAccessible(true);
				return f;
			}
		} else {
			Class<?> curr = clazz;
			while (curr != null) {
				Field f = classHelper.getField(clazz, name);
				if (f != null && !Modifier.isStatic(f.getModifiers())) {
					f.setAccessible(true);
					return f;
				}

				curr = curr.getSuperclass();
			}
		}

		throw new NoSuchFieldException();
	}

	protected void initField(Field field) {
		getters.getDefault2(field, () -> {
			try {
				return (field.getModifiers() & Modifier.STATIC) == 0 ?
						lookup.findGetter(field.getDeclaringClass(), field.getName(), field.getType()) :
						lookup.findStaticGetter(field.getDeclaringClass(), field.getName(), field.getType());
				//return lookup.unreflectGetter(field);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				throw new RuntimeException(e);
			}
		});
		setters.getDefault2(field, () -> {
			try {
				return (field.getModifiers() & Modifier.STATIC) == 0 ?
						lookup.findSetter(field.getDeclaringClass(), field.getName(), field.getType()) :
						lookup.findStaticSetter(field.getDeclaringClass(), field.getName(), field.getType());
				//return lookup.unreflectSetter(field);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public void setByte(Object object, String name, byte value) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				Unsafer.setByte(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setByteStatic(Class<?> clazz, String name, byte value) {
		try {
			Field field = getField(clazz, name, false);
			if (useUnsafe) {
				Unsafer.setByteStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte getByte(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return Unsafer.getByte(field, object);
			} else {
				initField(field);
				return (byte) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte getByteStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return Unsafer.getByteStatic(field);
			} else {
				initField(field);
				return (byte) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setShort(Object object, String name, short value) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				Unsafer.setShort(field, object, value);
			} else {

				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setShortStatic(Class<?> clazz, String name, short value) {
		try {
			Field field = getField(clazz, name, false);
			if (useUnsafe) {
				Unsafer.setShortStatic(field, value);
			} else {

				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public short getShort(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return Unsafer.getShort(field, object);
			} else {
				initField(field);
				return (short) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public short getShortStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return Unsafer.getShortStatic(field);
			} else {
				initField(field);
				return (short) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setInt(Object object, String name, int value) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				Unsafer.setInt(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setIntStatic(Class<?> clazz, String name, int value) {
		try {
			Field field = getField(clazz, name, false);
			if (useUnsafe) {
				Unsafer.setIntStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getInt(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return Unsafer.getInt(field, object);
			} else {
				initField(field);
				return (int) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getIntStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return Unsafer.getIntStatic(field);
			} else {
				initField(field);
				return (int) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setLong(Object object, String name, long value) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				Unsafer.setLong(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setLongStatic(Class<?> clazz, String name, long value) {
		try {
			Field field = getField(clazz, name, false);
			if (useUnsafe) {
				Unsafer.setLongStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long getLong(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return Unsafer.getLong(field, object);
			} else {
				initField(field);
				return (long) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long getLongStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return Unsafer.getLongStatic(field);
			} else {
				initField(field);
				return (long) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setFloat(Object object, String name, float value) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				Unsafer.setFloat(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setFloatStatic(Class<?> clazz, String name, float value) {
		try {
			Field field = getField(clazz, name, false);

			if (useUnsafe) {
				Unsafer.setFloatStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public float getFloat(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return Unsafer.getFloat(field, object);
			} else {
				initField(field);
				return (float) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public float getFloatStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return Unsafer.getFloatStatic(field);
			} else {
				initField(field);
				return (float) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setDouble(Object object, String name, double value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (useUnsafe) {
				Unsafer.setDouble(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setDoubleStatic(Class<?> clazz, String name, double value) {
		try {
			Field field = getField(clazz, name, false);
			if (useUnsafe) {
				Unsafer.setDoubleStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double getDouble(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return Unsafer.getDouble(field, object);
			} else {
				initField(field);
				return (double) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public double getDoubleStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return Unsafer.getDoubleStatic(field);
			} else {
				initField(field);
				return (double) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setChar(Object object, String name, char value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (useUnsafe) {
				Unsafer.setChar(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setCharStatic(Class<?> clazz, String name, char value) {
		try {
			Field field = getField(clazz, name, false);

			if (useUnsafe) {
				Unsafer.setCharStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public char getChar(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return Unsafer.getChar(field, object);
			} else {
				initField(field);
				return (char) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public char getCharStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return Unsafer.getCharStatic(field);
			} else {
				initField(field);
				return (char) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setBoolean(Object object, String name, boolean value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (useUnsafe) {
				Unsafer.setBoolean(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setBooleanStatic(Class<?> clazz, String name, boolean value) {
		try {
			Field field = getField(clazz, name, false);

			if (useUnsafe) {
				Unsafer.setBooleanStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean getBoolean(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return Unsafer.getBoolean(field, object);
			} else {
				initField(field);
				return (boolean) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean getBooleanStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return Unsafer.getBooleanStatic(field);
			} else {
				initField(field);
				return (boolean) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setObject(Object object, String name, Object value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (useUnsafe) {
				Unsafer.setReference(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setObjectStatic(Class<?> clazz, String name, Object value) {
		try {
			Field field = getField(clazz, name, false);

			if (useUnsafe) {
				Unsafer.setReferenceStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return (T) Unsafer.getReference(field, object);
			} else {
				initField(field);
				return (T) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return (T) Unsafer.getReferenceStatic(field);
			} else {
				initField(field);
				return (T) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void set(Object object, String name, Object value) {
		try {
			Field field = getField(object.getClass(), name, false);

			if (useUnsafe) {
				Unsafer.set(field, object, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(object, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(object, value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setStatic(Class<?> clazz, String name, Object value) {
		try {
			Field field = getField(clazz, name, false);

			if (useUnsafe) {
				Unsafer.setStatic(field, value);
			} else {
				if (finalFields.contains(field)) {
					field.set(null, value);
					return;
				}

				initField(field);
				setters.get(field).invoke(value);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object object, String name) {
		try {
			Field field = getField(object.getClass(), name, false);
			if (useUnsafe) {
				return (T) Unsafer.get(field, object);
			} else {
				initField(field);
				return (T) getters.get(field).invoke(object);
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getStatic(Class<?> clazz, String name) {
		try {
			Field field = getField(clazz, name, true);
			if (useUnsafe) {
				return (T) Unsafer.getStatic(field);
			} else {
				initField(field);
				return (T) getters.get(field).invoke();
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
