package com.psda.hydra.controller;

import com.psda.hydra.exception.ResourceNotFoundException;
import com.psda.hydra.model.*;
import com.psda.hydra.payload.ApiResponse;
import com.psda.hydra.payload.NodeDTO;
import com.psda.hydra.repository.ChannelRepository;
import com.psda.hydra.repository.KadasterRepository;
import com.psda.hydra.repository.NodeRepository;
import com.psda.hydra.repository.SungaiRepository;
import com.psda.hydra.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/kadaster")
public class KadasterController {

    @Autowired
    private KadasterRepository kadasterRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private SungaiRepository sungaiRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ChannelService channelService;

    @GetMapping
    public List<Kadaster> getAllKadaster(){
        return kadasterRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kadaster> getKadaster(@PathVariable Long id) {
        return new ResponseEntity(this.kadasterRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createKadaster(@Valid @RequestBody Kadaster kadaster){
        Kadaster result =  kadasterRepository.save(kadaster);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/kadaster/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Kadaster Berhasil Disimpan"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateKadaster(@PathVariable Long id, @Valid @RequestBody Kadaster request){
        return kadasterRepository.findById(id).map(kadaster -> {
            kadaster.setNamaPos(request.getNamaPos());
            kadaster.setReg(request.getReg());
            kadaster.setLokasi(request.getLokasi());
            kadaster.setElevasi(request.getElevasi());

            Kadaster result = kadasterRepository.save(kadaster);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/kadaster/{id}")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "Kadaster Berhasil Disimpan"));
        }).orElseThrow(()-> new ResourceNotFoundException("kadaster not found id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKadaster(@PathVariable Long id){
        return kadasterRepository.findById(id).map(kadaster -> {
            kadasterRepository.delete(kadaster);
            return ResponseEntity.ok().body(new ApiResponse(true, "Kadaster Berahasil dihapus"));
        }).orElseThrow(()-> new ResourceNotFoundException("Kadaster Not Found" + id));
    }

    //feed?id=2&lat=-6.532393&lng=108.117418&elevasi=33.60
    @GetMapping("/feed")
    public ResponseEntity<?> inputGps(@RequestParam(required = true) Long id, Double lat, Double lng, Double elevasi){
      return kadasterRepository.findById(id).map(kadaster -> {
          kadaster.setLat(lat);
          kadaster.setLng(lng);
          kadaster.setElevasi(elevasi);
          Kadaster result = kadasterRepository.save(kadaster);
          URI location = ServletUriComponentsBuilder
                  .fromCurrentContextPath().path("/kadaster/{id}")
                  .buildAndExpand(result.getId()).toUri();
          return ResponseEntity.created(location).body(new ApiResponse(true, "Feed Berhasil Disimpan"));
      }).orElseThrow(()->new ResourceNotFoundException("Kadaster not Found" + id));

    }

    @PostMapping("/{kadasterId}/nodes")
    public ResponseEntity<?> createNode(@PathVariable Long kadasterId, @Valid @RequestBody NodeDTO nodeRequest ){
        return kadasterRepository.findById(kadasterId).map(kadaster -> {

             List<Node> nodes = new ArrayList<>();
             if (nodeRequest.getCh1()!=null){
                 nodes.add(new Node(nodeRequest.getCh1(), kadaster));
             }
            if (nodeRequest.getCh2()!=null){
                nodes.add(new Node(nodeRequest.getCh2(), kadaster));
            }
            if (nodeRequest.getCh3()!=null){
                nodes.add(new Node(nodeRequest.getCh3(), kadaster));
            }
            if (nodeRequest.getCh4()!=null){
                nodes.add(new Node(nodeRequest.getCh4(), kadaster));
            }
            if (nodeRequest.getCh5()!=null){
                nodes.add(new Node(nodeRequest.getCh5(), kadaster));
            }
            if (nodeRequest.getCh6()!=null){
                nodes.add(new Node(nodeRequest.getCh6(), kadaster));
            }
            if (nodeRequest.getCh7()!=null){
                nodes.add(new Node(nodeRequest.getCh7(), kadaster));
            }
            if (nodeRequest.getCh8()!=null){
                nodes.add(new Node(nodeRequest.getCh8(), kadaster));
            }
            if (nodeRequest.getCh9()!=null){
                nodes.add(new Node(nodeRequest.getCh9(), kadaster));
            }
            if (nodeRequest.getCh10()!=null){
                nodes.add(new Node(nodeRequest.getCh10(), kadaster));
            }
            List<Node> result = nodeRepository.saveAll(nodes);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/{kadasterId}/nodes")
                    .buildAndExpand(result).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "Node Berhasil Disimpan"));
        }).orElseThrow(()->new ResourceNotFoundException("Kadaster not Found" + kadasterId));
    }

    @GetMapping("/{kadasterId}/nodes")
    public ResponseEntity<Page<Node>> getNodes(@PathVariable Long kadasterId, Pageable pageable){
        return new ResponseEntity(nodeRepository.findByKadaster_Id(kadasterId, pageable), HttpStatus.OK);
    }


    @GetMapping("/{kadasterId}/nodes/{nodeId}/hours")
    public List<Channel> getAnHour(@PathVariable Long kadasterId, @PathVariable Long nodeId){
        if (!this.kadasterRepository.existsById(kadasterId)){
            throw new ResourceNotFoundException("Kadaster Not Found with Id" + kadasterId);
        }
        return channelRepository.getByHours(nodeId);

    }

    @GetMapping("/{kadasterId}/nodes/{nodeId}/daily")
    public Collection<Channel> getDaily(@PathVariable Long kadasterId, @PathVariable Long nodeId) throws Exception{
        if (!this.kadasterRepository.existsById(kadasterId)){
            throw new ResourceNotFoundException("Kadaster Not Found with Id" + kadasterId);
        }
        return null;
    }


    @PutMapping("/{kadasterId}/nodes/{nodeId}")
    public ResponseEntity<?> updateNode(@PathVariable Long kadasterId, @PathVariable Long nodeId, @Valid @RequestBody Node noderequest){
        if (!this.kadasterRepository.existsById(kadasterId)){
            throw new ResourceNotFoundException("Kadaster Not Found with Id" + kadasterId);
        }
        return nodeRepository.findById(nodeId)
                .map(node -> {
                    node.setNamaNode(noderequest.getNamaNode());
                     nodeRepository.save(node);
                    return ResponseEntity.ok().body(new ApiResponse(true, "node berhasil diupdate"));
                }).orElseThrow(()-> new ResourceNotFoundException("node Not Found" + nodeId));
    }

    @DeleteMapping("/{kadasterId}/nodes/{nodeId}")
    public ResponseEntity<?> deleteNode(@PathVariable Long kadasterId, @PathVariable Long nodeId){
        if (!this.nodeRepository.existsById(kadasterId)){
            throw new ResourceNotFoundException("Node Not Found with Id" + kadasterId);
        }
        return nodeRepository.findById(nodeId)
                .map(node -> {
                    nodeRepository.delete(node);
                    return ResponseEntity.ok().body(new ApiResponse(true, "node berhasil dihapus"));
                }).orElseThrow(()-> new ResourceNotFoundException("node Not Found" + nodeId));
    }

    @GetMapping("/{kadasterId}/sungai")
    public Sungai getSungai(@PathVariable Long kadasterId){
        return sungaiRepository.findByKadaster_Id(kadasterId);
    }

    @PostMapping("/{kadasterId}/sungai")
    public ResponseEntity<?> addSungai(@PathVariable Long kadasterId, @Valid @RequestBody Sungai sungai){
        return this.kadasterRepository.findById(kadasterId).map(kadaster -> {
            sungai.setKadaster(kadaster);
            if (kadaster.getSungai()==null){
                Sungai result = sungaiRepository.save(sungai);
                URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/{kadasterId}/sungai")
                        .buildAndExpand(result.getId()).toUri();
                return ResponseEntity.created(location).body(new ApiResponse(true, "Sungai berhasil diinput"));
            }
            else {
                return ResponseEntity.badRequest().body(new ApiResponse(false, "Kadaster Sudah Memiliki Sungai"));
            }

        }).orElseThrow(()-> new ResourceNotFoundException("Kadaster Not Found" + kadasterId));
    }

    @PutMapping("/{kadasterId}/sungai/{sungaiId}")
    public ResponseEntity<?> updateSungai(@PathVariable Long kadasterId, @PathVariable Long sungaiId, @Valid @RequestBody Sungai sungaiRequest){
        if (!kadasterRepository.existsById(kadasterId)){
            throw new ResourceNotFoundException("kadaster not Found " + kadasterId);
        }
        return sungaiRepository.findById(sungaiId)
                .map(sungai -> {
                    sungai.setNamaSungai(sungaiRequest.getNamaSungai());
                    sungai.setKelas(sungaiRequest.getKelas());
                    Sungai result =  sungaiRepository.save(sungai);
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentContextPath().path("/{kadasterId}/sungai/{sungaiId}")
                            .buildAndExpand(result.getId()).toUri();
                    return ResponseEntity.created(location).body(new ApiResponse(true, "Sungai berhasil diupdate"));
                }).orElseThrow(()-> new ResourceNotFoundException("sungai Not Found" + sungaiId));
    }

    @DeleteMapping("/{kadasterId}/sungai/{sungaiId}")
    public ResponseEntity<?> deleteSungai(@PathVariable Long kadasterId, @PathVariable Long sungaiId ){
        if (!kadasterRepository.existsById(kadasterId)){
            throw new ResourceNotFoundException("Node not Found " + kadasterId);
        }
        return sungaiRepository.findById(sungaiId)
                .map(sungai -> {
                    sungaiRepository.delete(sungai);
                    return ResponseEntity.ok().body(new ApiResponse(true, "sungai berhasil didelete"));
                }).orElseThrow(()-> new ResourceNotFoundException("sungai Not Found" + sungaiId));
    }

}
