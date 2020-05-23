package ÊµÑé2;

public class _2_30ÂÝÐý·½Õó {
		public static int[][] helixPhalanx(int n){
		int[][] mat = new int[n][n];
		int right = 1, down = 2, left = 3, up = 4;
		int direction = right;	
		int numb = n * n;
		int i = 0, j = 0;
		for (int p = 1; p <= numb; p++)
		{
			mat[i][j] = p;
			if(direction == right)
			{
				if (j + 1 < n &&mat[i][j + 1] == 0)
				{
					j++;
				} else {
					i++;
					direction = down;
					continue;					}
				}
				if (direction == down)
				{
				if (i + 1 < n && mat[i + 1][j] == 0)
				{
					i++;
				} else {
					j--;
					direction = left;
					continue;
				}
			}
			if (direction == left)
			{
				if (j - 1 >= 0 && mat[i][j - 1] == 0)
				{
					j--;
				} else {
					i--;
					direction = up;
					continue;
				}
		}
			if (direction == up)
			{
				if (i - 1 >= 0 && mat[i - 1][j] == 0)
				{
					i--;
				} else {
					j++;
					direction = right;
				continue;
				}
			}
		}
		return mat;

	}	
		
			
		public static void print(int[][] mat) {
			for(int i=0;i<mat.length;i++) {
				for(int j=0;j<mat[i].length;j++)
					System.out.print(String.format("%4d", mat[i][j]));
				System.out.println();
			}
		}
			public static void main(String[] args) {
				 _2_30ÂÝÐý·½Õó.print(_2_30ÂÝÐý·½Õó.helixPhalanx(4));
				 
	}

}
