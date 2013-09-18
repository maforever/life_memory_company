package com.example.lifememory.activity;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.example.lifememory.chart.AbstractDemoChart;
import com.example.lifememory.db.service.BillCatagoryService;

public class BudgetBarChart extends AbstractDemoChart{
	  private static final int SERIES_NR = 2;
	  private BillCatagoryService catagoryService;
	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getDesc() {
		return null;
	}

	@Override
	public Intent execute(Context context) {
		return null;
	}
	
	public BudgetBarChart(Context context, LinearLayout layout, String ym,
			int billType, BillCatagoryService catagoryService) {
		super();
		this.catagoryService = catagoryService;
		myExecute(context, layout, ym, billType);
	}
	
	public void myExecute(Context context, LinearLayout layout, String ym,
			int billType) {
		
		
	      XYMultipleSeriesRenderer renderer = getBarDemoRenderer();
	      setChartSettings(renderer);
	      GraphicalView view = ChartFactory.getBarChartView(context, getBarDemoDataset(), renderer,  Type.DEFAULT);
		layout.addView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}

	  public XYMultipleSeriesRenderer getBarDemoRenderer() {
		    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		    renderer.setAxisTitleTextSize(16);
		    renderer.setChartTitleTextSize(20);
		    renderer.setLabelsTextSize(15);
		    renderer.setLegendTextSize(15);
		    renderer.setMargins(new int[] {20, 30, 15, 0});
		    SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		    r.setColor(Color.BLUE);
		    renderer.addSeriesRenderer(r);
		    r = new SimpleSeriesRenderer();
		    r.setColor(Color.RED);
		    renderer.addSeriesRenderer(r);
		    return renderer;
		  }
	
	  private XYMultipleSeriesDataset getBarDemoDataset() {
		    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		    final int nr = 10;
		    Random r = new Random();
		    for (int i = 0; i < SERIES_NR; i++) {
		      CategorySeries series = new CategorySeries("Demo series " + (i + 1));
		      for (int k = 0; k < nr; k++) {
		        series.add(100 + r.nextInt() % 100);
		      }
		      dataset.addSeries(series.toXYSeries());
		    }
		    return dataset;
		  }
	
	  private void setChartSettings(XYMultipleSeriesRenderer renderer) {
		    renderer.setChartTitle("Chart demo");
		    renderer.setXTitle("x values");
		    renderer.setYTitle("y values");
		    renderer.setXAxisMin(0.5);
		    renderer.setXAxisMax(10.5);
		    renderer.setYAxisMin(0);
		    renderer.setYAxisMax(210);
		  }
	
	
}
