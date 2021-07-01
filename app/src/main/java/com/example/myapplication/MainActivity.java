package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_theory, btn_test, btn_stat, btn_help, btn_settings, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton ();
    }

    public void addListenerOnButton() {
        btn_theory = (Button)findViewById(R.id.btn_theory);
        btn_theory.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Intent intent = new Intent(".Theory");
                        startActivity(intent);
                    }
                }

        );
        btn_test = (Button)findViewById(R.id.btn_test);
        btn_test.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Intent intent = new Intent(".Tests");
                        startActivity(intent);
                    }
                }

        );

        btn_stat = (Button)findViewById(R.id.btn_stat);
        btn_stat.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Intent intent = new Intent(".statistics");
                        startActivity(intent);
                    }
                }

        );

        btn_help = (Button)findViewById(R.id.btn_help);
        btn_help.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Intent intent = new Intent(".help");
                        startActivity(intent);
                    }
                }

        );

        btn_settings = (Button)findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        Intent intent = new Intent(".Settings");
                        startActivity(intent);
                    }
                }

        );

        btn_exit = (Button)findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
                        finish();
                    }
                }

        );
    }






}
