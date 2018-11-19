package com.fedyushko.lilia.p0031_firstprogect;


import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PlaceViewHolder>  {
    private static final String TAG = "myLogs";
    List<Place> places = new ArrayList<>();
    Set<Place> route = new HashSet<>();

    public class PlaceViewHolder extends RecyclerView.ViewHolder  {


        CardView cv;
        TextView placeName;
        TextView placeInfo;
        ImageView placePhoto;
        ImageView placeAdd;
        public View view;
        public Place currentItem;//????

        PlaceViewHolder(final View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cv);
            placeAdd = (ImageView)itemView.findViewById(R.id.place_add);
            placeName = (TextView)itemView.findViewById(R.id.place_name);
            placeInfo = (TextView)itemView.findViewById(R.id.place_info);
            placePhoto = (ImageView)itemView.findViewById(R.id.place_photo);
            placeAdd = (ImageView)itemView.findViewById(R.id.place_add);
            placeAdd = (ImageView)itemView.findViewById(R.id.place_add);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(),Place_Activity.class );

                    intent.putExtra("photoId",places.get(adapterPosition).getBigPhotoId());
                    intent.putExtra("placeName", places.get(adapterPosition).getName());
                    intent.putExtra("longInfo", places.get(adapterPosition).getLongInfo());
                    v.getContext().startActivity(intent);
                }
            });
            placeAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch ( v.getId()){
                        case R.id.place_add :
                            placeAdd.setActivated(!placeAdd.isActivated());
                            int adapterPosition = getAdapterPosition();
                            if (placeAdd.isActivated()){
                                route.add(places.get(adapterPosition));
                            } else{
                                route.remove(places.get(adapterPosition));
                            }
                        break;
                    }
                }
            });
        }



    }


    RVAdapter(List<Place> places){
        this.places = places;
    }

    public Set<Place> getRoute() {
        return route;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PlaceViewHolder pvh = new PlaceViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder placeViewHolder, int i) {
        placeViewHolder.placeName.setText(places.get(i).name);
        placeViewHolder.placeInfo.setText(places.get(i).info);
        placeViewHolder.placePhoto.setImageResource(places.get(i).photoId);
        placeViewHolder.placeAdd.setActivated(route.contains(places.get(i)));

    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}
