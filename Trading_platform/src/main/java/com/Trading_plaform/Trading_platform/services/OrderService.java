package com.Trading_plaform.Trading_platform.services;

import com.Trading_plaform.Trading_platform.domain.OrderType;
import com.Trading_plaform.Trading_platform.models.Coin;
import com.Trading_plaform.Trading_platform.models.Order;
import com.Trading_plaform.Trading_platform.models.OrderItem;
import com.Trading_plaform.Trading_platform.models.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(User user, OrderItem orderItem, OrderType orderType);

    Optional<Order> getOrderById(Long orderId) throws Exception;

    List<Order> getALLOrdersUser(Long userId,OrderType orderType,String assetSymbol);

    Order processOrder(Coin coin, double quantity,OrderType orderType,User user);

}
