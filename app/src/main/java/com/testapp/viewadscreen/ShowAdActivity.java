package com.testapp.viewadscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.testapp.R;
import com.testapp.editadscreen.EditAdActivity;
import com.testapp.model.Advertisement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowAdActivity extends MvpActivity<ShowAdView, ShowAdPresenter> implements ShowAdView {
    @BindView(R.id.showAdToolbar)
    Toolbar toolbar;
    @BindView(R.id.title_view)
    TextInputEditText titleView;
    @BindView(R.id.price_view)
    TextInputEditText priceView;
    @BindView(R.id.content_view)
    TextInputEditText contentView;

    private String adTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ad);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.show_ad);
        adTitle = getIntent().getStringExtra("adTitle");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.loadAd(adTitle);
    }

    @NonNull
    @Override
    public ShowAdPresenter createPresenter() {
        return new ShowAdPresenter();
    }

    @Override
    public void showAd(Advertisement advertisement) {
        titleView.setText(advertisement.title);
        priceView.setText(advertisement.price.toString());
        contentView.setText(advertisement.content);
    }

    public void editAd(View view) {
        Intent editAdIntent = new Intent(this, EditAdActivity.class);
        editAdIntent.putExtra("adTitle", adTitle);
        startActivity(editAdIntent);
        finish();
    }

    public void navigateBack(View view) {
        finish();
    }
}
