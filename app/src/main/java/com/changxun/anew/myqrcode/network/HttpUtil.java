package com.changxun.anew.myqrcode.network;

import android.content.Context;
import android.net.ConnectivityManager;
import com.changxun.anew.myqrcode.QRApplication;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
  private static OkHttpClient mOkHttpClient = null;
  public static boolean isWIFI = true;
  public static Call mCall;

  synchronized
  public static OkHttpClient newInstance() {
    if (mOkHttpClient == null) {
      mOkHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
          .readTimeout(30, TimeUnit.SECONDS)
          .writeTimeout(30, TimeUnit.SECONDS).build();
    }

    return mOkHttpClient;
  }

  /**
   * 该不会开启异步线程。
   *
   * @throws IOException
   */
  public static Response execute(Request request) throws IOException {
    return mOkHttpClient.newCall(request).execute();
  }

  /**
   * 开启异步线程访问网络
   */
  public static void enqueue(Request request, Callback responseCallback) {
    mOkHttpClient.newCall(request).enqueue(responseCallback);
  }

  /**
   * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
   */
  public static void enqueue(Request request) {
    mOkHttpClient.newCall(request).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {

      }

      @Override public void onResponse(Call call, Response response) throws IOException {

      }
    });
  }

  public static boolean readNetworkState() {

    ConnectivityManager
        cm = (ConnectivityManager) QRApplication.sAppContext.getSystemService(
        Context.CONNECTIVITY_SERVICE);

    if (cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo()
        .isConnected()) {
      isWIFI = (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI);
      return true;
    } else {

      return false;
    }
  }
}