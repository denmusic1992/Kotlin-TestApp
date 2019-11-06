package ru.meteor.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.meteor.myapplication.R
import ru.meteor.myapplication.models.Speciality

class SpecsAdapter(var specs: List<Speciality>, private val listener: SpecClickInterface) :
    RecyclerView.Adapter<SpecsAdapter.SpecsViewHolder>() {

    interface SpecClickInterface {
        fun onClick(spec: Speciality)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_spec, parent, false)
        return SpecsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return specs.size
    }

    override fun onBindViewHolder(holder: SpecsViewHolder, position: Int) {
        holder.specTextView.text = specs[position].name
        holder.itemView.setOnClickListener {
            listener.onClick(specs[position])
        }
    }

    class SpecsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var specTextView: TextView = itemView.findViewById(R.id.specTextView)

    }
}