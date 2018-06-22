package com.memberslist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Anna Likhachova
 *
 */
@SpringBootApplication
@EnableSwagger2
public class App {


	public static void main(String[] args) throws Exception{
		ApplicationContext ctx = new GenericXmlApplicationContext("config.xml");
		   MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		SpringApplication.run(App.class, args);
	}

	@RefreshScope
	@RestController
	class MessageRestController {

		@Value("${message:Hello default}")
		private String message;

		@RequestMapping("/message")
		String getMessage() {
			return this.message;
		}
	}

	@Bean
    public Docket membersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.memberslist.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

