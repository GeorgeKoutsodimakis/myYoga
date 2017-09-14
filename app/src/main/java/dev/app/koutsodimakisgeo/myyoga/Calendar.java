package dev.app.koutsodimakisgeo.myyoga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import dev.app.koutsodimakisgeo.myyoga.Custom.WorkoutDoneDecorator;
import dev.app.koutsodimakisgeo.myyoga.Database.YogaDB;

public class Calendar extends AppCompatActivity {

    MaterialCalendarView materialCalendarView;
    HashSet<CalendarDay> list = new HashSet<>();
    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        yogaDB = new YogaDB(this);
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar);
        List<String> workoutDay = yogaDB.getWorkDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();
        for (String value:workoutDay) {
                convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
            materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));

        }
    }
}
