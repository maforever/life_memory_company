package com.example.lifememory.activity;

import java.text.DecimalFormat;

import com.example.lifememory.R;
import com.example.lifememory.activity.model.Bill;
import com.example.lifememory.utils.AppAplication;
import com.example.lifememory.utils.CopyFileFromData;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class BillInputActivity extends Activity {
	private TextView zhichuBtn, shouruBtn, zhuanzhangBtn;
	private CheckBox baoxiaoCb = null;
	private ViewFlipper viewFlipper = null;
	private LayoutInflater inflater;
	private View childView1, childView2, childView3;
	private PopupWindow calculator = null; // 计算器
	private View popWinParentView = null;
	private TextView zhichuJine, shouruJine, zhuanzhangJine, jineTv;
	private String jie_txt;
	private Bill bill = null;
	private boolean isFloat = false; // 标记为，用于标记是否点击了计算器中的小数点
	private String temp1Str, temp2Str; // 计算时符号两端的数据缓存 temp1Str +(-, *,
										// /)temp2Str
	private boolean isClickFlag = false; // 标记是否点击了加减乘除按钮
	private int flagId; // 0加1减2乘3除

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bill_input_layout);

		inflater = LayoutInflater.from(this);
		bill = new Bill();
		findViews();
		initViews();
		initCalculator();
	}

	private void findViews() {
		zhichuBtn = (TextView) this.findViewById(R.id.zhichu);
		shouruBtn = (TextView) this.findViewById(R.id.shouru);
		zhuanzhangBtn = (TextView) this.findViewById(R.id.zhuanzhang);
		baoxiaoCb = (CheckBox) this.findViewById(R.id.baoxiaocb);
		viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);
		childView1 = viewFlipper.getChildAt(0);
		childView2 = viewFlipper.getChildAt(1);
		childView3 = viewFlipper.getChildAt(2);
		zhichuJine = (TextView) childView1.findViewById(R.id.jine);
		shouruJine = (TextView) childView2.findViewById(R.id.jine);
		zhuanzhangJine = (TextView) childView3.findViewById(R.id.jine);

		popWinParentView = this.findViewById(R.id.popWinParent);
	}

	private void initViews() {
		zhichuBtn.setBackgroundResource(R.drawable.exit_demo_mode_btn_pressed);
		zhichuBtn.setTextColor(Color.WHITE);

	}

	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.zhichu:
			// 支出按钮
			changeBtnBack(0);
			break;
		case R.id.shouru:
			// 收入按钮
			changeBtnBack(1);
			break;
		case R.id.zhuanzhang:
			// 转账按钮
			changeBtnBack(2);
			break;
		case R.id.jinelayout:
			// 金额
			showCalculator();
			break;
		case R.id.leixinglayout:
			//点击转到类型设置界面
			Intent intent = new Intent(BillInputActivity.this, CatagorySettingActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.activity_up, R.anim.activity_steady);
			break;
		case R.id.zhanghulayout:
			break;
		case R.id.riqilayout:
			break;
		case R.id.chengyuanlayout:
			break;
		case R.id.beizhulayout:
			break;
		case R.id.baoxiaolayout:
			if (baoxiaoCb.isChecked()) {
				baoxiaoCb.setChecked(false);
			} else {
				baoxiaoCb.setChecked(true);
			}
			break;
		case R.id.back:
			BillInputActivity.this.finish();
			overridePendingTransition(R.anim.activity_steady,
					R.anim.activity_down);
			break;
		case R.id.save:
			break;
		}
	}

	private void changeBtnBack(int index) {
		switch (index) {
		case 0:
			// 支出
			zhichuBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_pressed);
			zhichuBtn.setTextColor(Color.WHITE);
			shouruBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_normal);
			shouruBtn.setTextColor(Color.BLACK);
			zhuanzhangBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_normal);
			zhuanzhangBtn.setTextColor(Color.BLACK);

			if (viewFlipper.getDisplayedChild() != 0) {
				if (viewFlipper.getDisplayedChild() == 1) {
					viewFlipper.showPrevious();
				} else if (viewFlipper.getDisplayedChild() == 2) {
					viewFlipper.showPrevious();
					viewFlipper.showPrevious();
				}
			}

			break;
		case 1:
			// 收入
			shouruBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_pressed);
			shouruBtn.setTextColor(Color.WHITE);
			zhuanzhangBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_normal);
			zhuanzhangBtn.setTextColor(Color.BLACK);
			zhichuBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_normal);
			zhichuBtn.setTextColor(Color.BLACK);

			if (viewFlipper.getDisplayedChild() != 1) {
				if (viewFlipper.getDisplayedChild() == 0) {
					viewFlipper.showNext();
				} else if (viewFlipper.getDisplayedChild() == 2) {
					viewFlipper.showPrevious();
				}
			}

			break;
		case 2:
			// 转账
			zhuanzhangBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_pressed);
			zhuanzhangBtn.setTextColor(Color.WHITE);
			zhichuBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_normal);
			zhichuBtn.setTextColor(Color.BLACK);
			shouruBtn
					.setBackgroundResource(R.drawable.exit_demo_mode_btn_normal);
			shouruBtn.setTextColor(Color.BLACK);

			if (viewFlipper.getDisplayedChild() != 2) {
				if (viewFlipper.getDisplayedChild() == 0) {
					viewFlipper.showNext();
					viewFlipper.showNext();
				} else if (viewFlipper.getDisplayedChild() == 1) {
					viewFlipper.showNext();
				}
			}
			break;
		}
		refreshJinETextView();
	}

	LinearLayout cal_equal;
	LinearLayout cal_del;
	ImageView cal_sure;

	// 初始化计算器 popwindow
	private void initCalculator() {
		View contentView = inflater.inflate(R.layout.popwindow_bill_counter,
				null);
		calculator = new PopupWindow(contentView, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		TextView cal_add = (TextView) contentView.findViewById(R.id.cal_add);
		TextView cal_minus = (TextView) contentView
				.findViewById(R.id.cal_minus);
		TextView cal_multiply = (TextView) contentView
				.findViewById(R.id.cal_multiply);
		TextView cal_divide = (TextView) contentView
				.findViewById(R.id.cal_divide);
		TextView cal_one = (TextView) contentView.findViewById(R.id.cal_one);
		TextView cal_two = (TextView) contentView.findViewById(R.id.cal_two);
		TextView cal_three = (TextView) contentView
				.findViewById(R.id.cal_three);
		TextView cal_four = (TextView) contentView.findViewById(R.id.cal_four);
		TextView cal_five = (TextView) contentView.findViewById(R.id.cal_five);
		TextView cal_six = (TextView) contentView.findViewById(R.id.cal_six);
		TextView cal_seven = (TextView) contentView
				.findViewById(R.id.cal_seven);
		TextView cal_eight = (TextView) contentView
				.findViewById(R.id.cal_eight);
		TextView cal_nine = (TextView) contentView.findViewById(R.id.cal_nine);
		TextView cal_zero = (TextView) contentView.findViewById(R.id.cal_zero);
		TextView cal_dot = (TextView) contentView.findViewById(R.id.cal_dot);
		cal_equal = (LinearLayout) contentView.findViewById(R.id.cal_equal);
		cal_del = (LinearLayout) contentView.findViewById(R.id.cal_del);
		cal_sure = (ImageView) contentView.findViewById(R.id.cal_sure);
		cal_add.setOnClickListener(new CalculatorBtnClickListener());
		cal_minus.setOnClickListener(new CalculatorBtnClickListener());
		cal_multiply.setOnClickListener(new CalculatorBtnClickListener());
		cal_divide.setOnClickListener(new CalculatorBtnClickListener());
		cal_one.setOnClickListener(new CalculatorBtnClickListener());
		cal_two.setOnClickListener(new CalculatorBtnClickListener());
		cal_three.setOnClickListener(new CalculatorBtnClickListener());
		cal_four.setOnClickListener(new CalculatorBtnClickListener());
		cal_five.setOnClickListener(new CalculatorBtnClickListener());
		cal_six.setOnClickListener(new CalculatorBtnClickListener());
		cal_seven.setOnClickListener(new CalculatorBtnClickListener());
		cal_eight.setOnClickListener(new CalculatorBtnClickListener());
		cal_nine.setOnClickListener(new CalculatorBtnClickListener());
		cal_zero.setOnClickListener(new CalculatorBtnClickListener());
		cal_dot.setOnClickListener(new CalculatorBtnClickListener());
		cal_del.setOnClickListener(new CalculatorBtnClickListener());
		cal_sure.setOnClickListener(new CalculatorBtnClickListener());
		cal_equal.setOnClickListener(new CalculatorBtnClickListener());
		calculator.setFocusable(true);
		calculator.setBackgroundDrawable(new ColorDrawable());
		calculator.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				BillInputActivity.this.isClickFlag = false;   //当每次关闭计算器时，让是否点击符号复位
				BillInputActivity.this.isFloat = false;       //当每次关闭计算器时，让是否是浮点数复位
			}
		});
	}

	private void showCalculator() {
		calculator.showAtLocation(popWinParentView, Gravity.BOTTOM, 0, 0);
	}

	private class CalculatorBtnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.cal_add:
				onClickFlag(0);
				break;
			case R.id.cal_minus:
				onClickFlag(1);
				break;
			case R.id.cal_multiply:
				onClickFlag(2);
				break;
			case R.id.cal_divide:
				onClickFlag(3);
				break;
			case R.id.cal_sure:
				if (calculator.isShowing()) {
					calculator.dismiss();
				}
				break;
			case R.id.cal_equal:
				onClickEqual();
				break;
			case R.id.cal_one:
				onClickNum(1);
				break;
			case R.id.cal_two:
				onClickNum(2);
				break;
			case R.id.cal_three:
				onClickNum(3);
				break;
			case R.id.cal_four:
				onClickNum(4);
				break;
			case R.id.cal_five:
				onClickNum(5);
				break;
			case R.id.cal_six:
				onClickNum(6);
				break;
			case R.id.cal_seven:
				onClickNum(7);
				break;
			case R.id.cal_eight:
				onClickNum(8);
				break;
			case R.id.cal_nine:
				onClickNum(9);
				break;
			case R.id.cal_dot:
				onClickDot();
				break;
			case R.id.cal_zero:
				onClickNum(0);
				break;
			case R.id.cal_del:
				onClickDel();
				break;
			}
		}
	}

	private void refreshJinETextView() {
		if (bill.getJine() == null || "".equals(bill.getJine())) {
			jie_txt = "0";
		} else {
			jie_txt = bill.getJine();
		}
		zhichuJine.setText(jie_txt);
		shouruJine.setText(jie_txt);
		zhuanzhangJine.setText(jie_txt);
	}

	// 点击了计算器上的数字键
	private void onClickNum(int numStr) {
		jineTv = (TextView) viewFlipper.getCurrentView()
				.findViewById(R.id.jine);
		String jie_str = jineTv.getText().toString();
		Float jie_float = 0f;
		Long jie_long = 0l;
		if (jie_str.contains(".")) {
			// 当前值是浮点型
			if (jie_str.substring(jie_str.indexOf("."), jie_str.length())
					.length() < 3) {
				// 保存2位小数位
				jie_float = Float.parseFloat(jie_str);
				jie_txt = jie_float + "" + numStr;
			}
		} else {
			// 当前值是整数
			jie_long = Long.parseLong(jie_str);
			if (isFloat) {
				// 点击了小数点
				if (jie_long == 0) {
					jie_txt = "0." + numStr;
				} else {
					jie_txt = jie_str + "." + numStr;
				}
			} else {
				// 未点击小数点
				if (jie_str.length() < 7) {
					// 整数位保留到百万
					if (jie_long == 0) {
						jie_txt = "" + numStr;
					} else {
						jie_txt = jineTv.getText().toString() + numStr;
					}
				}
			}
		}
		jineTv.setText(jie_txt);
		bill.setJine(jie_txt);
	}

	// 点击了计算器上的小数点
	private void onClickDot() {
		isFloat = true;
	}

	// 点击了计算器上的删除
	private void onClickDel() {
		jineTv = (TextView) viewFlipper.getCurrentView()
				.findViewById(R.id.jine);
		String jie_str = jineTv.getText().toString();
		Log.i("a", "jine length = " + jie_str.length());
		if (jie_str.length() > 1) {
			jie_txt = jie_str.substring(0, jie_str.length() - 1); // 去掉最后一个字符
			if (jie_txt.endsWith(".")) {
				// 如果以小数点结尾
				jie_txt = jie_txt.substring(0, jie_txt.length() - 1);
				isFloat = false;
			}
		} else {
			jie_txt = "0";
		}
		jineTv.setText(jie_txt);
		bill.setJine(jie_txt);
	}

	// 点击了计算器上的加减乘除
	private void onClickFlag(int flagId) {
		cal_sure.setVisibility(ViewGroup.GONE);
		cal_equal.setVisibility(ViewGroup.VISIBLE);

		if (!isClickFlag) {
			jineTv = (TextView) viewFlipper.getCurrentView().findViewById(
					R.id.jine);
			temp1Str = jineTv.getText().toString();
			jineTv.setText("0");
			isClickFlag = true; // 点击了加减乘除按钮
		}
		this.flagId = flagId;
		this.isFloat = false;    //点击符号按钮后将是否是浮点数标记为复位，不然再点击数字时会是小数
	}

	// 点击了计算器上的等号
	private void onClickEqual() {
		cal_sure.setVisibility(ViewGroup.VISIBLE);
		cal_equal.setVisibility(ViewGroup.GONE);

		jineTv = (TextView) viewFlipper.getCurrentView()
				.findViewById(R.id.jine);
		String jie_str = jineTv.getText().toString();
		if (jie_str.length() > 0 && !"0".equals(jie_str)) {
			// 输入了数字，并且不是0
			DecimalFormat df = new DecimalFormat("0.00");
			temp2Str = jie_str;
			String resultStr;
			Float temp1Float = 0f;
			Float temp2Float = 0f;
			Float resultFloat = 0f;
			switch (this.flagId) {
			case 0:
				// 加
				temp1Float = Float.parseFloat(temp1Str);
				temp2Float = Float.parseFloat(temp2Str);
				resultFloat = temp1Float + temp2Float;
				resultStr = resultStrDeleteZero(df.format(resultFloat));
				jineTv.setText(resultStr);
				Log.i("a", "temp1Float = " + temp1Float + "   temp2Float = " + temp2Float + "   result = " + resultFloat);
				bill.setJine(resultStr);

				break;
			case 1:
				// 减
				temp1Float = Float.parseFloat(temp1Str);
				temp2Float = Float.parseFloat(temp2Str);
				resultFloat = temp1Float - temp2Float;
				resultStr = resultStrDeleteZero(df.format(resultFloat));
				jineTv.setText(resultStr);
				Log.i("a", "temp1Float = " + temp1Float + "   temp2Float = " + temp2Float + "   result = " + resultFloat);
				bill.setJine(resultStr);
				break;
			case 2:
				// 乘
				temp1Float = Float.parseFloat(temp1Str);
				temp2Float = Float.parseFloat(temp2Str);
				resultFloat = temp1Float * temp2Float;
				resultStr = resultStrDeleteZero(df.format(resultFloat));
				jineTv.setText(resultStr);
				Log.i("a", "temp1Float = " + temp1Float + "   temp2Float = " + temp2Float + "   result = " + resultFloat);
				bill.setJine(resultStr);
				break;
			case 3:
				// 除
				temp1Float = Float.parseFloat(temp1Str);
				temp2Float = Float.parseFloat(temp2Str);
				resultFloat = temp1Float / temp2Float;
				resultStr = resultStrDeleteZero(df.format(resultFloat));
				jineTv.setText(resultStr);
				Log.i("a", "temp1Float = " + temp1Float + "   temp2Float = " + temp2Float + "   result = " + resultFloat);
				bill.setJine(resultStr);
				break;
			}

		} else {
			jineTv.setText(temp1Str);    //如果点击完符号后在没有输入数字或者输入的数字为0，此时按等号时，jineTv的值就是之前缓存的值temp1Str
		}
		isClickFlag = false;
	}
	

	//将最后的金额字符型结果去掉小数位的0，例如12.30， 去掉0后成12.3，再如12.00去零成为12
	private String resultStrDeleteZero(String resultStr) {
		String str = null;
		str = resultStr.substring(resultStr.lastIndexOf(".") + 1, resultStr.length());
		if(str.length() == 1){
			if(str.equals("0")) {
				return resultStr.substring(0, resultStr.length() - 2);
			}
		}else {
			String a = str.substring(1, 2);   //01 中的1
			String b = str.substring(0, 1);	  //01中的0
			if(a.equals("0")) {
				resultStr = resultStr.substring(0, resultStr.length() - 1);
				if(b.equals("0")) {
					return resultStr.substring(0, resultStr.length() - 2);
				}
			}
		}
		return resultStr;
	}
	
//	@Override
//	public boolean onKeyUp(int keyCode, KeyEvent event) {
//		switch (keyCode) {
//		case KeyEvent.KEYCODE_BACK:
//
//			Toast.makeText(getBaseContext(), "back", 0).show();
//			
//			if(calculator.isShowing()) {
//				calculator.dismiss();
//			}
//			break;
//		}
//		return true;
//	}
	
}















