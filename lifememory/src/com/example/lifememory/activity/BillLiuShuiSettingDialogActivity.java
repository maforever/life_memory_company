package com.example.lifememory.activity;

import com.example.lifememory.R;
import com.example.lifememory.db.service.BillInfoService;
import com.example.lifememory.dialog.DialogAlert;
import com.example.lifememory.dialog.DialogAlertBill;
import com.example.lifememory.dialog.DialogAlertListener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BillLiuShuiSettingDialogActivity extends Activity {
	private int idx;
	private BillInfoService billService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_bill_liushui);
		
		billService = new BillInfoService(this);
		idx = this.getIntent().getIntExtra("idx", 0);
		
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.edit:
			Intent intent = new Intent(BillLiuShuiSettingDialogActivity.this, BillInputActivity.class);
			intent.putExtra("flag", "view");
			intent.putExtra("idx", idx);
			this.startActivity(intent);
			this.overridePendingTransition(R.anim.activity_up, R.anim.activity_steady);
			break;
		case R.id.delete:
			new DialogAlertBill(this, listener, "确定删除所选账单信息吗？").show();
			break;
		case R.id.changetoincome:
			
			break;
		case R.id.changetospend:
			
			break;
		case R.id.close:
			BillLiuShuiSettingDialogActivity.this.finish();
			break;
		}
	}
	
	private DialogAlertListener listener = new DialogAlertListener() {
		
		@Override
		public void onDialogUnSave(Dialog dlg, Object param) {
			
		}
		
		@Override
		public void onDialogSave(Dialog dlg, Object param) {
			
		}
		
		@Override
		public void onDialogOk(Dialog dlg, Object param) {
			billService.deleteBillByIdx(idx);
			BillLiuShuiSettingDialogActivity.this.finish();
		}
		
		@Override
		public void onDialogCreate(Dialog dlg, Object param) {
			
		}
		
		@Override
		public void onDialogCancel(Dialog dlg, Object param) {
			
		}
	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		billService.closeDB();
	}
}
