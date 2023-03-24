package ru.tinkoff.edu.java.scrapper.scheduled;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;


public class LinkUpdaterScheduler {

    private static final Logger logger = LogManager.getLogger();

    @Scheduled(fixedDelayString = "#{app.scheduler.interval}")
    public void update(){
        logger.info("viu-viu-viu");
        //доделать
    }
}
