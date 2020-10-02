package com.example.sqliteregistrationimage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Allview_Adapter extends RecyclerView.Adapter<Allview_Adapter.viewholder> {
     private ArrayList<Alldata_Modal> product;
     private Context context;
     Dialog dialog;
  DataBaseHelper db;

    public Allview_Adapter(ArrayList<Alldata_Modal> product, Context context) {
        this.product = product;
        this.context = context;
        dialog=new Dialog(context);
        db=new DataBaseHelper(context);
    }

    @NonNull
    @Override
    public Allview_Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dialog.setContentView(R.layout.delete_dialog);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewlist = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new viewholder(viewlist);
    }

    @Override
    public void onBindViewHolder(@NonNull final Allview_Adapter.viewholder holder, int position) {

        holder.tvid.setText(product.get(position).getId());
        holder.tvname.setText(product.get(position).getName());
        byte[]image=product.get(position).getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageView.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {

        return product.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView,imdelete;
        TextView tvid, tvname;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.Tv_id);
            tvname = itemView.findViewById(R.id.Tv_list);
            imageView = itemView.findViewById(R.id.Im_List);
            imdelete=itemView.findViewById(R.id.Im_delete);
            imdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id= tvid.getText().toString();
                  int res= db.deletedata(id);
                  if (res>0){
                      Toast.makeText(context, "deleted item", Toast.LENGTH_SHORT).show();
                  }else {
                      Toast.makeText(context, "no delete", Toast.LENGTH_SHORT).show();

                  }
                }
            });

        }
    }

}
