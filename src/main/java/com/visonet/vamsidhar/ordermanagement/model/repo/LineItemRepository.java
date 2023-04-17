package com.visonet.vamsidhar.ordermanagement.model.repo;

import com.visonet.vamsidhar.ordermanagement.model.entity.LineItem;
import com.visonet.vamsidhar.ordermanagement.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository  extends JpaRepository<LineItem, Long> {
}
