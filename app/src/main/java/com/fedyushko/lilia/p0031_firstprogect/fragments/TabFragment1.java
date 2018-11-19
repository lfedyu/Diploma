package com.fedyushko.lilia.p0031_firstprogect.fragments;


import com.fedyushko.lilia.p0031_firstprogect.LoginActivity;
import com.fedyushko.lilia.p0031_firstprogect.NewTripActivity;
import com.fedyushko.lilia.p0031_firstprogect.R;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TabFragment1 extends Fragment {

    boolean FLAG = true;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (FLAG == true)
            //add condition to show your planned routs if you already have ones
            return inflater.inflate(R.layout.tab_fragment1, container, false);
            return inflater.inflate(R.layout.tab_fragment1, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.addRoutImage).setOnClickListener(mListener);
    }
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addRoutImage:
                    Intent intent = new Intent(getActivity(), NewTripActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };


}