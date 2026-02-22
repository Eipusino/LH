package template;

import sun.reflect.ReflectionFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;

public final class Handles {
	public static Lookup lookup = getLookup();

	private Handles() {}

	public static Object invokeStatic(MethodHandle handle, Object... args) {
		try {
			return switch (args.length) {
				case 0 -> handle.invoke();
				case 1 -> handle.invoke(args[0]);
				case 2 -> handle.invoke(args[0], args[1]);
				case 3 -> handle.invoke(args[0], args[1], args[2]);
				case 4 -> handle.invoke(args[0], args[1], args[2], args[3]);
				case 5 -> handle.invoke(args[0], args[1], args[2], args[3], args[4]);
				case 6 -> handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5]);
				case 7 -> handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
				case 8 -> handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
				case 9 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
				case 10 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9]);
				case 11 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10]);
				case 12 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11]);
				case 13 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12]);
				case 14 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13]);
				case 15 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14]);
				case 16 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15]);
				case 17 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16]);
				case 18 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17]);
				case 19 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18]);
				case 20 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19]);
				case 21 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20]);
				case 22 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21]);
				case 23 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22]);
				case 24 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23]);
				case 25 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24]);
				case 26 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25]);
				case 27 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26]);
				case 28 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27]);
				case 29 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28]);
				case 30 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28],
								args[29]);
				case 31 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28],
								args[29], args[30]);
				case 32 ->
						handle.invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28],
								args[29], args[30], args[31]);
				default -> handle.invokeWithArguments(args);
			};
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static Object invokeVirtual(Object inst, MethodHandle handle, Object... args) {
		try {
			return switch (args.length) {
				case 0 -> handle.invoke(inst);
				case 1 -> handle.invoke(inst, args[0]);
				case 2 -> handle.invoke(inst, args[0], args[1]);
				case 3 -> handle.invoke(inst, args[0], args[1], args[2]);
				case 4 -> handle.invoke(inst, args[0], args[1], args[2], args[3]);
				case 5 -> handle.invoke(inst, args[0], args[1], args[2], args[3], args[4]);
				case 6 -> handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5]);
				case 7 -> handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
				case 8 -> handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
				case 9 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
				case 10 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9]);
				case 11 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10]);
				case 12 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11]);
				case 13 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12]);
				case 14 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13]);
				case 15 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14]);
				case 16 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15]);
				case 17 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16]);
				case 18 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17]);
				case 19 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18]);
				case 20 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19]);
				case 21 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20]);
				case 22 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21]);
				case 23 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22]);
				case 24 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23]);
				case 25 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24]);
				case 26 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25]);
				case 27 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26]);
				case 28 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27]);
				case 29 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28]);
				case 30 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28],
								args[29]);
				case 31 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28],
								args[29], args[30]);
				case 32 ->
						handle.invoke(inst, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
								args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18],
								args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28],
								args[29], args[30], args[31]);
				default -> handle.invokeWithArguments(args);
			};
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	static Lookup getLookup() {
		try {
			return (Lookup) ReflectionFactory.getReflectionFactory()
					.newConstructorForSerialization(Lookup.class, Lookup.class.getDeclaredConstructor(Class.class, Class.class, int.class))
					.newInstance(Main.class, null, -1);
		} catch (Throwable e) {
			throw new AssertionError(e);
		}
	}
}
