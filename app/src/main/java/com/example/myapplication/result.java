package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.Time;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class result extends AppCompatActivity {
    ResultDBHelper dbHelper;
    TextView result, answers1, answers2, answers3;
    ArrayList<String> answer, answer2;
    Button btn;
    int[] bally = {1,21,31,41,51,61,71,81,91};
    int[] bally2 = {2,3,4,22,23,24,32,33,34,42,43,44,52,53,54,62,63,64,72,73,74,82,83,84,92,93,94};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btn = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.lastscore);
        answers1 = (TextView) findViewById(R.id.answers);
        answers2 = (TextView) findViewById(R.id.answers1);
        answers3 = (TextView) findViewById(R.id.answers2);
        Time time = new Time(Time.getCurrentTimezone());
        time.setToNow();
        final String timeStr = time.format("%d.%m.%Y %H:%M:%S");

        dbHelper = new ResultDBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        final ArrayList<String> answer, answer2;
        final Intent intent = getIntent();

        int k; k = 1;
        int lastscore = intent.getIntExtra("lastscore", 0);
        answer = intent.getStringArrayListExtra("answers");
        answer2 = intent.getStringArrayListExtra("answers2");


        for (int i = 0; i < bally.length; i++)
            if (lastscore == bally[i]) {
                result.setText("Ваш результат: " + lastscore + " балл"); }
        for (int i = 0; i < bally2.length; i++)
            if (lastscore == bally2[i]) {
                    result.setText("Ваш результат: " + lastscore + " балла");
                break;
            } else result.setText("Ваш результат: " + lastscore + " баллов");
        if (lastscore == 1) result.setText("Ваш результат: " + lastscore + " балл");
        int r = 0;
        answers1.setText("\n\n№");
        answers2.setText("\n\nВаш ответ\n");
        answers3.setText("\n\nПравильный ответ");
        for (int i = 0; i < answer.size(); i++){
            SpannableStringBuilder ssb = new SpannableStringBuilder(answer.get(i));
            ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);
            ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.GREEN);
            if (answer.get(i).equals(answer2.get(i))) {
                ssb.setSpan(fcsGreen, 0, answer.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                r++;
            } else {
                ssb.setSpan(fcsRed, 0, answer.get(i).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
         answers1.append("\n" + k);
         k++;
         answers2.append(ssb);
         answers2.append("\n");
         answers3.append("\n" + "  " + answer2.get(i));
        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, answers2.getText().toString()+answers3.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        final double j = r * 100.0;
        final double l = (int) (j/answer.size());

        contentValues.put(ResultDBHelper.KEY_TIME, timeStr);
//        contentValues.put(ResultDBHelper.KEY_COUNTTOTAL, all);
        contentValues.put(ResultDBHelper.KEY_PASSED, answer.size());
        contentValues.put(ResultDBHelper.KEY_PERCENT, l);
        database.insert(ResultDBHelper.TABLE_RESULT, null, contentValues);
}
}