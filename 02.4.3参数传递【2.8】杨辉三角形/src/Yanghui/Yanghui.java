package Yanghui;
public class Yanghui {
public static int[][] create(final int n){
	int mat[][]=new int[n][];
	for(int i=0;i<n;i++) {
		mat[i]=new int[i+1];
		mat[i][0]=mat[i][i]=1;
		for(int j=1;j<i;j++) {
			mat[i][j]=mat[i-1][j-1]+mat[i-1][j];
		}
	}
	return mat;
}
public static void print(int[][] mat) {
	for(int i=0;i<mat.length;i++) {
		System.out.print(String.format("%"+(mat.length-i+1)*2+"c",' '));
		for(int j=0;j<mat[i].length;j++) {
			System.out.print(String.format("%5d", mat[i][j]));
		}
		System.out.println();
	}
}
	public static void main(String[] args) {
		Yanghui.print(Yanghui.create(10));

	}

}
