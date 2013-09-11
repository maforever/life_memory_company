package com.example.lifememory.db.service;

import com.example.lifememory.activity.model.BillTemplate;
import com.example.lifememory.db.PregnancyDiaryOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BillTemplateService {
	private SQLiteDatabase db;

	public BillTemplateService(Context context) {
		PregnancyDiaryOpenHelper helper = new PregnancyDiaryOpenHelper(context);
		db = helper.getReadableDatabase();
	}

	/**
	 * idx integer primary key autoincrement, name text,catagoryname text,
	 * accountid integer, member text, canbaoxiao, transferinaccountdid integer,
	 * transferoutaccountid integer, billtype integer
	 * 
	 */
	public void addTemplate(BillTemplate template) {
		db.execSQL(
				"insert into bill_template (name, incatagoryname, outcatagoryname, accountid, member, canbaoxiao, transferinaccountdid, transferoutaccountid, billtype) values (?,?,?,?,?,?,?,?,?)",
				new String[] { template.getName(), template.getInCatagoryName(), template.getOutCatagoryName(),
						String.valueOf(template.getAccountid()),
						template.getMember(),
						String.valueOf(template.isCanBaoXiao()),
						String.valueOf(template.getTransferInAccountId()),
						String.valueOf(template.getTransferOutAccountId()),
						String.valueOf(template.getBillType()) });
	}
}
