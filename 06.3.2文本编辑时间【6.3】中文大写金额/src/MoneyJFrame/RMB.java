package MoneyJFrame;

public class RMB {
	public static String toString(double x) {
		String yuan="��ǧ��ʰ��ǧ��ʰԪ�Ƿ�";
		String digit="��Ҽ��������½��ƾ�";
		String result="";
		int y=(int)(x*100);
		for(int i=yuan.length()-1;y>0&&i>0;i--,y/=10)
			result=""+digit.charAt(y%10)+yuan.charAt(i)+result;
		return result;
	}
}
