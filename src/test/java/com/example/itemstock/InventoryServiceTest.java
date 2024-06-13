package com.example.itemstock;

import entity.Inventory;
import entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import service.InventoryService;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceTest {
    @Autowired
    private InventoryService inventoryService;

    @Test
    public void testGetAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories(PageRequest.of(0, 10));
        assertNotNull(inventories);
    }

    @Test
    public void testGetInventory() {
        Inventory inventory = inventoryService.getInventory(1L);
        assertNotNull(inventory);
    }

    @Test
    public void testSaveInventory() {
        Inventory inventory = new Inventory(new Item(1L), 10, "T");
        Inventory savedInventory = inventoryService.saveInventory(inventory);
        assertNotNull(savedInventory);
    }

    @Test
    public void testEditInventory() {
        Inventory inventory = inventoryService.getInventory(1L);
        inventory.setQty(20);
        Inventory updatedInventory = inventoryService.editInventory(1L, inventory);
        assertNotNull(updatedInventory);
    }

    @Test
    public void testDeleteInventory() {
        inventoryService.deleteInventory(1L);
        assertNull(inventoryService.getInventory(1L));
    }
}