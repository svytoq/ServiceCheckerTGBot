package ru.tinkoff.edu.java.scrapper.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class LinkUpdaterScheduler {

    private static final Logger logger = LogManager.getLogger();

    @Scheduled(fixedDelayString = "#{app.scheduler.interval}")
    public void update(){
        logger.info("viu-viu-viu");
        //доделать
    }
}
