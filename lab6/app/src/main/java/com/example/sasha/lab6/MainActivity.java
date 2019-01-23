package com.example.sasha.lab6;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton animationButton;
    private Button startButton, showCalendar;
    private Animation buttonAnimation, buttonReverseAnimation, calendarAnimation;

    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation);
        buttonReverseAnimation = AnimationUtils.loadAnimation(this, R.anim.button_reverse_animation);

        startButton = (Button) findViewById(R.id.startButton);
        animationButton = (FloatingActionButton) findViewById(R.id.animationButton);
        calendarAnimation = AnimationUtils.loadAnimation(this, R.anim.calendar_animation);

        showCalendar = (Button) findViewById(R.id.showCalendar);
        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setVisibility(View.INVISIBLE);

        animationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationButton.startAnimation(buttonAnimation);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationButton.startAnimation(buttonReverseAnimation);
            }
        });

        showCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setVisibility(View.VISIBLE);
                calendar.startAnimation(calendarAnimation);
            }
        });
    }

}
