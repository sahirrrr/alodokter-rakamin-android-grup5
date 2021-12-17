package com.rakamin.alodokter.core.data.source.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rakamin.alodokter.core.data.source.remote.response.DoctorTimeItem
import com.rakamin.alodokter.core.data.source.remote.response.ScheduleItem

class TypeConverter {

    @TypeConverter
    fun listOfScheduleToJson(value: List<ScheduleItem>) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonOfScheduleToList(value: String) = Gson().fromJson(value, Array<ScheduleItem>::class.java).toList()

    @TypeConverter
    fun listOfDoctorTimeToJson(value: List<DoctorTimeItem>) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonOfDoctorTimeToList(value: String) = Gson().fromJson(value, Array<DoctorTimeItem>::class.java).toList()
}