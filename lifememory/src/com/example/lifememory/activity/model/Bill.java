package com.example.lifememory.activity.model;

public class Bill {
	
	private int idx;
	private String jine;
	private String leixing;   //catagoryname
	private String zhanghu;   //accountname
	private String chengyuan; //memebername
	private String member;    //成员
	/*
	 * 以下属性与数据库关联
	 */
	private int catagoryid;
	private int accountid;
	private int memberid;
	private String riqi;
	private String beizhu;
	private boolean isCanBaoXiao;
	private boolean isBaoxiaoed;
	private int billType;    //1 支出 2 收入 3 转账
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getJine() {
		return jine;
	}
	public void setJine(String jine) {
		this.jine = jine;
	}
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	public String getZhanghu() {
		return zhanghu;
	}
	public void setZhanghu(String zhanghu) {
		this.zhanghu = zhanghu;
	}
	public String getRiqi() {
		return riqi;
	}
	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}
	public String getChengyuan() {
		return chengyuan;
	}
	public void setChengyuan(String chengyuan) {
		this.chengyuan = chengyuan;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public boolean isCanBaoXiao() {
		return isCanBaoXiao;
	}
	public void setCanBaoXiao(boolean isCanBaoXiao) {
		this.isCanBaoXiao = isCanBaoXiao;
	}
	public boolean isBaoxiaoed() {
		return isBaoxiaoed;
	}
	public void setBaoxiaoed(boolean isBaoxiaoed) {
		this.isBaoxiaoed = isBaoxiaoed;
	}
	public int getCatagoryid() {
		return catagoryid;
	}
	public void setCatagoryid(int catagoryid) {
		this.catagoryid = catagoryid;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	
	
	
	
	
}
