package com.example.lifememory.adapter;

import java.util.List;

import com.example.lifememory.R;
import com.example.lifememory.activity.model.BillAccountExpandableListViewItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BillAccountExpandableListAdapter extends BaseExpandableListAdapter {
	private LayoutInflater inflater;
	private List<BillAccountExpandableListViewItem> expandableItems;
	private int currentGroupId = -1;
	private int currentChildId = -1;
	public BillAccountExpandableListAdapter(Context context, List<BillAccountExpandableListViewItem> expandableItems) {
		inflater = LayoutInflater.from(context);
		this.expandableItems = expandableItems;
	}
	
	@Override
	public int getGroupCount() {
		return this.expandableItems.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.expandableItems.get(groupPosition).getAccountItems().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.expandableItems.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.expandableItems.get(groupPosition).getAccountItems().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}
	
	public void setSelected(int groupId, int childId) {
		this.currentGroupId = groupId;
		this.currentChildId = childId;
		this.notifyDataSetChanged();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView title;
		ImageView indicator;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.bill_account_expandablelist_parentitem, null);
			title = (TextView) convertView.findViewById(R.id.title);
			indicator = (ImageView) convertView.findViewById(R.id.indicator);
		}else {
			title = (TextView) convertView.findViewById(R.id.title);
			indicator = (ImageView) convertView.findViewById(R.id.indicator);
		}
		title.setText(this.expandableItems.get(groupPosition).getTitle());
		
		if(isExpanded) {
			indicator.setImageResource(R.drawable.bill_account_indicator_down);
		}else {
			indicator.setImageResource(R.drawable.bill_account_indicator_up);
		}
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ImageView imageView;
		ImageView selectedTag;
		TextView name;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.bill_account_listview_item, null);
			imageView = (ImageView) convertView.findViewById(R.id.imageView);
			selectedTag = (ImageView) convertView.findViewById(R.id.selectedTag);
			name = (TextView) convertView.findViewById(R.id.name);
		}else {
			imageView = (ImageView) convertView.findViewById(R.id.imageView);
			selectedTag = (ImageView) convertView.findViewById(R.id.selectedTag);
			name = (TextView) convertView.findViewById(R.id.name);
		}
		imageView.setImageResource(this.expandableItems.get(groupPosition).getAccountItems().get(childPosition).getImageId());
		name.setText(this.expandableItems.get(groupPosition).getAccountItems().get(childPosition).getName());
		selectedTag.setVisibility(ViewGroup.GONE);
		
		if(currentGroupId == groupPosition && currentChildId == childPosition) {
			selectedTag.setVisibility(ViewGroup.VISIBLE);
		}
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
