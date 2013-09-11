package com.example.lifememory.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lifememory.R;
import com.example.lifememory.activity.model.BillTemplate;

public class BillTemplateGridViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<BillTemplate> templates;
	public BillTemplateGridViewAdapter(Context context, List<BillTemplate> templates) {
		this.inflater = LayoutInflater.from(context);
		this.templates = templates;
	}
	
	@Override
	public int getCount() {
		return templates.size();
	}

	@Override
	public Object getItem(int position) {
		return templates.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout background;
		TextView name;
		ViewHolder vh;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.bill_templates_gridview_item, null);
			background = (LinearLayout) convertView.findViewById(R.id.background);
			name = (TextView) convertView.findViewById(R.id.name);
			vh = new ViewHolder();
			vh.background = background;
			vh.name = name;
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
			background = vh.background;
			name = vh.name;
		}
		
		switch (templates.get(position).getBillType()) {
		case 1:
			//支出
			convertView.setBackgroundResource(R.drawable.bill_out_template_selector);
			break;
		case 2:
			//收入
			convertView.setBackgroundResource(R.drawable.bill_in_template_selector);
			break;
		case 3:
			//转账
			convertView.setBackgroundResource(R.drawable.bill_transfer_template_selector);
			break;
		}
		
		name.setText(templates.get(position).getName());
		
		return convertView;
	}
	
	static class ViewHolder {
		LinearLayout background;
		TextView name;
	}

}














