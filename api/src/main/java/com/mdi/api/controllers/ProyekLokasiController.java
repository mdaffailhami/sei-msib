package com.mdi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proyek-lokasi")
public class ProyekLokasiController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("{proyek_id}/{lokasi_id}")
    public ResponseEntity<String> saveProyekLokasi(@PathVariable("proyek_id") int proyek_id,
            @PathVariable("lokasi_id") int lokasi_id) {

        String sql = "INSERT INTO proyek_lokasi(proyek_id,lokasi_id) VALUES (?,?)";

        jdbcTemplate.update(sql, proyek_id, lokasi_id);

        return new ResponseEntity<String>("Create proyek_lokasi sukses", HttpStatus.OK);
    }

    @DeleteMapping("{proyek_id}/{lokasi_id}")
    public ResponseEntity<String> deleteProyekLokasi(@PathVariable("proyek_id") int proyek_id,
            @PathVariable("lokasi_id") int lokasi_id) {
        String sql = "DELETE FROM proyek_lokasi WHERE proyek_id=? AND lokasi_id=?";

        jdbcTemplate.update(sql, proyek_id, lokasi_id);

        return new ResponseEntity<String>("Delete proyek_lokasi sukses", HttpStatus.OK);
    }
}
