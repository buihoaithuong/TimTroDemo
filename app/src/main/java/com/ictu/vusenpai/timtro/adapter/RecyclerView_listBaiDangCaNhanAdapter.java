package com.ictu.vusenpai.timtro.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.xuly.MyOnItemClickListener;
import com.ictu.vusenpai.timtro.xuly.Update;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_listBaiDangCaNhanAdapter extends RecyclerView.Adapter<RecyclerView_listBaiDangCaNhanAdapter.PhongHolder>{
    private Context context;
    private List<BaiDang> baiDangList=new ArrayList<>();
    private MyOnItemClickListener myOnItemClickListener;

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }
    public RecyclerView_listBaiDangCaNhanAdapter(Context mContext, List<BaiDang> baiDangList) {
        this.baiDangList.clear();
        this.context = mContext;
        this.baiDangList = baiDangList;
    }

    @NonNull
    @Override
    public RecyclerView_listBaiDangCaNhanAdapter.PhongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View phongView =inflater.inflate(R.layout.phong_layout, parent, false);
        RecyclerView_listBaiDangCaNhanAdapter.PhongHolder viewHolder = new RecyclerView_listBaiDangCaNhanAdapter.PhongHolder(phongView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_listBaiDangCaNhanAdapter.PhongHolder holder, final int position) {
        BaiDang baiDang = baiDangList.get(position);
        holder.txtTieuDe.setText(setTieuDe(baiDang.getTieuDe()));
        holder.txtGia.setText(setGia(baiDang.getGia()));
        holder.txtDiaChi.setText(baiDang.getDiaChi());
        holder.txtDienTich.setText("Diện tích:"+String.valueOf(baiDang.getDienTich())+"m2");
        holder.txtThoiGianDang.setText(baiDang.getTimeDang());
        Glide.with(context).load(baiDang.getAnhFeeback().get(0)).error(R.drawable.ic_no_internet).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnItemClickListener.onClick(baiDangList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return baiDangList.size();
    }

    public class PhongHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private ImageView imageView;
        private TextView txtTieuDe;
        private TextView txtDiaChi;
        private TextView txtGia;
        private TextView txtDienTich;
        private TextView txtThoiGianDang;
        private RelativeLayout rlLayout;
        public PhongHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagePhong);
            txtTieuDe=itemView.findViewById(R.id.txtTieuDeBaiDang);
            txtDiaChi=itemView.findViewById(R.id.txtDiaChi);
            txtGia= itemView.findViewById(R.id.txtGia);
            txtDienTich=itemView.findViewById(R.id.txtDienTich);
            txtThoiGianDang=itemView.findViewById(R.id.txtThoigian);
            rlLayout= itemView.findViewById(R.id.rlItem);
            rlLayout.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(),0,0,"Xoá bài viết");
        }
    }
    public void remove(int i){
        Update.removeBaiDang(baiDangList.get(i).getIdBaiDang(),baiDangList.get(i).getAnhFeeback());
        baiDangList.remove(i);
        notifyDataSetChanged();
    }
    private String setGia(int gia){
        if(gia>0&&gia<1000000)
            return String.valueOf(gia);
        if(gia>=1000000&&gia<1000000000)
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
