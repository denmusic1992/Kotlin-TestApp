package ru.meteor.myapplication.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.meteor.myapplication.models.Speciality


class SpecConverter {

    @TypeConverter
    fun fromSpec(specs: List<Speciality>?): String {
        return Gson().toJson(specs)
    }

    @TypeConverter
    fun toSpec(string: String?): List<Speciality> {
        val objects = Gson().fromJson(string, Array<Speciality>::class.java) as Array<Speciality>
        return objects.toList()
    }
}