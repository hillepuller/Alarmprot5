package com.example.alarmprot;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarm_manager;
    //upatere om alermen er off/on
    TextView update_text;

    // asssisent der henter ting som..
        /*
        Loading common resources
        Creating dynamic views
        Displaying Toast messages
        Launching Activities etc.
        */
            Context context;
    //  PendingIntent giver tilladelsen videre-> "tilladelse lever videre."
    PendingIntent pending_intent;
    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    private String format = "";
    Intent intentSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize our alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // we added: http://www.tutorialspoint.com/android/android_timepicker_control.htm
        // date 6/4/16
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        //initialize our text update box
        // on/off text:
        update_text = (TextView) findViewById(R.id.update_text);

        time = (TextView) findViewById(R.id.textView1);
        //?
        // en fancy måde at lave et default calendar object på.
        // det gør mansådan og ikke som normal, det virker i hvert fald ikke.
        calendar = Calendar.getInstance();

        // en måde at den nuværende tid på.
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        // create new intent, inisiate new class.
        intentSettings = new Intent(this, MySettings.class);



        // create an intent to the Alarm Receiver class
        // Intent my_intent = new Intent(this.context, Alarm_Receiver.class);

        // initialize start button
        //Button alarm_on = (Button) findViewById(R.id.alarm_on);

        showTime(hour, min);
    }

    //metoden bliver kaldt ved et clik på "set time knappen".
    //View view kaldes når der bliver trykket på et view. (en button kan være et View)
    // parametren  "(View view) er egenligt  "button" inden i content_main.xml
    public void setTime(View view) {
        Log.e("Hi", "setTime: ");
        //to finish the intent, call the startActivity() method,
        // passing it the Intent object
        startActivity(intentSettings);
        int hour = timePicker1.getHour();

        int min = timePicker1.getMinute();


        //calendar.set(Calendar.HOUR_OF_DAY, timePicker1.getHour());
        //calendar.set(Calendar.MINUTE, timePicker1.getMinute());

        //System.out.println(calendar);
        showTime(hour, min);
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        }
        else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        // vi sætter textwiev time til til at "printe" den nuværende tid i am og pm format.
        //append: modtager alle data typer hægter dem efter hinanden her ibland int og chars, String
        // og laver det til en string.
        //Stringbuilder(): laver en string vha. append.

        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }
    // deafault.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // deafault.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
