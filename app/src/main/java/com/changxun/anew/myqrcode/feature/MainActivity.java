package com.changxun.anew.myqrcode.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.changxun.anew.myqrcode.R;
import com.changxun.anew.myqrcode.model.Contract;

public class MainActivity extends AppCompatActivity {

  private MainPresenter mPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (mPresenter == null) {
      mPresenter = new MainPresenter(this);
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter.onDestroy();
  }

  public void onSuccess(Contract contract) {

  }

  public void onError(Throwable throwable) {

  }

}
