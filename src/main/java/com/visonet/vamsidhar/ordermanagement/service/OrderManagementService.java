package com.visonet.vamsidhar.ordermanagement.service;


import com.visonet.vamsidhar.ordermanagement.model.ValidationException;
import com.visonet.vamsidhar.ordermanagement.model.entity.LineItem;
import com.visonet.vamsidhar.ordermanagement.model.entity.Order;
import com.visonet.vamsidhar.ordermanagement.model.repo.LineItemRepository;
import com.visonet.vamsidhar.ordermanagement.model.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderManagementService {

    private OrderRepository orderRepository;
    private LineItemRepository lineItemRepository;

    /**
     *
     * @param order
     * @return
     */
    public com.visonet.vamsidhar.ordermanagement.model.dto.Order createNewOrder(
            com.visonet.vamsidhar.ordermanagement.model.dto.Order order) {
        Order newOrder = new Order();
        newOrder.setOrderNumber(order.getOrderNumber());
        if (order.getLineItems().isEmpty()) {
            throw new ValidationException("Order has no line items, orderNumber: "+order.getOrderNumber(),"");
        }
        final Order createdOrder = orderRepository.save(newOrder);
        List<LineItem> lineItems = order.getLineItems().stream().map(lineItem -> {
            LineItem i = new LineItem();
            BeanUtils.copyProperties(lineItem, i,
                    new String[]{"id", "active", "createdDate","modifiedDate","order"});
            i.setOrder(createdOrder);
            return i;
        }).collect(Collectors.toList());
        lineItemRepository.saveAll(lineItems);
        return orderRepository.findById(createdOrder.getId()).get().toDto();
    }

    public List<com.visonet.vamsidhar.ordermanagement.model.dto.Order> listAllOrders() {
        return orderRepository.findAllByActive(true).stream().map(o -> o.toDto()).toList();
    }

    /**
     *
     * @param orders
     * @return
     */
    public List<com.visonet.vamsidhar.ordermanagement.model.dto.Order> createBatch(
            List<com.visonet.vamsidhar.ordermanagement.model.dto.Order> orders) {
        if (orders == null || orders.isEmpty()) {
            throw new ValidationException("Provide a batch of Orders to create","");
        }
        return orders.stream().sorted((o1, o2) -> {
            String on1 = o1.getOrderNumber().isEmpty() ? "" : o1.getOrderNumber().substring(0, Math.min(2, o1.getOrderNumber().length()));
            String on2 = o2.getOrderNumber().isEmpty() ? "" : o2.getOrderNumber().substring(0, Math.min(2, o2.getOrderNumber().length()));
            return on1.compareTo(on2);
        }).map(o -> {
            return createNewOrder(o);
        }).toList();
    }
}
