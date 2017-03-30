package com.changxun.anew.myqrcode.feature.base;

import android.os.Binder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends AppCompatActivity {
  private Unbinder mUnbinder;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mUnbinder = ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();

    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
    mUnbinder = null;
  }
}
