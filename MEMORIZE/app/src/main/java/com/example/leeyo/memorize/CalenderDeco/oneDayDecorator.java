package com.example.leeyo.memorize.CalenderDeco;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;

import com.example.leeyo.memorize.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;

import static com.example.leeyo.memorize.R.color.colorWhite;

public class oneDayDecorator implements DayViewDecorator {

    private final Drawable drawable;
    private CalendarDay date;

    public oneDayDecorator(Activity context) {
        drawable = context.getResources().getDrawable(R.drawable.circle);
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
        view.addSpan(new ForegroundColorSpan(colorWhite));
    }

    /*
      We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
     */
    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}