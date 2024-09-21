package org.example.orderservice.web;

import org.example.orderservice.entities.Item;
import org.example.orderservice.entities.Order;
import org.example.orderservice.service.ItemService;
import org.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService; // Assuming you have an OrderService

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        Order order = orderService.getOrderById(item.getOrder().getOrderId());
        item.setOrder(order); // Associate the order
        return itemService.createItem(item);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable int id) {
        return itemService.getItemById(id);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody Item itemDetails) {
        Order order = orderService.getOrderById(itemDetails.getOrder().getOrderId());
        itemDetails.setOrder(order);
        return itemService.updateItem(id, itemDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
    }
}