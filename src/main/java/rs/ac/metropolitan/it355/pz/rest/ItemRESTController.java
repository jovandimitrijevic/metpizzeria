package rs.ac.metropolitan.it355.pz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.it355.pz.model.Item;
import rs.ac.metropolitan.it355.pz.model.ProductType;
import rs.ac.metropolitan.it355.pz.service.ItemService;
import rs.ac.metropolitan.it355.pz.service.ProductTypeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemRESTController {

    private final ItemService itemService;
    private final ProductTypeService productTypeService;

    @Autowired
    public ItemRESTController(ItemService itemService, ProductTypeService productTypeService) {
        this.itemService = itemService;
        this.productTypeService = productTypeService;
    }

    @GetMapping("/items")
    public List<Item> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return items;
    }

    @PostMapping("/items")
    public Item saveItem(@RequestBody Item item) {
        itemService.saveOrUpdate(item);
        return item;
    }

    @PutMapping("/items")
    public Item updateItem(@RequestBody Item item) {
        itemService.saveOrUpdate(item);
        return item;
    }

    @DeleteMapping("/admin/items/{itemID}")
    public void delete(@PathVariable int itemID) {
        itemService.deleteById(itemID);
    }

    @GetMapping("/items/{itemID}")
    public Item getItemById(@PathVariable int itemID) {
        Item item = itemService.getItemById(itemID);
        return item;
    }

    @GetMapping("/itemss/{productTypeID}")
    public List<Item> getAllProductsByProductType(@PathVariable int productTypeID) {
        List<Item> items = itemService.getItemByProductType(productTypeID);
        return items;
    }
}
