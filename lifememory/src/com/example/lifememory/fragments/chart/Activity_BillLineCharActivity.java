package com.example.lifememory.fragments.chart;

import com.example.lifememory.R;
import com.example.lifememory.activity.AverageTemperatureChart;
import com.example.lifememory.db.service.BillCatagoryService;
import com.example.lifememory.db.service.BillInfoService;
import com.example.lifememory.utils.DateFormater;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

public class Activity_BillLineCharActivity extends Activity {
	String ym;
	int billType;
	BillInfoService infoService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_chart_line_layout);
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.container);
		infoService = new BillInfoService(this);
		ym = DateFormater.getInstatnce().getYear();
		
		AverageTemperatureChart chart = new AverageTemperatureChart(this, layout, ym, billType, infoService);
		
		
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			back();
			break;
		}
	}
	
	private void back() {
		Activity_BillLineCharActivity.this.finish();
		overridePendingTransition(R.anim.activity_steady, R.anim.activity_down);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			back();
		}
		return true;
	}
	
}











