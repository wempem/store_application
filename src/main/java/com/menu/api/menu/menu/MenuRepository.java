package com.menu.api.menu.menu;

import com.menu.api.menu.menu.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

    Menu findByStoreId(long store_id);
}
