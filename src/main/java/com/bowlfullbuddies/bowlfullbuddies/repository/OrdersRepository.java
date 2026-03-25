package com.bowlfullbuddies.bowlfullbuddies.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bowlfullbuddies.bowlfullbuddies.entity.admin.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUsersIdOrderByOrderDateTimeDesc(Long userId);
    List<Orders> findAllByOrderByOrderDateTimeDesc();
}
