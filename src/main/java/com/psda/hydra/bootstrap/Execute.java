package com.psda.hydra.bootstrap;

import com.psda.hydra.repository.ChannelRepository;
import com.psda.hydra.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Execute implements CommandLineRunner{

    @Autowired
    ChannelService chanServ;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>");
    }
}
