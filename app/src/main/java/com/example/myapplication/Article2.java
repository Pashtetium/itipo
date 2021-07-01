package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Article2 extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    TextView txt1, txt2, txt3, txt4;
    SeekBar SbSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article2);
        txt1 = (TextView) findViewById(R.id.textView);
        txt2 = (TextView) findViewById(R.id.textView2);

        SbSize = (SeekBar) findViewById(R.id.seekBar);
        SbSize.setOnSeekBarChangeListener(this);
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean set = prefs.getBoolean(getString(R.string.UserSettings), false);
        if (!set) {} else {
            float fSize = Float.parseFloat(prefs.getString(getString(R.string.Size), "14"));
            int min = 10; int max = 30;
            String color = prefs.getString(getString(R.string.Color), "Черный");
            String regular = prefs.getString(getString(R.string.style), "");
            int typeface = Typeface.NORMAL;
            if (regular.contains("Полужирный"))
                typeface += Typeface.BOLD;
            if (regular.contains("Курсив"))
                typeface += Typeface.ITALIC;
            txt1.setTypeface(null, typeface);
            txt2.setTypeface(null, typeface);


            if (fSize<min) fSize = min;
            if (fSize>max) fSize = max;
            txt1.setTextSize(fSize);
            txt2.setTextSize(fSize);


            int col = Color.BLACK;
            if (color.contains("Белый"))
                col += Color.WHITE;
            if (color.contains("Красный"))
                col += Color.RED;
            if (color.contains("Синий"))
                col += Color.BLUE;
            if (color.contains("Зеленый"))
                col += Color.GREEN;
            if (color.contains("Желтый"))
                col += Color.YELLOW;
            if (color.contains("Голубой"))
                col += Color.CYAN;
            if (color.contains("Светло-фиолетовый"))
                col += Color.MAGENTA;
            txt1.setTextColor(col);
            txt2.setTextColor(col);

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        txt1.setTextSize(progress);
        txt2.setTextSize(progress);

    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this, Integer.toString(seekBar.getProgress()), Toast.LENGTH_SHORT).show();
    }


}
