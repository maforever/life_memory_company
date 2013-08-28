package com.example.lifememory.activity;

import com.example.lifememory.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BillAccountAddActivity extends Activity {
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_account_add_layout);
	}
	
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			BillAccountAddActivity.this.finish();
			overridePendingTransition(R.anim.activity_steady, R.anim.activity_down);
			break;
		case R.id.save:
			break;
		case R.id.accountCatagoryLayout:
			break;
		case R.id.accountNameLayout:
			break;
		case R.id.accountBizhongLayout:
			break;
		case R.id.accountyueLayout:
			break;
		case R.id.accountIsNoticeLayout:
			break;
		case R.id.accountNoticeValueLayout:
			break;
		case R.id.beizhu:
			intent = new Intent(BillAccountAddActivity.this, BillTextInputActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.activity_up, R.anim.activity_steady);
			break;
		}
	}
}
