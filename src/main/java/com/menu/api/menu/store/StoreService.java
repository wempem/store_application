package com.menu.api.menu.store;

import com.menu.api.menu.authentication.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final AuthService authService;

    public StoreService(@Autowired StoreRepository storeRepository, @Autowired AuthService authService) {
        this.storeRepository = storeRepository;
        this.authService = authService;
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public Store addStore(String name) {
        Store store = new Store("Name", LocalDateTime.now(),null, authService.getUser().getId(),null,false,null);
        storeRepository.save(store);
        return store;
    }
}
