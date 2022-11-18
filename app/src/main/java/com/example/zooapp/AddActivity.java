package com.example.zooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText editTextNome;
    Button btnInsert;
    SQLiteDatabase bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextNome = (EditText) findViewById(R.id.editTextNome) ;
        btnInsert = (Button) findViewById(R.id.btnEdit);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarAnimal();
            }
        });

    }

    public void cadastrarAnimal(){
            if(!TextUtils.isEmpty(editTextNome.getText().toString())){
                try{
                    bancoDados = openOrCreateDatabase("zoo", MODE_PRIVATE, null);
                    String sql = "INSERT INTO animal (nome) VALUES (?)";
                    SQLiteStatement stmt = bancoDados.compileStatement(sql);
                    stmt.bindString(1,editTextNome.getText().toString());
                    stmt.executeInsert();
                    bancoDados.close();
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
}