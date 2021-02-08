package rs.ac.metropolitan.it355.pz.service;

import rs.ac.metropolitan.it355.pz.model.Item;

import java.util.List;

public interface ItemService {
    void saveOrUpdate(Item item);

    void saveItemList(List<Item> items);

    void deleteById(int itemID);

    List<Item> getAllItems();

    Item getItemById(int itemID);

    List<Item> getItemByProductType(int productTypeID);
}
