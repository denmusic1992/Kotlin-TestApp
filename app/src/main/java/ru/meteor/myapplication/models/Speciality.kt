package ru.meteor.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Speciality (
    @SerializedName("specialty_id")
    @ColumnInfo(name = "specialty_id")
    @PrimaryKey
    @Expose
    val specialtyId: Int,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    @Expose
    val name: String?
) {
    // равенство объектов
    override fun equals(other: Any?): Boolean {
        val res: Speciality = other as Speciality
        return this.name.equals(res.name) && this.specialtyId == res.specialtyId
    }

    // хеш сгенеренный
    override fun hashCode(): Int {
        var result = specialtyId ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}