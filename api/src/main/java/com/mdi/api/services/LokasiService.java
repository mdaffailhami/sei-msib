package com.mdi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdi.api.models.Lokasi;
import com.mdi.api.repositories.LokasiRepository;

@Service
public class LokasiService {
    @Autowired
    private LokasiRepository lokasiRepository;

    public Lokasi saveLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    public Lokasi getLokasiById(int id) {
        Optional<Lokasi> lokasi = lokasiRepository.findById(id);

        if (lokasi.isPresent()) {
            return lokasi.get();
        } else {
            return null;
        }
    }

    public Lokasi updateLokasi(int id, Lokasi lokasi) {
        Lokasi existingLokasi = lokasiRepository.findById(id).orElseThrow(() -> new RuntimeException());

        existingLokasi.setNamaLokasi(lokasi.getNamaLokasi());
        existingLokasi.setNegara(lokasi.getNegara());
        existingLokasi.setProvinsi(lokasi.getProvinsi());
        existingLokasi.setKota(lokasi.getKota());

        lokasiRepository.save(existingLokasi);
        return existingLokasi;
    }

    public void deleteLokasi(int id) {
        lokasiRepository.findById(id).orElseThrow(() -> new RuntimeException());

        lokasiRepository.deleteById(id);
    }
}
