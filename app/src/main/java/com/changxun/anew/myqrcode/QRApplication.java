package com.changxun.anew.myqrcode;

import android.app.Application;
import android.content.Context;
import com.changxun.anew.myqrcode.network.HttpUtil;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class QRApplication extends Application {

  public static volatile Context sAppContext;
  public static volatile OkHttpClient mOkHttpClient;

  @Override public void onCreate() {
    super.onCreate();
  }

  @Override public void onTerminate() {
    super.onTerminate();
    sAppContext = null;
    mOkHttpClient = null;
  }

  private void initialize() {
    sAppContext = getApplicationContext();
    mOkHttpClient = HttpUtil.newInstance();
  }
}
