package com.menu.api.menu.menu;

import com.menu.api.menu.authentication.AuthService;
import com.menu.api.menu.store.Store;
import com.menu.api.menu.store.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

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

        if (store == null) {
            throw new InvalidParameterException("The provided store does not exist.");
        }

        if (store.getMenu() != null) {
            throw new InvalidParameterException("This store already has a menu.");
        }

        Menu menu = new Menu(menuName, store, LocalDateTime.now(), null, authService.getUser().getId(),null, false, null);
        store.setUpdated_at(LocalDateTime.now());
        store.setUpdated_by(authService.getUser().getId());

        try {
            menuRepository.save(menu);
        } catch (Exception e) {
            logger.error("The menu failed to save.", e);
            throw e;
        }

        try {
            storeRepository.save(store);
        } catch (Exception e) {
            logger.error("The store failed to update.", e);
            throw e;
        }

        return menu;
    }

    public MenuItem addMenuItem(Long store_id, String description, String section, Double price) {
        Menu menu = storeRepository.findById(store_id).orElse(null).getMenu();

        if(menu == null) {
            throw new InvalidParameterException("The provided menu does not exist.");
        }

        MenuItem menuItem = new MenuItem(menu, description, section, price, LocalDateTime.now(), null,
                authService.getUser().getId(),null, false);

        menu.setUpdated_at(LocalDateTime.now());
        menu.setUpdated_by(authService.getUser().getId());

        try {
            menuItemRepository.save(menuItem);
        } catch (Exception e) {
            logger.error("There was an issue while saving the menu item.", e);
            throw e;
        }

        try {
            menuRepository.save(menu);
        } catch (Exception e) {
            logger.error("There was an issue while updating the menu.", e);
            throw e;
        }

        return menuItem;
    }
}
