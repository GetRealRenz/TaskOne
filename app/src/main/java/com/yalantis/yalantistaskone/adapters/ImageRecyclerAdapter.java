package com.yalantis.yalantistaskone.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yalantis.yalantistaskone.R;


/**
 * Created by Антон on 12.03.2016.
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {

    private String[] imageUrls;
    private Context context;

    public ImageRecyclerAdapter(String[] ImageIds, Context context) {
        this.imageUrls = ImageIds;
        this.context = context;
    }

    @Override
    public ImageRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    /*
    *Loading images from Internet with Picasso and showing them in RecyclerView
    * When user preses on image control name will be shown
    */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(imageUrls[position]).resize(160, 180)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = holder.imageView.getClass().getSimpleName() + " " + (position + 1);
                Toast.makeText(context, imageName
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return imageUrls.length;
    }

    /*Simple ViewHolder with only one item*/
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.recyclerItem);

        }
    }
}
