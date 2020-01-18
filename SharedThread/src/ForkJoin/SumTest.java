package ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;


public class SumTest {
	int i=0;
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//����ִ���̳߳�
		ForkJoinPool pool=new ForkJoinPool();
		
		//��������
		SumTask task=new SumTask(1,10000);
		//�ύ����
		ForkJoinTask<Long> resulTask=pool.submit(task);
		//�ȴ����
		do {
			System.out.printf("Main Thread Count:%d\n",pool.getActiveThreadCount());
			System.out.printf("Main Paralelism:%d",pool.getParallelism());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		} while (task.isDone());  //�ж�task�Ƿ����
		System.out.println(resulTask.get().toString());
	}

}
