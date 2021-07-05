package com.menu.api.menu.store;

import com.menu.api.menu.authentication.AuthService;
import com.menu.api.menu.menu.Menu;
import com.menu.api.menu.menu.MenuItem;
import com.menu.api.menu.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;
    private final MenuService menuService;
    private final AuthService authService;

    public StoreController(@Autowired StoreService storeService,
                           @Autowired MenuService menuService,
                           @Autowired AuthService authService) {
        this.storeService = storeService;
        this.menuService = menuService;
        this.authService = authService;
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        return new ResponseEntity<>(storeService.getStoreById(id), HttpStatus.OK);
    }

    @GetMapping("/store/menu/{id}")
    public ResponseEntity<List<MenuItem>> getStoreMenuById(@PathVariable Long id) {
        return new ResponseEntity<>(menuService.getMenuByStoreId(id, authService.isAdmin()), HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<Store> addStore(@RequestParam String name) {
        return new ResponseEntity<>(storeService.addStore(name), HttpStatus.OK);
    }

    @PostMapping("/store/menu")
    public ResponseEntity<Menu> addMenu(@RequestParam Long store_id, @RequestParam String menuName) {
        return new ResponseEntity<>(menuService.addMenu(store_id, menuName), HttpStatus.OK);
    }

    @PostMapping("/store/menu/menuitem")
    public ResponseEntity<MenuItem> addMenuitem(@RequestParam Long menu_id,
                                            @RequestParam String description,
                                            @RequestParam String section,
                                            @RequestParam Double price) {
        return new ResponseEntity<>(menuService.addMenuItem(menu_id, description, section, price), HttpStatus.OK);
    }
}
