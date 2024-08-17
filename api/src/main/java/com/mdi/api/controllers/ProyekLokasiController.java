package com.mdi.api.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/proyek-lokasi")
public class ProyekLokasiController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public ResponseEntity<String> saveMultiProyekLokasi(@RequestBody String reqBody) {
        JSONArray json = new JSONArray(reqBody);

        String sql = "INSERT INTO proyek_lokasi(proyek_id,lokasi_id) VALUES";

        for (int i = 0; i < json.length(); i++) {
            JSONObject proyekLokasi = json.getJSONObject(i);
            sql += "(" + proyekLokasi.get("proyek_id") + "," + proyekLokasi.get("lokasi_id") + ")";

            if (i + 1 != json.length()) {
                sql += ",";
            }
        }

        System.out.println("===================");
        System.out.println(sql);
        System.out.println("===================");

        jdbcTemplate.update(sql);

        return new ResponseEntity<String>("Create proyek_lokasi sukses", HttpStatus.OK);
    }

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
