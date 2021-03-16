<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/
Route::post('register', 'API\UserController@register');
Route::post('login', 'API\UserController@login');
Route::put('users/{id}', 'API\UserController@update');
Route::get('users/{id}', 'API\UserController@show');

Route::get('comments', 'API\CommentController@index');
Route::get('commentsday/{dateP}', 'API\CommentController@last24hours');
Route::post('comments', 'API\CommentController@store');
Route::get('comments/{id}', 'API\CommentController@show');
Route::put('comments/{id}', 'API\CommentController@update');

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});
