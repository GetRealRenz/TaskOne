package com.yalantis.yalantistaskone.ui.view.adapters;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * Created by Антон on 18.04.2016.
 */
public class ListAdapter extends BaseAdapter {
    private List<DataModel> mModel;
    private final onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(DataModel model);
    }

    public ListAdapter(List<DataModel> model, onItemClickListener listener) {
        this.mModel = model;
        mListener = listener;

    }

    @Override
    public int getCount() {
        return mModel.size();
    }

    @Override
    public Object getItem(int position) {
        return mModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
            holder = new ViewHolder(view, mListener);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.bindData(mModel.get(position));

        return view;
    }

    class ViewHolder {
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
        private onItemClickListener mListener;
        private DataModel mModel;

        public ViewHolder(View itemView, @Nullable onItemClickListener listener) {
            ButterKnife.bind(this, itemView);
            mListener = listener;
        }

        public void bindData(DataModel data) {
            mModel = data;
            SecondsToDate toDate = new SecondsToDate();
            title.setText(data.getTitle());
            likes.setText(data.getLikes());
            address.setText(data.getAddress());
            date.setText(toDate.toDate(data.getDate()));
            daysLeft.setText(toDate.toDate(data.getDaysleft()));
            category.setImageDrawable(data.getCategory());
        }

        @OnClick(R.id.card_container)
        void onItemClick() {
            if (mListener != null) {
                mListener.onItemClick(mModel);
            }
        }
    }
}
