package com.example.itemstock;

import entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import service.ItemService;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Test
    public void testGetAllItems() {
        List<Item> items = itemService.getAllItems(PageRequest.of(0, 10));
        assertNotNull(items);
    }

    @Test
    public void testGetItem() {
        Item item = itemService.getItem(1L);
        assertNotNull(item);
    }

    @Test
    public void testSaveItem() {
        Item item = new Item("Test Item", 10);
        Item savedItem = itemService.saveItem(item);
        assertNotNull(savedItem);
    }

    @Test
    public void testEditItem() {
        Item item = itemService.getItem(1L);
        item.setName("Updated Item");
        Item updatedItem = itemService.editItem(1L, item);
        assertNotNull(updatedItem);
    }

    @Test
    public void testDeleteItem() {
        itemService.deleteItem(1L);
        assertNull(itemService.getItem(1L));
    }
}