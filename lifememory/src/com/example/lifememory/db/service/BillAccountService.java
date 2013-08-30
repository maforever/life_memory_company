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
	
	
	//根据idx超找账户信息，用于显示所有账户的列表，只需要idx，name数据
	public BillAccountItem findItemById(int idx) {
		BillAccountItem item = new BillAccountItem();
		Cursor cursor = db.rawQuery("select * from bill_account where idx = ? ", new String[]{String.valueOf(idx)});
		cursor.moveToFirst();
		item.setIdx(cursor.getInt(cursor.getColumnIndex("idx")));
		item.setName(cursor.getString(cursor.getColumnIndex("name")));
		item.setImageId(cursor.getInt(cursor.getColumnIndex("imageid")));
		return item;
	}
	
	//根据idx查找账户的详细信息,用于修改前显示数据
	public BillAccountItem findItemDetailById(int idx) {
		BillAccountItem item = new BillAccountItem();
		Cursor cursor = db.rawQuery("select * from bill_account where idx = ? ", new String[]{String.valueOf(idx)});
		cursor.moveToFirst();
		item.setIdx(cursor.getInt(cursor.getColumnIndex("idx")));
		item.setCatagoryname(cursor.getInt(cursor.getColumnIndex("catagoryname")));
		item.setName(cursor.getString(cursor.getColumnIndex("name")));
		item.setBizhong(cursor.getString(cursor.getColumnIndex("bizhong")));
		item.setDangqianyue(cursor.getDouble(cursor.getColumnIndex("dangqianyue")));
		String booleanTemp = cursor.getString(cursor.getColumnIndex("isnotice"));
		if("false".equals(booleanTemp)) {
			item.setNotice(false);
		}else {
			item.setNotice(true);
		}
		item.setNoticeValue(cursor.getDouble(cursor.getColumnIndex("noticevalue")));
		item.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));
		return item;
	}

	
	
	public void addAccount(BillAccountItem item) {
		db.execSQL("insert into bill_account (catagoryname, name, bizhong, dangqianyue, isnotice, noticevalue, imageid, beizhu) values (?, ?, ?, ?, ?, ?, ?, ?)", new String[]{
				String.valueOf(item.getCatagoryname()), item.getName(), item.getBizhong(), String.valueOf(item.getDangqianyue()),
				String.valueOf(item.isNotice()), String.valueOf(item.getNoticeValue()), String.valueOf(item.getImageId()), item.getBeizhu()
				
		});
	}
	
	//根据id删除账户信息
	public void deleteItemById(int idx) {
		db.execSQL("delete from bill_account where idx = ?", new String[]{String.valueOf(idx)});
	}
	
	public void updateItem(BillAccountItem item) {
		db.execSQL("update bill_account set catagoryname = ?, name = ?, bizhong = ?, dangqianyue = ?, isnotice = ?, noticevalue = ?, imageid =?, beizhu = ? where idx = ?", 
				new String[]{
				String.valueOf(item.getCatagoryname()), item.getName(), item.getBizhong(), String.valueOf(item.getDangqianyue()),
				String.valueOf(item.isNotice()), String.valueOf(item.getNoticeValue()), String.valueOf(item.getImageId()), item.getBeizhu(), String.valueOf(item.getIdx())
		});
	}
	
	public void closeDB() {
		db.close();
	}
}

























