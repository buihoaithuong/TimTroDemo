package com.ictu.vusenpai.timtro.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ictu.vusenpai.timtro.R;
import java.util.ArrayList;

public class ImageSliderAdapterInternet extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    private ArrayList<String> urlImage;

    public ImageSliderAdapterInternet(Context context,ArrayList<String> urlImage) {
        this.urlImage = urlImage;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View itemView = layoutInflater.inflate(R.layout.viewpageritem, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewPager);
        Glide.with(context).load(urlImage.get(position)).into(imageView);
        container.addView(itemView);
        return itemView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // container chính là ViewPager, còn Object chính là return của instantiateItem ứng với position
        container.removeView((View) object);
    }
}
