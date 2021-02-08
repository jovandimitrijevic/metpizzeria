package rs.ac.metropolitan.it355.pz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.metropolitan.it355.pz.model.Item;
import rs.ac.metropolitan.it355.pz.model.ProductType;
import rs.ac.metropolitan.it355.pz.model.User;
import rs.ac.metropolitan.it355.pz.service.ItemService;
import rs.ac.metropolitan.it355.pz.service.ProductTypeService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/")
public class ItemController {

    private final ItemService itemService;
    private final ProductTypeService productTypeService;
    RestTemplate restTemplate = new RestTemplate();
    private final HomepageController homepageController;

    private static String UPLOADED_FOLDER = "src//main//resources//static//img//";

    @Autowired
    public ItemController(ItemService itemService, ProductTypeService productTypeService, HomepageController homepageController) {
        this.itemService = itemService;
        this.productTypeService = productTypeService;
        this.homepageController = homepageController;
    }

    @GetMapping("/admin/addItemForm")
    public String addItem(Model model) {
        List<ProductType> productTypeList = productTypeService.findAll();
        Item item = new Item();

        model.addAttribute("item", item);
        model.addAttribute("productTypeList", productTypeList);

        return "admin/addItemForm";
    }

    @PostMapping("/admin/save")
    public String saveItem(@ModelAttribute("item") Item item, @RequestParam("file") MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            throw new Exception();
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String uriItems = "http://localhost:8080/api/items";
        item.setImgPath("/img/" + file.getOriginalFilename());
        restTemplate.postForEntity(uriItems, item, Item.class);
        return "redirect:/admin/addItemForm";
    }

    @GetMapping("/admin/itemsList")
    public String itemsList(Model model) {
        final String uriItems = "http://localhost:8080/api/items";
        List<Item> items = restTemplate.getForObject(uriItems, List.class);

        model.addAttribute("items", items);
        return "admin/itemsList";
    }

    @GetMapping("/itemsList")
    public String itemList(@RequestParam("id") int productTypeID, Model model) {
        final String uriItems = "http://localhost:8080/api/itemss";
        List<Item> items = restTemplate.getForObject(uriItems + "/" + productTypeID, List.class);

        if (homepageController.isUserLoggedIn()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            RestTemplate restTemplate = new RestTemplate();

            final String uriUser = "http://localhost:8080/api/user";
            User user = restTemplate.getForObject(uriUser + "/" + username, User.class);

            int userID = user.getId();

            final String uriItemsInShoppingCart = "http://localhost:8080/api/users/items";
            List<Item> itemsInShoppingCart = restTemplate.getForObject(uriItemsInShoppingCart + "/" + userID, List.class);

            int size = itemsInShoppingCart.size();
            model.addAttribute("itemsInShoppingCart", itemsInShoppingCart);
            model.addAttribute("size", size);
        }
        model.addAttribute("items", items);
        model.addAttribute("productTypeID", productTypeID);
        return "admin/itemsList";
    }

    @GetMapping("/admin/deleteItem")
    public String delete(@RequestParam("id") int itemID) {
        final String uriItem = "http://localhost:8080//api/admin/items";
        restTemplate.delete(uriItem + "/" + itemID);
        return "redirect:/admin/itemsList";
    }

    @GetMapping("/admin/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int itemID, Model model) {
        List<ProductType> productTypes = productTypeService.findAll();
        model.addAttribute("productTypes", productTypes);

        final String uriItem = "http://localhost:8080/api/items";
        Item item = restTemplate.getForObject(uriItem + "/" + itemID, Item.class);

        RestTemplate template = new RestTemplate();
        template.put(uriItem, item);

        model.addAttribute("item", item);
        return "admin/updateItemForm";
    }

    @PostMapping("/admin/update")
    public String updateItem(@ModelAttribute("item") Item item) {
        final String uriItem = "http://localhost:8080/api/items";
        restTemplate.postForEntity(uriItem, item, Item.class);
        return "redirect:/admin/itemsList";
    }

    @GetMapping("/item/showItem")
    public String showItem(@RequestParam("id") int itemID, Model model) {
        final String uriItem = "http://localhost:8080/api/items";
        Item item = restTemplate.getForObject(uriItem + "/" + itemID, Item.class);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        RestTemplate restTemplate = new RestTemplate();

        final String uriUser = "http://localhost:8080/api/user";
        User user = restTemplate.getForObject(uriUser + "/" + username, User.class);

        int userID = user.getId();

        model.addAttribute("item", item);

        final String uriItems = "http://localhost:8080/api/users/items";
        List<Item> items = restTemplate.getForObject(uriItems + "/" + userID, List.class);

        int size = items.size();
        model.addAttribute("items", items);
        model.addAttribute("size", size);

        return "showItem";
    }
}
