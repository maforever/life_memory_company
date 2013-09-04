package com.example.lifememory.db.service;

import com.example.lifememory.activity.model.Bill;
import com.example.lifememory.db.PregnancyDiaryOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BillInfoService {
	private SQLiteDatabase db;
	public BillInfoService(Context context) {
		PregnancyDiaryOpenHelper helper = new PregnancyDiaryOpenHelper(context);
		db = helper.getReadableDatabase();
	}
	
	/**bill数据库属性
	 * jine text, incatagory text, outcatagory text , 
	 * account text, date text, member text, 
	 * beizhu text, isCanBaoXiao text, isBaoxiaoed text, 
	 * transferIn text, transferOut text, billType integer
	 */
	
	
	
	//添加支出类型的账单信息
	public void addOutBill(Bill bill) {
		db.execSQL("insert into bill_info (jine, outcatagory, account, date, member, beizhu, isCanBaoXiao, billType) values (?,?,?,?,?,?,?,?)", 
				new String[]{bill.getJine(), bill.getOutCatagory(), bill.getAccount(), bill.getDate(), bill.getMember(),
				bill.getBeizhu(), String.valueOf(bill.isCanBaoXiao()), String.valueOf(bill.getBillType())});
	}
	//添加收入类型的账单信息
	public void addInBill(Bill bill) {
		db.execSQL("insert into bill_info (jine, incatagory, account, date, member, beizhu, billType) values (?, ?, ?, ?, ?, ?, ?)", 
				new String[]{bill.getJine(), bill.getInCatagory(), bill.getAccount(), bill.getDate(), bill.getMember(),
				bill.getBeizhu(), String.valueOf(bill.getBillType())});
	}
	//添加转账类型的账单信息
	public void addTransferBill(Bill bill) {
		db.execSQL("insert into bill_info (jine, date, transferIn, transferOut, billType) values (?, ?, ?, ?, ?)", new String[]{
				bill.getJine(), bill.getDate(), bill.getTransferIn(), bill.getTransferOut(), String.valueOf(bill.getBillType())
		});
	}
	//删除
	//根据账户id查找引用了该账户id的账单数
	public boolean isRelatedWithAccount(int accountId) {
		Cursor cursor = db.rawQuery("select count(*) from bill_info where accountid = ?", new String[]{String.valueOf(accountId)});
		cursor.moveToFirst();
		Long num = cursor.getLong(0);
		if(num > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public void closeDB() {
		db.close();
	}
	
}








