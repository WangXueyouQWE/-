package producerAndconsumer;

import java.util.Random;

public class Producer implements Runnable {
	private Storage storage;
	public Producer (Storage storage) {
		this.storage=storage;
	}
	@Override
	public void run() { 
		// TODO Auto-generated method stub
		int i=0;
		Random random=new Random();
		while(i<10) {
			i++;
			product product1=new product(i, "Ãû×Ö"+random.nextInt(100));
			storage.push(product1);
		}
		
	}

}
