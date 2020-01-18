package producerAndconsumer;

public class InterruptTest {
	public static void main(String[] args) throws InterruptedException {
		Storage storage=new Storage();
		
		
		Thread consumser1=new Thread(new Consumer(storage));
		consumser1.setName("消费者1");
		Thread consumer2=new Thread(new Consumer(storage));
		consumer2.setName("消费者2");
		Thread producer1=new Thread(new Producer(storage));
		producer1.setName("生产者1");
		Thread producer2=new Thread(new Producer(storage));
		producer2.setName("生产者2");

		consumser1.start();
		consumer2.start();
		Thread.sleep(1000);
		producer1.start();
		producer2.start();
		
		
		
//		Test1Thread t1=new Test1Thread();
//		Test2Thread t2=new Test2Thread();
//		
//		
//		t1.start();
//		t2.start();
//		
//		
//		//让线程运行一会儿后中断
//		Thread.sleep(2000);
//		
//		t1.interrupt();
//		t2.flag=false;
//		System.out.println("mian thread is existing");
//		
		
	}

}
//class Test1Thread extends  Thread {
//	public void run() {
//		//判断标志，当本线程被别人interrupt后，JVM会被本线程设置interrupt标记
//		while (!interrupted()) {
//			System.out.println("test thread is running");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			break;
//		}
//		System.out.println("test thread is exiting");
//	}
//}
//	
//class Test2Thread extends Thread{
//	public volatile boolean flag=true;
//	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		while (flag) {
//			System.out.println("test thread2 is running");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println("test thread2 is existing");
//	}
//	
//}
//
