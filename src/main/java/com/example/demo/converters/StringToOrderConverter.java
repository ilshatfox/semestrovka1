package com.example.demo.converters;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrdersRepository;
import com.example.demo.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sun.security.x509.Extension;

import java.text.ParseException;
import java.util.Optional;

@Component
public class StringToOrderConverter implements Converter<String, Order> {

    private Logger logger = (Logger) LoggerFactory.getLogger("converters.StringToOrderConverter");

    @Autowired
    private OrdersRepository repository;

    @Override
    public Order convert(String orderId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
            Optional<Order> optionalOrder = repository.findOrderByUserAndId(details.getUser(), Long.parseLong(orderId));
            if (optionalOrder.isPresent()) {
                return optionalOrder.get();
            }
        } catch (NumberFormatException ex) {
//            logger
            logger.info("При попытке преобразовать тип вышла ошибка!");
        }
        return null;
    }
}
