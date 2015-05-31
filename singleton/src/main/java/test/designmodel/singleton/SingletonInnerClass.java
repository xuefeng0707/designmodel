package test.designmodel.singleton;

import org.apache.log4j.Logger;

/**
 * 使用内部类成员，第一次调用时初始化内部类，由JVM来保证只初始化一次
 * 推荐使用此种方法。
 * @author xuefeng
 *
 */
public class SingletonInnerClass {
	
	private static final Logger logger = Logger.getLogger(SingletonInnerClass.class);
	
	private SingletonInnerClass() {
		logger.info("construct SingletonInnerClass");
	}
	
	public static SingletonInnerClass getInstance() {
		return InstanceHolder.INSTANCE;
	}
	
	private static class InstanceHolder {
		public static SingletonInnerClass INSTANCE = new SingletonInnerClass();
	}
	
	public static void main(String[] args) {
		logger.info(SingletonInnerClass.getInstance());
	}
}
