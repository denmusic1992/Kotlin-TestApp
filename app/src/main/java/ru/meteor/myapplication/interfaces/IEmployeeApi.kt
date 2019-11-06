package ru.meteor.myapplication.interfaces

import retrofit2.http.GET
import retrofit2.Call
import ru.meteor.myapplication.models.Employee
import ru.meteor.myapplication.models.Response

interface IEmployeeApi {
    @GET("65gb/static/raw/master/testTask.json")
    fun getEmployees(): Call<Response<List<Employee>>>
}