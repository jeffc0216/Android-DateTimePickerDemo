package idv.ron.datetimepickerdemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static TextView tvDateTime;
    private static int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tvDateTime = (TextView) findViewById(R.id.tvDateTime);
        showNow();
    }

    private static void showNow() {
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        updateDisplay();
    }

    private static void updateDisplay() {
        tvDateTime.setText(new StringBuilder().append(mYear).append("-")
                .append(pad(mMonth + 1)).append("-").append(pad(mDay))
                .append(" ").append(pad(mHour)).append(":")
                .append(pad(mMinute)));
    }

    private static String pad(int number) {
        if (number >= 10)
            return String.valueOf(number);
        else
            return "0" + String.valueOf(number);
    }

    public static class DatePickerDialogFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new DatePickerDialog(
                    getActivity(), this, mYear, mMonth, mDay);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            mYear = year;
            mMonth = month;
            mDay = day;
            updateDisplay();
        }
    }

    public static class TimePickerDialogFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new TimePickerDialog(
                    getActivity(), this, mHour, mMinute, false);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            updateDisplay();
        }
    }

    public void onDateClick(View view) {
        DatePickerDialogFragment datePickerFragment = new DatePickerDialogFragment();
        FragmentManager fm = getSupportFragmentManager();
        datePickerFragment.show(fm, "datePicker");
    }

    public void onTimeClick(View view) {
        TimePickerDialogFragment timePickerFragment = new TimePickerDialogFragment();
        FragmentManager fm = getSupportFragmentManager();
        timePickerFragment.show(fm, "timePicker");
    }
}
