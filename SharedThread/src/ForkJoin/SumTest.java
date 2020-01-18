package ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;


public class SumTest {
	int i=0;
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//创建执行线程池
		ForkJoinPool pool=new ForkJoinPool();
		
		//创建任务
		SumTask task=new SumTask(1,10000);
		//提交任务
		ForkJoinTask<Long> resulTask=pool.submit(task);
		//等待结果
		do {
			System.out.printf("Main Thread Count:%d\n",pool.getActiveThreadCount());
			System.out.printf("Main Paralelism:%d",pool.getParallelism());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		} while (task.isDone());  //判断task是否结束
		System.out.println(resulTask.get().toString());
	}

}
