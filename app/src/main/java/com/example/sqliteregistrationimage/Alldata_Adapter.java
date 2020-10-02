package com.example.sqliteregistrationimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Alldata_Adapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Alldata_Modal>product;

    public Alldata_Adapter(Context context, int layout, ArrayList<Alldata_Modal> product) {
        this.context = context;
        this.layout = layout;
        this.product = product;
    }

    @Override
    public int getCount() {
        return product.size();
    }

    @Override
    public Object getItem(int position) {
        return product.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class viewholder{
        ImageView imageView;
        TextView tvname;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View row=view;
        viewholder holder=new viewholder();
        if (row==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.tvname=row.findViewById(R.id.Tv_list);
            holder.imageView=row.findViewById(R.id.Im_List);
            row.setTag(holder);

        }
        else {
            holder= (viewholder) row.getTag();

        }
        Alldata_Modal modal=product.get(position);
        holder.tvname.setText(modal.getName());
        byte[]imagel=modal.getImage();

        Bitmap bitmap= BitmapFactory.decodeByteArray(imagel,0,imagel.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
