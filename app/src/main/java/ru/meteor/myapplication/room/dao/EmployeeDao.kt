package ru.meteor.myapplication.room.dao

import androidx.room.*
import ru.meteor.myapplication.models.Employee

@Dao
interface EmployeeDao {
    @Query("Select * from Employee")
    fun getAll(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: Employee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employees: List<Employee>)

    @Update
    fun update(employee: Employee)

    @Delete
    fun delete(employee: Employee)
}