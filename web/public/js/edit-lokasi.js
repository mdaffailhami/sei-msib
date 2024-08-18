const form = document.getElementById("form");
const tombolSimpanProyek = document.getElementById("tombol_simpan_proyek");
const modalBody = document.querySelector(".modal-body");

const namaLokasi = document.getElementById("nama_lokasi");
const negara = document.getElementById("negara");
const provinsi = document.getElementById("provinsi");
const kota = document.getElementById("kota");
const proyekDipilih = [];

const api = "http://localhost:8081/api/";

const params = new Proxy(new URLSearchParams(window.location.search), {
  get: (searchParams, prop) => searchParams.get(prop),
});

async function main() {
  const resProyek = await fetch(api + "proyek", {
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });

  if (resProyek.ok) {
    // Get proyeks
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

  // Get locations
  const resLokasi = await fetch(api + "lokasi/" + params.id);

  if (resLokasi.ok) {
    const lokasi = await resLokasi.json();
    namaLokasi.value = lokasi.nama_lokasi;
    negara.value = lokasi.negara;
    provinsi.value = lokasi.provinsi;
    kota.value = lokasi.kota;

    lokasi.proyek.forEach((proyek, index) => {
      const listTile = document.querySelector(
        `[type="checkbox"][value="${proyek.id}"]`
      );
      listTile.checked = true;
    });
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

  // Put lokasi
  const resLokasi = await fetch(api + "lokasi/" + params.id, {
    method: "PUT",
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

  await fetch(api + "proyek-lokasi/send-sql", {
    method: "POST",
    body: `DELETE FROM proyek_lokasi WHERE lokasi_id=${params.id}`,
  });

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

  alert("Lokasi berhasil diedit");
  window.location.href = "/";
});
