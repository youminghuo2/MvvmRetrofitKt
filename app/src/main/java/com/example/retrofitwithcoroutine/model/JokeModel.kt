package com.example.retrofitwithcoroutine.model

data class JokeModel(
    val code: Int,
    val message: String,
    val result: List<Result>
)

data class Result(
    val comment: String,
    val down: String,
    val forward: String,
    val header: String,
    val images: Any,
    val name: String,
    val passtime: String,
    val sid: String,
    val text: String,
    val thumbnail: String,
    val top_comments_content: String,
    val top_comments_header: String,
    val top_comments_name: String,
    val top_comments_uid: String,
    val top_comments_voiceuri: String,
    val type: String,
    val uid: String,
    val up: String,
    val video: String
)