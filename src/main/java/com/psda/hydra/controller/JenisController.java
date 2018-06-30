package com.psda.hydra.controller;

import com.psda.hydra.exception.ResourceNotFoundException;
import com.psda.hydra.model.Jenis;
import com.psda.hydra.model.NamaJenis;
import com.psda.hydra.model.User;
import com.psda.hydra.payload.ApiResponse;
import com.psda.hydra.payload.JenisRequest;
import com.psda.hydra.repository.JenisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/jenis")
public class JenisController {

    @Autowired
    private JenisRepository jenisRepository;

    @GetMapping
    public List<Jenis> getAllJenis(){
        return jenisRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jenis> getJenisById(@PathVariable Long id){
        return new ResponseEntity(this.jenisRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createJenis(@Valid @RequestBody Jenis jenisRequest){
        Jenis result= jenisRepository.save(jenisRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/kadaster/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Jenis Berhasil Disimpan"));
    }

    @GetMapping("/{type}")
    public Jenis getUserProfile(@PathVariable(value = "type") String type) {
        return jenisRepository.findByType(type)
                .orElseThrow(() -> new ResourceNotFoundException("Type " + type));

    }


}
