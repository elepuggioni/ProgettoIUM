package com.example.progettoium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
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
        ImageView img1 = view.findViewById(R.id.img1);
        ImageView img2 = view.findViewById(R.id.img2);

        if(title.equals("Milano MI, Italia")){
            tvImage.setImageResource(R.drawable.milano);
            img1.setImageResource(R.drawable.m1);
            img2.setImageResource(R.drawable.m2);
        }else if(title.equals("Roma RM, Italia")){
            tvImage.setImageResource(R.drawable.roma);
            img1.setImageResource(R.drawable.r);
            img2.setImageResource(R.drawable.r1);
        }

        tvImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img2.setScaleType(ImageView.ScaleType.CENTER_CROP);

        /*ImageView imageView = view.findViewById(R.id.window_bottom);
        imageView.setPivotX(imageView.getWidth()/2);
        imageView.setPivotY(imageView.getHeight()/2);
        imageView.setRotation(45);
        imageView.layout(0,0,70,70);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);*/
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
