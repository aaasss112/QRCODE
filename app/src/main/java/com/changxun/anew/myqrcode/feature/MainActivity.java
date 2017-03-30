package com.changxun.anew.myqrcode.feature;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.changxun.anew.myqrcode.R;
import com.changxun.anew.myqrcode.feature.base.BaseActivity;
import com.changxun.anew.myqrcode.model.Contract;
import com.changxun.anew.myqrcode.widget.LoadingDialog;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends BaseActivity {

  public static final int REQUEST_CODE = 0;
  private MainPresenter mPresenter;
  @BindView(R.id.img_qr) ImageView mQRImg;
  @BindView(R.id.txt_amount) TextView txtAmount;
  @BindView(R.id.txt_amount_total) TextView txtAmountTotal;
  @BindView(R.id.txt_budget) TextView txtBudget;
  @BindView(R.id.txt_budget_income) TextView txtBudgetIncome;
  @BindView(R.id.txt_cotname) TextView txtCotName;
  @BindView(R.id.txt_expense) TextView txtExpense;
  @BindView(R.id.txt_gathering) TextView txtGathering;
  @BindView(R.id.txt_nature) TextView txtNature;
  @BindView(R.id.txt_pm_id) TextView txtPMId;
  @BindView(R.id.txt_prj_name) TextView txtPrjName;
  @BindView(R.id.txt_progress) TextView txtProgress;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
    if (mPresenter == null) {
      mPresenter = new MainPresenter(this);
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mPresenter.onDestroy();
  }

  public void onSuccess(Contract contract) {

    txtCotName.setText(getString(R.string.contract_info, getString(R.string.contract_cotname), ""));
    txtAmount.setText(getString(R.string.contract_info, getString(R.string.contract_amount), ""));
    txtAmountTotal.setText(getString(R.string.contract_info, getString(R.string.contract_amount_total), ""));
    txtBudget.setText(getString(R.string.contract_info, getString(R.string.contract_budget), ""));
    txtBudgetIncome.setText(getString(R.string.contract_info, getString(R.string.contract_budget_income), ""));
    txtExpense.setText(getString(R.string.contract_info, getString(R.string.contract_expenses), ""));
    txtGathering.setText(getString(R.string.contract_info, getString(R.string.contract_gathering), ""));
    txtNature.setText(getString(R.string.contract_info, getString(R.string.contract_nature), ""));
    txtPMId.setText(getString(R.string.contract_info, getString(R.string.contract_pm_id), ""));
    txtPrjName.setText(getString(R.string.contract_info, getString(R.string.contract_prj_name), ""));
    txtProgress.setText(getString(R.string.contract_info, getString(R.string.contract_progress), ""));

  }

  public void onError(Throwable throwable) {

  }

  @OnClick({ R.id.img_qr }) public void onImgClick() {
    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
    startActivityForResult(intent, REQUEST_CODE);
    ////测试网络请求
    //mPresenter.request();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE) {
      //处理扫描结果（在界面上显示）
      if (null != data) {
        Bundle bundle = data.getExtras();
        if (bundle == null) {
          return;
        }
        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
          String result = bundle.getString(CodeUtils.RESULT_STRING);
          Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();

          //根据处理结果传参调用接口
          mPresenter.request();
        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
          Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
        }
      }
    }
  }
}
