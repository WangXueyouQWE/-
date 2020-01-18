
public class SynchronizedThread {
		public static void main(String[] args) {
			TestThread2 testThread2=new TestThread2();
			new Thread(testThread2).start();
			new Thread(testThread2,"Thread-1").start();
			new Thread(testThread2,"Thread-2").start();
			new Thread(testThread2,"Thread-4").start();
		}

}
class TestThread2 implements Runnable{
	volatile int ticket=100;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			sale();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ticket<=0) {
				break;
			}
		}
	}
synchronized private void sale() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+"is selling tickets"+ticket--);
		
	}
	
}
