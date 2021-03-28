package com.ictu.vusenpai.timtro.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.ictu.vusenpai.timtro.R;

import java.util.ArrayList;

public class ImageSliderAdapterLoca extends PagerAdapter {
    private ArrayList<Uri> lstUri;

    public ImageSliderAdapterLoca(ArrayList<Uri> lstUri) {
        this.lstUri = lstUri;
    }

    @Override
    public int getCount() {
        return lstUri.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Tạo ImageView
        final Context context = container.getContext();
        final AppCompatImageView imageView = new AppCompatImageView(context);
        container.addView(imageView);
        imageView.setImageURI(lstUri.get(position));
        return imageView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // container chính là ViewPager, còn Object chính là return của instantiateItem ứng với position
        container.removeView((View) object);
    }
}
