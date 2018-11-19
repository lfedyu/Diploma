package com.fedyushko.lilia.p0031_firstprogect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends Activity {

    TextView placeName;
    TextView placeInfo;
    ImageView placePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        placeName = (TextView)findViewById(R.id.place_name);
        placeInfo = (TextView)findViewById(R.id.place_info);
        placePhoto = (ImageView)findViewById(R.id.place_photo);

    }
}
