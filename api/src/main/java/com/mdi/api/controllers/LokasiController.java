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

import com.mdi.api.models.Lokasi;
import com.mdi.api.services.LokasiService;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
@RequestMapping("/api/lokasi")
public class LokasiController {
    @Autowired
    private LokasiService lokasiService;

    @GetMapping
    public ResponseEntity<String> getAllLokasi() {
        List<Lokasi> daftarLokasi = lokasiService.getAllLokasi();
        JSONArray json = new JSONArray();

        for (Iterator<Lokasi> iterator = daftarLokasi.iterator(); iterator.hasNext();) {
            Lokasi lokasi = iterator.next();

            json.put(lokasi.toJson(true));
        }

        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getLokasiById(@PathVariable("id") int id) {
        Lokasi lokasi = lokasiService.getLokasiById(id);
        JSONObject json = lokasi.toJson(true);

        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveLokasi(@RequestBody String reqBody) {
        Lokasi lokasi = lokasiService.saveLokasi(Lokasi.fromJson(new JSONObject(reqBody)));
        JSONObject json = lokasi.toJson(false);

        return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateLokasi(@PathVariable("id") int id, @RequestBody String reqBody) {
        Lokasi lokasi = lokasiService.updateLokasi(id, Lokasi.fromJson(new JSONObject(reqBody)));
        JSONObject json = lokasi.toJson(false);

        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLokasi(@PathVariable("id") int id) {
        lokasiService.deleteLokasi(id);
        return new ResponseEntity<String>("Delete lokasi sukses", HttpStatus.OK);
    }
}
