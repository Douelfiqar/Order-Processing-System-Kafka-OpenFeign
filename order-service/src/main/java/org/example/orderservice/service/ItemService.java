package org.example.orderservice.service;

import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.orderservice.entities.Item;
import org.example.orderservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemService{

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemById(int id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item updateItem(int id, Item itemDetails) {
        Item existingItem = getItemById(id);
        existingItem.setItemName(itemDetails.getItemName());
        existingItem.setOrder(itemDetails.getOrder());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(int id) {
        Item existingItem = getItemById(id);
        itemRepository.delete(existingItem);
    }
}