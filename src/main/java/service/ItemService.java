package service;

import entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems(Pageable pageable) {
        return (List<Item>) itemRepository.findAll(pageable);
    }

    public Item getItem(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item editItem(Long id, Item item) {
        Item existingItem = getItem(id);
        existingItem.setName(item.getName());
        existingItem.setStock(item.getStock());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}