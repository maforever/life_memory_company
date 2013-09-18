package com.example.lifememory.fragments.chart;

import com.example.lifememory.R;
import com.example.lifememory.activity.BudgetBarChart;
import com.example.lifememory.activity.BudgetPieChart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FR_BillBarChartFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bill_chart_bar_layout, container, false);
		LinearLayout layout = (LinearLayout)view.findViewById(R.id.container);
		BudgetBarChart chart = new BudgetBarChart(getActivity(),layout, null, 1, null);
		return view;
	}
}
