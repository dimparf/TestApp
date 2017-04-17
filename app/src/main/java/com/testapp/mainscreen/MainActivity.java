package com.testapp.mainscreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.testapp.R;
import com.testapp.TestApp;
import com.testapp.editadscreen.EditAdActivity;
import com.testapp.model.Advertisement;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.RealmResults;


public class MainActivity extends MvpActivity<AdvertisementView, AdvertisementPresenter> implements AdvertisementView {
    @BindView(R.id.adsList)
    RealmRecyclerView adsList;
    @BindView(R.id.adToolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestApp.getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem fileMenu = menu.findItem(R.id.action_create_ad);
        fileMenu.setIcon(new IconicsDrawable(this)
                .icon(MaterialDesignIconic.Icon.gmi_file_add)
                .color(Color.WHITE)
                .sizeDp(24));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_ad:
                Intent editAdIntent = new Intent(this, EditAdActivity.class);
                startActivity(editAdIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.loadAds();
    }

    @NonNull
    @Override
    public AdvertisementPresenter createPresenter() {
        return new AdvertisementPresenter();
    }

    @Override
    public void loadAds(RealmResults<Advertisement> ads) {
        AdvertisementAdapter mAdapter = new AdvertisementAdapter(this, ads, true, false);
        adsList.addItemDecoration(new SimpleDividerItemDecoration(this));
        adsList.setAdapter(mAdapter);
    }
}
