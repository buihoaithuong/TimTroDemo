package com.ictu.vusenpai.timtro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.ictu.vusenpai.timtro.R;
import com.ictu.vusenpai.timtro.adapter.BaiDangAdapter;
import com.ictu.vusenpai.timtro.model.BaiDang;
import com.ictu.vusenpai.timtro.model.Phong;

import java.util.ArrayList;

public class listBaiDang extends Fragment {
    ArrayList<BaiDang> baiDangList;
    ListView lvBaiDang;
    BaiDangAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_baidang,container,false);
        lvBaiDang=(ListView)view.findViewById(R.id.listViewBaiDang);
        baiDangList=new ArrayList<>();
        Phong phong1 = new Phong("p1","Thái Nguyên",15,R.drawable.nha2,150000000);
        Phong phong2 = new Phong("p2","Hà Nội",16,R.drawable.nha3,2000000);
        Phong phong3 = new Phong("p3","Tuyên Quang",25,R.drawable.nha4,6000000);
        Phong phong4 = new Phong("p4","Hải Phòng",20,R.drawable.nha5,2000000);
        Phong phong5 = new Phong("p5","Thái Nguyên",35,R.drawable.nha6,100000);
        baiDangList.add(new BaiDang("1","Nhà của Vũ, Rộng rãi, thoáng mát","20 phút trước",phong1));
        baiDangList.add(new BaiDang("2","Vẫn là nhà của Vũ nhưng đỡ rộng hơn","15 phút trước",phong2));
        baiDangList.add(new BaiDang("3","Đây là nhà của thưn, rộng lắm. Thuê đi","25 phút trước",phong3));
        baiDangList.add(new BaiDang("4","Nhà của thưn nà","Hôm qua",phong4));
        baiDangList.add(new BaiDang("5","Nhà riêng của thưn và vũ không cho thuê nhưng thícfsdf","Hôm qua",phong5));
        adapter=new BaiDangAdapter(getActivity(),R.layout.bai_dang,baiDangList);
        lvBaiDang.setAdapter(adapter);
        lvBaiDang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),chitietbaidang_activity.class);
                BaiDang temp=baiDangList.get(position);
                intent.putExtra("BaiDang",temp);
                startActivity(intent);
            }
        });
        return view;
    }
}
