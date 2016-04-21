package com.yalantis.yalantistaskone.ui.view.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.model.DataModel;
import com.yalantis.yalantistaskone.ui.util.SecondsToDate;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Антон on 10.04.2016.
 */
public class BlankFragmentAdapter extends RecyclerView.Adapter<BlankFragmentAdapter.ViewHolder> {
    private List<DataModel> mModel;
    private final ItemClickListener mListener;

    public BlankFragmentAdapter(List<DataModel> model, @Nullable ItemClickListener listener) {
        mListener = listener;
        mModel = model;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mModel.get(position));
    }

    @Override
    public int getItemCount() {
        return mModel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tx_title)
        TextView title;
        @Bind(R.id.tx_likes)
        TextView likes;
        @Bind(R.id.tx_address)
        TextView address;
        @Bind(R.id.tx_date)
        TextView date;
        @Bind(R.id.days_left)
        TextView daysLeft;
        @Bind(R.id.img_category)
        ImageView category;
        private ItemClickListener mItemClickListener;
        private DataModel mModel;


        public ViewHolder(View itemView, @Nullable ItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemClickListener = listener;
        }

        public void bindData(DataModel data) {
            mModel = data;
            SecondsToDate toDate=new SecondsToDate();
            title.setText(data.getTitle());
            likes.setText(data.getLikes());
            address.setText(data.getAddress());
            date.setText(toDate.toDate(data.getDate()));
            daysLeft.setText(toDate.toDate(data.getDaysleft()));
            category.setImageDrawable(data.getCategory());
        }

        @OnClick(R.id.card_container)
        void onItemClick() {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mModel);
            }
        }
    }


    public interface ItemClickListener {
        void onItemClick(DataModel model);
    }
}
