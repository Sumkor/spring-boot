package com.sumkor.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;

/**
 * 如果提示 No auto configuration classes found in META-INF/spring.factories
 * 是因为 spring-boot-autoconfigure 模块编译后没有生成 spring.factories 文件。
 * 多尝试编译几次这个模块，编译时需在 gradle 指定阿里镜像源。确认文件生成后就没问题了。
 *
 * @author Sumkor
 * @since 2020/10/14
 */
@SpringBootApplication
public class ConfigSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigSpringApplication.class, args);
	}
	/**
	 * 1. 默认包扫描路径为：启动类当前包及子包。如何实现？
	 * @see AutoConfigurationPackage
	 * 其中引入了 {@link AutoConfigurationPackages.Registrar}，注意到它实现了 ImportBeanDefinitionRegistrar
	 * 即，它在项目启动时，把 basePackage 作为 beanDefinition 注册到 Spring 工厂
	 * @see AutoConfigurationPackages.Registrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 *
	 * 1.1 取得 basePackage
	 * @see AutoConfigurationPackages.PackageImports#PackageImports(org.springframework.core.type.AnnotationMetadata)
	 *
	 * 1.2 将 basePackage 注册到 Spring
	 * @see AutoConfigurationPackages#register(org.springframework.beans.factory.support.BeanDefinitionRegistry, java.lang.String...)
	 *
	 *
	 * 2，自动配置
	 * @see EnableAutoConfiguration
	 * 需要读取 spring-boot-project/spring-boot/src/main/resources/META-INF/spring.factories 中的配置
	 * 其中引入了 {@link AutoConfigurationImportSelector}
	 * 关注其中对 spring.factories 自动配置的筛选逻辑：根据 gradle/maven 中配置的依赖来自动装配
	 * @see AutoConfigurationImportSelector#getAutoConfigurationEntry(org.springframework.core.type.AnnotationMetadata)
	 *
	 *
	 * 3. 配置文件内容
	 * 例如 server.port
	 */
}
