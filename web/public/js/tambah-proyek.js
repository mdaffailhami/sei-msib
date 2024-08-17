const form = document.getElementById("form");
const tombolSimpanLokasi = document.getElementById("tombol_simpan_lokasi");
const modalBody = document.querySelector(".modal-body");

const namaProyek = document.getElementById("nama_proyek");
const client = document.getElementById("client");
const tglMulai = document.getElementById("tgl_mulai");
const tglSelesai = document.getElementById("tgl_selesai");
const pimpinanProyek = document.getElementById("pimpinan_proyek");
const keterangan = document.getElementById("keterangan");
const lokasiDipilih = [];

const api = "http://localhost:8081/api/";

async function main() {
  const resLokasi = await fetch(api + "lokasi", {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });

  if (resLokasi.ok) {
    const lokasi = await resLokasi.json();
    let lokasiListTiles = "";

    lokasi.forEach((value, index) => {
      lokasiListTiles += `
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="${value.id}" id="lokasi_${index}">
            <label class="form-check-label" for="lokasi_${index}">${value.nama_lokasi} (${value.negara}, ${value.provinsi}, ${value.kota})</label>
        </div>
      `;
    });

    modalBody.innerHTML = lokasiListTiles;
  }
}

main();

tombolSimpanLokasi.addEventListener("click", () => {
  // Reset first
  lokasiDipilih.length = 0;

  document
    .querySelectorAll('#lokasi_modal input[type="checkbox"]:checked')
    .forEach(function (checkbox) {
      lokasiDipilih.push(checkbox.value);
    });

  document.querySelector('[data-bs-dismiss="modal"]').click();
});

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  // Post proyek
  const resProyek = await fetch(api + "proyek", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nama_proyek: namaProyek.value,
      client: client.value,
      tgl_mulai: tglMulai.value + " 00:00:00",
      tgl_selesai: tglSelesai.value + " 00:00:00",
      pimpinan_proyek: pimpinanProyek.value,
      keterangan: keterangan.value,
    }),
  });

  const proyek = await resProyek.json();

  if (lokasiDipilih.length != 0) {
    const reqBody = [];

    lokasiDipilih.forEach((value, index) => {
      reqBody.push({ proyek_id: proyek.id, lokasi_id: value });
    });

    await fetch(api + "proyek-lokasi", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(reqBody),
    });
  }

  alert("Proyek berhasil ditambahkan");
  window.location.pathname = "/";
});
