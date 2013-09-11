package com.example.lifememory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lifememory.R;

public class BillTemplateNameInputDialogActivity extends Activity {
	private TextView title;
	private EditText content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_bill_template_name_input);
		
		findViews();
	}
	
	private void findViews() {
		title = (TextView) this.findViewById(R.id.title);
		content = (EditText) this.findViewById(R.id.content);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.sure:
			BillTemplateNameInputDialogActivity.this.finish();
			break;
		case R.id.cancel:
			BillTemplateNameInputDialogActivity.this.finish();
			break;
		}
	}
	
}
