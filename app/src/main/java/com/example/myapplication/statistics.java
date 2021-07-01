package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class statistics extends AppCompatActivity {
    ResultDBHelper dbHelper;
    TextView txt1, txt2, txt3 , txt4, txt5, txtId, txt;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent starterIntent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        txt1 = (TextView) findViewById(R.id.textView_date);

        txt3 = (TextView) findViewById(R.id.textView_pass);
        txt4 = (TextView) findViewById(R.id.textView_percent);

        txtId = (TextView) findViewById(R.id.textView_number);
        txt = (TextView) findViewById(R.id.textView000);
        btn = (Button) findViewById(R.id.button);
        dbHelper = new ResultDBHelper(this);
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(ResultDBHelper.TABLE_RESULT, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(ResultDBHelper.KEY_ID);
            int TimeIndex = cursor.getColumnIndex(ResultDBHelper.KEY_TIME);
            int countIndex = cursor.getColumnIndex(ResultDBHelper.KEY_COUNTTOTAL);
            int passedIndex = cursor.getColumnIndex(ResultDBHelper.KEY_PASSED);
            int percentIndex = cursor.getColumnIndex(ResultDBHelper.KEY_PERCENT);
                do {txtId.append("\n"+cursor.getInt(idIndex));
                    txt1.append("\n"+cursor.getString(TimeIndex));

                    txt3.append("\n"+cursor.getString(passedIndex));
                    txt4.append("\n"+cursor.getString(percentIndex)+"%");

                } while (cursor.moveToNext());
        } else {
            txt.setText("Вы еще не проходили ни одного теста");
            txt1.setText("");

            txt3.setText("");
            txt4.setText("");

        }
        cursor.close();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.delete(ResultDBHelper.TABLE_RESULT, null, null);
                dbHelper.close();
                finish();
                startActivity(starterIntent);
            }
        });

    }
}
