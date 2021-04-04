package com.example.datmonanquatinnhan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Food extends AppCompatActivity {
    View googleMap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            FoodEntity food = (FoodEntity) bundle.get("Food");
            ImageView imageView = findViewById(R.id.img);
            TextView textView = findViewById(R.id.info);

            EditText editText = findViewById(R.id.yeucauthem);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

            textView.setText(food.getInfo());

            Context context = this;
            int resID = context.getResources().getIdentifier(food.getImg(), "drawable", context.getPackageName());
            Drawable drawable = AppCompatResources.getDrawable(this, resID);
            imageView.setImageDrawable(drawable);

            Button buttonSub = findViewById(R.id.btn_less);
            buttonSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textViewMount = findViewById(R.id.et_number);
                    if (Integer.parseInt(textViewMount.getText().toString()) != 0) {
                        buttonSubMount();
                    }
                }
            });

            Button buttonAdd = findViewById(R.id.btn_more);
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonAddMount();
                }
            });

            Button button = findViewById(R.id.buttonorder);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foodOrderButton(food);
                }
            });

            googleMap = findViewById(R.id.google_map);
            googleMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoUrl("https://goo.gl/maps/2QvF3q3yUwQA3E92A");
                }
            });
        } else {
            Toast.makeText(Food.this, "Intent is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void foodOrderButton(FoodEntity foodEntity) {
        String foodName = foodEntity.getInfo().toString();

        RadioGroup rg = (RadioGroup) findViewById(R.id.groupcobanh);
        int checkedRadioButtonId = rg.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checkedRadioButtonId);
        String foodSize = radioButton.getText().toString();

        TextView addOptionView = findViewById(R.id.yeucauthem);
        String addOption = (addOptionView.getText().length() != 0) ? (addOptionView.getText().toString()) : "không có";

        TextView foodAmountView = findViewById(R.id.et_number);
        int foodAmount = Integer.parseInt(foodAmountView.getText().toString());

        System.out.println(foodName + " " + foodSize + " " + foodAmount + " " + addOption);

        String message = "Tên món: " + foodName + ";\n Kích cỡ: " + foodSize + ";\n Số lượng: " + foodAmount + ";\n Yêu cầu thêm: " + addOption + ";";

        String phoneNumber = "5556";
        Intent intent = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }

    public void buttonAddMount() {
        TextView textView = findViewById(R.id.et_number);
        String text = ((Integer) (Integer.parseInt(textView.getText().toString()) + 1)).toString();
        textView.setText(text);
    }

    public void buttonSubMount() {
        TextView textView = findViewById(R.id.et_number);
        String text = ((Integer) (Integer.parseInt(textView.getText().toString()) - 1)).toString();
        textView.setText(text);
    }
}
