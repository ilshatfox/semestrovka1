package com.example.demo.service;

import com.example.demo.dto.CoronaTestOrderDto;
import com.example.demo.models.Order;

import java.util.List;

public interface CoronaTestService {
    public List<Order> getMyOrders();
    public Status editMyOrderPost(CoronaTestOrderDto coronaTestOrderDto, String orderId);
    public Status buyCoronaTest(String orderId);
    public void buyCoronaTest(CoronaTestOrderDto form);
}
