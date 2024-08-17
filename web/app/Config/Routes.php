<?php

use CodeIgniter\Router\RouteCollection;

/**
 * @var RouteCollection $routes
 */
$routes->get('/', 'Home::index');
$routes->get('/tambah-proyek', 'TambahProyek::index');
$routes->get('/add-location', 'AddLocation::index');
