package com.ictu.vusenpai.timtro.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ictu.vusenpai.timtro.R;

import java.io.File;
import java.util.ArrayList;

public class ImageSliderAdapterInternet extends PagerAdapter {
    private ArrayList<String> urlImage;

    public ImageSliderAdapterInternet(ArrayList<String> urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public int getCount() {
        return urlImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Tạo ImageView
        final Context context = container.getContext();
        final AppCompatImageView imageView = new AppCompatImageView(context);
        container.addView(imageView);
        // Load ảnh vào ImageView bằng Glide
        Glide.with(context).load(urlImage.get(position)).placeholder(R.mipmap.ic_launcher).error(R.drawable.no_internet).into(imageView);
        // Return
        return imageView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // container chính là ViewPager, còn Object chính là return của instantiateItem ứng với position
        container.removeView((View) object);
    }
}
