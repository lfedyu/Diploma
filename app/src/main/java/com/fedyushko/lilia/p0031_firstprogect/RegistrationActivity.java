package com.fedyushko.lilia.p0031_firstprogect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    // UI references.
    private AutoCompleteTextView mNameView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordRepeatView;
    DBHelper dbHelper;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // Set up the login form.

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mNameView = (AutoCompleteTextView) findViewById(R.id.name);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordRepeatView = (EditText) findViewById(R.id.password_repeat);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }



    private String [] attemptLogin() { //return array of 3 strings: name, email, password

       String []results = new String[3];
        // Reset errors.
        mNameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordRepeatView.setError(null);

        // Store values at the time of the login attempt.
        String name = mNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String password_repeat = mPasswordRepeatView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //  Check for name.
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }
        else results[0]=name;//remember name

        //  Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else
        if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        //  Check for a valid password repeat
        if (TextUtils.isEmpty(password_repeat)) {
            mPasswordRepeatView.setError(getString(R.string.error_empty_repeat_password));
            focusView = mPasswordView;
            cancel = true;
        } else
        if (!password_repeat.equals(password)) {
            mPasswordRepeatView.setError(getString(R.string.error_invalid_password_repeat));
            focusView = mPasswordRepeatView;
            cancel = true;
        } else results[2]= password_repeat;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }else results[1] = email;

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        return results;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }





    @Override
    public void onClick(View v) {
        String []signUpInformation  = new String[3];
         signUpInformation =attemptLogin();

        // to open and return copy of database
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues(); // as an array with column name and its value
        // for example, column KEY_NAME, and its value that contains in signUpInformation[0]
        contentValues.put(DBHelper.KEY_NAME, signUpInformation[0]);
        contentValues.put(DBHelper.KEY_MAIL, signUpInformation[1]);
        contentValues.put(DBHelper.KEY_PASSWORD, signUpInformation[2]);

        database.insert(DBHelper.TABLE_USERS, null, contentValues);
        Intent intent = new Intent(this, ActivityTwo.class);
        startActivity(intent);

    }

}

