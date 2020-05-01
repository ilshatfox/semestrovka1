package com.example.demo.controllers;

import com.example.demo.dto.CoronaTestOrderDto;
import com.example.demo.models.Order;
import com.example.demo.models.OrderType;
import com.example.demo.models.User;
import com.example.demo.repositories.OrdersRepository;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.CoronaTestService;
import com.example.demo.service.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class CoronaTestController {
    @Autowired
    ConversionService conversionService;

    @Autowired
    private CoronaTestService coronaTestService;


    @GetMapping("/corona_test")
    public String coronaTest(Model model) {
        return "main/corona_test";
    }

    @GetMapping("/corona_test/success")
    public String coronaResult(Model model) {
        model.addAttribute("message", "Вы успешно оставили заявку! Мы скоро вам перезвоним!");
        return "result_page";
    }

    @GetMapping("/my_orders")
    public String getMyOrders(Model model) {
        List<Order> orders = coronaTestService.getMyOrders();
        model.addAttribute("orders", orders);
        return "main/my_orders";
    }

    @GetMapping("/my_orders/order/{order_id}")
    public String getMyOrder(Model model, @PathVariable("order_id") String orderId) {
        Order order = conversionService.convert(orderId, Order.class);
        model.addAttribute("order", order);
        return "main/my_order";
    }

    @GetMapping("/my_orders/edit/{order_id}")
    public String editMyOrder(Model model, @PathVariable("order_id") String orderId) {
        Order order = conversionService.convert(orderId, Order.class);
        model.addAttribute("order", order);
        return "main/edit_my_order";
    }

    @PostMapping("/my_orders/edit/{order_id}")
    public String editMyOrderPost(CoronaTestOrderDto coronaTestOrderDto, Model model, @PathVariable("order_id") String orderId) {
        Status st = coronaTestService.editMyOrderPost(coronaTestOrderDto, orderId);
        model.addAttribute("message", st.getMessage());
        return "result_page";
    }

    @PostMapping("/my_orders/delete_order/{order_id}")
    public String buyCoronaTest(Model model, @PathVariable("order_id") String orderId) {
        Status st = coronaTestService.buyCoronaTest(orderId);
        model.addAttribute("message", st.getMessage());
        return "result_page";
    }

    @PostMapping("/corona_test/buy_test")
    public String buyCoronaTest(CoronaTestOrderDto form) {
        coronaTestService.buyCoronaTest(form);
        return "redirect:/corona_test/success";
    }

}
