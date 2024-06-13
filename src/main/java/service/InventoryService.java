package service;

import entity.Inventory;
import entity.Item;
import exception.InsufficientStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.InventoryRepository;
import repository.ItemRepository;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ItemRepository itemRepository;

    public List<Inventory> getAllInventories(Pageable pageable) {
        return (List<Inventory>) inventoryRepository.findAll(pageable);
    }

    public Inventory getInventory(Long id) {
        return inventoryRepository.findById(id).orElseThrow();
    }

    public Inventory saveInventory(Inventory inventory) {
        Item item = itemRepository.findById(inventory.getItem().getId()).orElseThrow();
        if (inventory.getType().equals("T")) {
            item.setStock(item.getStock() + inventory.getQty());
        } else if (inventory.getType().equals("W")) {
            if (item.getStock() < inventory.getQty()) {
                throw new InsufficientStockException();
            }
            item.setStock(item.getStock() - inventory.getQty());
        }
        itemRepository.save(item);
        return inventoryRepository.save(inventory);
    }

    public Inventory editInventory(Long id, Inventory inventory) {
        Inventory existingInventory = getInventory(id);
        existingInventory.setQty(inventory.getQty());
        existingInventory.setType(inventory.getType());
        return saveInventory(existingInventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}