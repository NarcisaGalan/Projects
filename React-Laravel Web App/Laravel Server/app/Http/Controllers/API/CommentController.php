<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Comment;
use Validator;
use Carbon\Carbon;

class CommentController extends Controller
{
     /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $comments = Comment::all();
        $data = $comments->toArray();

        $response = [
            'success' => true,
            'data' => $data,
            'message' => 'Comment retrieved successfully.'
        ];

        return response()->json($response, 200);
    }
    /**
     * Display a listing of the resource.
     * @param String $dateP
     * @return \Illuminate\Http\Response
     */
    public function last24hours($dateP)
    {

        $comments = Comment::all();
        $data = $comments->toArray();

		$data = Comment::where('created_at', '>', $dateP)
              ->groupBy(\DB::raw('DATE(created_at)'))
              ->count();

        $response = [
            'date' => $dateP,
            'commentsNumber' => $data
        ];

        return response()->json($response, 200);
    }


    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $input = $request->all();

        $validator = Validator::make($input, [
            'content' => 'required'
        ]);

        if ($validator->fails()) {
            $response = [
                'success' => false,
                'data' => 'Validation Error.',
                'message' => $validator->errors()
            ];
            return response()->json($response, 404);
        }

        $comment = Comment::create($input);
        $data = $comment->toArray();

        $response = [
            'success' => true,
            'data' => $data,
            'message' => 'Comment stored successfully.'
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
        $comment = Comment::find($id);
        $data = $comment->toArray();

        if (is_null($comment)) {
            $response = [
                'success' => false,
                'data' => 'Empty',
                'message' => 'Comment not found.'
            ];
            return response()->json($response, 404);
        }


        $response = [
            'success' => true,
            'data' => $data,
            'message' => 'Comment retrieved successfully.'
        ];

        return response()->json($response, 200);
    }


    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Comment $comment)
    {
        $input = $request->all();

        $validator = Validator::make($input, [
            'content' => 'required'
        ]);

        if ($validator->fails()) {
            $response = [
                'success' => false,
                'data' => 'Validation Error.',
                'message' => $validator->errors()
            ];
            return response()->json($response, 404);
        }

        $comment->content = $input['content'];
        $comment->save();

        $data = $comment->toArray();

        $response = [
            'success' => true,
            'data' => $data,
            'message' => 'Comment updated successfully.'
        ];

        return response()->json($response, 200);
    }


}
