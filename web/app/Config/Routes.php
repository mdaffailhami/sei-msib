<?php

use CodeIgniter\Router\RouteCollection;

/**
 * @var RouteCollection $routes
 */
$routes->get('/', 'Beranda::index');
$routes->get('/tambah-proyek', 'TambahProyek::index');
$routes->get('/tambah-lokasi', 'TambahLokasi::index');
