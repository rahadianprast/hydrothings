package com.psda.hydra.controller;


import com.psda.hydra.model.*;
import com.psda.hydra.payload.ApiResponse;
import com.psda.hydra.payload.ChannelDTO;
import com.psda.hydra.repository.*;
import com.psda.hydra.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelRepository channelrepository;

    @Autowired
    private DebitRepository debitRepository;

    @Autowired
    private RumusRepository rumusRepository;

    @Autowired
    private SungaiRepository sungaiRepository;

    @Autowired
    private KadasterRepository kadasterRepository;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private NodeRepository nodeRepository;


    @GetMapping("/feed")
    public ResponseEntity<?> resultNode(@RequestParam(required = true) String api_key,
                                         Double ch1, Double ch2, Double ch3, Double ch4, Double ch5,
                                              Double ch6, Double ch7, Double ch8, Double ch9, Double ch10){
        Kadaster kadaster = kadasterRepository.findByApiKey(api_key);

        Sungai sungai = sungaiRepository.findByKadaster(kadaster);
        Rumus rumus = rumusRepository.findTopBySungaiOrderByCreatedDesc(sungai);

        if (kadaster.getJenis().getType() == NamaJenis.PDA && sungai!=null && rumus !=null) {
            try {
                Double result = Math.pow(rumus.getKoefC() * (ch1 + rumus.getKoefA()), rumus.getKoefB());
                DecimalFormat df = new DecimalFormat("#.##");
                String hasil = df.format(result);
                Debit debit = new Debit(Double.parseDouble(hasil), sungai);

                debitRepository.save(debit);
            }catch (RuntimeException e){
                Debit debit = new Debit(0.0, sungai);

                debitRepository.save(debit);
            }

        }else {
            Debit debit = new Debit(0.0, sungai);
        }

        Long total = kadaster.getNodes().stream().count();
        if (api_key!= null) {

            List<Channel> channels = new ArrayList<>();
            if (ch1!=null && channels.size() < total){
                channels.add(new Channel(ch1, kadaster.getNodes().get(0)));
            }
            if (ch2!=null && channels.size() < total){
                channels.add(new Channel(ch2, kadaster.getNodes().get(1)));
            }
            if (ch3!=null && channels.size() < total){
                channels.add(new Channel(ch3, kadaster.getNodes().get(2)));
            }
            if (ch4!=null && channels.size() < total){
                channels.add(new Channel(ch4, kadaster.getNodes().get(3)));
            }
            if (ch5!=null && channels.size() < total){
                channels.add(new Channel(ch5, kadaster.getNodes().get(4)));
            }
            if (ch6!=null && channels.size() < total){
                channels.add(new Channel(ch6, kadaster.getNodes().get(5)));
            }
            if (ch7!=null && channels.size() < total){
                channels.add(new Channel(ch7, kadaster.getNodes().get(6)));
            }
            if (ch8!=null && channels.size() < total){
                channels.add(new Channel(ch8, kadaster.getNodes().get(7)));
            }
            if (ch9!=null && channels.size() < total){
                channels.add(new Channel(ch9, kadaster.getNodes().get(8)));
            }
            if (ch10!=null && channels.size() < total){
                channels.add(new Channel(ch10, kadaster.getNodes().get(9)));
            }
            List<Channel> result = channelrepository.saveAll(channels);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/node/{id}")
                    .buildAndExpand(result).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "Value Berhasil Disimpan"));
        }else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

    }



}
