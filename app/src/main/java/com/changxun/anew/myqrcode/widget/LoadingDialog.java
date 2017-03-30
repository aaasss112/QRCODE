package com.changxun.anew.myqrcode.widget;

import android.app.ProgressDialog;
import android.content.Context;
import com.changxun.anew.myqrcode.R;

public class LoadingDialog extends ProgressDialog {

  private static LoadingDialog mDialog;

  public LoadingDialog(Context context) {
    super(context);
  }

  public LoadingDialog(Context context, int theme) {
    super(context, theme);
  }

  public static LoadingDialog createDialog(Context context) {
    mDialog = new LoadingDialog(context, R.style.ProgressDialogStyle);
    return mDialog;
  }

  public void show() {
    super.show();
    mDialog.setContentView(R.layout.dialog_loading);
  }
}
