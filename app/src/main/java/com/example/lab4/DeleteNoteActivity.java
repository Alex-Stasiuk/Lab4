package com.example.lab4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class DeleteNoteActivity extends AppCompatActivity {
    private String selectedNote = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        ListView lvSelectionList = findViewById(R.id.lvSelectionList);
        Button btnExecuteDelete = findViewById(R.id.btnExecuteDelete);
        boolean usePrefs = getIntent().getBooleanExtra("storage_mode", true);

        ArrayList<String> list = new ArrayList<>();
        if (usePrefs) {
            Map<String, ?> notes = getSharedPreferences("NotesPref", MODE_PRIVATE).getAll();
            list.addAll(notes.keySet());
        } else {
            for (String f : fileList()) list.add(f);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, list);
        lvSelectionList.setAdapter(adapter);
        lvSelectionList.setOnItemClickListener((p, v, pos, id) -> selectedNote = list.get(pos));

        btnExecuteDelete.setOnClickListener(v -> {
            if (selectedNote.isEmpty()) return;
            if (usePrefs) {
                getSharedPreferences("NotesPref", MODE_PRIVATE).edit().remove(selectedNote).apply();
            } else {
                deleteFile(selectedNote);
            }
            finish();
        });
    }
}