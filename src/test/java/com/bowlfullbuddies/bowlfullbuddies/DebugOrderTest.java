package com.bowlfullbuddies.bowlfullbuddies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bowlfullbuddies.bowlfullbuddies.service.UserOrderService;
import com.bowlfullbuddies.bowlfullbuddies.entity.customer.Users;
import com.bowlfullbuddies.bowlfullbuddies.repository.UserRepository;

@SpringBootTest
public class DebugOrderTest {

    @Autowired
    UserOrderService svc;

    @Autowired
    UserRepository repo;

    @Test
    public void testOrders() {
        try {
            Users u = repo.findAll().get(0);
            System.out.println("====== Fetching orders for user ID " + u.getId() + " ======");
            System.out.println(svc.getUserOrders(u.getId()));
            System.out.println("====== SUCCESS ======");
        } catch (Exception e) {
            System.out.println("====== EXCEPTION CAUGHT ======");
            e.printStackTrace();
        }
    }
}
