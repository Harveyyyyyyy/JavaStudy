package NumberRunnable;

public class NumberRunnable implements Runnable{
	private int first,end;
	public NumberRunnable(int first,int end) {
		this.first=first;
		this.end=end;
	}
	public void run() {
		for(int i=first;i<end;i+=2)
			System.out.print(i+" ");
		System.out.println("结束！");
	}
	public static void main(String args[]) {
		Runnable target=new NumberRunnable(1, 20);
		Thread thread_odd=new Thread(target,"奇数线程");
		thread_odd.start();
		new Thread(new NumberRunnable(2,10),"偶数线程").start();
	}

}
