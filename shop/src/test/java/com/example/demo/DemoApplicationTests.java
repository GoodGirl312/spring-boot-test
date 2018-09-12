package com.example.demo;

import cn.ys.shop.DemoApplication;
import cn.ys.shop.product.Product;
import cn.ys.shop.product.ProductController;
import cn.ys.shop.product.ProductService;
import cn.ys.shop.user.User;
import cn.ys.shop.user.UserController;
import cn.ys.shop.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
	@Autowired
	private ProductService ps;

	@Test
	public  void  findByPage(){

		Page<Product> pages= ps.findByPage(0);
		Iterator<Product> it=pages.iterator();
		while(it.hasNext()){
			System.out.println("value:"+((Product)it.next()).getPname());
		}
        Assert.isTrue(1==1,"");

	}

}
