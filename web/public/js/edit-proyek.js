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

const params = new Proxy(new URLSearchParams(window.location.search), {
  get: (searchParams, prop) => searchParams.get(prop),
});

async function main() {
  // Get locations
  const resLokasi = await fetch(api + "lokasi");

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

  // Get project
  const resProyek = await fetch(api + "proyek/" + params.id);

  if (resProyek.ok) {
    const proyek = await resProyek.json();
    namaProyek.value = proyek.nama_proyek;
    client.value = proyek.client;
    tglMulai.value = proyek.tgl_mulai.split(" ")[0];
    tglSelesai.value = proyek.tgl_selesai.split(" ")[0];
    pimpinanProyek.value = proyek.pimpinan_proyek;
    keterangan.value = proyek.keterangan;

    proyek.lokasi.forEach((lokasi, index) => {
      const listTile = document.querySelector(
        `[type="checkbox"][value="${lokasi.id}"]`
      );
      listTile.checked = true;
    });
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

  // Put proyek
  const resProyek = await fetch(api + "proyek/" + params.id, {
    method: "PUT",
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

  await fetch(api + "proyek-lokasi/send-sql", {
    method: "POST",
    body: `DELETE FROM proyek_lokasi WHERE proyek_id=${params.id}`,
  });

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

  alert("Proyek berhasil diedit");
  window.location.href = "/";
});
