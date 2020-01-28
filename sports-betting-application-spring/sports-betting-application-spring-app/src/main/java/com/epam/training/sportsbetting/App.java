package com.epam.training.sportsbetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.epam.training.sportsbetting.domain.user.Player;
import com.epam.training.sportsbetting.service.ApplicationService;
import com.epam.training.sportsbetting.ui.ApplicationController;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationController applicationController;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        applicationController.chooseLocale();
        Player player = applicationController.obtainPlayerData();
        applicationController.processBetting(player);
    }

}
