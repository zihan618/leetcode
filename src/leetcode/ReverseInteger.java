package leetcode;
/**
 * �����ַ�ת  �����Խ��С��  ��Ҫ����0
 * @author 12260
 *
 */
public class ReverseInteger {

	public static void main(String[] args) {
		int i=0, j=0;
		while (true) {
			i--;
			j++;
			if( j%1000 == 0)
			System.out.println(i);
		}
		//System.out.println(new ReverseInteger().reverse2(-32));
	}

	//ֱ�ӳ�  Ȼ���
	public int reverse(int x) {
		int res = 0;
		int new_res = 0;
		int remain = 0;
		while (x != 0) {
			remain = x % 10;
			new_res = res * 10 + remain;
			x /= 10;
			if( (new_res - remain)/10 != res) {
				return 0;
			}
			res = new_res;

		}
		return res;
	}
	//�����ַ�����ת   ��ת֮�󳬹���С�ᱨ�쳣
	public int reverse2(int x) {
		String string = Integer.toString(x, 10);
		string = string.startsWith("-")? string.substring(1, string.length()): string;
		StringBuilder stringBuilder = new StringBuilder(string);
		stringBuilder.reverse();
		try {
			return Integer.parseInt( x<0? "-"+stringBuilder.toString(): stringBuilder.toString(), 10);
		}catch (Exception e) {
			return 0;
		}
	}
}
