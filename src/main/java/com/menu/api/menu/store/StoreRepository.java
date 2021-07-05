package com.menu.api.menu.store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {

    Store findByName(String name);

    Optional<Store> findById(Long id);
}
