package com.asiainfo.entity;


public class IdenCard {
	private String idenNo;
	private String address;

//	public String getIdenNo() {
//		return idenNo;
//	}

	public void setIdenNo(String idenNo) {
		this.idenNo = idenNo;
	}

//	public String getAddress() {
//		return address;
//	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "IdenCard [idenNo=" + idenNo + ", address=" + address + "]";
	}

	
}
