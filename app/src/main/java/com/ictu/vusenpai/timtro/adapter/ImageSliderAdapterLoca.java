package com.ictu.vusenpai.timtro.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.ictu.vusenpai.timtro.R;

import java.util.ArrayList;

public class ImageSliderAdapterLoca extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    private ArrayList<Uri> lstUri;

    public ImageSliderAdapterLoca(Context context,ArrayList<Uri> lstUri) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View itemView = layoutInflater.inflate(R.layout.viewpageritem, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewPager);
        container.addView(itemView);
        imageView.setImageURI(lstUri.get(position));
        return itemView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // container chính là ViewPager, còn Object chính là return của instantiateItem ứng với position
        container.removeView((View) object);
    }
}
