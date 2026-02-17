package com.example.lab4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText etInputName = findViewById(R.id.etInputName);
        EditText etInputContent = findViewById(R.id.etInputContent);
        Button btnSaveNote = findViewById(R.id.btnSaveNote);
        boolean usePrefs = getIntent().getBooleanExtra("storage_mode", true);

        btnSaveNote.setOnClickListener(v -> {
            String name = etInputName.getText().toString();
            String content = etInputContent.getText().toString();

            if (name.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, R.string.warning_empty, Toast.LENGTH_SHORT).show();
                return;
            }

            if (usePrefs) {
                getSharedPreferences("NotesPref", MODE_PRIVATE).edit().putString(name, content).apply();
            } else {
                try (FileOutputStream fos = openFileOutput(name, MODE_PRIVATE)) {
                    fos.write(content.getBytes());
                } catch (Exception e) { e.printStackTrace(); }
            }
            finish();
        });
    }
}