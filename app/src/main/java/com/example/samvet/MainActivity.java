package com.example.samvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText name,password;
    private Button Confirm,Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Register= findViewById(R.id.register);
        Confirm=findViewById(R.id.confirm);
        name=findViewById(R.id.kullanciadi);
        password=findViewById(R.id.sifre);

        dbHelper databaseHelper = new dbHelper(getApplicationContext());
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.createUser(name.getText().toString(),password.getText().toString());
                final Intent intent = new Intent(MainActivity.this, CreateUserHandler.class);
                finish();
                startActivity(intent);
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (databaseHelper.isUserValid(name.getText().toString(),password.getText().toString())){
                    Intent inte = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(inte);
                }
            }
        });
    }
}