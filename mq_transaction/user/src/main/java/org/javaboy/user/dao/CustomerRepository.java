package org.javaboy.user.dao;

import org.javaboy.user.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by mavlarn on 2018/1/20.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);

    @Modifying
    @Query("UPDATE customer SET deposit = deposit - ?2 WHERE id = ?1")
    int charge(Long customerId, int amount);
}
