package producerAndconsumer;

public class Storage {
	//仓库容量为10
	private product[] products=new product[10];
	private int top=10;
	//生产者往仓库中放产品
	public synchronized void push(product product) {
		while (top == products.length) {
			try {
				System.out.println("producer wait");
				wait();//仓库已满，等待
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//把产品放入仓库
		products[top++]=product;
		System.out.println(Thread.currentThread().getName()+"生产了产品"+product);
		System.out.println("producer notifyAll");
		notifyAll(); //等待唤醒线程
	
	}
	public synchronized product pop() {
		while (top == 0) {
			System.out.println("consumer wait");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		--top;
		product p=new product(products[top].getsId(), products[top].getsName());
		products[top]=null;
		System.out.println(Thread.currentThread().getName()+"消费了产品"+p);
		System.out.println("consumer notifyAll");
		notifyAll();//唤醒等待的线程
		return p;
	}
}
