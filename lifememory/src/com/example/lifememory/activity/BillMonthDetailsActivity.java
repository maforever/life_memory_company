package com.example.lifememory.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.example.lifememory.R;
import com.example.lifememory.activity.model.Bill;
import com.example.lifememory.activity.model.BillMonthDetailsListViewModel;
import com.example.lifememory.adapter.BillMonthDetailsListViewAdapter;
import com.example.lifememory.db.service.BillInfoService;
import com.example.lifememory.utils.DateFormater;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class BillMonthDetailsActivity extends Activity {
	private String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
	private String[] months_little = { "4", "6", "9", "11" };
	final List<String> list_big = Arrays.asList(months_big);
	final List<String> list_little = Arrays.asList(months_little);
	private String dateTitle;   //activity的标题
	private Calendar calendar;
	private int year, month, day;
	private TextView title;    
	private String dateYM;      //数据库查找当月数据的参数，格式yyyy-MM
	private BillInfoService billService;
	private LinearLayout contentLayout;
	private TextView emptyNoticeTv;
	private List<String> dbYMDs;;               //查找数据库数据的属性 yyyy-MM-dd
	private List<BillMonthDetailsListViewModel> monthDetails = new ArrayList<BillMonthDetailsListViewModel>();
	private BillMonthDetailsListViewModel monthDetail = null;
	private List<Bill> bills;
	private ListView listView;
	private BillMonthDetailsListViewAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_month_details_layout);
		
		billService = new BillInfoService(this);
		findViews();
		title.setText(initDateTitle());
		
		initDatasAndSetAdapter(dateYM);
		
	}
	
	private void findViews() {
		title = (TextView) this.findViewById(R.id.title);
		listView = (ListView) this.findViewById(R.id.listView);
		contentLayout = (LinearLayout) this.findViewById(R.id.content);
		emptyNoticeTv = (TextView) this.findViewById(R.id.msg);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			back();
			break;
		case R.id.arrowLeft:
			arrowLeftDonw();
			break;
		case R.id.arrowRight:
			arrowRightDonw();
			break;
		}
	}
	
	private void back() {
		BillMonthDetailsActivity.this.finish();
		overridePendingTransition(R.anim.activity_steady, R.anim.activity_down);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			back();
		}
		return true;
	}
	
	private String initDateTitle() {
		String dateTitleStr = "";
		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DATE);
		
		dateTitleStr = year + "年" + month + "月流水";
		if(month < 10) {
			dateYM = year + "-0" + month;
		}else {
			dateYM = year + "-" + month;
		}
//		Log.i("a", "dataYM = " + dataYM);
		return dateTitleStr;
	}
	
	private void arrowLeftDonw() {
		String dateTitleStr = "";
		if(month > 1) {
			month--;
		}else {
			month = 12;
			year--;
		}
		dateTitleStr = year + "年" + month + "月流水";
		
		if(month < 10) {
			dateYM = year + "-0" + month;
		}else {
			dateYM = year + "-" + month;
		}
//		Log.i("a", "dataYM = " + dataYM);
		title.setText(dateTitleStr);
		
		
		if(billService.isCurrentHaveBills(dateYM)) {
			contentLayout.setVisibility(ViewGroup.VISIBLE);
			emptyNoticeTv.setVisibility(ViewGroup.GONE);
			initDatasAndSetAdapter(dateYM);
		}else {
			contentLayout.setVisibility(ViewGroup.GONE);
			emptyNoticeTv.setVisibility(ViewGroup.VISIBLE);
		}
	}
	
	private void arrowRightDonw() {
		String dateTitleStr = "";
		if(month < 12) {
			month++;
		}else {
			month = 1;
			year++;
		}
		if(month < 10) {
			dateYM = year + "-0" + month;
		}else {
			dateYM = year + "-" + month;
		}
		dateTitleStr = year + "年" + month + "月流水";
		title.setText(dateTitleStr);
//		Log.i("a", "dataYM = " + dataYM);
		
		
		if(billService.isCurrentHaveBills(dateYM)) {
			contentLayout.setVisibility(ViewGroup.VISIBLE);
			emptyNoticeTv.setVisibility(ViewGroup.GONE);
			initDatasAndSetAdapter(dateYM);
		}else {
			contentLayout.setVisibility(ViewGroup.GONE);
			emptyNoticeTv.setVisibility(ViewGroup.VISIBLE);
		}
	}
	
	
	private void initDatasAndSetAdapter(String dateYM) {
		if(billService.isCurrentHaveBills(dateYM)) {
			contentLayout.setVisibility(ViewGroup.VISIBLE);
			emptyNoticeTv.setVisibility(ViewGroup.GONE);
			dbYMDs = billService.findAllYMDInMonth(dateYM);
			
			monthDetails = new ArrayList<BillMonthDetailsListViewModel>();
			for(String paramYMD : dbYMDs) {
				bills = billService.findBillByYMD(paramYMD);
				double todayIncomeResult = 0;
				double todaySpendResult = 0;
				if (bills != null && bills.size() > 0) {
					for (Bill bill : bills) {
						double todayNum = Double.parseDouble(bill.getJine());
						switch (bill.getBillType()) {
						case 1:
							// 支出
							todaySpendResult -= todayNum;
							break;
						case 2:
							// 收入
							todayIncomeResult += todayNum;
							break;
						}
					}
				}
				monthDetail = new BillMonthDetailsListViewModel();
				monthDetail.setBills(bills);
				monthDetail.setIncome(todayIncomeResult + "");
				monthDetail.setSpend(todaySpendResult + "");
				String day = paramYMD.substring(paramYMD.lastIndexOf("-") + 1, paramYMD.length());
				monthDetail.setDay(day);
				monthDetail.setWeek(DateFormater.getInstatnce().getWeekday(paramYMD));
				monthDetails.add(monthDetail);
			}
			
			
			adapter = new BillMonthDetailsListViewAdapter(this, monthDetails);
			listView.setAdapter(adapter);
			

			
		}else {
			contentLayout.setVisibility(ViewGroup.GONE);
			emptyNoticeTv.setVisibility(ViewGroup.VISIBLE);
		}
	}
}


























