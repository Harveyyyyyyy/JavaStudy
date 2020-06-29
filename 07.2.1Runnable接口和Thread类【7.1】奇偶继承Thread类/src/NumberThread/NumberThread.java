package NumberThread;

public class NumberThread extends Thread{
	private int first,n;
	public NumberThread(String name,int first,int n) {
		super(name);
		this.first=first;
		this.n=n;
	}
	public void run() {
		long time1=System.currentTimeMillis();
		System.out.print("\n"+this.getName()+"开始时间="+time1+",");
		for(int i=0;i<n;i++) 
			System.out.print((first+2*i)+" ");
		long time2=System.currentTimeMillis();
		System.out.println(this.getName()+"结束时间="+time2+",耗时"+(time2-time1)+"毫秒。");
	}
	public static void main(String args[]) {
		System.out.println("currentThread="+Thread.currentThread().getName());
		Thread thread_odd=new NumberThread("奇数线程", 1, 20);
		Thread thread_even=new NumberThread("偶数线程", 2, 10);
		thread_odd.start();
		thread_even.start();
		System.out.println("activeCount="+Thread.activeCount());
	}

}
