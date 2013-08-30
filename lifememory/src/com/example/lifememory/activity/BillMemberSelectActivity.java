package com.example.lifememory.activity;

import java.util.List;

import com.example.lifememory.R;
import com.example.lifememory.activity.model.BillMember;
import com.example.lifememory.adapter.BillMemberListViewAdapter;
import com.example.lifememory.db.service.BillMemberService;
import com.example.lifememory.utils.ConstantUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BillMemberSelectActivity extends Activity {
	private ListView listView;
	private Intent intent;
	private List<BillMember> members;
	private BillMemberService memberService;
	private int currentSelectedIndex = 0;
	private BillMemberListViewAdapter adapter;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				initViews();
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_member_select_layout);
		
		memberService = new BillMemberService(this);
		currentSelectedIndex = this.getIntent().getIntExtra("currentSelectedIndex", 0);
		findViews();
		new InitDatasThread().start();
		setListeners();
	}
	
	
	private class InitDatasThread extends Thread {
		@Override
		public void run() {
			members = memberService.listAllMember();
			handler.sendEmptyMessage(0);
		}
	}
	
	private void findViews() {
		listView = (ListView) this.findViewById(R.id.listView);
	}
	
	private void initViews() {
		adapter = new BillMemberListViewAdapter(BillMemberSelectActivity.this, members);
		listView.setAdapter(adapter);
		adapter.setSelected(currentSelectedIndex);
	}
	
	private void setListeners() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				intent = new Intent();
				intent.putExtra("member", members.get(position).getName());
				intent.putExtra("currentSelectedIndex", position);
				BillMemberSelectActivity.this.setResult(ConstantUtil.SELECT_MEMEBER_FINISHED, intent);
				BillMemberSelectActivity.this.finish();
				overridePendingTransition(R.anim.activity_steady, R.anim.activity_down);
			}
		});
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			back();
			break;
		case R.id.add:
			break;
		}
	}
	
	private void back() {
		intent = new Intent();
		this.setResult(ConstantUtil.EDIT_NOCHANGE_BACK, intent);
		BillMemberSelectActivity.this.finish();
		overridePendingTransition(R.anim.activity_steady, R.anim.activity_down);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			back();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		memberService.closeDB();
	}
}























