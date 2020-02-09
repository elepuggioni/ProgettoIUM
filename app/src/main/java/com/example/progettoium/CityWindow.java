package com.example.progettoium;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;

public class CityWindow implements GoogleMap.InfoWindowAdapter{

    // These are both viewgroups containing an ImageView with id "badge" and two TextViews with id
    // "title" and "snippet".
    private final View mWindow;
    private Context mContext;


    public CityWindow(final Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void  renderWindowText (final Marker marker, final View view){
        String title = marker.getTitle();
        TextView tvTitle = view.findViewById(R.id.window_title);

        if(!title.equals("")){
            tvTitle.setText(title);
        }

        ImageView tvImage = view.findViewById(R.id.window_image);
        if(title.equals("Milano MI, Italia")){
            tvImage.setImageResource(R.drawable.milano);
        }else if(title.equals("Roma RM, Italia")){
            tvImage.setImageResource(R.drawable.roma);
        }

        tvImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderWindowText(marker, mWindow);

        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;
    }
}
