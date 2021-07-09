package com.menu.api.menu.store;

import com.menu.api.menu.authentication.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StoreService {
    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);

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
        Store store = new Store(name, LocalDateTime.now(),null, authService.getUser().getId(),null,false,null);

        try {
            storeRepository.save(store);
        } catch (Exception e) {
            logger.error("There was an issue while saving the store", e);
            throw e;
        }
        return store;
    }
}
