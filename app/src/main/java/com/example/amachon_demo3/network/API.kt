package com.example.amachon_demo3.network

import com.example.amachon_demo3.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
    fun postSearch(@Body searchDto: SearchDto) : Call<ProjectDto>

    @GET("/tech-tags")
    fun getTechTags() : Call<TagsSearchDto>

    @GET("/region-tags")
    fun getRegionTags() : Call<RegionSearchDto>

    @POST("/project")
    fun createProject(@Body projectCreateDto: ProjectCreateDto) : Call<RespawnDto>

    @POST("/project/{projectId}/apply")
    fun projectApply(@Path("projectId") projectId: Int) : Call<BaseDto>

    @GET("/project/{projectId}")
    fun getProject(@Path("projectId") projectId : Int) : Call<ProjectDetailDto>

    @GET("/project/{projectId}/recommend-teamMember")
    fun getReCoMember(@Path("projectId") projectId: Int) : Call<ReMembercpresponeDto>

    @GET("/project/{projectId}/recruit-list")
    fun getNowMember(@Path("projectId") projectId: Int) : Call<ReMembercpresponeDto>

    @GET("project/apply/accept/{recruitId}")
    fun projectAccept(@Path("recruitId") recruitId : Int) : Call<BaseDto>

    @GET("project/apply/reject/{recruitId}")
    fun projectReject(@Path("recruitId") recruitId: Int) : Call<BaseDto>

    @GET("/home")
    fun getHome() : Call<CurrentProjectDto>

    @POST("/project/{projectId}/kick/{teamMemberId}")
    fun kickMember(@Path("projectId") projectId: Int, @Path("teamMemberId") teamMemberId : Int) : Call<BaseDto>
}