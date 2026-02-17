package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView lvNotesDisplay;
    private TextView tvCurrentStorage;
    private boolean useSharedPrefs = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotesDisplay = findViewById(R.id.lvNotesDisplay);
        tvCurrentStorage = findViewById(R.id.tvCurrentStorage);
        Button btnSwitch = findViewById(R.id.btnSwitchStorage);

        btnSwitch.setOnClickListener(v -> {
            useSharedPrefs = !useSharedPrefs;
            refreshUI();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshUI();
    }

    private void refreshUI() {
        tvCurrentStorage.setText(useSharedPrefs ? R.string.storage_pref : R.string.storage_file);
        ArrayList<String> notes = new ArrayList<>();
        if (useSharedPrefs) {
            Map<String, ?> allEntries = getSharedPreferences("NotesPref", MODE_PRIVATE).getAll();
            notes.addAll(allEntries.keySet());
        } else {
            for (String file : fileList()) notes.add(file);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        lvNotesDisplay.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        if (item.getItemId() == R.id.menu_add) {
            intent = new Intent(this, AddNoteActivity.class);
        } else if (item.getItemId() == R.id.menu_delete) {
            intent = new Intent(this, DeleteNoteActivity.class);
        }
        if (intent != null) {
            intent.putExtra("storage_mode", useSharedPrefs);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}