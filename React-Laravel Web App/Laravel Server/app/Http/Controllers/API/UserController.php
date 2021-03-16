<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\User;
use Validator;

class UserController extends Controller
{

    /**
     * Register api
     *
     * @return \Illuminate\Http\Response
     */
    public function register(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'email' => 'required|email',
            'password' => 'required',
            'c_password' => 'required|same:password',
			'photourl' => 'required',
            'preferences' => 'required',
        ]);

        if ($validator->fails()) {
            $response = [
                'success' => false,
                'data' => 'Validation Error.',
                'message' => $validator->errors()
            ];
            return response()->json($response, 404);
        }

        $input = $request->all();
        $input['password'] = bcrypt($input['password']);
        $user = User::create($input);
        $success['token'] = $user->createToken('MyApp')->accessToken;
        $success['name'] = $user->name;

        $response = [
            'success' => true,
            'data' => $success,
            'message' => 'User register successfully.'
        ];

        return response()->json($response, 200);
    }

    /**
     * Login api
     *
     * @return \Illuminate\Http\Response
     */
    public function login()
    {
        if (Auth::attempt(['email' => request('email'), 'password' => request('password')])) {
            $user = Auth::user();
            $success['token'] = $user->createToken('MyApp')->accessToken;

            return response()->json(['success' => $success, 'info'=>$user], 200);
        } else {
            return response()->json(['error' => 'Unauthorised'], 401);
        }
    }


    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */

    public function update($id,Request $request, User $user)
    {
        $input = $request->all();
        $input['password'] = bcrypt($input['password']);

        $validator = Validator::make($input, [
			'name' => 'required',
            'email' => 'required|email',
            'password' => 'required',
			'photourl' => 'required',
            'preferences' => 'required',
        ]);

        if ($validator->fails()) {
            $response = [
                'success' => false,
                'data' => 'Validation Error.',
                'message' => $validator->errors()
            ];
            return response()->json($response, 404);
        }

        $user = User::find($id);
		$user->name = $input['name'];
		$user->email = $input['email'];
        $user->password =  $input['password'];
		$user->photourl = $input['photourl'];
        $user->preferences = $input['preferences'];
        $user->save();
        $user->update($input);

        $data = $user->toArray();

        $response = [
            'success' => true,
            'data' => $data,
            'message' => 'User updated successfully.'
        ];

        return response()->json($response, 200);
    }

	/**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $user = User::find($id);
        $data = $user->toArray();

        if (is_null($user)) {
            $response = [
                'success' => false,
                'data' => 'Empty',
                'message' => 'User not found.'
            ];
            return response()->json($response, 404);
        }


        $response = [
            'success' => true,
            'data' => $data,
            'message' => 'User retrieved successfully.'
        ];

        return response()->json($response, 200);
    }
}
