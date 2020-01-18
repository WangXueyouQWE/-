package ForkJoin;

import java.lang.Thread.State;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {
	private int start;
	private int end;

	public SumTask(int start, int end) {
		this.start = start;
		this.end = end;
		// TODO Auto-generated constructor stub
	}

	public static final int threadhold = 5;

	@Override
	protected Long compute() {
		Long sum = 0L;
		boolean canComputer = (end - start) <= threadhold;
		// 如果任务足够小，就直接执行
		if (canComputer) {
			for (int i = start; i <= end; i++) {
				sum = sum + i;
			}
		} else {
			// 任务大于阀值
			int middle = (start + end) / 2;
			SumTask sumTask1 = new SumTask(start, middle);
			SumTask sumTask2 = new SumTask(middle + 1, end);

			invokeAll(sumTask1, sumTask2);

			Long sum1 = sumTask1.join();
			Long sum2 = sumTask2.join();

			// 结果合并
			sum = sum1 + sum2;
		}
		// TODO Auto-generated method stub
		return sum;
	}

}
