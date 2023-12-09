<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class DbController extends Controller
{
    //
    public function getAllRoom(){
        $name = DB::table('user_data')
            ->join('users', 'users.id', '=', 'user_data.user_id')
            ->select('first_name', 'phone', 'last_name')
            ->get();

            return json_encode($name);
    }
    
}
