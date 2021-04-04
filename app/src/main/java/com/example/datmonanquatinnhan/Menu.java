package com.example.datmonanquatinnhan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity implements OnMapReadyCallback {
    ListView listView;
    GoogleMap map;
    View googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment =(MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        List<FoodEntity> foods = new ArrayList<FoodEntity>();

        foods.add(new FoodEntity("banhmikebab","Bánh mì kebab"));
        foods.add(new FoodEntity("banhran","Bánh rán"));
        foods.add(new FoodEntity("banhcanhgioheo","Bánh canh giò heo"));
        foods.add(new FoodEntity("bunbohue","Bún bò huế"));
        foods.add(new FoodEntity("comsuonnuong","Cơm sườn nướng"));
        foods.add(new FoodEntity("comgadagion","Cơm gà da giòn"));
        foods.add(new FoodEntity("goicuon","Gỏi cuốn"));
        foods.add(new FoodEntity("hutieunamvang","Hủ tiếu nam vang"));
        foods.add(new FoodEntity("phobo","Phở bò"));

        listView = findViewById(R.id.listView);
        listView.setAdapter( new customListAdapter(this,foods));
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) (a, v, position, id) -> {
            Object o = listView.getItemAtPosition(position);
            FoodEntity food = (FoodEntity) o;
            Intent intent = new Intent(Menu.this, Food.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Food", (Serializable) food);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng SGU = new LatLng(10.759975262886478, 106.68224460777869);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SGU,16));
        map.addMarker(new MarkerOptions()
                .position(SGU)
                .title("Đại học Sài Gòn"));
        map.moveCamera(CameraUpdateFactory.newLatLng(SGU));
    }
}