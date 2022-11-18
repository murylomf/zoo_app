package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
    EditText editTextNome;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterar();
            }
        });



        carregarDados();

    }

    public void carregarDados(){
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id",0);
        try {
            bancoDados = openOrCreateDatabase("zoo", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("SELECT id, nome FROM animal WHERE id = " + id.toString(), null);
            cursor.moveToFirst();
            editTextNome.setText(cursor.getString(1));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void alterar(){
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id",0);
        String valueNome;
        valueNome = editTextNome.getText().toString();
        try{
            bancoDados = openOrCreateDatabase("zoo", MODE_PRIVATE, null);
            String sql = "UPDATE animal SET nome=? WHERE id=?";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);
            stmt.bindString(1,valueNome);
            stmt.bindLong(2,id);
            stmt.executeUpdateDelete();
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}