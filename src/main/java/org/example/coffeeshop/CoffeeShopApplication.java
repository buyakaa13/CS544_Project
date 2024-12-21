package org.example.coffeeshop;

import org.example.coffeeshop.Concurrency.TestOptimisticLocking;
import org.example.coffeeshop.Concurrency.TestPessimisticLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CoffeeShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopApplication.class, args);

//        TestOptimisticLocking test = new TestOptimisticLocking();
//        test.testOptimisticLocking();
//
//        TestPessimisticLock test1 = new TestPessimisticLock();
//        test1.testPessimisticLocking();
    }

}
