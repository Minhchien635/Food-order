package com.example.datmonanquatinnhan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.datmonanquatinnhan.databinding.ActivityMainBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class Food extends AppCompatActivity{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            FoodEntity food = (FoodEntity) bundle.get("Food");
            ImageView imageView = findViewById(R.id.img);
            TextView textView = findViewById(R.id.info);

            EditText editText =findViewById(R.id.yeucauthem);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            textView.setText(food.getInfo());

            Context context = this;
            int resID = context.getResources().getIdentifier(food.getImg() , "drawable", context.getPackageName());
            Drawable drawable = AppCompatResources.getDrawable(this, resID);
            imageView.setImageDrawable(drawable);
        }
        else{
            Toast.makeText(Food.this, "Intent is null", Toast.LENGTH_SHORT).show();
        }
    }
}
