package 实验2;

public class _2_21n行数字塔 {
public static int[][] create(final int n){
	int mat[][]=new int[n][];
	for(int i=0;i<n;i++) {
		int x=i;
		mat[i]=new int[i*2+1];
		mat[i][0]=mat[i][i*2]=1;
	for(int j=1;j<i*2;j++) {
		if(j<=i)
			mat[i][j]=j+1;
		if(j>i) {
			mat[i][j]=x;
			x--;
				
		}
	}
	}
	return mat;
}
public static void print(int[][] mat) {
	for(int i=0;i<mat.length;i++) {
		System.out.print(String.format("%"+(mat.length-i+1)*4+"c",' '));
		for(int j=0;j<mat[i].length;j++) {
			System.out.print(String.format("%4d", mat[i][j]));
		}
		System.out.println();
	}
}
	public static void main(String[] args) {
		_2_21n行数字塔.print(_2_21n行数字塔.create(10));

	}

}
