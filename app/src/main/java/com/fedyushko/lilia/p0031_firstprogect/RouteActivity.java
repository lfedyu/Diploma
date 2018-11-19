package com.fedyushko.lilia.p0031_firstprogect;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fedyushko.lilia.p0031_firstprogect.view.DivideView;
import com.fedyushko.lilia.p0031_firstprogect.view.PlaceInfoView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RouteActivity extends AppCompatActivity {

    LinearLayout llMail;
    Toolbar toolbar;
    ImageView imageView;
    TextView textView;
    DBHelper dbHelper;
    String []distance = new String [] {"523m","264m","280m", "665m", "864m", "710m"};
    public List<Place> route = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_activity);

        llMail = (LinearLayout) findViewById(R.id.llMain);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.my_route);

        //setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.map_menu);
        route =  getIntent().getParcelableArrayListExtra("route");

        findViewById(R.id.id_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RouteActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        for (int i=0;i<route.size(); i++){
            //imageView = new ImageView(this);
            //imageView.setImageResource(route.get(i).getPhotoId());
           // textView = new TextView(this);
            //textView.setText(route.get(i).getName());
            PlaceInfoView placeInfoView = new PlaceInfoView(this);
            placeInfoView.setText(route.get(i).getName());
            placeInfoView.setImageResource(route.get(i).getPhotoId());
            llMail.addView(placeInfoView);
            if (i!=route.size()-1)
                {
                    DivideView divideView = new DivideView(this);
                    divideView.setText(distance[i]);// текст біля стрілочки
                    llMail.addView(divideView);
                }
        }
    }




}
