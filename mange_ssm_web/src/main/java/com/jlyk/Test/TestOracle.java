package com.jlyk.Test;

import com.jlyk.dao.ProductDao;
import com.jlyk.domian.Product;
import com.jlyk.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestOracle {
    @Test
    public void testOracle() throws Exception {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductService productService = ac.getBean("productService", ProductService.class);
        List<Product> productList = productService.findAll();
        System.out.println(productList);

    }
}
