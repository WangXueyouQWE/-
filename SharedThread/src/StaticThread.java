
public class StaticThread  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestThread testThread1=new TestThread();
		testThread1.start();
		new TestThread().start();
		new TestThread().start();
		new TestThread().start();

	}

}
class TestThread extends Thread {
	public static int ticket=100;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (ticket > 0) {
				System.out.println(Thread.currentThread().getName()+"si selling ticket"+ticket);
				ticket--;
			}else {
				break;
			}
		}
	}
}
