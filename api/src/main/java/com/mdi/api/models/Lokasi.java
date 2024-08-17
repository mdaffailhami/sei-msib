package com.mdi.api.models;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.Iterator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lokasi")
public class Lokasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama_lokasi", nullable = false)
    private String namaLokasi;

    @Column(name = "negara", nullable = false)
    private String negara;

    @Column(name = "provinsi", nullable = false)
    private String provinsi;

    @Column(name = "kota", nullable = false)
    private String kota;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToMany(mappedBy = "lokasi")
    private Set<Proyek> proyek;

    public static Lokasi fromJson(JSONObject json) {
        Lokasi lokasi = new Lokasi();
        lokasi.setNamaLokasi(json.getString("nama_lokasi"));
        lokasi.setNegara(json.getString("negara"));
        lokasi.setProvinsi(json.getString("provinsi"));
        lokasi.setKota(json.getString("kota"));

        return lokasi;
    }

    public JSONObject toJson(boolean includeProyek) {
        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("nama_lokasi", this.namaLokasi);
        json.put("negara", this.negara);
        json.put("provinsi", this.provinsi);
        json.put("kota", this.kota);
        json.put("created_at", this.createdAt);

        if (includeProyek) {
            JSONArray daftarProyek = new JSONArray();
            for (Iterator<Proyek> iterator = this.proyek.iterator(); iterator.hasNext();) {
                Proyek proyek = iterator.next();

                daftarProyek.put(proyek.toJson(false));
            }
            json.put("proyek", daftarProyek);
        }

        return json;
    }

    public int getId() {
        return id;
    }

    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }

    public String getNegara() {
        return negara;
    }

    public void setNegara(String negara) {
        this.negara = negara;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Set<Proyek> getProyek() {
        return proyek;
    }
}
