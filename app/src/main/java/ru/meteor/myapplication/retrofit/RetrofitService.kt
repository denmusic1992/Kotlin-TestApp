package ru.meteor.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.meteor.myapplication.interfaces.IEmployeeApi
import ru.meteor.myapplication.utils.Constants

// объявление синглтона
object RetrofitService {

    // Создаем экземпляр ретрофит
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // конструктор
    init {
        println("Retrofit Complete!")
    }

    fun getRetrofitService() : IEmployeeApi = retrofit.create(IEmployeeApi::class.java)
}