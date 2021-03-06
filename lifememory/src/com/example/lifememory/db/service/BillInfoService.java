package com.example.lifememory.db.service;

import java.util.ArrayList;
import java.util.List;

import com.example.lifememory.activity.model.Bill;
import com.example.lifememory.activity.model.BillAccountItem;
import com.example.lifememory.db.PregnancyDiaryOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BillInfoService {
	private SQLiteDatabase db;
	private BillAccountService accountService;
	public BillInfoService(Context context) {
		PregnancyDiaryOpenHelper helper = new PregnancyDiaryOpenHelper(context);
		accountService = new BillAccountService(context);
		db = helper.getReadableDatabase();
	}
	
	/**bill数据库属性
	 *idx integer primary key autoincrement, jine text, incatagory text,
	 * outcatagory text , outcatagorychildid integer, outcatagoryparentid integer,
	 *  account text, accountid integer, date text, dateymd text,
	 *   member text, beizhu text, isCanBaoXiao text, isBaoxiaoed text,
	 *    transferIn text, transferInAccountId integer, transferOut text, 
	 *    transferOutAccountId, billType integer
	 */
	
	
	
	//添加支出类型的账单信息
	public void addOutBill(Bill bill) {
//		Log.i("a", bill.toString());
		db.execSQL("insert into bill_info (jine, outcatagory, outcatagorychildid, outcatagoryparentid, account, accountid, date, dateymd, member, beizhu, isCanBaoXiao, billType) values (?,?,?,?,?,?,?,?,?,?,?, ?)", 
				new String[]{bill.getJine(), bill.getOutCatagory(),String.valueOf(bill.getOutCatagoryChildId()), String.valueOf(bill.getOutCatagoryParentId()), bill.getAccount(), String.valueOf(bill.getAccountid()), bill.getDate(),bill.getDateYMD(), bill.getMember(),
				bill.getBeizhu(), String.valueOf(bill.isCanBaoXiao()), String.valueOf(bill.getBillType())});
//		double accountYue = 0;
//		Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountid())});
//		cursor.moveToFirst();
//		accountYue = cursor.getDouble(0);
//		double currentAccountYue = accountYue - Float.parseFloat(bill.getJine());
//		db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(currentAccountYue), String.valueOf(bill.getAccountid())});
		accountService.updateOutAccount(bill);
		
	}
	//添加收入类型的账单信息
	public void addInBill(Bill bill) {
//		Log.i("a", bill.toString());
		db.execSQL("insert into bill_info (jine, incatagory, account, accountid, date,dateymd, member, beizhu, billType) values (?, ?,?, ?, ?, ?, ?, ?, ?)", 
				new String[]{bill.getJine(), bill.getInCatagory(), bill.getAccount(), String.valueOf(bill.getAccountid()), bill.getDate(),bill.getDateYMD(), bill.getMember(),
				bill.getBeizhu(), String.valueOf(bill.getBillType())});
//		double accountYue = 0;
//		Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountid())});
//		cursor.moveToFirst();
//		accountYue = cursor.getDouble(0);
//		double currentAccountYue = accountYue + Float.parseFloat(bill.getJine());
//		db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(currentAccountYue), String.valueOf(bill.getAccountid())});
		accountService.updateInAccount(bill);
	}
	//添加转账类型的账单信息
	public void addTransferBill(Bill bill) {
		db.execSQL("insert into bill_info (jine, date, dateymd, transferIn, transferInAccountId, transferOut, transferOutAccountId, billType) values (?, ?, ?, ?, ?, ?, ?, ?)", new String[]{
				bill.getJine(), bill.getDate(),bill.getDateYMD(), bill.getTransferIn(), String.valueOf(bill.getTransferInAccountId()), bill.getTransferOut(), String.valueOf(bill.getTransferOutAccountId()), String.valueOf(bill.getBillType())
		});
//		double inAccountYue = 0;
//		double outAccountYue = 0;
//		Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getTransferInAccountId())});
//		cursor.moveToFirst();
//		inAccountYue = cursor.getDouble(0);
//		cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getTransferOutAccountId())});
//		cursor.moveToFirst();
//		outAccountYue = cursor.getDouble(0);
//		double IncurrentAccountYue = inAccountYue + Float.parseFloat(bill.getJine());
//		double outCurrentAccountYue = outAccountYue - Float.parseFloat(bill.getJine());
//		db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(IncurrentAccountYue), String.valueOf(bill.getTransferInAccountId())});
//		db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(outCurrentAccountYue), String.valueOf(bill.getTransferOutAccountId())});
		accountService.updateTransferAccount(bill);
	}

	
	
	//修改支出类型的账单信息
	public void updateOutBill(Bill bill) {
		
		if(bill.getLastBillType() == 1) {
			//支出，不是变换类型
			//先将之前的idx指示的账户金额恢复
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountLastIdx())});
//			cursor.moveToFirst();
//			double lastValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(lastValue), String.valueOf(bill.getAccountLastIdx())});
			accountService.updateLastInAccount(bill);
//			//修改现在实际的账户金额
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountid())});
//			cursor.moveToFirst();
//			double currentValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(currentValue), String.valueOf(bill.getAccountid())});
			accountService.updateOutAccount(bill);
		}else if(bill.getLastBillType() == 2) {
			//之前是收入
			//先将之前的idx指示的账户金额恢复
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountLastIdx())});
//			cursor.moveToFirst();
//			double lastValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(lastValue), String.valueOf(bill.getAccountLastIdx())});
			accountService.updateLastOutAccount(bill);
			//修改现在实际的账户金额
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountid())});
//			cursor.moveToFirst();
//			double currentValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(currentValue), String.valueOf(bill.getAccountid())});
			accountService.updateOutAccount(bill);
		}else if(bill.getLastBillType() == 3) {
			//之前是转账
			//先恢复之前的idx所关联的转入转出账户金额
			//之前的转入账户
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getLastTransferInAccountId())});
//			cursor.moveToFirst();
//			double inLastValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(inLastValue), String.valueOf(bill.getLastTransferInAccountId())});
			accountService.updateLastTransferOutAccount(bill);
			//之前的转出账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getLastTransferOutAccountId())});
//			cursor.moveToFirst();
//			double outLastValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(outLastValue) ,String.valueOf(bill.getLastTransferOutAccountId())});
			accountService.updateLastTransferInAccount(bill);
			//修改当前关联的账户的金额
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountid())});
//			cursor.moveToFirst();
//			double value = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(value), String.valueOf(bill.getAccountid())});
			accountService.updateOutAccount(bill);
		}
		
		db.execSQL("update bill_info set jine = ?, outcatagory = ?,outcatagorychildid = ?,  outcatagoryparentid = ?, account = ?, accountid = ?,  date = ?, dateymd = ?, member = ?, beizhu = ?, isCanBaoXiao = ?, billType = ? where idx = ?", 
				new String[]{bill.getJine(), bill.getOutCatagory(), String.valueOf(bill.getOutCatagoryChildId()), String.valueOf(bill.getOutCatagoryParentId()), bill.getAccount(), String.valueOf(bill.getAccountid()), bill.getDate(),bill.getDateYMD(), bill.getMember(),
				bill.getBeizhu(), String.valueOf(bill.isCanBaoXiao()), String.valueOf(bill.getBillType()), String.valueOf(bill.getIdx())});
		
		
		
	}
	//修改收入类型的账单信息
	public void updateInBill(Bill bill) {
		
		if(bill.getLastBillType() == 1) {
			//之前是支出
			//先将之前的idx指示的账户金额恢复
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountLastIdx())});
//			cursor.moveToFirst();
//			double lastValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(lastValue), String.valueOf(bill.getAccountLastIdx())});
			accountService.updateLastInAccount(bill);
			//修改现在实际的账户金额
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountid())});
//			cursor.moveToFirst();
//			double currentValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(currentValue), String.valueOf(bill.getAccountid())});
			accountService.updateInAccount(bill);
		}else if(bill.getLastBillType() == 2){
			//之前是收入
			
			//先将之前的idx指示的账户金额恢复
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountLastIdx())});
//			cursor.moveToFirst();
//			double lastValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(lastValue), String.valueOf(bill.getAccountLastIdx())});
			accountService.updateLastOutAccount(bill);
			//修改现在实际的账户金额
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountid())});
//			cursor.moveToFirst();
//			double currentValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(currentValue), String.valueOf(bill.getAccountid())});
			accountService.updateInAccount(bill);
		}else if(bill.getLastBillType() == 3) {
			//之前是转账
			//先恢复之前的idx所关联的转入转出账户金额
			//之前的转入账户
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getLastTransferInAccountId())});
//			cursor.moveToFirst();
//			double inLastValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(inLastValue), String.valueOf(bill.getLastTransferInAccountId())});
			accountService.updateLastTransferOutAccount(bill);
			//之前的转出账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getLastTransferOutAccountId())});
//			cursor.moveToFirst();
//			double outLastValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(outLastValue) ,String.valueOf(bill.getLastTransferOutAccountId())});
			accountService.updateLastTransferInAccount(bill);
			//修改当前关联的账户的金额
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountid())});
//			cursor.moveToFirst();
//			double value = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(value), String.valueOf(bill.getAccountid())});
			accountService.updateInAccount(bill);
		}
		
		db.execSQL("update  bill_info set jine = ?, incatagory = ?, account = ?, accountid = ?, date = ?,dateymd = ?, member = ?, beizhu = ?, billType = ? where idx = ?", 
				new String[]{bill.getJine(), bill.getInCatagory(), bill.getAccount(), String.valueOf(bill.getAccountid()), bill.getDate(),bill.getDateYMD(), bill.getMember(),
				bill.getBeizhu(), String.valueOf(bill.getBillType()), String.valueOf(bill.getIdx())});
	}
	//修改转账类型的账单信息
	public void updateTransferBill(Bill bill) {
		
		if(bill.getLastBillType() == 1) {
			//之前是支出
			//恢复之前的支出账户金额
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountLastIdx())});
//			cursor.moveToFirst();
//			double lastValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(lastValue), String.valueOf(bill.getAccountLastIdx())});
		
			accountService.updateLastInAccount(bill);
			
			//再将当前选择的转入转出账户金额修改
			//转入账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getTransferInAccountId())});
//			cursor.moveToFirst();
//			double inValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(inValue) ,String.valueOf(bill.getTransferInAccountId())});
			//转出账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getTransferOutAccountId())});
//			cursor.moveToFirst();
//			double outValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(outValue) ,String.valueOf(bill.getTransferOutAccountId())});
			accountService.updateTransferAccount(bill);
			
		}else if(bill.getLastBillType() == 2) {
			//之前是收入
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getAccountLastIdx())});
//			cursor.moveToFirst();
//			double lastValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(lastValue), String.valueOf(bill.getAccountLastIdx())});
			accountService.updateLastOutAccount(bill);

			
			//再将当前选择的转入转出账户金额修改
			//转入账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getTransferInAccountId())});
//			cursor.moveToFirst();
//			double inValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(inValue) ,String.valueOf(bill.getTransferInAccountId())});
//			//转出账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getTransferOutAccountId())});
//			cursor.moveToFirst();
//			double outValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(outValue) ,String.valueOf(bill.getTransferOutAccountId())});
			accountService.updateTransferAccount(bill);
		}else if(bill.getLastBillType() == 3) {
			//之前是转账
			//先恢复之前的idx所关联的转入转出账户金额
			//之前的转入账户
//			Cursor cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getLastTransferInAccountId())});
//			cursor.moveToFirst();
//			double inLastValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(inLastValue), String.valueOf(bill.getLastTransferInAccountId())});
			accountService.updateLastTransferOutAccount(bill);
//			//之前的转出账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getLastTransferOutAccountId())});
//			cursor.moveToFirst();
//			double outLastValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getLastJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(outLastValue) ,String.valueOf(bill.getLastTransferOutAccountId())});
			accountService.updateLastTransferInAccount(bill);
			//再将当前选择的转入转出账户金额修改
			//转入账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getTransferInAccountId())});
//			cursor.moveToFirst();
//			double inValue = cursor.getDouble(0) + Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(inValue) ,String.valueOf(bill.getTransferInAccountId())});
//			//转出账户
//			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(bill.getTransferOutAccountId())});
//			cursor.moveToFirst();
//			double outValue = cursor.getDouble(0) - Math.abs(Double.parseDouble(bill.getJine()));
//			db.execSQL("update bill_account set dangqianyue  = ? where idx = ?", new String[]{String.valueOf(outValue) ,String.valueOf(bill.getTransferOutAccountId())});
			accountService.updateTransferAccount(bill);
		}
		
		
		db.execSQL("update bill_info set jine = ?, date = ?, dateymd = ?, transferIn = ?, transferInAccountId = ?, transferOut = ?, transferOutAccountId = ?, billType = ? where idx = ?", new String[]{
				bill.getJine(), bill.getDate(),bill.getDateYMD(), bill.getTransferIn(), String.valueOf(bill.getTransferInAccountId()), bill.getTransferOut(), String.valueOf(bill.getTransferOutAccountId()), String.valueOf(bill.getBillType()), String.valueOf(bill.getIdx())
		});
		
		
	}
	
	
	
	//删除
	//根据账户id查找引用了该账户id的账单数
	public boolean isRelatedWithAccount(int accountId) {
		Cursor cursor = db.rawQuery("select count(*) from bill_info where accountid = ? or transferInAccountId = ? or transferOutAccountId = ?", new String[]{String.valueOf(accountId), String.valueOf(accountId), String.valueOf(accountId)} );
		cursor.moveToFirst();
		Long num = cursor.getLong(0);
		if(num > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	

	
	//根据日期查找账单信息  yyyy-MM-d   用于记账首页的本日记账的统计
	public List<Bill> findBillByYMD(String ymd) {
		List<Bill> bills = new ArrayList<Bill>();
		Bill bill = null;
		String dateParam = "%" + ymd + "%";
//		Log.i("a", "dateParam = " + dateParam);
		Cursor cursor = db.rawQuery("select * from bill_info where billType != 3 and  date like ?", new String[]{dateParam});
		while(cursor.moveToNext()) {
			bill = new Bill();
			int billType = cursor.getInt(cursor.getColumnIndex("billType"));
			bill.setBillType(billType);
			bill.setJine(cursor.getString(cursor.getColumnIndex("jine")));
			bills.add(bill);
		}
		return bills;
	}
	
	
	//根据日期查找账单信息  yyyy-MM-d   用于记账本月流水信息
	public List<Bill> findBillByYMDInDetails(String ymd) {
		List<Bill> bills = new ArrayList<Bill>();
		Bill bill = null;
		String dateParam = "%" + ymd + "%";
//		Log.i("a", "dateParam = " + dateParam);
		Cursor cursor = db.rawQuery("select * from bill_info where date like ? order by date desc", new String[]{dateParam});
		while(cursor.moveToNext()) {
			bill = new Bill();
			bill.setIdx(cursor.getInt(cursor.getColumnIndex("idx")));
			int billType = cursor.getInt(cursor.getColumnIndex("billType"));
			bill.setBillType(billType);
			if(billType == 1) {
				//支出
				bill.setOutCatagory(cursor.getString(cursor.getColumnIndex("outcatagory")));
			}else if(billType == 2) {
				//收入
				bill.setInCatagory(cursor.getString(cursor.getColumnIndex("incatagory")));
			}
			bill.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			bill.setJine(cursor.getString(cursor.getColumnIndex("jine")));
			bill.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));
			bill.setTransferIn(cursor.getString(cursor.getColumnIndex("transferIn")));
			bill.setTransferOut(cursor.getString(cursor.getColumnIndex("transferOut")));
			bill.setAccountid(cursor.getInt(cursor.getColumnIndex("accountid")));
			bill.setTransferInAccountId(cursor.getInt(cursor.getColumnIndex("transferInAccountId")));
			bill.setTransferOutAccountId(cursor.getInt(cursor.getColumnIndex("transferOutAccountId")));
			bill.setOutCatagoryChildId(cursor.getInt(cursor.getColumnIndex("outcatagorychildid")));
			bill.setOutCatagoryParentId(cursor.getInt(cursor.getColumnIndex("outcatagoryparentid")));
			bills.add(bill);
		}
		return bills;
	}
	
	//查找当月的所有收入账单信息 billType = 2
	public List<Bill> findIncomeByYM(String ym) {
		List<Bill> bills = new ArrayList<Bill>();
		Bill bill = null;
		String dateParam = "%" + ym + "%";
		Cursor cursor = db.rawQuery("select * from bill_info where billType = 2 and  date like ?", new String[]{dateParam});
		while(cursor.moveToNext()) {
			bill = new Bill();
			bill.setJine(cursor.getString(cursor.getColumnIndex("jine")));
			bills.add(bill);
		}
		return bills;
	}
	
	//查找当月的所有支出账单信息 billType = 1
	public List<Bill> findSpendByYM(String ym) {
		List<Bill> bills = new ArrayList<Bill>();
		Bill bill = null;
		String dateParam = "%" + ym + "%";
//		Log.i("a", "spend param = " + dateParam);
		Cursor cursor = db.rawQuery("select * from bill_info where billType = 1 and  date like ?", new String[]{dateParam});
		while(cursor.moveToNext()) {
			bill = new Bill();
			bill.setJine(cursor.getString(cursor.getColumnIndex("jine")));
			bills.add(bill);
		}
		return bills;
	}
	
	//根据月份查找当前月是否有账单信息
	public boolean isCurrentHaveBills(String ym) {
		String dateParam = "%" + ym + "%";
		Cursor cursor = db.rawQuery("select count(*) from bill_info where dateymd like ?", new String[]{dateParam});
		cursor.moveToFirst();
		Long count = cursor.getLong(0);
		return count > 0 ? true : false;
	}
	
	//根据月份查找当前月所有记过帐的日期  yyyy-MM-dd
	public List<String> findAllYMDInMonth(String ym) {
		List<String> ymds = new ArrayList<String>();
		String dateParam = "%" + ym + "%";
		Cursor cursor = db.rawQuery("select dateymd from bill_info where dateymd like ? group by dateymd", new String[]{dateParam});
		while(cursor.moveToNext()) {
			ymds.add(cursor.getString(0));
		}
		return ymds;
	}
	
	//根据idx查找记账信息
	public Bill findBillByIdx(int idx) {
		Bill bill = new Bill();
		Cursor cursor = db.rawQuery("select * from bill_info where idx = ?", new String[]{String.valueOf(idx)});
		if(cursor.moveToFirst()) {
			bill.setIdx(idx);
			bill.setJine(cursor.getString(cursor.getColumnIndex("jine")));
			bill.setInCatagory(cursor.getString(cursor.getColumnIndex("incatagory")));
			bill.setOutCatagory(cursor.getString(cursor.getColumnIndex("outcatagory")));
			bill.setOutCatagoryChildId(cursor.getInt(cursor.getColumnIndex("outcatagorychildid")));
			bill.setOutCatagoryParentId(cursor.getInt(cursor.getColumnIndex("outcatagoryparentid")));
			bill.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			bill.setDate(cursor.getString(cursor.getColumnIndex("date")));
			bill.setDateYMD(cursor.getString(cursor.getColumnIndex("dateymd")));
			bill.setMember(cursor.getString(cursor.getColumnIndex("member")));
			bill.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));
			String canBaoxiaoStr = cursor.getString(cursor.getColumnIndex("isCanBaoXiao"));
			if("false".equals(canBaoxiaoStr)) {
				bill.setCanBaoXiao(false);
			}else if("true".equals(canBaoxiaoStr)) {
				bill.setCanBaoXiao(true);
			}
			bill.setTransferIn(cursor.getString(cursor.getColumnIndex("transferIn")));
			bill.setTransferOut(cursor.getString(cursor.getColumnIndex("transferOut")));
			bill.setBillType(cursor.getInt(cursor.getColumnIndex("billType")));
			bill.setAccountid(cursor.getInt(cursor.getColumnIndex("accountid")));
			bill.setTransferInAccountId(cursor.getInt(cursor.getColumnIndex("transferInAccountId")));
			bill.setTransferOutAccountId(cursor.getInt(cursor.getColumnIndex("transferOutAccountId")));
			bill.setLastJine(bill.getJine());
			bill.setAccountLastIdx(bill.getAccountid());
			bill.setLastTransferInAccountId(bill.getTransferInAccountId());
			bill.setLastTransferOutAccountId(bill.getTransferOutAccountId());
			bill.setLastBillType(bill.getBillType());
		}
		return bill;
	}
	
	//根据IDX删除信息
	public void deleteBillByIdx(int idx) {
		//查找idx的账单信息
		Bill bill = null;
		bill = findBillByIdx(idx);
		int billType = bill.getBillType();
		int accountId = bill.getAccountid();       //当前账单所关联的账户信息
		Cursor cursor;
		double accountValue;
		double value = Double.parseDouble(bill.getJine());
		switch (billType) {
		case 1:
			//支出
			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(accountId)});
			cursor.moveToFirst();
			accountValue = cursor.getDouble(0);
			accountValue = accountValue + value;
			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(accountValue), String.valueOf(accountId)});
			break;
		case 2:
			//收入
			 cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(accountId)});
			cursor.moveToFirst();
			accountValue = cursor.getDouble(0);
			accountValue = accountValue - value;
			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(accountValue), String.valueOf(accountId)});
			break;
		case 3:
			//转账
			int inAccountId = bill.getTransferInAccountId();           //转入账户idx
			int outAccountId = bill.getTransferOutAccountId();		   //转出账户idx
			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(inAccountId)});
			cursor.moveToFirst();
			double inAccountValue = cursor.getDouble(0);
			inAccountValue = inAccountValue - value;
			cursor = db.rawQuery("select dangqianyue from bill_account where idx = ?", new String[]{String.valueOf(outAccountId)});
			cursor.moveToFirst();
			double outAccountValue = cursor.getDouble(0);
			outAccountValue = outAccountValue + value;
			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(inAccountValue), String.valueOf(inAccountId)});
			db.execSQL("update bill_account set dangqianyue = ? where idx = ?", new String[]{String.valueOf(outAccountValue), String.valueOf(outAccountId)});
			
			break;
		}
		db.execSQL("delete from bill_info where idx = ?", new String[]{String.valueOf(idx)});
	}
	
	//统计所有billType = 1 支出账单的花费金额
	public String findTotalSpendValue() {
		Cursor cursor = db.rawQuery("select jine from bill_info where billType = 1", null);
		double totalSpendValue = 0;
		while(cursor.moveToNext()) {
			double currentSpendValue = cursor.getDouble(0);
			totalSpendValue += currentSpendValue;
		}
		return String.valueOf(totalSpendValue);
	}
	
	//查找本月是否有收入账单
	public boolean isHaveIncomeDatas(String ym, int billType) {
		String dateParam = "%" + ym + "%";
		Cursor cursor = db.rawQuery("select count(*) from bill_info where dateymd like ? and billType = ?", new String[]{dateParam, String.valueOf(billType)});
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		return count > 0 ? true : false;
	}
	//查找本月是否有支出账单
	public boolean isHaveSpendDatas(String ym) {
		String dateParam = "%" + ym + "%";
		Cursor cursor = db.rawQuery("select count(*) from bill_info where dateymd like ? and billType = 1", new String[]{dateParam});
		cursor.moveToFirst();
		long count = cursor.getLong(0);
		return count > 0 ? true : false;
	}
	
	public void closeDB() {
		accountService.closeDB();
		db.close();
	}
	
}


	





