package com.asiainfo.mapper;

import com.asiainfo.entity.Bill;

public interface BillMaper {
	Bill getBillById(int id);
	
	int insertBill(Bill bill);
}
