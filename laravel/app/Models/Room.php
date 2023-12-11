<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Room extends Model
{
    use HasFactory;
    protected $table = 'rooms';
    protected $attributes = [
        'name' => "",
        'num' => "",
        'image_path'=>""
    ];
    protected $fillable = [
        'name',
        'num',
        'image_path',
    ];

}
