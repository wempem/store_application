package com.menu.api.menu.menu;

import com.menu.api.menu.authentication.AuthService;
import com.menu.api.menu.store.Store;
import com.menu.api.menu.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;
    private final StoreRepository storeRepository;
    private final AuthService authService;

    public MenuService(@Autowired MenuRepository menuRepository,
                       @Autowired MenuItemRepository menuItemRepository,
                       @Autowired StoreRepository storeRepository,
                       @Autowired AuthService authService) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
        this.storeRepository = storeRepository;
        this.authService = authService;
    }

    public List<MenuItem> getMenuByStoreId(Long store_id, boolean isAdmin) {
        Menu menu = menuRepository.findByStoreId(store_id);
        return isAdmin ? menuItemRepository.findAllByMenuId(menu) : menuItemRepository.findAllByMenuIdForUser(menu);
    }

    public Menu addMenu(long store_id, String menuName) {
        Store store = storeRepository.findById(store_id).orElse(null);

        if(store == null) {
            throw new InvalidParameterException("The provided store does not exist.");
        }

        Menu menu = new Menu(menuName, store, LocalDateTime.now(), null, authService.getUser().getId(),null, false, null);

        menuRepository.save(menu);
        return menu;
    }

    public MenuItem addMenuItem(Long menu_id, String description, String section, Double price) {
        Menu menu = menuRepository.findById(menu_id).orElse(null);

        if(menu == null) {
            throw new InvalidParameterException("The provided menu does not exist.");
        }

        MenuItem menuItem = new MenuItem(menu, description, section, price, LocalDateTime.now(), null,
                authService.getUser().getId(),null, false);

        menuItemRepository.save(menuItem);
        return menuItem;
    }
}
