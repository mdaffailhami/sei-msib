package com.mdi.api.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mdi.api.models.Proyek;
import com.mdi.api.services.ProyekService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/proyek")
public class ProyekController {
    @Autowired
    private ProyekService proyekService;

    @GetMapping
    public ResponseEntity<List<Proyek>> getAllProyek() {
        return new ResponseEntity<List<Proyek>>(proyekService.getAllProyek(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Proyek> getProyekById(@PathVariable("id") int id) {
        return new ResponseEntity<Proyek>(proyekService.getProyekById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Proyek> saveProyek(@RequestBody String reqBody) {
        JSONObject json = new JSONObject(reqBody);
        Proyek proyek = Proyek.fromJson(json);

        return new ResponseEntity<Proyek>(proyekService.saveProyek(proyek), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable("id") int id, @RequestBody String reqBody) {
        JSONObject json = new JSONObject(reqBody);
        Proyek proyek = Proyek.fromJson(json);

        return new ResponseEntity<Proyek>(proyekService.updateProyek(id, proyek), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProyek(@PathVariable("id") int id) {
        proyekService.deleteProyek(id);
        return new ResponseEntity<String>("Delete proyek sukses", HttpStatus.OK);
    }
}
