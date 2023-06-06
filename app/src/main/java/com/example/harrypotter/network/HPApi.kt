package com.example.harrypotter.network

import com.example.harrypotter.model.StaffDetailHP
import com.example.harrypotter.model.StaffHP
import com.example.harrypotter.model.StudentDetailHP
import com.example.harrypotter.model.StudentHP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface HPApi {
    @GET
    fun getStudents(
        @Url url: String?    //getStudents("api/characters")
    ): Call<ArrayList<StudentHP>>

    @GET
    fun getStaff(
        @Url url: String?    //getStudents("api/characters/staff")
    ): Call<ArrayList<StaffHP>>

    @GET("/api/character/{id}")    //getStudentDetail("3763783")   /api/character/{3763783}
    fun getStudentDetail(
        @Query("id") id: String?
    ): Call<StudentDetailHP>

    @GET("/api/character/{id}")    //getStaffDetail("3763783")   /api/character/{3763783}
    fun getStaffDetail(
        @Query("id") id: String?
    ): Call<StaffDetailHP>
}