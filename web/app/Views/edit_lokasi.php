<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/form-page.css" />

    <script defer src="/js/bootstrap.bundle.min.js"></script>
    <script defer src="/js/edit-lokasi.js"></script>

    <title>Edit Lokasi</title>
  </head>
  <body>
    <div class="container mt-4 mb-5">
      <h2>Edit Lokasi</h2>
      <form id="form">
        <div class="mb-3">
          <label for="nama_lokasi" class="form-label">Nama lokasi</label>
          <input
            type="text"
            class="form-control"
            id="nama_lokasi"
            placeholder="Masukkan nama lokasi"
            required
          />
        </div>
        <div class="mb-3">
          <label for="negara" class="form-label">Negara</label>
          <input
            type="text"
            class="form-control"
            id="negara"
            placeholder="Masukkan nama negara"
            required
          />
        </div>
        <div class="mb-3">
          <label for="provinsi" class="form-label">Provinsi</label>
          <input
            type="text"
            class="form-control"
            id="provinsi"
            placeholder="Masukkan nama provinsi"
            required
          />
        </div>
        <div class="mb-3">
          <label for="kota" class="form-label">Kota</label>
          <input
            type="text"
            class="form-control"
            id="kota"
            placeholder="Masukkan nama kota"
            required
          />
        </div>
        <div class="mb-3">
          <label class="form-label">Proyek</label>
          <button
            type="button"
            class="btn btn-outline-success"
            data-bs-toggle="modal"
            data-bs-target="#proyek_modal"
          >
            Pilih Proyek
          </button>
        </div>
        <center>
          <button type="submit" class="btn btn-primary" style="width: 60%">
            Simpan
          </button>
        </center>
      </form>
    </div>

    <div
      class="modal fade"
      id="proyek_modal"
      tabindex="-1"
      aria-labelledby="proyek_modal_label"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="proyek_modal_label">Pilih proyek</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <!-- <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="proyek 1" id="proyek1">
                        <label class="form-check-label" for="proyek1">proyek 1</label>
                    </div> -->
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Tutup
            </button>
            <button
              type="button"
              class="btn btn-primary"
              id="tombol_simpan_proyek"
            >
              Simpan Pilihan
            </button>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
