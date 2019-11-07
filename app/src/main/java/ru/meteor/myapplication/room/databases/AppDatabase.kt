package ru.meteor.myapplication.room.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.meteor.myapplication.models.Employee
import ru.meteor.myapplication.models.Speciality
import ru.meteor.myapplication.room.converters.SpecConverter
import ru.meteor.myapplication.room.dao.EmployeeDao
import ru.meteor.myapplication.room.dao.SpecialityDao

@Database(entities = [Employee::class, Speciality::class], version = 1)
@TypeConverters(SpecConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun specialityDao(): SpecialityDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "employee.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }

    public fun insertEmployees(employees: List<Employee>) {
        val count = employeeDao().insert(employees)
    }

    public fun insertSpecs(specs: List<Speciality>) {
        val count = specialityDao().insert(specs)
    }
}