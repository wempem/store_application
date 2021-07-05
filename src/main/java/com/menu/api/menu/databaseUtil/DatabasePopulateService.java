package com.menu.api.menu.databaseUtil;

import com.menu.api.menu.menu.Menu;
import com.menu.api.menu.menu.MenuItem;
import com.menu.api.menu.menu.MenuItemRepository;
import com.menu.api.menu.menu.MenuRepository;
import com.menu.api.menu.store.Store;
import com.menu.api.menu.store.StoreRepository;
import com.menu.api.menu.user.User;
import com.menu.api.menu.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DatabasePopulateService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    UserRepository userRepository;

    public void execute() {
        populateStoreAndMenuRepos();
        populateUserRepo();
    }

    private void populateStoreAndMenuRepos() {
        Store basils = new Store("Basil's Beans", LocalDateTime.now(), null, 0L, null, false, null);
        Store theRocks = new Store("Dwayne 'The Rock' Johnson's Jacked Jerky", LocalDateTime.now(), null, 0L, null, false, null);
        Store devitos = new Store("Danny Devito's Rum Ham", LocalDateTime.now(), null, 0L, null, false, null);
        Store franks = new Store("Uncle Franks Food and Drinkz", LocalDateTime.now(), null, 0L, null, false, null);

        storeRepository.save(basils);
        storeRepository.save(theRocks);
        storeRepository.save(devitos);
        storeRepository.save(franks);

        Menu basilsMenu = new Menu("Basil's Beans Menu", basils, LocalDateTime.now(),null,0L,null,false,null);
        Menu theRocksMenu = new Menu("The Rock's Menu", theRocks, LocalDateTime.now(),null,0L,null,false,null);
        Menu devitosMenu = new Menu("Rum Ham", devitos, LocalDateTime.now(),null,0L,null,false,null);
        Menu franksMenu = new Menu("Food and Drinkz Menu", franks, LocalDateTime.now(),null,0L,null,false,null);

        menuRepository.save(basilsMenu);
        menuRepository.save(theRocksMenu);
        menuRepository.save(devitosMenu);
        menuRepository.save(franksMenu);

        // Basil's Items
        menuItemRepository.save(new MenuItem(basilsMenu, "Sumatra Pour Over", "Breakfast", 4.99, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(basilsMenu, "Coasta Rican Drip", "Breakfast", 2.99, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(basilsMenu, "Ethiopian Pour Over", "Breakfast", 4.50, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(basilsMenu, "Bagel w Basil", "Breakfast", 5.99, LocalDateTime.now(), null,0L,null,true));

        menuItemRepository.save(new MenuItem(basilsMenu, "Kona Cold Press", "Lunch", 7.99, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(basilsMenu, "Rwanda Siphon", "Lunch", 6.50, LocalDateTime.now(), null,0L,null,false));

        // Rock's items

        menuItemRepository.save(new MenuItem(theRocksMenu, "Beef and Egg Jerky", "Breakfast", 1.99, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(theRocksMenu, "Sausage and Egg Jerky", "Breakfast", 1.99, LocalDateTime.now(), null,0L,null,false));

        menuItemRepository.save(new MenuItem(theRocksMenu, "Beef and Protein Shake Jerky", "Lunch", 2.49, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(theRocksMenu, "Spicy Beef Jerky", "Lunch", 2.49, LocalDateTime.now(), null,0L,null,false));

        menuItemRepository.save(new MenuItem(theRocksMenu, "Extra Spicy Beef Jerky", "Dinner", 5.99, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(theRocksMenu, "Slightly Warm Jerky", "Dinner", 5.99, LocalDateTime.now(), null,0L,null,true));


        // Devito's items
        menuItemRepository.save(new MenuItem(devitosMenu, "Rum Ham", "Dinner", 79.99, LocalDateTime.now(), null,0L,null,false));

        // Frank's items

        menuItemRepository.save(new MenuItem(franksMenu, "Watery Coffee", "Breakfast", 0.99, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(franksMenu, "Cold Eggs", "Breakfast", 1.99, LocalDateTime.now(), null,0L,null,true));
        menuItemRepository.save(new MenuItem(franksMenu, "Half cooked Pork", "Breakfast", 2.59, LocalDateTime.now(), null,0L,null,false));

        menuItemRepository.save(new MenuItem(franksMenu, "Warm Water (Ice Machine is broken)", "Lunch", 1.99, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(franksMenu, "Wilted Salad", "Lunch", 4.99, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(franksMenu, "Half Eaten Sandwich", "Lunch", 6.99, LocalDateTime.now(), null,0L,null,false));

        menuItemRepository.save(new MenuItem(franksMenu, "Old Fashioned without the Whiskey", "Dinner", 5.39, LocalDateTime.now(), null,0L,null,false));
        menuItemRepository.save(new MenuItem(franksMenu, "Eggs from breakfast", "Dinner", 1.25, LocalDateTime.now(), null,0L,null,true));
        menuItemRepository.save(new MenuItem(franksMenu, "Leathery Steak", "Dinner", 19.99, LocalDateTime.now(), null,0L,null,false));

    }

    private void populateUserRepo() {
        userRepository.save(new User("user",false, LocalDateTime.now(),null,false));
        userRepository.save(new User("admin",true, LocalDateTime.now(),null,false));
    }
}
