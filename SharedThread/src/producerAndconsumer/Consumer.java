package producerAndconsumer;

public class Consumer implements Runnable{
	private Storage storage;
	public Consumer(Storage storage) {
		this.storage=storage;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() { 
		// TODO Auto-generated method stub
		int i=0;
		while (i<10) {
			i++;
		storage.pop();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
