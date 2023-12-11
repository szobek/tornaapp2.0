<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\DbController;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::any('/', function () {
    return "éééééééééééééééééé";
});
Route::get('/rooms', [DbController::class,'getAllRoom']);
Route::get('/users', [DbController::class,'getAllUsers']);
Route::any('/checklogin', [DbController::class,'checkLogin']);