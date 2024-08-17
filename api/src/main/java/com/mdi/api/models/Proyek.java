package com.mdi.api.models;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "proyek")
public class Proyek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama_proyek", nullable = false)
    private String namaProyek;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "tgl_mulai", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglMulai;

    @Column(name = "tgl_selesai", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglSelesai;

    @Column(name = "pimpinan_proyek", nullable = false)
    private String pimpinanProyek;

    @Column(name = "keterangan", nullable = false, columnDefinition = "TEXT")
    private String keterangan;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToMany
    @JoinTable(name = "proyek_lokasi", joinColumns = @JoinColumn(name = "proyek_id"), inverseJoinColumns = @JoinColumn(name = "lokasi_id"))
    private Set<Lokasi> lokasi;

    public static Proyek fromJson(JSONObject json) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date tglMulai = dateFormatter.parse(json.getString("tgl_mulai"));
            Date tglSelesai = dateFormatter.parse(json.getString("tgl_selesai"));

            Proyek proyek = new Proyek();
            proyek.setNamaProyek(json.getString("nama_proyek"));
            proyek.setClient(json.getString("client"));
            proyek.setTglMulai(tglMulai);
            proyek.setTglSelesai(tglSelesai);
            proyek.setPimpinanProyek(json.getString("pimpinan_proyek"));
            proyek.setKeterangan(json.getString("keterangan"));

            return proyek;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject toJson(boolean includeLokasi) {
        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("nama_proyek", this.namaProyek);
        json.put("client", this.client);
        json.put("tgl_mulai", this.tglMulai);
        json.put("tgl_selesai", this.tglSelesai);
        json.put("pimpinan_proyek", this.pimpinanProyek);
        json.put("keterangan", this.keterangan);
        json.put("created_at", this.createdAt);

        if (includeLokasi) {
            JSONArray daftarLokasi = new JSONArray();
            for (Iterator<Lokasi> iterator = this.lokasi.iterator(); iterator.hasNext();) {
                Lokasi lokasi = iterator.next();

                daftarLokasi.put(lokasi.toJson(false));
            }
            json.put("lokasi", daftarLokasi);
        }

        return json;
    }

    public int getId() {
        return id;
    }

    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(Date tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Date getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(Date tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getPimpinanProyek() {
        return pimpinanProyek;
    }

    public void setPimpinanProyek(String pimpinanProyek) {
        this.pimpinanProyek = pimpinanProyek;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Set<Lokasi> getLokasi() {
        return lokasi;
    }
}
