package com.fedyushko.lilia.p0031_firstprogect;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewTripActivity extends AppCompatActivity implements View.OnClickListener {


    TextView startDate;
    TextView endDate;
    Toolbar toolbar;
    Button pressContinue;
    private String start, end;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_trip_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startDate = (TextView) findViewById(R.id.startDate);
        endDate = (TextView) findViewById(R.id.endDate);
        pressContinue = (Button) findViewById(R.id.press_continue);
        pressContinue.setOnClickListener(this);

        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);


    }

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        //The listener used to indicate the user has finished selecting a date.
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            start=updateLabel(startDate, myCalendar);
        }

    };




    Calendar myCalendar2 = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        //The listener used to indicate the user has finished selecting a date.
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, monthOfYear);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            end =updateLabel(endDate, myCalendar2);
        }

    };


    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.startDate:
                new DatePickerDialog(NewTripActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    getStart();
                break;
            case R.id.endDate:
                new DatePickerDialog(NewTripActivity.this, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
                    getEnd();
                break;
            case R.id.press_continue:
                //createListOfPlaces
                //add to database dates
                Intent intent = new Intent(this, ListOfPlaces_Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    private String updateLabel(TextView v, Calendar calendar) {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        v.setText(sdf.format(calendar.getTime()));
        String result = sdf.format(calendar.getTime());
        return result;
    }
}
