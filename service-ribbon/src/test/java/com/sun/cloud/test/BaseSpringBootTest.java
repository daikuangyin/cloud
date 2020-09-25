package com.sun.cloud.test;

import com.sun.cloud.RibbonApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <简单描述>
 * <一句话描述>
 *
 * @author: 李涛
 * @version: 2019年04月25日 10:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RibbonApplication.class)
public abstract class BaseSpringBootTest {
	@Before
	public void init() {
		System.out.println("开始测试-----------------");
	}

	@After
	public void after() {
		System.out.println("测试结束-----------------");
	}
}
