package com.fedyushko.lilia.p0031_firstprogect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button button1;
    TextView myText;
    TextView myText2;
    TextView myText3;
    TextView myText4;



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
}