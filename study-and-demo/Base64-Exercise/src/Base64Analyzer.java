
public class Base64Analyzer {

	public static void main(String[] args) {
		/* TODO
		 * 此为练习，部分逻辑未实现：
		 * 1、无考虑补位到6、8的公倍数情况；
		 * 2、无考虑6位全部为补位，编码为=的情况
		 */
		
		/* 转换为二进制 */
		String bitStr = BinaryUtils.bytes2Bit("hello".getBytes());
		System.out.println(bitStr);
		
		int bitSize = 6;
		String sixBitStr = null;
		Integer index = null;
		char base64Char;
		StringBuffer base64SB = new StringBuffer();
		for (int i = 0; i < bitStr.length(); i += 6) {
			/* 二进制按6位分组 */
			sixBitStr = bitStr.substring(i, i + 6 > bitStr.length() ? bitStr.length() : i + 6);
			if (sixBitStr.length() != 6) {
				sixBitStr = sixBitStr + String.format("%0" + (bitSize - sixBitStr.length()) + "d", 0); // 不足位后补0
			}
			
			// 将每6位的字符转换为十进制
			index = BinaryUtils.binary2Decimal(sixBitStr);
			
			// 根据下标获取对应的Base64字符
			base64Char = Base64Map.getBase64(index);
			System.out.println(sixBitStr + " -> " + base64Char);
			base64SB.append(base64Char);
		}
		
		System.out.println(base64SB.toString());
	}

}
