package mobileappscompany.mutualmobileapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fallaye on 1/5/18.
 */

public class AdapterReceipe extends RecyclerView.Adapter<AdapterReceipe.MyViewHolder> {

    private Context context;
    List<Receipe> data;

    public AdapterReceipe(Context context, List<Receipe> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
        .inflate(R.layout.container_recipe, parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Receipe currentBook =data.get(position);
        holder.tvUri.setText("Title: " + currentBook.uri);
        holder.tvLabel.setText("Author: " + currentBook.label);

        Picasso.with(context).load(currentBook.getImage())
                .into(holder.ivReceipe);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvUri;
        TextView tvLabel;
        ImageView ivReceipe;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvUri = (TextView) itemView.findViewById(R.id.tvUri);
            tvLabel = (TextView) itemView.findViewById(R.id.tvLabel);
            ivReceipe = (ImageView) itemView.findViewById(R.id.ivReceipe);
        }

    }


}
