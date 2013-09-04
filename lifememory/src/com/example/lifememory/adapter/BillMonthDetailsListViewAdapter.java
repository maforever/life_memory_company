package com.example.lifememory.adapter;

import java.util.List;

import com.example.lifememory.R;
import com.example.lifememory.activity.model.BillMonthDetailsListViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BillMonthDetailsListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private List<BillMonthDetailsListViewModel> monthDetails;
	public BillMonthDetailsListViewAdapter(Context context, List<BillMonthDetailsListViewModel> monthDetails) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.monthDetails = monthDetails;
	}
	@Override
	public int getCount() {
		return monthDetails.size();
	}

	@Override
	public Object getItem(int position) {
		return monthDetails.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView dayTv;
		TextView weekTv;
		TextView incomeTv;
		TextView spendTv;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.bill_monthdetails_listview_item, null);
			dayTv = (TextView) convertView.findViewById(R.id.day);
			weekTv = (TextView) convertView.findViewById(R.id.week);
			incomeTv = (TextView) convertView.findViewById(R.id.income);
			spendTv = (TextView) convertView.findViewById(R.id.spend);
		}else {
			dayTv = (TextView) convertView.findViewById(R.id.day);
			weekTv = (TextView) convertView.findViewById(R.id.week);
			incomeTv = (TextView) convertView.findViewById(R.id.income);
			spendTv = (TextView) convertView.findViewById(R.id.spend);
		}
		
		dayTv.setText(monthDetails.get(position).getDay());
		incomeTv.setText(monthDetails.get(position).getIncome());
		spendTv.setText(monthDetails.get(position).getSpend());
		weekTv.setText(monthDetails.get(position).getWeek());
		return convertView;
	}

}







