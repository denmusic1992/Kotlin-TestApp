package ru.meteor.myapplication.room.dao

import androidx.room.*
import ru.meteor.myapplication.models.Employee
import ru.meteor.myapplication.models.Speciality

@Dao
interface SpecialityDao {
    @Query("Select * from Speciality")
    fun getAll(): List<Speciality>

    @Query("Select * from Speciality where specialty_id = :id and name = :name")
    fun getSpecificSpec(id: Int, name: String): Speciality

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(speciality: Speciality)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(specialities: List<Speciality>)

    @Update
    fun update(speciality: Speciality)

    @Delete
    fun delete(speciality: Speciality)
}