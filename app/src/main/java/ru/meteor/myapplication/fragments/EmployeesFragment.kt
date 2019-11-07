package ru.meteor.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_spec.*
import ru.meteor.myapplication.R
import ru.meteor.myapplication.adapters.EmployeeAdapter
import ru.meteor.myapplication.models.Employee
import ru.meteor.myapplication.models.Speciality
import ru.meteor.myapplication.room.dao.EmployeeDao
import ru.meteor.myapplication.room.databases.AppDatabase

class EmployeesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spec, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем записанный в bundle аргумент
        val bundle: Bundle? = this.arguments
        var speciality: Speciality? = null
        if (bundle != null) {
            speciality = bundle.getParcelable(specKey)
        }

        val listener: EmployeeAdapter.EmployeeClickInterface =
            object : EmployeeAdapter.EmployeeClickInterface {
                override fun onClick(employee: Employee) {

                    // Кладем во фрагмент нашу спецку
                    val currentEmployeeFragment = CurrentEmployeeFragment()
                    val employeeBundle = Bundle()
                    employeeBundle.putParcelable(CurrentEmployeeFragment.employeeKey, employee)
                    currentEmployeeFragment.arguments = employeeBundle

                    fragmentManager?.beginTransaction()?.replace(
                        R.id.mainFragment,
                        currentEmployeeFragment
                    )
                        ?.addToBackStack(getString(R.string.current_employee_fragment))
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

        if (speciality != null) {
            val employees =
                loadEmployees(
                    speciality.name,
                    AppDatabase.getDatabase(view.context)?.employeeDao()
                )
            //val employees: EmployeeDao? = AppDatabase.getDatabase(view.context)?.employeeDao()
            specRecyclerView.adapter = EmployeeAdapter(employees, listener)
        }
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

    companion object {
        const val specKey: String = "spec"
    }

}