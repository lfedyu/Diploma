package com.fedyushko.lilia.p0031_firstprogect;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    private Button button1;
    private TextView myText;
    private TextView myText2;
    private TextView myText3;
    private TextView myText4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        myText = (TextView) findViewById(R.id.myText);
        myText2 = (TextView) findViewById(R.id.myText2);
        myText3 = (TextView) findViewById(R.id.myText3);
        myText4 = (TextView) findViewById(R.id.myText4);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        myText3.setOnClickListener(this);
        myText4.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent = new Intent(this, ActivityTwo.class);
                startActivity(intent);
                break;
            case R.id.myText3:
                Intent intent_login = new Intent(this, MapsActivity.class);
                startActivity(intent_login);
                break;
            case R.id.myText4:
                Intent intent_registration = new Intent(this, RegistrationActivity.class);
                startActivity(intent_registration);
                break;
            default:
                break;

        }
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


//for prossesing menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.action_regestration ){
            Intent intent_regestration = new Intent(this, RegistrationActivity.class);
            startActivity(intent_regestration);

        }
        return super.onOptionsItemSelected(item);
    }

    //google services checker
    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS){
            //everything is fine and user can make request
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //the error has occured but we can resolve it (wrong version)
            Log.d(TAG, "isServicesOK: the error has occured but we can resolve it(wrong version)");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            //there is a problem and we can't fix it
            Toast.makeText(this, "You can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}