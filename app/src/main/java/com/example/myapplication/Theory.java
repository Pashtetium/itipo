package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class Theory extends AppCompatActivity {
    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);
        fillExampleList();
        setUpRecyclerView();


    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("Шифрование методом простой замены"));
        exampleList.add(new ExampleItem("Шифрование методом полиалфавитной замены"));
        exampleList.add(new ExampleItem("Шифрование информации методом перестановок с использованием маршрутов Гамильтона"));
        exampleList.add(new ExampleItem("Цифровая подпись"));
        exampleList.add(new ExampleItem("Брандмауэр"));
        exampleList.add(new ExampleItem("Антивирусы"));
        exampleList.add(new ExampleItem("Программные закладки"));
        exampleList.add(new ExampleItem("Клавиатурные шпионы"));
        exampleList.add(new ExampleItem("Шифрование информации методом перестановок"));
        exampleList.add(new ExampleItem("Шифрование информации методом гаммирования"));

    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 0){
                    startActivity(new Intent(".Article1"));

                }

                else if (position == 1){
                    Intent intent = new Intent(".Article2");
                    startActivity(intent);

                }

                else if (position == 2){
                    Intent intent = new Intent(".Article3");
                    startActivity(intent);

                }

                else if (position == 3){
                    Intent intent = new Intent(".Article4");
                    startActivity(intent);

                }

                else if (position == 4){
                    Intent intent = new Intent(".Article5");
                    startActivity(intent);

                }
                else if (position == 5){
                    Intent intent = new Intent(".Article6");
                    startActivity(intent);

                }
                else if (position == 6){
                    Intent intent = new Intent(".Article7");
                    startActivity(intent);

                }
                else if (position == 7){
                    Intent intent = new Intent(".Article8");
                    startActivity(intent);

                }
                else if (position == 8){
                    Intent intent = new Intent(".Article9");
                    startActivity(intent);

                }
                else if (position == 9){
                    Intent intent = new Intent(".Article10");
                    startActivity(intent);

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}