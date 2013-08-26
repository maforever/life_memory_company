package com.example.lifememory.db;

import com.example.lifememory.utils.BillCatagoryCreator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PregnancyDiaryOpenHelper extends SQLiteOpenHelper {

	public PregnancyDiaryOpenHelper(Context context) {
		super(context, "pregnancy_diary.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/**
		 * diary_jishiben 孕期记事本数据库
		 * diary_luyin    孕期录音数据库
		 */
		db.execSQL("create table if not exists diary_jishiben(idx integer primary key autoincrement, " +
				"title text, content text, textcolorindex integer, textsizeindex integer, createdate text, updatedate text, createymd text, updateymd text, createym text, updateym text, ismodyfied integer, imageid int" +
				")");
		
		db.execSQL("create table if not exists diary_luyin(" +
				"idx integer primary key autoincrement, title text, audiopath text, createdate text, createymd, createym" +
				")");
		/**
		 * baby_jishiben   宝宝记事本数据库
		 * baby_luyin	          宝宝录音数据库
		 */
		db.execSQL("create table if not exists baby_jishiben(idx integer primary key autoincrement, " +
				"title text, content text, textcolorindex integer, textsizeindex integer, createdate text, updatedate text, createymd text, updateymd text, createym text, updateym text, ismodyfied integer, imageid int" +
				")");
		
		db.execSQL("create table if not exists baby_luyin(" +
				"idx integer primary key autoincrement, title text, audiopath text, createdate text, createymd, createym" +
				")");
		
		db.execSQL("create table if not exists bill_catagory(idx integer primary key autoincrement, name text, imageid integer parentid integer)");
		
		new BillCatagoryCreator().initFirstLevel(db);
		
		
		
		
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
