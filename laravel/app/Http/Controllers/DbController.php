<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Models\Room;

class DbController extends Controller
{
    //
    public function getAllRoom(){
        $rooms = Room::all();
        $name = DB::table('user_data')
            ->join('users', 'users.id', '=', 'user_data.user_id')
            ->select('first_name', 'phone', 'last_name')
            ->get();

            return json_encode($rooms);
    }

    public function getAllUsers(){

        $users = DB::table('user_data')
            ->join('users', 'users.id', '=', 'user_data.user_id')
            ->join('user_rights','user_rights.user_id',"=","users.id")
            ->select('first_name', 'phone', 'last_name')
            ->get();

            return json_encode($users);
    }

    public function checkLogin(Request $req)  {
        // kunszt.norbert@gmail.com 72239e8b21c5b0d1435b672ce16340acb3d9672bcfa890a1517a495853c61366
        $data=$this->convertStringFromJavaToJson($req);        
        $logged=DB::table('users')->where('email',$data->email)->where('password',$data->password)->first();
        return ($logged!=null)?"Sikeres":"Sikertelen";
        // return $email;
    }
    private static function convertStringFromJavaToJson($req){
        $body=$req->getContent();
        $n=  str_replace(["\"","'"],["'","\""],$body);
        $t=json_decode($n);
        return $t;
    }
}
