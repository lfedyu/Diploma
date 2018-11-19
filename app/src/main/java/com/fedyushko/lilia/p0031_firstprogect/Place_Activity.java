package com.fedyushko.lilia.p0031_firstprogect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Place_Activity extends AppCompatActivity {

    private ImageView mPlaceImageView;
    private TextView mPlaceNameView;
    private TextView mPlaceDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.place_activity);

        Intent intent = getIntent();

        mPlaceImageView = (ImageView) findViewById(R.id.placeImage);
        mPlaceImageView.setImageResource(intent.getIntExtra("photoId", R.drawable.rinok_square));

        mPlaceNameView = (TextView) findViewById(R.id.placeName);
        mPlaceNameView.setText(intent.getStringExtra("placeName"));

        mPlaceDescriptionView = (TextView) findViewById(R.id.placeInfo);
        mPlaceDescriptionView.setText(intent.getStringExtra("longInfo"));
    }
}
