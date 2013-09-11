package com.example.lifememory.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.lifememory.R;
import com.example.lifememory.activity.model.BillTemplate;
import com.example.lifememory.adapter.BillTemplateGridViewAdapter;
import com.example.lifememory.db.service.BillTemplateService;

public class BillTempleteSelectActivity extends Activity {
	private GridView gridView;
	private List<BillTemplate> templates;
	private BillTemplateService templateService;
	private BillTemplateGridViewAdapter adapter;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				//读取数据成功
				initGridView();
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_template_select_layout);
		
		templateService = new BillTemplateService(this);
		findViews();
		new InitDatasThread().start();
	}
	
	private void findViews() {
		gridView = (GridView) this.findViewById(R.id.gridView);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			back();
			break;
		}
	}
	
	private class InitDatasThread extends Thread {
		@Override
		public void run() {
			templates = templateService.findAllTemplates();
			if(templates.size() > 0) {
				handler.sendEmptyMessage(0);
			}
		}
	}
	
	private void initGridView() {
		adapter = new BillTemplateGridViewAdapter(this, templates);
		gridView.setAdapter(adapter);
	}
	
	private void back() {
		BillTempleteSelectActivity.this.finish();
		overridePendingTransition(R.anim.activity_steady, R.anim.activity_down);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			back();
			break;
		}
		return true;
	}
}


















