package org.j2server.j2cache;

import org.j2server.j2cache.cache.CacheManager;
import org.j2server.j2cache.cache.ICache;
import org.j2server.j2cache.cache.redis.RedisCacheStategy;
import org.j2server.j2cache.utils.PropsUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestRedisCache {

	public TestRedisCache() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		PropsUtils.setCacheStrategyClass(RedisCacheStategy.class.getName());
		ICache<String, DataClass> cache = CacheManager.getOrCreateCache("redisCache", String.class, DataClass.class);
		
		
		Integer cnt = 1000000;
		System.out.println("开始测试写入缓存" + cache.getName());
		long begin = System.currentTimeMillis();
		for (int i = 0; i < cnt; i++) {
			DataClass dc = new DataClass();
			dc.setName(Integer.toString(i));
			dc.setValue(i);
			dc.setStrValue("asdfadsfasfda");
			cache.put(Integer.toString(i), dc);
		}
		long end = System.currentTimeMillis();
		System.out.println("总共耗时：" + (end - begin));
		System.out.println("每毫秒写入:"+cnt/(end - begin)+"条。");  
        System.out.println("每秒写入:"+(cnt/(end - begin))*1000+"条。");  
	}
	
	private static class DataClass {
		private String name;
		private long value;
		private String strValue;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getValue() {
			return value;
		}
		public void setValue(long value) {
			this.value = value;
		}
		public String getStrValue() {
			return strValue;
		}
		public void setStrValue(String strValue) {
			this.strValue = strValue;
		}
	}

}
