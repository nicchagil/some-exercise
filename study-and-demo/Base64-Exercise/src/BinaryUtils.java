import org.junit.Test;

public class BinaryUtils {
	
	/**
	 * 将多个字节转换为能打印的位
	 * @param b 多个字节
	 * @return 能打印的位
	 */
	public static String bytes2Bit(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(BinaryUtils.byte2Bit(b));
		}
		return sb.toString();
	}

	/**
	 * 将字节转换为能打印的位
	 * @param b 字节
	 * @return 能打印的位
	 */
	public static String byte2Bit(byte b) {
		/* 备注：也可用JDK方法实现：Integer.toBinaryString() */
		
		StringBuffer sb = new StringBuffer();
		for (int i = 7; i >= 0; i--) { // 右移i位
			sb.append((byte) ((b >> i) & 1));
		}
		return sb.toString();
	}
	
	/**
	 * 二进制转换为十进制
	 * @param binary 二进制
	 * @return 十进制
	 */
	public static Integer binary2Decimal(String binary) {
		if (binary == null || binary.trim().length() == 0) {
			return null;
		}
		
		binary = binary.trim();
		char[] chars = binary.toCharArray();
		
		int sum = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			if (chars[i] == '0') {
				continue;
			}
			sum = sum + (int)Math.pow(2, chars.length - 1 - i);
		}
		return sum;
	}
	
	@Test
	public void bytes2BitTest() {
		System.out.println(BinaryUtils.bytes2Bit("hello".getBytes()));
	}
	
	@Test
	public void byte2BitTest() {
		System.out.println(BinaryUtils.byte2Bit("h".getBytes()[0]));
		System.out.println(Integer.toBinaryString("h".getBytes()[0]));
	}
	
	@Test
	public void binary2DecimalTest() {
		System.out.println(BinaryUtils.binary2Decimal("011010"));
	}

}
