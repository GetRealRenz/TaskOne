package com.yalantis.yalantistaskone.ui.view.adapters;

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

    private String[] mImageUrls;
    private Context mContext;
    private static final int IMAGE_WIDTH = 160;
    private static final int IMAGE_HEIGHT = 180;

    public ImageRecyclerAdapter(String[] mImageUrls, Context mContext) {
        this.mImageUrls = mImageUrls;
        this.mContext = mContext;
    }

    @Override
    public ImageRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Loading images from Internet with Picasso and showing them in RecyclerView
     * When user preses on image control name will be shown
     */

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(mContext).load(mImageUrls[position]).resize(IMAGE_WIDTH, IMAGE_HEIGHT).centerCrop()
                .into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageName = holder.mImageView.getClass().getSimpleName() + " " + (position + 1);
                Toast.makeText(mContext, imageName
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.length;
    }

    /**
     * Simple ViewHolder with only one item
     */

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHolder(View mItemView) {
            super(mItemView);
            this.mImageView = (ImageView) mItemView.findViewById(R.id.recycler_item);

        }
    }
}
