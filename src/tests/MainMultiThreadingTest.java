package tests;

public class MainMultiThreadingTest {
	private int count = 0;

	public static void main(String[] args) {
		MainMultiThreadingTest m = new MainMultiThreadingTest();
		m.something();
	}
	
	//sans synchronized ca marche pas
	public synchronized void add(int i) {
		count += i;
	}
	
	public void something() {
		Thread t1 = new Thread(new Runnable(){

		@Override
		public void run() {
			for (int i=0 ;i < 10000;i++)
				add(3);
		}
		});		
		
		Thread t2 = new Thread(new Runnable(){
		@Override
		public void run() {
			for (int i=0 ;i < 10000;i++)
				add(2);	
		}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(count);

	}

}
