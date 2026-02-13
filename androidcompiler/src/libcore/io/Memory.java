package libcore.io;

import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class Memory {
	private Memory() {}

	public static void unsafeBulkGet(Object dst, int dstOffset, int byteCount, byte[] src, int srcOffset, int sizeofElements, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void unsafeBulkPut(byte[] dst, int dstOffset, int byteCount, Object src, int srcOffset, int sizeofElements, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static int peekInt(byte[] src, int offset, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static long peekLong(byte[] src, int offset, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static short peekShort(byte[] src, int offset, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeInt(byte[] dst, int offset, int value, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeLong(byte[] dst, int offset, long value, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeShort(byte[] dst, int offset, short value, ByteOrder order) {
		throw new RuntimeException("Stub!");
	}

	public static void memmove(Object dstObject, int dstOffset, Object srcObject, int srcOffset, long byteCount) {
		throw new RuntimeException("Stub!");
	}

	public static byte peekByte(long address) {
		throw new RuntimeException("Stub!");
	}

	public static int peekInt(long address, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static long peekLong(long address, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static short peekShort(long address, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void peekByteArray(long address, byte[] dst, int dstOffset, int byteCount) {
		throw new RuntimeException("Stub!");
	}

	public static void peekCharArray(long address, char[] dst, int dstOffset, int charCount, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void peekDoubleArray(long address, double[] dst, int dstOffset, int doubleCount, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void peekFloatArray(long address, float[] dst, int dstOffset, int floatCount, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void peekIntArray(long address, int[] dst, int dstOffset, int intCount, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void peekLongArray(long address, long[] dst, int dstOffset, int longCount, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void peekShortArray(long address, short[] dst, int dstOffset, int shortCount, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeByte(long address, byte value) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeInt(long address, int value, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeLong(long address, long value, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeShort(long address, short value, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeByteArray(long address, byte[] src, int offset, int count) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeCharArray(long address, char[] src, int offset, int count, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeDoubleArray(long address, double[] src, int offset, int count, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeFloatArray(long address, float[] src, int offset, int count, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeIntArray(long address, int[] src, int offset, int count, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeLongArray(long address, long[] src, int offset, int count, boolean swap) {
		throw new RuntimeException("Stub!");
	}

	public static void pokeShortArray(long address, short[] src, int offset, int count, boolean swap) {
		throw new RuntimeException("Stub!");
	}
}