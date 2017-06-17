
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
		String s = null;
		Integer index = null;
		StringBuffer base64SB = new StringBuffer();
		for (int i = 0; i < bitStr.length(); i += 6) {
			/* 二进制按6位分组 */
			s = bitStr.substring(i, i + 6 > bitStr.length() ? bitStr.length() : i + 6);
			if (s.length() == 6) {
				System.out.println(s);
			} else {
				s = s + String.format("%0" + (bitSize - s.length()) + "d", 0); // 不足位后补0
				System.out.println(s);
			}
			
			// 将每6位的字符转换为十进制
			index = BinaryUtils.binary2Decimal(s);
			
			// 根据下标获取对应的Base64字符
			base64SB.append(Base64Map.getBase64(index));
		}
		
		System.out.println(base64SB.toString());
	}

}
