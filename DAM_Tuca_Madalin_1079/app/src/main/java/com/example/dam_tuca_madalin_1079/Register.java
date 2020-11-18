package com.example.dam_tuca_madalin_1079;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.Calendar;
import java.util.Date;

public class Register extends AppCompatActivity {

    private EditText etName;
    private EditText etSurname;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etVerifPassword;
    private DatePicker dpBirth;
    private EditText etCounty;
    private EditText etCity;
    private CheckBox cbConsent;
    private Button submit;

    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        setupListeners();
    }

    private void initViews(){
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etVerifPassword = findViewById(R.id.etRetypePassword);
        dpBirth = findViewById(R.id.dpBirth);
        etCounty = findViewById(R.id.etCounty);
        etCity = findViewById(R.id.etCity);
        cbConsent = findViewById(R.id.cbConsent);
        submit = findViewById(R.id.btnSubmit);
    }

    private void setupListeners(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateContent()){
                    String name = etName.getText().toString();
                    String surname = etSurname.getText().toString();
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    Date birth = getDateFromDatePicker(dpBirth);
                    String county = etCounty.getText().toString();
                    String city = etCity.getText().toString();
                    User newUser = new User(name,surname,email,password,birth,county,city);
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    intent.putExtra("emailKey", email);
                    intent.putExtra("passKey", password);
                    startActivity(intent);
                };
            }
        });
    }

    private boolean validateContent(){
        boolean valid = true;
        if(etName.getText().toString().isEmpty()){
            StyleableToast.makeText(this, "You must type your name!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }else if(!etName.getText().toString().matches("^[a-zA-Z]+$")){
            StyleableToast.makeText(this,"Name must only contain letters!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        else if(etSurname.getText().toString().isEmpty()){
            StyleableToast.makeText(this, "You must type your surname!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }else if(!etSurname.getText().toString().matches("^[a-zA-Z]+$")){
            StyleableToast.makeText(this,"Surname must only contain letters!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        else if(etEmail.getText().toString().isEmpty()){
            StyleableToast.makeText(this,"You must type your email!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        else if(!etEmail.getText().toString().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            StyleableToast.makeText(this, "Email must have a valid format!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        else if(etPassword.getText().toString().isEmpty()){
            StyleableToast.makeText(this,"You must type your password!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        else if(etVerifPassword.getText().toString().isEmpty()){
            StyleableToast.makeText(this,"You must verify your password!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }else if(etPassword.getText().toString().compareTo(etVerifPassword.getText().toString()) != 0){
            StyleableToast.makeText(this, "First password does not match the verify password!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }else if(etCounty.getText().toString().isEmpty()){
            StyleableToast.makeText(this,"You must type your county!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }else if(!etCounty.getText().toString().matches("^[a-zA-Z]+$")){
            StyleableToast.makeText(this,"County must only contain letters!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }else if(etCity.getText().toString().isEmpty()){
            StyleableToast.makeText(this,"You must type a city!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }else if(!etCity.getText().toString().matches("^[a-zA-Z]+$")){
            StyleableToast.makeText(this,"City must contain only letters!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }else if(!cbConsent.isChecked()){
            StyleableToast.makeText(this,"You must agree with the terms!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        if(valid){
            StyleableToast.makeText(this,"Welcome "+etName.getText().toString()+"! "+"You may login", Toast.LENGTH_LONG, R.style.successToast).show();
        }
        return valid;
    }
}