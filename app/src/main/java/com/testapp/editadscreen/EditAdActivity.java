package com.testapp.editadscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.testapp.R;
import com.testapp.model.Advertisement;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stream on 17.04.17.
 */

public class EditAdActivity extends MvpActivity<EditAdView, EditAdPresenter> implements EditAdView {
    @BindView(R.id.editAdToolbar)
    Toolbar toolbar;
    @BindView(R.id.back_button)
    Button backButton;
    @BindView(R.id.ok_button)
    Button okButton;
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
        setContentView(R.layout.activity_edit_ad);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.edit_ad);
        setSupportActionBar(toolbar);
        adTitle = getIntent().getStringExtra("adTitle");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (adTitle != null) {
            presenter.loadAd(adTitle);
        }
    }

    @NonNull
    @Override
    public EditAdPresenter createPresenter() {
        return new EditAdPresenter();
    }

    public void navigateBack(View view) {
        finish();
    }

    public void saveAd(View view) {
        String title = titleView.getText().toString();
        String price = priceView.getText().toString();
        String content = contentView.getText().toString();
        presenter.saveAd(title, price, content);
    }

    @Override
    public void loadAd(Advertisement advertisement) {
        titleView.setText(advertisement.title);
        priceView.setText(advertisement.price.toString());
        contentView.setText(advertisement.content);
    }

    @Override
    public void showPriceWarning() {
        Toast.makeText(this, R.string.price_should_be_greater_than_zero, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAdsTitleWarning() {
        Toast.makeText(this, R.string.adtitle_should_be_non_empty, Toast.LENGTH_LONG).show();
    }

    public void deleteAd(View view) {
        String title = titleView.getText().toString();
        presenter.deleteAd(title);
        finish();
    }
}
