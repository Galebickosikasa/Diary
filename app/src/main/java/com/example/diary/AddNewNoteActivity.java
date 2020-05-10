package com.example.diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNoteActivity extends AppCompatActivity {
    EditText text, important;
    Button button;
    SharedPreferences sp;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        sp = getSharedPreferences("Notes", MODE_PRIVATE);
        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Diary");
        text = findViewById(R.id.textNote);
        important = findViewById(R.id.importantNote);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cnt = sp.getInt("cnt", 0);
                String s1 = text.getText().toString(), s2 = important.getText().toString();
                SharedPreferences.Editor e = sp.edit();
                e.putString("text_" + cnt, s1);
                e.putString("important_" + cnt, s2);
                e.putBoolean("done_" + cnt, false);
                e.putInt("cnt", cnt + 1);
                e.apply();
                Log.e ("kek", "" + sp.getInt("cnt", 0));
                finish ();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.casual_toolbar, menu);
        return true;
    }

}
