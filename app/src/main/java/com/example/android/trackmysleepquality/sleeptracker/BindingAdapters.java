package com.example.android.trackmysleepquality.sleeptracker;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import com.example.android.trackmysleepquality.R;
import com.example.android.trackmysleepquality.UtilKt;
import com.example.android.trackmysleepquality.database.SleepNight;

public class BindingAdapters {

    @BindingAdapter("sleepImage")
    public static void setSleepImage(ImageView view, SleepNight item) {
        int resource;
        switch (item.getSleepQuality()) {
            case 0: resource = R.drawable.ic_sleep_0; break;
            case 1: resource = R.drawable.ic_sleep_1; break;
            case 2: resource = R.drawable.ic_sleep_2; break;
            case 3: resource = R.drawable.ic_sleep_3; break;
            case 4: resource = R.drawable.ic_sleep_4; break;
            case 5: resource = R.drawable.ic_sleep_5; break;
            default: resource = R.drawable.ic_sleep_active; break;
        }
        view.setImageResource(resource);
    }

    @BindingAdapter("sleepDurationFormatted")
    public static void setSleepDurationFormatted(TextView view, SleepNight item) {
        view.setText(UtilKt.convertDurationToFormatted(
            item.getStartTimeMilli(),
            item.getEndTimeMilli(),
            view.getContext().getResources()
        ));
    }

    @BindingAdapter("sleepQualityString")
    public static void setSleepQualityString(TextView view, SleepNight item) {
        view.setText(UtilKt.convertNumericQualityToString(
            item.getSleepQuality(),
            view.getContext().getResources()
        ));
    }
}
