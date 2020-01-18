
public class VolatileThread {
	public static void main(String[] args) {
		new Thread(new TestThread1()).start();
		new Thread(new TestThread1()).start();
		new Thread(new TestThread1()).start();
		new Thread(new TestThread1()).start();
	}

}
class TestThread1 implements Runnable{
	volatile int ticket=100;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (ticket>0) {
				System.out.println(Thread.currentThread().getName()+"is selling tickets"+ticket);
				ticket--;
			}else {
				break;
			}
		}
		
	}
	
}
