package com.changxun.anew.myqrcode.feature;

import android.os.AsyncTask;
import com.changxun.anew.myqrcode.model.Contract;
import com.changxun.anew.myqrcode.network.HttpUtil;
import com.changxun.anew.myqrcode.network.service.ContractService;
import com.changxun.anew.myqrcode.widget.LoadingDialog;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainPresenter {

  private Throwable mThrowable;
  private MainActivity mView;
  private Contract mContract;
  private Request mRequest;
  private ScanAsyncTask mScanAsyncTask;
  private LoadingDialog mLoadingDialog;

  public MainPresenter(MainActivity view) {
    mView = view;
    mScanAsyncTask = new ScanAsyncTask();
    mLoadingDialog = LoadingDialog.createDialog(view);
  }

  public void request() {
    //用于测试网络
    mLoadingDialog.show();
    Request.Builder builder = new Request.Builder();
    builder.url(ContractService.daily_url);
    mRequest = builder.build();
    HttpUtil.newInstance();
    HttpUtil.enqueue(mRequest, new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        mView.onError(e);
        mLoadingDialog.dismiss();
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        mView.onSuccess(mContract);
        mLoadingDialog.dismiss();
      }
    });
    //mScanAsyncTask.execute(ContractService.daily_url);
  }

  public void onDestroy() {
    mThrowable = null;
    mView = null;
    mContract = null;
    mRequest = null;
  }

  //使用异步进程调用程序
  private class ScanAsyncTask extends AsyncTask<String, Integer, String> {

    @Override protected void onPreExecute() {
      super.onPreExecute();
      mLoadingDialog.show();
    }

    @Override protected String doInBackground(String... params) {
      return null;
    }

    @Override protected void onPostExecute(String s) {
      super.onPostExecute(s);
      mLoadingDialog.dismiss();
      mView.onSuccess(mContract);
    }

    @Override protected void onCancelled() {
      super.onCancelled();
      mLoadingDialog.dismiss();
    }
  }
}
