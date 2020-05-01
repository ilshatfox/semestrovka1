package com.example.demo.service;

import com.example.demo.dto.CoronaTestOrderDto;
import com.example.demo.models.Order;
import com.example.demo.models.OrderType;
import com.example.demo.repositories.OrdersRepository;
import com.example.demo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoronaTestServiceImpl implements CoronaTestService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    ConversionService conversionService;


    public List<Order> getMyOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
        return ordersRepository.findOrdersByUser(details.getUser());
    }

    public Status editMyOrderPost(CoronaTestOrderDto coronaTestOrderDto, String orderId) {
        Order order = conversionService.convert(orderId, Order.class);
        if (order != null) {
            order.setPhone(coronaTestOrderDto.getPhone());
            order.setOrderAmount(coronaTestOrderDto.getAmount());
            ordersRepository.save(order);
            return new Status(true, "Успешно изменил!");
        }
        return new Status(false, "Не получилось изменить!");
    }

    public Status buyCoronaTest(String orderId) {
        Order order = conversionService.convert(orderId, Order.class);
        if (order != null) {
            ordersRepository.delete(order);
            return new Status(true, "Успешно удалил!");
        }
        return new Status(false, "Не получилось удалить! Не нашел такого заказа!");
    }

    public void buyCoronaTest(CoronaTestOrderDto form) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
        System.out.println(details.getUser());
        Order order = Order.builder()
                .orderType(OrderType.CORONA_TEST)
                .orderAmount(form.getAmount())
                .phone(form.getPhone())
                .user(details.getUser())
                .isActive(true)
                .build();
        ordersRepository.save(order);
    }
}
