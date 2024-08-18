const form = document.getElementById("form");
const tombolSimpanProyek = document.getElementById("tombol_simpan_proyek");
const modalBody = document.querySelector(".modal-body");

const namaLokasi = document.getElementById("nama_lokasi");
const negara = document.getElementById("negara");
const provinsi = document.getElementById("provinsi");
const kota = document.getElementById("kota");
const proyekDipilih = [];

const api = "http://localhost:8081/api/";

async function main() {
  const resProyek = await fetch(api + "proyek", {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });

  if (resProyek.ok) {
    const proyek = await resProyek.json();
    let proyekListTiles = "";

    proyek.forEach((value, index) => {
      proyekListTiles += `
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="${
              value.id
            }" id="proyek_${index}">
            <label class="form-check-label" for="proyek_${index}">${
        value.nama_proyek
      } (${value.pimpinan_proyek} | ${value.tgl_mulai.split(" ")[0]} s.d. ${
        value.tgl_selesai.split(" ")[0]
      })</label>
        </div>
      `;
    });

    modalBody.innerHTML = proyekListTiles;
  }
}

main();

tombolSimpanProyek.addEventListener("click", () => {
  // Reset first
  proyekDipilih.length = 0;

  document
    .querySelectorAll('#proyek_modal input[type="checkbox"]:checked')
    .forEach(function (checkbox) {
      proyekDipilih.push(checkbox.value);
    });

  document.querySelector('[data-bs-dismiss="modal"]').click();
});

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  // Post lokasi
  const resLokasi = await fetch(api + "lokasi", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nama_lokasi: namaLokasi.value,
      negara: negara.value,
      provinsi: provinsi.value,
      kota: kota.value,
    }),
  });

  const lokasi = await resLokasi.json();

  if (proyekDipilih.length != 0) {
    const reqBody = [];

    proyekDipilih.forEach((value, index) => {
      reqBody.push({ proyek_id: value, lokasi_id: lokasi.id });
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

  alert("Lokasi berhasil ditambahkan");
  window.location.pathname = "/";
});
