package producerAndconsumer;

public class Storage {
	//�ֿ�����Ϊ10
	private product[] products=new product[10];
	private int top=10;
	//���������ֿ��зŲ�Ʒ
	public synchronized void push(product product) {
		while (top == products.length) {
			try {
				System.out.println("producer wait");
				wait();//�ֿ��������ȴ�
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//�Ѳ�Ʒ����ֿ�
		products[top++]=product;
		System.out.println(Thread.currentThread().getName()+"�����˲�Ʒ"+product);
		System.out.println("producer notifyAll");
		notifyAll(); //�ȴ������߳�
	
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
		System.out.println(Thread.currentThread().getName()+"�����˲�Ʒ"+p);
		System.out.println("consumer notifyAll");
		notifyAll();//���ѵȴ����߳�
		return p;
	}
}
