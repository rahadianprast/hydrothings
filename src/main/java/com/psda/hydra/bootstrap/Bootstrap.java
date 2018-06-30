package com.psda.hydra.bootstrap;

import com.psda.hydra.model.Channel;
import com.psda.hydra.model.Kadaster;
import com.psda.hydra.model.Node;
import com.psda.hydra.repository.ChannelRepository;
import com.psda.hydra.repository.KadasterRepository;
import com.psda.hydra.repository.NodeRepository;
import com.psda.hydra.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class Bootstrap {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private NodeRepository nodeRepository;


    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );

        Random generator = new Random();
        Double ch1 = generator.nextDouble();
        Double ch2 = generator.nextDouble();
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("ch1 " +df.format(ch1));
        System.out.println("ch2 " +df.format(ch2));


        Optional<Node> node1 = nodeRepository.findById(1L);
        Optional<Node> node2 = nodeRepository.findById(2L);
        List<Channel> channels = new ArrayList<>();
        channels.add(new Channel(Double.parseDouble(df.format(ch1)), node1.get()));
        channels.add(new Channel(Double.parseDouble(df.format(ch1)), node2.get()));
        channelRepository.saveAll(channels);

    }



}
