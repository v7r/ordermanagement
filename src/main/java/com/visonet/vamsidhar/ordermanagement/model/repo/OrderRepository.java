package com.visonet.vamsidhar.ordermanagement.model.repo;

import com.visonet.vamsidhar.ordermanagement.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"lineItems"})
    List<Order> findAllByActive(boolean active);

    @Query(value= """
            select o
              from Order o
              left join LineItem i on o = i.order
             where o.active = ?1
            """)
    Page<Order> queryAllActiveOrders(@Param("activeClause")  Boolean activeClause, Pageable pageable);

    @Query("SELECT o FROM Order o JOIN FETCH o.lineItems")
    List<Order> findAllOrdersWithLineItems();
}
