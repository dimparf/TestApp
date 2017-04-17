package com.testapp.mainscreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testapp.R;
import com.testapp.TestApp;
import com.testapp.model.Advertisement;
import com.testapp.viewadscreen.ShowAdActivity;

import javax.inject.Inject;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;


public class AdvertisementAdapter extends RealmBasedRecyclerViewAdapter<Advertisement, AdvertisementAdapter.ViewHolder> {
    @Inject
    Context mContext;

    AdvertisementAdapter(
            Context context,
            RealmResults<Advertisement> realmResults,
            boolean automaticUpdate,
            boolean animateIdType) {
        super(context, realmResults, automaticUpdate, animateIdType);
        mContext = context;
        TestApp.getAppComponent().inject(this);
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.ad_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final Advertisement advertisement = realmResults.get(position);
        viewHolder.setTitle(advertisement.title);
        viewHolder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editAdIntent = new Intent(mContext, ShowAdActivity.class);
                editAdIntent.putExtra("adTitle", advertisement.title);
                mContext.startActivity(editAdIntent);
            }
        });
    }

    class ViewHolder extends RealmViewHolder {
        TextView titleView;

        ViewHolder(View container) {
            super(container);
            titleView = (TextView) itemView.findViewById(R.id.ad_title);

        }

        public void setTitle(String title) {
            if (null == titleView) return;
            titleView.setText(title);
        }

    }

}