package com.menu.api.menu;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.menu.api.menu.authentication.AuthService;
import com.menu.api.menu.menu.Menu;
import com.menu.api.menu.menu.MenuItem;
import com.menu.api.menu.menu.MenuRepository;
import com.menu.api.menu.menu.MenuService;
import com.menu.api.menu.store.Store;
import com.menu.api.menu.store.StoreRepository;
import com.menu.api.menu.store.StoreService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StoreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    StoreRepository storeRepository;

    @MockBean
    MenuRepository menuRepository;

    @MockBean
    StoreService storeService;

    @MockBean
    MenuService menuService;

    @MockBean
    AuthService authService;

    // Basil's Items
    Set<MenuItem> basilsItems = new HashSet<>(Arrays.asList(new MenuItem(null, "Sumatra Pour Over",
            "Breakfast", 4.99, LocalDateTime.now(), null,0L,null,false),
            new MenuItem(null, "Coasta Rican Drip", "Breakfast", 4.99, LocalDateTime.now(),
                    null,0L,null,false),
            new MenuItem(null, "Kona Cold Press", "Lunch", 7.99, LocalDateTime.now(),
                    null,0L,null,false),
            new MenuItem(null, "Rwanda Siphon", "Lunch", 6.50, LocalDateTime.now(),
                    null,0L,null,true)));

    Set<MenuItem> rockItems = new HashSet<>(Arrays.asList(new MenuItem(null, "Beef and Egg Jerky",
                    "Breakfast", 1.99, LocalDateTime.now(), null,0L,null,false),
            new MenuItem(null, "Sausage and Egg Jerky", "Breakfast", 1.99, LocalDateTime.now(),
                    null,0L,null,false),
            new MenuItem(null, "Beef and Protein Shake Jerky", "Lunch", 2.49, LocalDateTime.now(),
                    null,0L,null,false),
            new MenuItem(null, "Spicy Beef Jerky", "Lunch", 2.49, LocalDateTime.now(),
                    null,0L,null,false)));

    Menu basilsMenu = new Menu("Basil's Beans Menu", null, LocalDateTime.now(),null,0L,null,false, basilsItems);
    Menu theRocksMenu = new Menu("The Rock's Menu", null, LocalDateTime.now(),null,0L,null,false, rockItems);

    Store basils = new Store("Basil's Beans", LocalDateTime.now(), null, 0L, null, false, basilsMenu);
    Store theRocks = new Store("Dwayne 'The Rock' Johnson's Jacked Jerky", LocalDateTime.now(), null, 0L, null, false, theRocksMenu);

    @Test
    public void getStoreMenuUser() throws Exception {
        List<Store> stores = new ArrayList<>(Arrays.asList(basils, theRocks));
        List<MenuItem> items = new ArrayList<>(stores.get(0).getMenu().getMenuItems());
        items = items.stream().filter(menuItem -> !menuItem.getDeleted()).collect(Collectors.toList());
        Mockito.when(authService.isAdmin()).thenReturn(false);
        Mockito.when(menuService.getMenuByStoreId(1L, false)).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/store/menu/1")
                .contentType(MediaType.APPLICATION_JSON).with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void getStoreMenuAdmin() throws Exception {
        List<Store> stores = new ArrayList<>(Arrays.asList(basils, theRocks));
        List<MenuItem> items = new ArrayList<>(stores.get(1).getMenu().getMenuItems());
        Mockito.when(authService.isAdmin()).thenReturn(true);
        Mockito.when(menuService.getMenuByStoreId(1L, true)).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/store/menu/1")
                .contentType(MediaType.APPLICATION_JSON).with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)));
    }
}
