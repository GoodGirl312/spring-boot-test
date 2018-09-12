package cn.ys.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.*;


@SpringBootApplication
@ComponentScan("cn.ys.shop")
//指定Entity的扫描路径
@EntityScan(basePackages = "cn.ys.shop")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
