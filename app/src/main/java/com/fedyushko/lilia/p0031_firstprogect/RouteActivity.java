package com.fedyushko.lilia.p0031_firstprogect;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fedyushko.lilia.p0031_firstprogect.view.DivideView;
import com.fedyushko.lilia.p0031_firstprogect.view.PlaceInfoView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.List;

public class RouteActivity extends AppCompatActivity {

    //private MainActivity mainActivity;
    private static final String TAG = "RouteActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
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

        //if there is right version of Google Play Services Map will be shown
        if(isServicesOK()) {
            findViewById(R.id.id_map).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RouteActivity.this, GoogleMapsActivity.class);
                    startActivity(intent);
                }
            });
        }

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


    //google services checker
    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(RouteActivity.this);

        if (available == ConnectionResult.SUCCESS){
            //everything is fine and user can make request
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //the error has occured but we can resolve it (wrong version)
            Log.d(TAG, "isServicesOK: the error has occured but we can resolve it(wrong version)");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(RouteActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            //there is a problem and we can't fix it
            Toast.makeText(this, "You can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
