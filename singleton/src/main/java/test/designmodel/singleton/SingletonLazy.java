package test.designmodel.singleton;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

/**
 * 最简单的延迟写法，静态方法加synchronized
 * @author xuefeng
 *
 */
public class SingletonLazy {
	
	private static final Logger logger = Logger.getLogger(SingletonLazy.class);
	
	private static SingletonLazy INSTANCE = null;
	
	private SingletonLazy() {
		// 假设构建单例需要1ms
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			logger.error("SingletonLazy sleep error", e);
		}
	}
	
	public static synchronized SingletonLazy getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new SingletonLazy();
		}
		return INSTANCE;
	}
	
	public static void main(String[] args) {
		final Set<SingletonLazy> set = new HashSet<SingletonLazy>();
		final CountDownLatch latch = new CountDownLatch(10000);
		for(int i=0;i<10000;i++) {
			new Thread(){
				public void run() {
					set.add(getInstance());
					latch.countDown();
				};
			}.start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error("latch await error", e);
		}
		
		// 如果不加synchronized，这里数字不是1
		logger.info(set.size());
	}
}
