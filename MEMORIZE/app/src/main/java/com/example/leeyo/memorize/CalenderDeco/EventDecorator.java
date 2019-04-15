package com.example.leeyo.memorize.CalenderDeco;

import android.annotation.SuppressLint;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

import static com.example.leeyo.memorize.R.color.colorMainDark;
import static com.example.leeyo.memorize.R.color.colorGray;


public class EventDecorator implements DayViewDecorator {

    private HashSet<CalendarDay> dates;

    public EventDecorator(Collection<CalendarDay> dates) {
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(false);
        view.addSpan(new ForegroundColorSpan(colorMainDark));
        view.addSpan(new DotSpan(7, colorGray));
    }
}