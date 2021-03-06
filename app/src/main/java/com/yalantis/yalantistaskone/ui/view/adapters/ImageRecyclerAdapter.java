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
import com.yalantis.yalantistaskone.ui.model.TicketFiles;
import com.yalantis.yalantistaskone.ui.util.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Антон on 12.03.2016.
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {

    final List<TicketFiles> mImageUrls;
    private final Context mContext;
    private static final int IMAGE_WIDTH = 160;
    private static final int IMAGE_HEIGHT = 180;

    public ImageRecyclerAdapter(List<TicketFiles> imageUrls, Context context) {
        this.mImageUrls = imageUrls;
        this.mContext = context;
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
        holder.bindData(mImageUrls.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    /**
     * Simple ViewHolder with only one item
     */

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recycler_item)
        ImageView mImageView;

        public ViewHolder(View mItemView) {
            super(mItemView);
            ButterKnife.bind(this, mItemView);

        }

        public void bindData(TicketFiles image, final Context context) {
            String path = Constants.URL_IMAGE + image.getFilename();
            Picasso.with(context).load(path).placeholder(R.drawable.placeholder).
                    resize(IMAGE_WIDTH, IMAGE_HEIGHT).centerCrop()
                    .into(mImageView);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String imageName = mImageView.getClass().getSimpleName();
                    Toast.makeText(context, imageName
                            , Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
