import org.junit.Test;

public class Base64Map {
	
	public static char[] chars = new char[64];
	
	static {
		for (int i = 0; i < 26; i++) {
			chars[i] = (char)((int)'A' + i);
		}
		for (int i = 26; i < 52; i++) {
			chars[i] = (char)((int)'a' + i - 26);
		}
		for (int i = 52; i < 62; i++) {
			chars[i] = (char)((int)'0' + i - 52);
		}
		chars[62] = '+';
		chars[63] = '/';
	}
	
	/**
	 * 获取对应的Base64字符
	 * @param index 下标
	 * @return 对应的Base64字符
	 */
	public static char getBase64(int index) {
		return chars[index];
	}
	
	@Test
	public void printAll() {
		System.out.println(chars);
	}

}
