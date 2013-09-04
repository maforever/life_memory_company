package com.example.lifememory.activity.model;

public class Bill {
	
	private int idx;
	private String jine;
	private String outCatagory;   //支出类型catagoryname
	private String inCatagory;    //收入类型catagoryname;
	private String account;   //accountname
	private String member; //memebername
	private String date;
	private String dateYMD;   //yyyy-MM-dd
	private String beizhu;
	private boolean isCanBaoXiao;
	private boolean isBaoxiaoed;
	private String TransferIn;     //转入     现金
	private String TransferOut;    //转出   银行卡
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
	public String getOutCatagory() {
		return outCatagory;
	}
	public void setOutCatagory(String outCatagory) {
		this.outCatagory = outCatagory;
	}
	public String getInCatagory() {
		return inCatagory;
	}
	public void setInCatagory(String inCatagory) {
		this.inCatagory = inCatagory;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getTransferIn() {
		return TransferIn;
	}
	public void setTransferIn(String transferIn) {
		TransferIn = transferIn;
	}
	public String getTransferOut() {
		return TransferOut;
	}
	public void setTransferOut(String transferOut) {
		TransferOut = transferOut;
	}
	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}
	public String getDateYMD() {
		return dateYMD;
	}
	public void setDateYMD(String dateYMD) {
		this.dateYMD = dateYMD;
	}
	@Override
	public String toString() {
		return "Bill [idx=" + idx + ", jine=" + jine + ", outCatagory="
				+ outCatagory + ", inCatagory=" + inCatagory + ", account="
				+ account + ", member=" + member + ", date=" + date
				+ ", dateYMD=" + dateYMD + ", beizhu=" + beizhu
				+ ", isCanBaoXiao=" + isCanBaoXiao + ", isBaoxiaoed="
				+ isBaoxiaoed + ", TransferIn=" + TransferIn + ", TransferOut="
				+ TransferOut + ", billType=" + billType + ", getIdx()="
				+ getIdx() + ", getJine()=" + getJine() + ", getOutCatagory()="
				+ getOutCatagory() + ", getInCatagory()=" + getInCatagory()
				+ ", getAccount()=" + getAccount() + ", getMember()="
				+ getMember() + ", getDate()=" + getDate() + ", getBeizhu()="
				+ getBeizhu() + ", isCanBaoXiao()=" + isCanBaoXiao()
				+ ", isBaoxiaoed()=" + isBaoxiaoed() + ", getTransferIn()="
				+ getTransferIn() + ", getTransferOut()=" + getTransferOut()
				+ ", getBillType()=" + getBillType() + ", getDateYMD()="
				+ getDateYMD() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
