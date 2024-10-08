<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <script defer src="/js/beranda.js"></script>

    <title>SEI - MSIB</title>
  </head>
  <body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">SEI - MSIB</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Dropdown
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a class="dropdown-item" href="#">Something else here</a>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" aria-disabled="true">Disabled</a>
            </li>
          </ul>
          <form class="d-flex" role="search">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
            <button class="btn btn-outline-success" type="submit">
              Search
            </button>
          </form>
        </div>
      </div>
    </nav>
    <!-- /Navbar -->

    <!-- Daftar Proyek -->
    <div class="container">
      <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center pt-4">Daftar Proyek</h1>
        <a class="btn btn-primary" href="/tambah-proyek" style="height:40px">Tambah Proyek</a>
      </div>
      <hr>
    </div>

    <div
      id="proyek-container"
      class="container d-flex flex-wrap justify-content-center"
    ></div>
    <!-- /Daftar Proyek -->

    <br>
    
    <!-- Daftar Lokasi -->
    <div class="container">
      <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center pt-4">Daftar Lokasi</h1>
        <a class="btn btn-primary" href="/tambah-lokasi" style="height:40px">Tambah Lokasi</a>
      </div>
      <hr>
    </div>

    <div
      id="lokasi-container"
      class="container d-flex flex-wrap justify-content-center"
    ></div>
    <!-- /Daftar Lokasi -->
  </body>
</html>
