package com.example.shade.simplenote;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class NoteBody extends AppCompatActivity {

    EditText noteEdittext;
    String noteText;
    DatabaseHelper db;
    Long noteID;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_body);

        fab=(FloatingActionButton) findViewById(R.id.fabdelete);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Alert!");
                builder.setMessage("Do you want to delete this note?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteNote(noteID);
                        finish();
                    }
                });
                builder.show();

            }
        });

        db = new DatabaseHelper(this);


        noteEdittext=(EditText) findViewById(R.id.notebodyEditText);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            noteID = extras.getLong("id");
            noteText=extras.getString("body");
        }
        noteEdittext.setText(noteText);

        noteEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                db.updateNote(noteID,s.toString());


            }
        });




    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
