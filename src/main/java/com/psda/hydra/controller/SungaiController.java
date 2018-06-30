package com.psda.hydra.controller;


import com.psda.hydra.exception.ResourceNotFoundException;
import com.psda.hydra.model.Level;
import com.psda.hydra.model.Rumus;
import com.psda.hydra.model.Sungai;
import com.psda.hydra.model.Sws;
import com.psda.hydra.payload.ApiResponse;
import com.psda.hydra.repository.LevelRepository;
import com.psda.hydra.repository.RumusRepository;
import com.psda.hydra.repository.SungaiRepository;
import com.psda.hydra.repository.SwsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/sungai")
public class SungaiController {

    @Autowired
    private SungaiRepository sungaiRepository;
    @Autowired
    private RumusRepository rumusRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private SwsRepository swsRepository;

    @GetMapping
    public Page<Sungai> getAllSungai(Pageable pageable){
        return sungaiRepository.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sungai> getSungai(@PathVariable Long id) {
        Optional<Sungai> sungai = this.sungaiRepository.findById(id);

        if (sungai!= null) {
            return new ResponseEntity(sungaiRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    // POST GROUP

    @PostMapping("/{id}/rumus")
    public ResponseEntity<?> addRumus(@PathVariable Long id, @Valid @RequestBody Rumus rumus){
        return this.sungaiRepository.findById(id).map(sungai -> {
            rumus.setSungai(sungai);
            Rumus result = rumusRepository.save(rumus);
            URI location = ServletUriComponentsBuilder
                            .fromCurrentContextPath().path("/rumus/{rumusId}")
                            .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "Rumus Berhasil di input"));
        }).orElseThrow(()-> new ResourceNotFoundException(id + "id Sungai not Found" ));
    }

    @PostMapping("/{id}/level")
    public ResponseEntity<?> addLevel(@PathVariable Long id, @Valid @RequestBody Level level){
        return this.sungaiRepository.findById(id).map(sungai -> {
           level.setSungai(sungai);
           Level result = levelRepository.save(level);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/level/{levelId}")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "Level Berhasil di input"));
        }).orElseThrow(()-> new ResourceNotFoundException(id + "id Sungai not Found"));
    }
    @PostMapping("/{id}/sws")
    public ResponseEntity<?> addSws(@PathVariable Long id, @Valid @RequestBody Sws sws){
        return this.sungaiRepository.findById(id).map(sungai -> {
            sws.setSungai(sungai);
            Sws result =  swsRepository.save(sws);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/sws/{id}")
                    .buildAndExpand(result.getId()).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "Sws Berhasil di input"));
        }).orElseThrow(()-> new ResourceNotFoundException("sungai Not Found" + id));
    }

    // PUT GROUP
    @PutMapping("/{sungaiId}/rumus/{rumusId}")
    public ResponseEntity<?> updateRumus(@PathVariable Long sungaiId, @PathVariable Long rumusId, @Valid @RequestBody Rumus request){
        if (!sungaiRepository.existsById(sungaiId)){
            throw new ResourceNotFoundException("sungai Not Found" + sungaiId);
        }
        return rumusRepository.findById(rumusId)
                .map(rumus -> {
                    rumus.setKoefA(request.getKoefA());
                    rumus.setKoefB(request.getKoefB());
                    rumus.setKoefC(request.getKoefC());
                    Rumus result = rumusRepository.save(rumus);
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentContextPath().path("/rumus/{rumusId}")
                            .buildAndExpand(result.getId()).toUri();
                    return ResponseEntity.created(location).body(new ApiResponse(true, "Rumus Berhasil di update"));
                }).orElseThrow(()->new ResourceNotFoundException("Rumus not found with id" + rumusId));
    }

    @PutMapping("/{sungaiId}/level/{levelId}")
    public ResponseEntity<?> updateLevel(@PathVariable Long sungaiId, @PathVariable Long levelId, @Valid @RequestBody Level request){
        if (!sungaiRepository.existsById(sungaiId)){
            throw new ResourceNotFoundException("sungai Not Found" + sungaiId);
        }
        return levelRepository.findById(levelId)
                .map(level -> {
                    level.setSiap(request.getSiap());
                    level.setSiaga(request.getSiaga());
                    level.setAwas(request.getAwas());
                    level.setMax(request.getMax());
                    Level result =  levelRepository.save(level);
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentContextPath().path("/level/{levelId}")
                            .buildAndExpand(result.getId()).toUri();
                    return ResponseEntity.created(location).body(new ApiResponse(true, "Level Berhasil di update"));
                }).orElseThrow(()->new ResourceNotFoundException("Level not found with id" + levelId));
    }

    @PutMapping("/{sungaiId}/sws/{swsId}")
    public ResponseEntity<?> updateSws(@PathVariable Long sungaiId, @PathVariable Long swsId, @Valid @RequestBody Sws request){
        if (!sungaiRepository.existsById(sungaiId)){
            throw new ResourceNotFoundException("sungai Not Found" + sungaiId);
        }
        return swsRepository.findById(swsId)
                .map(sws -> {
                    sws.setIndukSungai(request.getIndukSungai());
                    sws.setPanjangSungai(request.getPanjangSungai());
                    Sws result =  swsRepository.save(sws);
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentContextPath().path("/sws/{id}")
                            .buildAndExpand(result.getId()).toUri();
                    return ResponseEntity.created(location).body(new ApiResponse(true, "Sws Berhasil di update"));
                }).orElseThrow(()->new ResourceNotFoundException("Sws not found with id" + swsId));
    }

    // delete group
    @DeleteMapping("/{sungaiId}/rumus/{rumusId}")
    public ResponseEntity<?> deleteRumus(@PathVariable Long sungaiId, @PathVariable Long rumusId){
        if (!sungaiRepository.existsById(sungaiId)){
            throw new ResourceNotFoundException("sungai Not Found" + sungaiId);
        }
        return this.rumusRepository.findById(rumusId)
                .map(rumus -> {
                    this.rumusRepository.delete(rumus);
                    return ResponseEntity.ok().body(new ApiResponse(true, "Rumus Berhasil dihapus"));
                }).orElseThrow(()-> new ResourceNotFoundException("rumus not found with Id " + rumusId));
    }

    @DeleteMapping("/{sungaiId}/level/{levelId}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long sungaiId, @PathVariable Long levelId){
        if (!sungaiRepository.existsById(sungaiId)){
            throw new ResourceNotFoundException("sungai Not Found" + sungaiId);
        }
        return this.levelRepository.findById(levelId)
                .map(level -> {
                    this.levelRepository.delete(level);
                    return ResponseEntity.ok().body(new ApiResponse(true, "level berhasil dihapus"));
                }).orElseThrow(()-> new ResourceNotFoundException("level not found with Id " + levelId));
    }

    @DeleteMapping("/{sungaiId}/sws/{swsId}")
    public ResponseEntity<?> deleteSws(@PathVariable Long sungaiId, @PathVariable Long swsId){
        if (!sungaiRepository.existsById(sungaiId)){
            throw new ResourceNotFoundException("sungai Not Found" + sungaiId);
        }
        return this.swsRepository.findById(swsId)
                .map(sws -> {
                    this.swsRepository.delete(sws);
                    return ResponseEntity.ok().body(new ApiResponse(true, "Sws Berhasil Dihapus"));
                }).orElseThrow(()-> new ResourceNotFoundException("level not found with Id " + swsId));
    }

}
