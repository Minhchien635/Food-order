package com.example.datmonanquatinnhan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class customListAdapter extends BaseAdapter {
    private List<FoodEntity> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public customListAdapter(Context aContext,  List<FoodEntity> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView =  layoutInflater.inflate(R.layout.foods, null);
            holder = new ViewHolder();
            holder.ImageView = convertView.findViewById(R.id.imageFood);
            holder.InfoView = convertView.findViewById(R.id.infoFood);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FoodEntity food = this.listData.get(position);
        holder.InfoView.setText(food.getInfo());
        int imageId = this.getMipmapResIdByName(food.getImg());

        holder.ImageView.setImageResource(imageId);
        return convertView;
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView ImageView;
        TextView InfoView;
    }
}
