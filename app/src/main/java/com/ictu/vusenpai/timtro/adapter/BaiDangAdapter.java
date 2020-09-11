package com.ictu.vusenpai.timtro.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.R;

import java.util.List;

public class BaiDangAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<BaiDang> baiDangList;

    public BaiDangAdapter(Context context, int layout, List<BaiDang> baiDangList) {
        this.context = context;
        this.layout = layout;
        this.baiDangList = baiDangList;
    }

    @Override
    public int getCount() {
        return baiDangList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        //ánh xạ
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imagePhong);
        TextView txtTieuDe=(TextView)convertView.findViewById(R.id.txtTieuDeBaiDang);
        TextView txtDiaChi=(TextView)convertView.findViewById(R.id.txtDiaChi);
        TextView txtGia= (TextView)convertView.findViewById(R.id.txtGia);
        TextView txtDienTich=(TextView)convertView.findViewById(R.id.txtDienTich);
        TextView txtThoiGianDang=(TextView)convertView.findViewById(R.id.txtThoigian);
        //Gán giá trị
        BaiDang baiDang = baiDangList.get(position);
        txtTieuDe.setText(setTieuDe(baiDang.getTieuDe()));
        txtGia.setText(setGia(baiDang.getPhong().getGia()));
        txtDiaChi.setText(baiDang.getPhong().getDiaChi());
        txtDienTich.setText("Diện tích:"+String.valueOf(baiDang.getPhong().getDienTich())+"m2");
        txtThoiGianDang.setText(baiDang.getThoiGianDang());
        imageView.setImageResource(baiDang.getPhong().getAnhFeeback());
        return convertView;
    }
    private String setGia(int gia){
        if(gia>0&&gia<1000000)
            return String.valueOf(gia);
        if(gia>1000000&&gia<1000000000)
            return String.valueOf((gia/1000000)+" Triệu");
        if(gia>1000000000)
            return String.valueOf((gia/1000000000)+" Tỷ");
        else return String.valueOf(0);
    }
    public String setTieuDe(String tieude){
        if(tieude.length()>80)
            return tieude.substring(0,81)+"...";
        else return tieude;
    }
}
