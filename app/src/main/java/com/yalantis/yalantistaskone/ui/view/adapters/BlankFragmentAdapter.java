package com.yalantis.yalantistaskone.ui.view.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.yalantis.yalantistaskone.R;
import com.yalantis.yalantistaskone.ui.model.Address;
import com.yalantis.yalantistaskone.ui.model.Ticket;
import com.yalantis.yalantistaskone.ui.util.Constants;
import com.yalantis.yalantistaskone.ui.util.DateHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Антон on 10.04.2016.
 */
public class BlankFragmentAdapter extends RecyclerView.Adapter<BlankFragmentAdapter.ViewHolder> {
    private final List<Ticket> mModel;
    private final ItemClickListener mListener;
    private final Context mContext;

    public BlankFragmentAdapter(@Nullable ItemClickListener listener, Context context) {
        mListener = listener;
        mModel = new ArrayList<>();
        mContext = context;
    }

    private void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    public void addData(List<Ticket> newData) {
        mModel.addAll(newData);
        notifyDataSetChanged();
    }

    public void clear() {
        mModel.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mModel.get(position));
        animate(holder);
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
        private final ItemClickListener mItemClickListener;
        private Ticket mModel;


        public ViewHolder(View itemView, @Nullable ItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemClickListener = listener;
        }

        public void bindData(Ticket data) {
            mModel = data;
            title.setText(data.getTitle());
            likes.setText(String.valueOf(data.getLikesCounter()));
            if (data.getAddress() != null) {
                address.setText(Address.getFullAddress(data.getAddress()));
            } else {
                address.setText(R.string.no_address);
            }
            date.setText(DateHelper.getFormattedDate(data.getStartDate() * Constants.MILLIS_MULT));
            daysLeft.setText(DateHelper.getFormattedDate(data.getDeadline() * Constants.MILLIS_MULT));
            // Picasso.with(context).load(data.getCategory().getId())
        }

        @OnClick(R.id.card_container)
        void onItemClick() {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mModel);
            }
        }
    }


    public interface ItemClickListener {
        void onItemClick(Ticket model);
    }
}
