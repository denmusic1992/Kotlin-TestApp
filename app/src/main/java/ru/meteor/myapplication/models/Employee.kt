package ru.meteor.myapplication.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import ru.meteor.myapplication.room.converters.SpecConverter

@Entity(primaryKeys = ["f_name", "l_name"])
@TypeConverters(SpecConverter::class)
@Parcelize
data class Employee(
    @SerializedName("f_name")
    @ColumnInfo(name = "f_name")
    @Expose
    val fName: String = "",

    @SerializedName("l_name")
    @ColumnInfo(name = "l_name")
    @Expose
    val lName: String = "",

    @SerializedName("birthday")
    @ColumnInfo(name = "birthday")
    @Expose
    val birthday: String?,

    @SerializedName("avatr_url")
    @ColumnInfo(name = "avatr_url")
    @Expose
    var avatrUrl: String?,

    @SerializedName("specialty")
    @ColumnInfo(name = "specialty")
    @Expose
    val specialty: List<Speciality>?
): Parcelable