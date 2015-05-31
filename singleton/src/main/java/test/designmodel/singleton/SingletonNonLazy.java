package test.designmodel.singleton;

import org.apache.log4j.Logger;

/**
 * 最简单的非延迟写法
 * @author xuefeng
 *
 */
public class SingletonNonLazy {
	private static final Logger logger = Logger.getLogger(SingletonNonLazy.class);
	
	private static SingletonNonLazy INSTANCE = new SingletonNonLazy();
	
	private SingletonNonLazy() {
		
	}
	
	public static SingletonNonLazy getInstance() {
		return INSTANCE;
	}
	
	public static void main(String[] args) {
		logger.info(SingletonNonLazy.getInstance());
	}
}
