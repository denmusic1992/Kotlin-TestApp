package ru.meteor.myapplication.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_current_employee.*
import ru.meteor.myapplication.R
import ru.meteor.myapplication.models.Employee
import ru.meteor.myapplication.models.Speciality
import java.util.*

class CurrentEmployeeFragment(val employee: Employee) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_current_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Устанавливаем иконку
        if (employee.avatrUrl != null && employee.avatrUrl?.isNotEmpty()!!)
            Picasso.get()
                .load(employee.avatrUrl)
                .centerCrop()
                .noFade()
                .fit()
                .error(R.drawable.ic_photo_black_24dp)
                .into(employeeImageDetail)
        else {
            employeeImageDetail.setImageResource(R.drawable.ic_photo_black_24dp)
        }
        // Имя
        employeeFirstName.text = Utilities.firstUpperCase(employee.fName)
        // Фамилия
        employeeSecondName.text = Utilities.firstUpperCase(employee.lName)
        // Возраст

        if (!employee.birthday.isNullOrEmpty()) {
            val year: Int = Utilities.parseDate(employee.birthday).get(Calendar.YEAR)
            val month: Int = Utilities.parseDate(employee.birthday).get(Calendar.MONTH)
            val day: Int = Utilities.parseDate(employee.birthday).get(Calendar.DAY_OF_MONTH)

            employeeAge.text = String.format(
                "%s %s",
                resources.getString(R.string.age_with_colon),
                Utilities.getAge(year, month, day)
            )

            if (Utilities.checkDateFormat(Utilities.replaceDate(employee.birthday))) {
                employeeBirthday.text = String.format(
                    "%s %s",
                    resources.getString(R.string.dbirthday_with_colomn),
                    Utilities.replaceDate(employee.birthday))
            } else {
                val monthPlusOne = month + 1
                employeeBirthday.text = String.format(
                    "%s %s.%s.%s",
                    resources.getString(R.string.dbirthday_with_colomn),
                    day,
                    monthPlusOne,
                    year
                )

            }
        } else {
            employeeBirthday.text = getString(R.string.dbirthday_empty)
            employeeAge.text = getString(R.string.empty_age)
        }

        // Профессия
        var fullSpeciality = ""
        for (speciality: Speciality in employee.specialty?.toList()!!) {
            fullSpeciality = "$fullSpeciality, ${speciality.name}"
        }

        employeeSpecDetail.text = fullSpeciality
    }
}