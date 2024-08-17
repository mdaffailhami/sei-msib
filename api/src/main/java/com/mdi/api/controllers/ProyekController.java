package com.mdi.api.controllers;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mdi.api.models.Proyek;
import com.mdi.api.services.ProyekService;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@RequestMapping("/api/proyek")
public class ProyekController {
    @Autowired
    private ProyekService proyekService;

    @GetMapping
    public ResponseEntity<String> getAllProyek() {
        List<Proyek> daftarProyek = proyekService.getAllProyek();
        JSONArray json = new JSONArray();

        for (Iterator<Proyek> iterator = daftarProyek.iterator(); iterator.hasNext();) {
            Proyek proyek = iterator.next();

            json.put(proyek.toJson(true));
        }

        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getProyekById(@PathVariable("id") int id) {
        Proyek proyek = proyekService.getProyekById(id);
        JSONObject json = proyek.toJson(true);

        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveProyek(@RequestBody String reqBody) {
        Proyek proyek = proyekService.saveProyek(Proyek.fromJson(new JSONObject(reqBody)));
        JSONObject json = proyek.toJson(false);

        return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProyek(@PathVariable("id") int id, @RequestBody String reqBody) {
        Proyek proyek = proyekService.updateProyek(id, Proyek.fromJson(new JSONObject(reqBody)));
        JSONObject json = proyek.toJson(false);

        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProyek(@PathVariable("id") int id) {
        proyekService.deleteProyek(id);
        return new ResponseEntity<String>("Delete proyek sukses", HttpStatus.OK);
    }
}
