<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/form-page.css" />

    <script defer src="/js/bootstrap.bundle.min.js"></script>
    <script defer src="/js/edit-proyek.js"></script>

    <title>Edit Proyek</title>
  </head>
  <body>
    <div class="container mt-4 mb-5">
      <h2>Edit Proyek</h2>
      <form id="form">
        <div class="mb-3">
          <label for="nama_proyek" class="form-label">Nama proyek</label>
          <input
            type="text"
            class="form-control"
            id="nama_proyek"
            placeholder="Masukkan nama proyek"
            required
          />
        </div>
        <div class="mb-3">
          <label for="client" class="form-label">Client</label>
          <input
            type="text"
            class="form-control"
            id="client"
            placeholder="Masukkan nama client"
            required
          />
        </div>
        <div class="mb-3">
          <label for="tgl_mulai" class="form-label">Tanggal mulai</label>
          <input type="date" class="form-control" id="tgl_mulai" required />
        </div>
        <div class="mb-3">
          <label for="tgl_selesai" class="form-label">Tanggal selesai</label>
          <input type="date" class="form-control" id="tgl_selesai" required />
        </div>
        <div class="mb-3">
          <label for="pimpinan_proyek" class="form-label"
            >Pimpinan proyek</label
          >
          <input
            type="text"
            class="form-control"
            id="pimpinan_proyek"
            placeholder="Masukkan nama pimpinan proyek"
            required
          />
        </div>
        <div class="mb-3">
          <label for="keterangan" class="form-label">Keterangan</label>
          <textarea
            class="form-control"
            id="keterangan"
            rows="3"
            placeholder="Masukkan keterangan proyek"
          ></textarea>
        </div>
        <!-- Field Lokasi menggunakan pop-up modal -->
        <div class="mb-3">
          <label class="form-label">Lokasi</label>
          <button
            type="button"
            class="btn btn-outline-success"
            data-bs-toggle="modal"
            data-bs-target="#lokasi_modal"
          >
            Pilih Lokasi
          </button>
        </div>
        <center>
          <button type="submit" class="btn btn-primary" style="width: 60%">
            Simpan
          </button>
        </center>
      </form>
    </div>

    <!-- Modal untuk memilih lokasi -->
    <div
      class="modal fade"
      id="lokasi_modal"
      tabindex="-1"
      aria-labelledby="lokasi_modal_label"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="lokasi_modal_label">Pilih Lokasi</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <!-- <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="Lokasi 1" id="lokasi1">
                        <label class="form-check-label" for="lokasi1">Lokasi 1</label>
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
              id="tombol_simpan_lokasi"
            >
              Simpan Pilihan
            </button>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
