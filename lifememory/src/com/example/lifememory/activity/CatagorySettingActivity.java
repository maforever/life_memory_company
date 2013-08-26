package com.example.lifememory.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.lifememory.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CatagorySettingActivity extends Activity {
	private ListView firstListView, secondaryListView;
	private String[] firstDatas = new String[]{"a","b","c","d","e","f", "b1","b2","b3","b4","b5","b1","b2","b3","b4","b5", "b1","b2","b3","b4","b5","b1","b2","b3","b4","b5", "b1","b2","b3","b4","b5","b1","b2","b3","b4","b5"};
	private String[] secondaryDatas1 = new String[]{"a1","a2","a3","a4","a5"};
	private String[] secondaryDatas2 = new String[]{"b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1","b2","b3","b4","b5"};
	private String[] secondaryDatas3 = new String[]{"c1","c2","c3","c4","c5", "b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1"};
	private String[] secondaryDatas4 = new String[]{"d1","d2","d3","d4","d5", "b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1"};
	private String[] secondaryDatas5 = new String[]{"e1","e2","e3","e4","e5", "b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1"};
	private String[] secondaryDatas6 = new String[]{"f1","f2","f3","f4","f5", "b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1","b2","b3","b4","b5","b1"};
	private List<String[]> secondaryMainData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_catagoryselect_layout);
		
		findViews();
		initDatas();
		listViewAdapter();
		setListeners();
	}

	private void findViews() {
		firstListView = (ListView) this.findViewById(R.id.firstListView);
		secondaryListView = (ListView) this.findViewById(R.id.secondaryListView);
	}
	
	private void initDatas() {
		secondaryMainData = new ArrayList<String[]>();
		secondaryMainData.add(secondaryDatas1);
		secondaryMainData.add(secondaryDatas2);
		secondaryMainData.add(secondaryDatas3);
		secondaryMainData.add(secondaryDatas4);
		secondaryMainData.add(secondaryDatas5);
		secondaryMainData.add(secondaryDatas6);
	}
	
	private void listViewAdapter() {
		MyAdapter firstAdapter = new MyAdapter(firstDatas);
		firstListView.setAdapter(firstAdapter);
		MyAdapter secondaryAdapter = new MyAdapter(secondaryMainData.get(0));
		secondaryListView.setAdapter(secondaryAdapter);
	}
	
	private class MyAdapter extends BaseAdapter {
		private String[] datas = null;
		public MyAdapter(String[] datas) {
			this.datas = datas;
		}
		@Override
		public int getCount() {
			return datas.length;
		}

		@Override
		public Object getItem(int position) {
			return datas[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = new TextView(CatagorySettingActivity.this);
			view.setText(datas[position]);
			view.setTextSize(20);
			return view;
		}
		
	}
	
	private void setListeners() {
		firstListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyAdapter secondaryAdapter = new MyAdapter(secondaryMainData.get(position));
				secondaryListView.setAdapter(secondaryAdapter);
			}
			
		});
	}

}

















