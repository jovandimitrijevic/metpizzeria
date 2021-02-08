package rs.ac.metropolitan.it355.pz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355.pz.model.Item;
import rs.ac.metropolitan.it355.pz.repository.ItemRepository;
import rs.ac.metropolitan.it355.pz.service.ItemService;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void saveOrUpdate(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void saveItemList(List<Item> items) {
        itemRepository.saveAll(items);
    }

    @Override
    public void deleteById(int itemID) {
        itemRepository.deleteById(itemID);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(int itemID) {

        Optional<Item> itemResult = itemRepository.findById(itemID);

        Item item = null;
        if (itemResult.isPresent()) {
            item = itemResult.get();
        } else {
            throw new RuntimeException("There is no item with id: " + itemID);
        }
        return item;
    }

    @Override
    public List<Item> getItemByProductType(int productTypeID) {
        return itemRepository.findByProductType_Id(productTypeID);
    }
}
