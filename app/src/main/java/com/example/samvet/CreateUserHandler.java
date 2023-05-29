package com.example.samvet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class CreateUserHandler extends AppCompatActivity {
    private EditText mail;
    private EditText pass;
    private Button register;
    // @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_handler);
        mail = findViewById(R.id.Gmail);
        pass = findViewById(R.id.Gmailpass);
        register = findViewById(R.id.Regis);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailText = mail.getText().toString();
                String passText = pass.getText().toString();
                if(isValid(mailText,"^(.+)@(.+)$") && pass.length()>5){
                    new dbHelper(getApplicationContext()).createUser(mailText,passText);
                    startActivity(new Intent(CreateUserHandler.this, MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(CreateUserHandler.this, "lutfen e-mail adresi ve en az 6 haneli şifre girin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isValid(String mail, String regex){
        return Pattern.compile(regex)
                .matcher(mail)
                .matches();
    }
}