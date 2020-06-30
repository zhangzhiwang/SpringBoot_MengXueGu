package com.asiainfo.spi;

import java.util.ServiceLoader;

/**
 * Java SPI机制
 * 可参考：https://www.jianshu.com/p/3a3edbcd8f24
 * 		  https://www.jianshu.com/p/46b42f7f593c
 *
 * @author zhangzhiwang
 * @date Jun 29, 2020 4:29:40 PM
 */
public class SPITest {
	public static void main(String[] args) {
		ServiceLoader<SPIInterface> serviceLoader = ServiceLoader.load(SPIInterface.class);
		for(SPIInterface sp : serviceLoader) {
			sp.execute();
		}
	}
}