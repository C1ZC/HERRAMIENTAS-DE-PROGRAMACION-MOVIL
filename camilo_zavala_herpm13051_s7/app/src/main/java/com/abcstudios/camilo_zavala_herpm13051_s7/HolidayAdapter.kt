package com.abcstudios.camilo_zavala_herpm13051_s7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HolidayAdapter(context: Context, private val holidays: List<Holiday>) : ArrayAdapter<Holiday>(context, 0, holidays) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.holiday_item, parent, false)

        val holiday = holidays[position]
        view.findViewById<TextView>(R.id.holidayName).text = holiday.nombre
        view.findViewById<TextView>(R.id.holidayDate).text = holiday.fecha

        return view
    }
}
