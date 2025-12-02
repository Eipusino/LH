package template;

public class AsciiPracticalUtils {
	/**
	 * 检查字符串是否只包含ASCII字符
	 */
	public static boolean isPureAscii(String str) {
		if (str == null) return false;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > 127) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 移除字符串中的非ASCII字符
	 */
	public static String removeNonAscii(String str) {
		if (str == null) return null;

		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c <= 127) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将字符串转换为ASCII码序列
	 */
	public static String stringToAsciiCodes(String str) {
		if (str == null) return null;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (i > 0) sb.append(" ");
			sb.append((int) str.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 从ASCII码序列还原字符串
	 */
	public static String asciiCodesToString(String asciiCodes) {
		if (asciiCodes == null) return null;

		String[] codes = asciiCodes.split("\\s+");
		StringBuilder sb = new StringBuilder();

		for (String code : codes) {
			try {
				int asciiValue = Integer.parseInt(code);
				if (asciiValue >= 0 && asciiValue <= 127) {
					sb.append((char) asciiValue);
				}
			} catch (NumberFormatException e) {
				// 忽略无效的数字
			}
		}
		return sb.toString();
	}

	/**
	 * 生成ASCII艺术字（简单的）
	 */
	public static void generateAsciiArt(String text) {
		if (text == null || text.isEmpty()) return;

		System.out.println("\nASCII艺术字: " + text);
		System.out.println("═".repeat(text.length() + 4));

		for (char c : text.toCharArray()) {
			if (c >= 32 && c <= 126) {
				System.out.print(" " + c + " ");
			} else {
				System.out.print(" ? ");
			}
		}
		System.out.println("\n" + "═".repeat(text.length() + 4));
	}
}
