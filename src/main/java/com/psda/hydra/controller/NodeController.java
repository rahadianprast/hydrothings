package com.psda.hydra.controller;

import com.psda.hydra.exception.ResourceNotFoundException;
import com.psda.hydra.model.Channel;
import com.psda.hydra.model.Node;


import com.psda.hydra.repository.ChannelRepository;
import com.psda.hydra.repository.NodeRepository;
import com.psda.hydra.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Node> getNodeId(@PathVariable Long id){
       Optional<Node> node = this.nodeRepository.findById(id);

        if (node != null) {
            return new ResponseEntity(nodeRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }


}
