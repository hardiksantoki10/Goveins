package com.goviens.android.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {

    TimePickerDialog.OnTimeSetListener onTimeSet;

    public TimePickerFragment() {

    }
    public void setCallBack(TimePickerDialog.OnTimeSetListener ontime) {
        onTimeSet = ontime;
    }
    @SuppressLint("NewApi")
    private int hour, minute;
    public void setArguments(Bundle args) {
        super.setArguments(args);
        hour = args.getInt("hour");
        minute = args.getInt("minute");

    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();

        final TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), onTimeSet,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE) + 5, true);

        return timePickerDialog;
    }
}