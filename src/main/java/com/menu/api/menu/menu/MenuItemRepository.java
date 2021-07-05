package com.menu.api.menu.menu;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

    @Query("FROM MenuItem mi where mi.menu=:menu GROUP BY mi.section, mi.id")
    List<MenuItem> findAllByMenuId(@Param("menu")Menu menu_id);

    @Query("FROM MenuItem mi where mi.menu=:menu AND DELETED = false GROUP BY mi.section, mi.id")
    List<MenuItem> findAllByMenuIdForUser(@Param("menu")Menu menu_id);
}
