package com.srit.collegedesigns.myTypes

enum class Section{
    NOTIFICATION,
    SCHEDULE,
    LIBRARY,
    HOMEWORK
}

enum class WeekDays(val dayName:String) {

    SATURDAY("السبت"),
    SUNDAY("الاحد"),
    MONDAY("الاثنين"),
    TUESDAY( "الثلاثاء"),
    WEDNESDAY("الاربعاء"),
    THURSDAY( "الخميس"),
    FRIDAY("الجمعة");

    companion object{
        fun getDayName(pos: Int)= values()[pos].dayName
    }
}

enum class NotificationType{
    STUDENT,
    SECTION
}