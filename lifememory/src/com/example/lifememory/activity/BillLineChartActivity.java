package com.example.lifememory.activity;

import com.example.lifememory.R;
import com.example.lifememory.chart.line.XYChartView;
import com.example.lifememory.db.service.BillInfoService;
import android.app.Activity;
import android.os.Bundle;

public class BillLineChartActivity extends Activity {
	BillInfoService infoService;
	int[] inValues, outValues;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_chart_line_layout);
		
		infoService = new BillInfoService(this);
        XYChartView xyChart = (XYChartView)findViewById(R.id.xyChart);
        
        String[] coordinateX = new String[]{"1��", "2��", "3��", "4��", "5��", "6��",
        		"7��", "8��", "9��", "10��", "11��", "12��"  };
        
        inValues = infoService.findIncomeOrSpendByYear(2, "2013");
        outValues = infoService.findIncomeOrSpendByYear(1, "2013");
        
        xyChart.setUnitX("��");
//        xyChart.addLine("����", coordinateX, inValues);
//        xyChart.addLine("֧��", coordinateX, outValues);
	}
	
}
