package com.asiainfo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.asiainfo.annotation.EnableZzw;
import com.asiainfo.config.TaskConfig;
import com.asiainfo.service.TaskService;

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
@MapperScan(basePackages = {"com.asiainfo.mapper"})// 扫描Mapper接口，通过反射生成接口的代理实现类的bean并注册到IOC容器中
/**
 *  @Enable*** 模块驱动，即开启***组件</p>
 *  模块驱动的作用在于简化配置：</p>
 *  在mvc中，如果使用纯xml配置的方式实现事务，那么需要在spring的配置文件中配置以下信息：</p>
 *  <!-- 配置事务管理 -->
<bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

<!-- 拦截器方式配置事物 -->
<tx:advice id="transactionAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        <!-- 必须要有事务,没有则创建 -->
        <tx:method name="insert*" propagation="REQUIRED" />
        <tx:method name="update*" propagation="REQUIRED" />
        <tx:method name="delete*" propagation="REQUIRED" />
        <!-- 可以有可以没有 -->
        <tx:method name="get*" propagation="SUPPORTS" read-only="true" />
        <tx:method name="find*" propagation="SUPPORTS" read-only="true" />
        <tx:method name="select*" propagation="SUPPORTS" read-only="true" />
    </tx:attributes>
</tx:advice>
	
	如果使用注解的方式实现事务，娜美必须在xml中配置注解驱动已开启扫描@Transactional注解：</p>
	<!-- 基于注释的事务，当注释中发现@Transactional时，使用id为“transactionManager”的事务管理器  -->
    <!-- 如果没有设置transaction-manager的值，则spring以缺省默认的事务管理器来处理事务，默认事务管理器为第一个加载的事务管理器 -->
    <tx:annotation-driven transaction-manager="transactionManager"/>
    或者可以省略transaction-manager属性：<tx:annotation-driven/>
    
    在spb中，以代码的方式（@Configuration）来替代xml的配置，所以如果开启事务则需要创建一个事务配置类加上注解@Configuration，然后定义上面提到的bean（@Bean）也可以实现功能。
    但是Spring提供了更为简化的配置——注解驱动，连@Configuration类都不需要了，使用@EnableTransactionManagement将事务默认所需要的bean自动装配好，大大简化了配置。
    在继承其他第三方模块时也可以使用注解驱动，比如集成Redis、定时任务等，但是Spring默认提供的注解驱动是有限的，可以自己扩展。
 *
 *
 */
@EnableTransactionManagement// 在启动类上面添加此注解表示开启事物管理，就像在mvc中的配置文件配置一样。
/**
 * 在mvc中开启事务需要在配置文件中配置：
<bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
 */
//@EnableScheduling// 启动定时任务组件
//@EnableZzw
public class App {// extends SpringBootServletInitializer //使用外部容器
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	// 使用外部容器时使用此方法
//	@Override@
//	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(App.class);
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
//		LOG.debug("debug信息");
//		LOG.info("info信息");
//		LOG.warn("warn信息");
//		LOG.error("error信息");
		
		/**
		 * spb默认内嵌tomcat，如果不想使用内置tomcat而要使用外置tomcat的话，要进行以下修改：
		 * 1、启动类要继承SpringBootServletInitializer并重写configure方法
		 * 2、pom文件的打包类型改成war
		 * 3、将pom文件里的tomcat启动器的scope属性设置为provided
		 * 4、增加web.xml文件
		 */
		
		// 测试mvc环境下（非spb环境）的定时任务，使用xml配置
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
	}
}

/**
spb自动装配原理：
思考一个问题：在spb中整合第三方组件（比如redis）的方式是引入相关组件的启动器（starter），不需要做其他任何配置，然后就可以在代码里面Autowired该组件的对象，这个是怎么做到的？被引入的对象是什么时候被实例化的并放入IOC容器里面的？
spb的“约定优于配置”的原则就起作用了，spb约定：任何第三方组件必须在starter的工程目录里（对本项目来说当然是jar包）的classpath:META-INFO目录下有一个名为spring.factories的文件，即classpath:META-INFO/spring.factories。
将本starter工程所有的配置类的全路径名配置在该文件中，spb在启动的时候会扫描所有starter的该目录下面的spring.factories文件，并通过反射加载所有配置类，进而解析配置类中标注了@Bean方法，从而将所需要的bean放入IOC容器里面。

这里要说明几点：
1、classpath:META-INFO下的spring.factories文件并不是spb要求的，是Spring要求有的，里面不止配置了配置类的全路径还可能有其他跟spb无关的配置，所以spring.factories不是为加载配置类而特有的文件。
2、上面所说的约定是针对第三方组件而言的，spb的组件分为两种：一种是spb自带的，一种是第三方提供的。如何区分呢？spb自带的starter组件的明明规则是spring-boot-starter-***，而第三方组件的明明规则是***-spring-boot-starter。
	对spb自带的starter组件而言，classpath:META-INF下不需要有spring.factories文件，所有这些组件的配置类都在spring-boot-autoconfigure-[版本号].jar里面的classpath:META-INF/spring.factories进行配置
3、所有spring.factories里面对配置类进行配置的key统一为org.springframework.boot.autoconfigure.EnableAutoConfiguration（spb的@EnableAutoConfiguration注解类的全限定名），value是各配置类的全限定名
4、了解spb自动装配的源码，路径为：@SpringBootApplication->@EnableAutoConfiguration->@Import里面导入的类，也可以自定义ImportSelector和自定义注解来实现自动转配，如本工程的ZzwImportSelector和@EnableZzw

其实spb的自动装配就解决一个问题——到哪里找第三方组件的配置类并装配该组件所必须的bean
*/