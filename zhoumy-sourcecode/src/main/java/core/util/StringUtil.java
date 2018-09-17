package core.util;

public class StringUtil {

	public static String firstCharacterUppercase(String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}
}
