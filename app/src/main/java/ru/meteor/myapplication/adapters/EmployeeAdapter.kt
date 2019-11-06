package ru.meteor.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import ru.meteor.myapplication.R
import ru.meteor.myapplication.models.Employee
import java.util.*

class EmployeeAdapter(
    private val employees: List<Employee>,
    private val listener: EmployeeClickInterface
) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    interface EmployeeClickInterface {
        fun onClick(employee: Employee)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        // Устанавливаем иконку
        if (employees[position].avatrUrl != null && employees[position].avatrUrl?.isNotEmpty()!!)
            Picasso.get()
                .load(employees[position].avatrUrl)
                .centerCrop()
                .noFade()
                .fit()
                .error(R.drawable.ic_photo_black_24dp)
                .into(holder.imageView)
        // Имя
        holder.nameTextView.text = Utilities.firstUpperCase(employees[position].fName)
        // Фамилия
        holder.surnameTextView.text = Utilities.firstUpperCase(employees[position].lName)
        // Возраст

        if (!employees[position].birthday.isNullOrEmpty()) {
            val year: Int = Utilities.parseDate(employees[position].birthday!!).get(Calendar.YEAR)
            val month: Int = Utilities.parseDate(employees[position].birthday!!).get(Calendar.MONTH)
            val day: Int =
                Utilities.parseDate(employees[position].birthday!!).get(Calendar.DAY_OF_MONTH)

            holder.employeeAge.text = String.format(
                "%s %s",
                holder.itemView.context.getString(R.string.age_with_colon),
                Utilities.getAge(year, month, day)
            )
        } else {
            holder.employeeAge.text = holder.itemView.context.getString(R.string.empty_age)
        }

        holder.itemView.setOnClickListener {
            listener.onClick(employees[position])
        }
    }

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: CircleImageView = itemView.findViewById(R.id.employeeImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameEmployeeTextView)
        val surnameTextView: TextView = itemView.findViewById(R.id.surnameEmployeeTextView)
        val employeeAge: TextView = itemView.findViewById(R.id.specialityTextView)
    }
}