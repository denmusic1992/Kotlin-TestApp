package ru.meteor.myapplication.ui

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import ru.meteor.myapplication.R
import ru.meteor.myapplication.fragments.SpecsFragment
import ru.meteor.myapplication.models.Employee
import ru.meteor.myapplication.models.Response
import ru.meteor.myapplication.models.Speciality
import ru.meteor.myapplication.retrofit.CommonCallback
import ru.meteor.myapplication.retrofit.RetrofitService
import ru.meteor.myapplication.room.dao.EmployeeDao
import ru.meteor.myapplication.room.dao.SpecialityDao
import ru.meteor.myapplication.room.databases.AppDatabase
import java.util.*

class MainActivity : CommonActivity() {


    override fun initControls() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize(getString(R.string.app_name))

        if(isLaunchSuccessful) {
            getEmployees()
        }
    }

    private fun getEmployees() {
        showDialog()

        val callback = object : CommonCallback<Response<List<Employee>>>(this) {
            override fun onSuccess(content: Response<List<Employee>>) {
                val data = content.response
                AsyncTask.execute {
                    // В асинхроне записываем данные в БД

                    //AppDatabase.getDatabase(applicationContext)?.insertEmployees(data)
                    val database: AppDatabase = AppDatabase.getDatabase(applicationContext)!!
                    val employeesDB: EmployeeDao = database.employeeDao()
                    employeesDB.insert(data)

                    val specs: HashSet<Speciality> = LinkedHashSet()
                    for(employee: Employee in data) {
                        if(employee.specialty != null) {
                            for(spec: Speciality in employee.specialty)
                                specs.add(spec)
                        }
                    }
                    val specsDB: SpecialityDao = database.specialityDao()
                    specsDB.insert(specs.toList())

                    loadToFragments()
                }
            }
        }

        RetrofitService.getRetrofitService().getEmployees().enqueue(callback)
    }

    private fun loadToFragments() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragment, SpecsFragment())
            .addToBackStack(getString(R.string.spec_fragment))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}
