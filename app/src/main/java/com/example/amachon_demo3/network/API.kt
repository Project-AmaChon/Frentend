package com.example.amachon_demo3.network

import com.example.amachon_demo3.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    @POST("/login")
    fun login(@Body loginDto: LoginDto) : Call<LoginResponseDto>

    @GET("/tags")
    fun getTags() : Call<TokenDto>

    @POST("/join/send-verification-code")
    fun logUp(@Body email: EmailDto) : Call<Void>

    @POST("/join/certification")
    fun codeCheck(@Body code: EmailCheckDto) : Call<Void>

    @POST("/join/check-nickname")
    fun nickNameCheck(@Body nicknameDto: NicknameDto) : Call<Void>

    @POST("/join")
    fun join(@Body joinData: JoinData) : Call<JoinResponseDto>

    @POST("/change-tags")
    fun change(@Body changeDto: changeDto) : Call<Void>

    @POST("/project/search")
    fun postSearch(@Body searchDto: SearchDto) : Call<ProjectSearchDto>

    @GET("/messages/room")
    fun getMessage() : Call<MessageListDto>

    @GET("/tech-tags")
    fun getTechTags() : Call<TagsSearchDto>

    @GET("/region-tags")
    fun getRegionTags() : Call<RegionSearchDto>

    @POST("/project")
    fun createProject(@Body projectCreateDto: ProjectCreateDto) : Call<RespawnDto>
}