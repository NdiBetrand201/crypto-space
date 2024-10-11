package com.Trading_plaform.Trading_platform.implementations;

import com.Trading_plaform.Trading_platform.domain.OrderStatus;
import com.Trading_plaform.Trading_platform.domain.OrderType;
import com.Trading_plaform.Trading_platform.models.Coin;
import com.Trading_plaform.Trading_platform.models.Order;
import com.Trading_plaform.Trading_platform.models.OrderItem;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.repository.OrderRepository;
import com.Trading_plaform.Trading_platform.services.OrderService;
import com.Trading_plaform.Trading_platform.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WalletService walletService;

    @Override
    public Order createOrder(User user, OrderItem orderItem, OrderType orderType) {
        double price=orderItem.getCoin().getCurrentPrice()*orderItem.getQuantity();
        Order order=new Order();

        order.setUser(user);
        order.setOrderItem(orderItem);
        order.setOrderType(orderType);
        order.setPrice(BigDecimal.valueOf(price));
        order.setTimeStamp(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);


        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) throws Exception {

        return Optional.ofNullable(orderRepository.findById(orderId).orElseThrow(() -> new Exception(" order not found")));
    }

    @Override
    public List<Order> getALLOrdersUser(Long userId, OrderType orderType, String assetSymbol) {
        return (List<Order>) orderRepository.findByUserId(userId);
    }


    

    @Override
    public Order processOrder(Coin coin, double quantity, OrderType orderType, User user) {
        return null;
    }
}
