package template;

import java.util.ArrayList;
import java.util.List;

public final class AsciiUtils {
	private AsciiUtils() {}

	// ASCII字符分类
	public static final int ASCII_TOTAL = 128;
	public static final int ASCII_PRINTABLE_START = 32;
	public static final int ASCII_PRINTABLE_END = 126;
	public static final int ASCII_PRINTABLE_COUNT = 95;

	/**
	 * 获取所有ASCII字符的字符串
	 */
	public static String getAllAscii() {
		return buildAsciiString(0, ASCII_TOTAL - 1);
	}

	/**
	 * 获取可打印ASCII字符的字符串
	 */
	public static String getPrintableAscii() {
		return buildAsciiString(ASCII_PRINTABLE_START, ASCII_PRINTABLE_END);
	}

	/**
	 * 获取ASCII控制字符（0-31, 127）
	 */
	public static String getControlCharacters() {
		StringBuilder sb = new StringBuilder(33);

		// 0-31
		for (int i = 0; i <= 31; i++) {
			sb.append((char) i);
		}

		// 127
		sb.append((char) 127);

		return sb.toString();
	}

	/**
	 * 按类别获取ASCII字符
	 */
	public static String getAsciiByCategory(AsciiCategory category) {
		return switch (category) {
			case ALL -> getAllAscii();
			case PRINTABLE -> getPrintableAscii();
			case CONTROL -> getControlCharacters();
			case DIGITS -> buildAsciiString(48, 57);     // 0-9
			case UPPERCASE -> buildAsciiString(65, 90);  // A-Z
			case LOWERCASE -> buildAsciiString(97, 122); // a-z
			case PUNCTUATION -> getPunctuation();
			case WHITESPACE -> getWhitespace();
		};
	}

	/**
	 * 获取标点符号字符
	 */
	public static String getPunctuation() {
		// 标点符号在多个区间
		int[][] ranges = {
				{33, 47},   // !"#$%&'()*+,-./
				{58, 64},   // :;<=>?@
				{91, 96},   // [\]^_`
				{123, 126}  // {|}~
		};

		StringBuilder sb = new StringBuilder();
		for (int[] range : ranges) {
			sb.append(buildAsciiString(range[0], range[1]));
		}
		return sb.toString();
	}

	/**
	 * 获取空白字符
	 */
	public static String getWhitespace() {
		// 空格、制表符、换行等
		return "" +
				(char) 9 +   // 水平制表符
				(char) 10 +  // 换行符
				(char) 11 +  // 垂直制表符
				(char) 12 +  // 换页符
				(char) 13 +  // 回车符
				(char) 32;   // 空格
	}

	/**
	 * 构建指定范围的ASCII字符串
	 */
	private static String buildAsciiString(int start, int end) {
		StringBuilder sb = new StringBuilder(end - start + 1);
		for (int i = start; i <= end; i++) {
			sb.append((char) i);
		}
		return sb.toString();
	}

	/**
	 * 获取ASCII字符的详细信息
	 */
	public static List<AsciiInfo> getAsciiInfo() {
		List<AsciiInfo> infoList = new ArrayList<>(ASCII_TOTAL);

		for (int i = 0; i < ASCII_TOTAL; i++) {
			char c = (char) i;
			String description = getAsciiDescription(i);
			boolean printable = (i >= ASCII_PRINTABLE_START && i <= ASCII_PRINTABLE_END);

			infoList.add(new AsciiInfo(i, c, description, printable));
		}

		return infoList;
	}

	/**
	 * 获取ASCII字符描述
	 */
	private static String getAsciiDescription(int code) {
		return switch (code) {
			case 0 -> "空字符(NUL)";
			case 1 -> "标题开始(SOH)";
			case 2 -> "正文开始(STX)";
			case 3 -> "正文结束(ETX)";
			case 4 -> "传输结束(EOT)";
			case 5 -> "询问(ENQ)";
			case 6 -> "确认(ACK)";
			case 7 -> "响铃(BEL)";
			case 8 -> "退格(BS)";
			case 9 -> "水平制表符(HT)";
			case 10 -> "换行符(LF)";
			case 11 -> "垂直制表符(VT)";
			case 12 -> "换页符(FF)";
			case 13 -> "回车符(CR)";
			case 14 -> "移出(SO)";
			case 15 -> "移入(SI)";
			case 16 -> "数据链路转义(DLE)";
			case 17 -> "设备控制1(DC1)";
			case 18 -> "设备控制2(DC2)";
			case 19 -> "设备控制3(DC3)";
			case 20 -> "设备控制4(DC4)";
			case 21 -> "否定(NAK)";
			case 22 -> "同步空闲(SYN)";
			case 23 -> "传输块结束(ETB)";
			case 24 -> "取消(CAN)";
			case 25 -> "媒体结束(EM)";
			case 26 -> "替换(SUB)";
			case 27 -> "转义(ESC)";
			case 28 -> "文件分隔符(FS)";
			case 29 -> "组分隔符(GS)";
			case 30 -> "记录分隔符(RS)";
			case 31 -> "单元分隔符(US)";
			case 32 -> "空格(Space)";
			case 127 -> "删除(DEL)";
			default -> "字符: '" + (char) code + "'";
		};
	}

	/**
	 * ASCII分类枚举
	 */
	public enum AsciiCategory {
		ALL, PRINTABLE, CONTROL, DIGITS, UPPERCASE, LOWERCASE, PUNCTUATION, WHITESPACE
	}

	/**
	 * ASCII字符信息类
	 */
	public static class AsciiInfo {
		private final int code;
		private final char character;
		private final String description;
		private final boolean printable;

		public AsciiInfo(int code, char character, String description, boolean printable) {
			this.code = code;
			this.character = character;
			this.description = description;
			this.printable = printable;
		}

		// Getters
		public int getCode() { return code; }
		public char getCharacter() { return character; }
		public String getDescription() { return description; }
		public boolean isPrintable() { return printable; }

		@Override
		public String toString() {
			return String.format("Code: %3d, Char: %s, Desc: %s",
					code,
					printable ? "'" + character + "'" : "[控制字符]",
					description);
		}
	}
}
