package ru.meteor.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_spec.*
import ru.meteor.myapplication.R
import ru.meteor.myapplication.adapters.CurrentEmployeeFragment
import ru.meteor.myapplication.adapters.EmployeeAdapter
import ru.meteor.myapplication.models.Employee
import ru.meteor.myapplication.models.Speciality
import ru.meteor.myapplication.room.dao.EmployeeDao
import ru.meteor.myapplication.room.databases.AppDatabase

class EmployeesFragment(private val spec: Speciality) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_spec, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listener: EmployeeAdapter.EmployeeClickInterface = object : EmployeeAdapter.EmployeeClickInterface{
            override fun onClick(employee: Employee) {
                fragmentManager?.beginTransaction()?.replace(R.id.mainFragment, CurrentEmployeeFragment(employee))
                    ?.addToBackStack(getString(R.string.employee_fragment))
                    ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)?.commit()
            }
        }


        // описываем функционал recycler view
        specRecyclerView?.setHasFixedSize(true)
        specRecyclerView?.layoutManager = LinearLayoutManager(
            view.context,
            LinearLayoutManager.VERTICAL,
            false
        )

        val employees =
            loadEmployees(spec.name, AppDatabase.getDatabase(view.context)?.employeeDao())
        //val employees: EmployeeDao? = AppDatabase.getDatabase(view.context)?.employeeDao()
        specRecyclerView.adapter = EmployeeAdapter(employees, listener)
    }

    private fun loadEmployees(name: String?, employeesDao: EmployeeDao?): List<Employee> {
        val employees: ArrayList<Employee> = ArrayList()
        if (employeesDao != null)
            for (employee in employeesDao.getAll()) {
                if (employee.specialty != null)
                    for (spec in employee.specialty) {
                        if (spec.name.equals(name)) {
                            employees.add(employee)
                            break
                        }
                    }
            }
        return employees
    }

}