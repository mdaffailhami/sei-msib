package com.mdi.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdi.api.models.Proyek;
import com.mdi.api.repositories.ProyekRepository;

@Service
public class ProyekService {
    @Autowired
    private ProyekRepository proyekRepository;

    public Proyek saveProyek(Proyek proyek) {
        return proyekRepository.save(proyek);
    }

    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    public Proyek getProyekById(int id) {
        Optional<Proyek> proyek = proyekRepository.findById(id);

        if (proyek.isPresent()) {
            return proyek.get();
        } else {
            return null;
        }
    }

    public Proyek updateProyek(int id, Proyek proyek) {
        Proyek existingProyek = proyekRepository.findById(id).orElseThrow(() -> new RuntimeException());

        existingProyek.setNamaProyek(proyek.getNamaProyek());
        existingProyek.setClient(proyek.getClient());
        existingProyek.setTglMulai(proyek.getTglMulai());
        existingProyek.setTglSelesai(proyek.getTglSelesai());
        existingProyek.setPimpinanProyek(proyek.getPimpinanProyek());
        existingProyek.setKeterangan(proyek.getKeterangan());

        proyekRepository.save(existingProyek);
        return existingProyek;
    }

    public void deleteProyek(int id) {
        proyekRepository.findById(id).orElseThrow(() -> new RuntimeException());

        proyekRepository.deleteById(id);
    }
}
