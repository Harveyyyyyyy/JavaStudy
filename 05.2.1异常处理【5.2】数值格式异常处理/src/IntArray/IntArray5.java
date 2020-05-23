package IntArray;

public class IntArray5 {
	public static int[] getInts(String[] str) {
		if(str==null||str.length==0)
			return null;
		int x[] =new int[str.length],n=0,i=0;
		while(i<str.length) {
			try {
				x[n]=Integer.parseInt(str[i]);
				n++;
			}catch(NumberFormatException ex) {
				System.out.println("\""+str[i]+"\"字符串不能按十进制转换为整数,"+ex.toString());
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				i++;
			}
		}
		if(n==x.length)
			return x;
		int[] y=new int[n];
		System.arraycopy(x, 0, y, 0, n);
		return y;
	}
	public static void main(String[] args) {
		String[] valuestr= {"10","20","30","40","50","x","60","70"};
		int[] value=getInts(valuestr);
		System.out.print("value[]数组：");
		for(int i=0;i<value.length;i++)
			System.out.print(" "+ value[i]);
	}
	

}
