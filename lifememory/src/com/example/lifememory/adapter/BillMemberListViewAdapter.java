package com.example.lifememory.adapter;

import java.util.List;
import com.example.lifememory.R;
import com.example.lifememory.activity.model.BillMember;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BillMemberListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<BillMember> members;
	private int currentSelectedIndex = -1;
	
	public BillMemberListViewAdapter(Context context, List<BillMember> members2) {
		this.inflater = LayoutInflater.from(context);
		this.members = members2;
	}
	
	@Override
	public int getCount() {
		return members.size();
	}

	@Override
	public Object getItem(int position) {
		return members.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setSelected(int selectedId) {
		this.currentSelectedIndex = selectedId;
		this.notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView deleteTag;
		ImageView selectedTag;
		TextView name;
		ViewHolder vh;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.bill_member_listview_item, null);
			deleteTag = (TextView) convertView.findViewById(R.id.deleteTag);
			selectedTag = (ImageView) convertView.findViewById(R.id.selectedTag);
			name = (TextView) convertView.findViewById(R.id.name);
			vh = new ViewHolder();
			vh.selectedTag = selectedTag;
			vh.deleteTag = deleteTag;
			vh.name = name;
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
			deleteTag = vh.deleteTag;
			selectedTag = vh.selectedTag;
			name = vh.name;
		}
		selectedTag.setVisibility(ViewGroup.GONE);
		if(position == currentSelectedIndex) {
			selectedTag.setVisibility(ViewGroup.VISIBLE);
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView deleteTag;
		ImageView selectedTag;
		TextView name;
	}
	
	
}
