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
import ru.meteor.myapplication.adapters.SpecsAdapter
import ru.meteor.myapplication.models.Speciality
import ru.meteor.myapplication.room.dao.SpecialityDao
import ru.meteor.myapplication.room.databases.AppDatabase

class SpecsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spec, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listener: SpecsAdapter.SpecClickInterface = object : SpecsAdapter.SpecClickInterface{
            override fun onClick(spec: Speciality) {
                // Кладем во фрагмент нашу спецку
                val employeeFragment = EmployeesFragment()
                val bundle = Bundle()
                bundle.putParcelable(EmployeesFragment.specKey, spec)
                employeeFragment.arguments = bundle

                fragmentManager?.beginTransaction()?.replace(R.id.mainFragment, employeeFragment)
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

        val specs: SpecialityDao? = AppDatabase.getDatabase(view.context)?.specialityDao()
        if (specs != null)
            specRecyclerView.adapter = SpecsAdapter(specs.getAll(), listener)
    }
}