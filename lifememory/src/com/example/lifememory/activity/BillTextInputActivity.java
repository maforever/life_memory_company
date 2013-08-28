package com.example.lifememory.activity;

import com.example.lifememory.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BillTextInputActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_text_input_layout);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			BillTextInputActivity.this.finish();
			overridePendingTransition(R.anim.activity_steady, R.anim.activity_down);
			break;
		case R.id.ok:
			break;
		}
	}
	
}
