package com.changxun.anew.myqrcode.feature;

import com.changxun.anew.myqrcode.model.Contract;
import com.changxun.anew.myqrcode.network.HttpUtil;
import com.changxun.anew.myqrcode.network.service.ContractService;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainPresenter {

  private Throwable mThrowable;
  private MainActivity mView;
  private Contract mContract;
  private Request mRequest;

  public MainPresenter(MainActivity view) {
    mView = view;
  }

  public void request() {
    Request.Builder builder = new Request.Builder();
    builder.url(ContractService.daily_url);
    mRequest = builder.build();
    HttpUtil.enqueue(mRequest, new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        mView.onError(e);
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        //mView.onSuccess(mContract);
      }
    });
  }

  public void onDestroy() {
    mThrowable = null;
    mView = null;
    mContract = null;
    mRequest = null;
  }

}
