package com.asiainfo.spi;

/**
 * SPI接口，实现类由第三方提供，本工程只提供接口。比如jdk只提供数据库驱动接口java.sql.Driver，具体的实现由各数据库厂商实现。
 *
 * @author zhangzhiwang
 * @date Jun 29, 2020 4:31:13 PM
 */
public interface SPIInterface {
	void execute();// 该方法由第三方实现
}
