package org.example.orderservice;

import org.example.orderservice.entities.Item;
import org.example.orderservice.entities.Order;
import org.example.orderservice.service.ItemService;
import org.example.orderservice.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    CommandLineRunner insertMockData(OrderService orderService, ItemService itemService) {
        return args -> {
            // Creating Orders
            Order order1 = Order.builder()
                    .customerId(1) // Assuming the customer ID is 1
                    .totalAmount(300.0)
                    .build();

            Order order2 = Order.builder()
                    .customerId(2) // Assuming the customer ID is 2
                    .totalAmount(450.0)
                    .build();

            Order order3 = Order.builder()
                    .customerId(3) // Assuming the customer ID is 3
                    .totalAmount(600.0)
                    .build();

            // Saving Orders
            order1 = orderService.createOrder(order1);
            order2 = orderService.createOrder(order2);
            order3 = orderService.createOrder(order3);

            // Creating Items for Order 1
            Item item1 = Item.builder().itemName("Item 1-1").order(order1).build();
            Item item2 = Item.builder().itemName("Item 1-2").order(order1).build();
            Item item3 = Item.builder().itemName("Item 1-3").order(order1).build();

            // Creating Items for Order 2
            Item item4 = Item.builder().itemName("Item 2-1").order(order2).build();
            Item item5 = Item.builder().itemName("Item 2-2").order(order2).build();
            Item item6 = Item.builder().itemName("Item 2-3").order(order2).build();

            // Creating Items for Order 3
            Item item7 = Item.builder().itemName("Item 3-1").order(order3).build();
            Item item8 = Item.builder().itemName("Item 3-2").order(order3).build();
            Item item9 = Item.builder().itemName("Item 3-3").order(order3).build();

            // Saving Items
            itemService.createItem(item1);
            itemService.createItem(item2);
            itemService.createItem(item3);
            itemService.createItem(item4);
            itemService.createItem(item5);
            itemService.createItem(item6);
            itemService.createItem(item7);
            itemService.createItem(item8);
            itemService.createItem(item9);

            System.out.println("Mock orders and items inserted!");
        };
    }
}
