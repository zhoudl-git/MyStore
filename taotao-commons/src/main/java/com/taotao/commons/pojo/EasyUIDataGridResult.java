package com.taotao.commons.pojo;

import java.util.List;

/**
 * @author zhoudl
 * 映射数据到EasyUI进行展示
 *
 */
public class EasyUIDataGridResult {

	private long total;
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
