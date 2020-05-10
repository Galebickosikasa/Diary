package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private myAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new myAdapter(this);
        recyclerView.setAdapter(mAdapter);
        toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);
        sp = getSharedPreferences("Notes", MODE_PRIVATE);
//        sp.edit().clear().apply();
        getNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.noteAdd) {
            Intent intent = new Intent(this, AddNewNoteActivity.class);
            startActivity(intent);
            getNotes();
        }
        return true;
    }

    public void getNotes () {
        mAdapter.clearNotes();
        int cnt = sp.getInt("cnt", 0);
        Log.e ("kek", "" + cnt);
        if (cnt == 0) Toast.makeText(this, "Пока нет заданий", Toast.LENGTH_SHORT).show ();
        else {
            for (int i = 0; i < cnt; ++i) {
                String text, important;
                boolean done;
                text = sp.getString("text_" + i, "");
                important = sp.getString("important_" + i, "");
                done = sp.getBoolean("done_" + i, false);
                mAdapter.addNote (new Note (text, important, done));
            }
        }
    }
}
