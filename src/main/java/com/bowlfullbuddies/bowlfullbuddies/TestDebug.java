package com.bowlfullbuddies.bowlfullbuddies;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import com.bowlfullbuddies.bowlfullbuddies.service.UserOrderService;

public class TestDebug {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BowlfullBuddiesApplication.class, args);
        UserOrderService svc = ctx.getBean(UserOrderService.class);
        try {
            System.out.println("Fetching orders for user ID 1...");
            System.out.println(svc.getUserOrders(1L));
            System.out.println("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
