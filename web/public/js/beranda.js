const proyekContainer = document.querySelector("#proyek-container");
const lokasiContainer = document.querySelector("#lokasi-container");

const api = "http://localhost:8081/api/";

let proyek = [];

async function main() {
  const resProyek = await fetch(api + "proyek");

  if (resProyek.ok) {
    proyek = await resProyek.json();
    let proyekCards = "";

    proyek.forEach((value, index) => {
      proyekContainer.innerHTML += `
        <div class="card mb-3 me-3" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">${value.nama_proyek}</h5>
                <p class="card-text">Pemimpin: ${
                  value.pimpinan_proyek
                }<br>Client: ${value.client}<br>Mulai: ${
        value.tgl_mulai.split(" ")[0]
      }<br>Selesai: ${value.tgl_selesai.split(" ")[0]}</p>
                <div class="d-flex gap-1">
                  <button id="hapus_proyek_${index}" class="hapus_proyek btn btn-outline-danger btn-sm">
                    <i id="hapus_proyek_${index}" class="hapus_proyek bi bi-trash"></i>
                  </button>
                  <button id="edit_proyek_${index}" class="edit_proyek btn btn-outline-success btn-sm">
                    <i id="edit_proyek_${index}" class="edit_proyek bi bi-pencil"></i>
                  </button>
                </div>
            </div>
        </div>
      `;
    });
  }

  const resLokasi = await fetch(api + "lokasi");

  if (resLokasi.ok) {
    lokasi = await resLokasi.json();
    let lokasiCards = "";

    lokasi.forEach((value, index) => {
      lokasiContainer.innerHTML += `
        <div class="card mb-3 me-3" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">${value.nama_lokasi}</h5>
                <p class="card-text">Negara: ${value.negara}<br>Provinsi: ${value.provinsi}<br>Kota: ${value.kota}</p>
                <div class="d-flex gap-1">
                  <button id="hapus_lokasi_${index}" class="hapus_lokasi btn btn-outline-danger btn-sm">
                    <i id="hapus_lokasi_${index}" class="hapus_lokasi bi bi-trash"></i>
                  </button>
                  <button id="edit_lokasi_${index}" class="edit_lokasi btn btn-outline-success btn-sm">
                    <i id="edit_lokasi_${index}" class="edit_lokasi bi bi-pencil"></i>
                  </button>
                </div>
            </div>
        </div>
      `;
    });
  }
}

main();

document.addEventListener("click", async (e) => {
  if (e.target.className.split(" ")[0] == "edit_proyek") {
    const idSplitted = e.target.id.split("_");
    const index = idSplitted[idSplitted.length - 1];
    const selectedProyek = proyek[index];

    window.location.href = "/edit-proyek?id=" + selectedProyek.id;
  }

  if (e.target.className.split(" ")[0] == "hapus_proyek") {
    const idSplitted = e.target.id.split("_");
    const index = idSplitted[idSplitted.length - 1];
    const selectedProyek = proyek[index];

    const hapus = confirm(
      `Apakah kamu yakin mau menghapus proyek ${selectedProyek.nama_proyek}?`
    );

    if (hapus) {
      const res = await fetch(api + "proyek/" + selectedProyek.id, {
        method: "DELETE",
      });

      if (!res.ok)
        alert(`Gagal menghapus proyek ${selectedProyek.nama_proyek}!`);

      window.location.reload();
    }
  }

  if (e.target.className.split(" ")[0] == "edit_lokasi") {
    const idSplitted = e.target.id.split("_");
    const index = idSplitted[idSplitted.length - 1];
    const selectedLokasi = lokasi[index];

    window.location.href = "/edit-lokasi?id=" + selectedLokasi.id;
  }

  if (e.target.className.split(" ")[0] == "hapus_lokasi") {
    const idSplitted = e.target.id.split("_");
    const index = idSplitted[idSplitted.length - 1];
    const selectedLokasi = lokasi[index];

    const hapus = confirm(
      `Apakah kamu yakin mau menghapus lokasi ${selectedLokasi.nama_lokasi}?`
    );

    if (hapus) {
      await fetch(api + "proyek-lokasi/send-sql", {
        method: "POST",
        body: "SET FOREIGN_KEY_CHECKS=0;",
      });

      const res = await fetch(api + "lokasi/" + selectedLokasi.id, {
        method: "DELETE",
      });

      if (!res.ok)
        alert(`Gagal menghapus lokasi ${selectedLokasi.nama_lokasi}!`);

      window.location.reload();
    }
  }
});
