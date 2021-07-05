package com.menu.api.menu;

import com.menu.api.menu.databaseUtil.DatabasePopulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLine implements CommandLineRunner {

    @Autowired
    DatabasePopulateService databasePopulateService;

    @Override
    public void run(String... args) {
        databasePopulateService.execute();
    }
}
