package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.author.username = :authorUsername")
    List<Order> findAllByAuthor(String authorUsername);
    @Query("select o from Order o where o.author.sector.company.id = :companyId")
    List<Order> findAllByCompany(Long companyId);
    @Query("select o from Order o where o.reviewer.username = :directorUsername")
    List<Order> findAllByDirector(String directorUsername);

}
