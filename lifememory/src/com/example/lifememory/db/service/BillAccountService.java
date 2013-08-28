package com.example.lifememory.db.service;

import java.util.ArrayList;
import java.util.List;

import com.example.lifememory.activity.model.BillAccountItem;
import com.example.lifememory.db.PregnancyDiaryOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BillAccountService {
	private SQLiteDatabase db = null;
	public BillAccountService(Context context) {
		PregnancyDiaryOpenHelper helper = new PregnancyDiaryOpenHelper(context);
		db = helper.getReadableDatabase();
	}
	
	//超找所有账户信息
	public List<BillAccountItem> getAllAccounts() {
		List<BillAccountItem> items = new ArrayList<BillAccountItem>();
		BillAccountItem item = null;
		Cursor cursor = db.rawQuery("select * from bill_account", null);
		while(cursor.moveToNext()) {
			item = new BillAccountItem();
			item.setIdx(cursor.getInt(cursor.getColumnIndex("idx")));
			item.setCatagoryname(cursor.getInt(cursor.getColumnIndex("catagoryname")));
			item.setName(cursor.getString(cursor.getColumnIndex("name")));
			item.setImageId(cursor.getInt(cursor.getColumnIndex("imageid")));
			
			items.add(item);
		}
		return items;
	}
	
	
	//根据idx超找账户信息
	public BillAccountItem findItemById(int idx) {
		BillAccountItem item = new BillAccountItem();
		Cursor cursor = db.rawQuery("select * from bill_account where idx = ? ", new String[]{String.valueOf(idx)});
		cursor.moveToFirst();
		item.setIdx(cursor.getInt(cursor.getColumnIndex("idx")));
		item.setName(cursor.getString(cursor.getColumnIndex("name")));
		item.setImageId(cursor.getInt(cursor.getColumnIndex("imageid")));
		return item;
	}
	
	public void addAccount(BillAccountItem item) {
//		db.execSQL("insert into bill_account (?, ?) values ()", new String[]{});
	}
	
	public void closeDB() {
		db.close();
	}
}

























