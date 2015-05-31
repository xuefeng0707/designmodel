package test.designmodel.singleton;

import org.apache.log4j.Logger;

/**
 * 双重检查，注意volatile
 * JVM指令重排序，new对象分为三步：（1）分配内存，（2）构造函数，（3）赋值给变量
 * JVM中（2）（3）可能对调，如果顺序为1、3、2，那么另一个线程在（3）之后（2）之前获得单例对象，
 * 那么构造函数还未调用，此时使用则会报错
 * @author xuefeng
 *
 */
public class SingletonDoubleCheck {
	private static final Logger logger = Logger.getLogger(SingletonDoubleCheck.class);
	
	private static volatile SingletonDoubleCheck INSTANCE = null;
	
	private SingletonDoubleCheck() {
		logger.info("construct SingletonDoubleCheck");
	}
	
	public static SingletonDoubleCheck getInstance() {
		if(INSTANCE == null) {
			synchronized (SingletonDoubleCheck.class) {
				if(INSTANCE == null) {
					INSTANCE = new SingletonDoubleCheck();
				}
			}
		}
		return INSTANCE;
	}
	
	public static void main(String[] args) {
		SingletonDoubleCheck instance = SingletonDoubleCheck.getInstance();
		logger.info(instance);
	}
}
