package com.asiainfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Spring Boot的启动类</p>
 * 说明：以后Spring Boot在本工程的注释中简称spb
 *
 * @author zhangzhiwang
 * @date 2020年5月30日 下午3:46:19
 */
/**
 * 点进@SpringBootApplication注解，比较重要的注解有三个：
 *  @SpringBootConfiguration	标明该类是一个Spring Boot的配置类
 *  	引用注解@Configuration，该注解是Spring的一个原生注解，标明该类是一个配置类，作用等同于xml
	@EnableAutoConfiguration	标明启动自动配置
		@AutoConfigurationPackage 自动扫名启动类所在包及其子包下面所有的组件并添加到Spring IOC容器中
		@Import(AutoConfigurationImportSelector.class) AutoConfigurationImportSelector类将自动导入好多默认配置类，这类配置类大多以AutoConfiguration结尾
	@ComponentScan	组件扫面的范围，类似于Spring MVC中xml里的ComponentScan标签
 */
@SpringBootApplication // 标明该类是Spring Boot应用的启动类（引导类），也即标明该应用是一个Spring Boot应用
// 默认扫描该类所在包及其子包下面的组件（component），其它路径是扫描不到的，最佳实践是该类一般放在所有类的父级目录下。
//@ImportResource(locations = {"classpath:spring.xml"})// 导入外部资源文件，比如xml
public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
//		LOG.debug("debug信息");
//		LOG.info("info信息");
//		LOG.warn("warn信息");
//		LOG.error("error信息");
	}
}
