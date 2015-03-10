package com.team3.mbts.util;

import java.util.List;

/**
 * 分页辅助实体类
 * 
 * @author JohnFNash
 */
public class PageBean {
	private int curPage = 1; 	// 当前页码
	private int pageSize = 10;  // 页大小
	private int totalRecords; 	// 总的记录条数
	private int totalPage = 1; 	// 总的页数	
	private List<?> dataList;	//查询结果集

	/**
	 * 构造分页查询辅助类
	 * @param pageSizeStr 表示页大小字符串
	 * @param curPageStr 表示当前页的字符串
	 */
	public PageBean(int totalRecords, String pageSizeStr, String curPageStr) {	
		this.totalRecords = totalRecords;
		//设置页大小
		setPageSize(pageSizeStr, totalRecords);			
		//设置当前页
		setCurPage(curPageStr);
	}
	
	/**
	 * 设置当前页
	 * @param curPageStr 传入的当前页参数
	 */
	private void setCurPage(String curPageStr) {
		try {
			int curPage = Integer.parseInt(curPageStr);
			if(curPage >= 1 && curPage <= totalPage) {
				this.curPage = curPage;
			} else if(curPage > totalPage) {//如果当前页大小大于最大页数，则设置为最大页数
				this.curPage = totalPage;
			}
		} catch(NumberFormatException e) {	
			System.out.println("ex: " + curPageStr);
			this.curPage = 1;
		}		
	}

	/**
	 * 设置页的大小
	 * @param pageSizeStr 传入的页大小字符串
	 * @param assetCount 总的资产记录数目
	 */
	private void setPageSize(String pageSizeStr, int assetCount) {
		try {
			int pageSize = Integer.parseInt(pageSizeStr);
			if(pageSize >= 1) {
				this.pageSize = pageSize;			
				this.totalPage = (int)Math.ceil(assetCount*1.0/pageSize);
			}else if(pageSize <= 0 ) {//如果页大小小于或等于0，则设置为默认大小
				this.totalPage = (int)Math.ceil(assetCount*1.0/this.pageSize);
			}
		} catch(NumberFormatException e) {
			this.totalPage = (int)Math.ceil(assetCount*1.0/this.pageSize );
		}	
	}
	
	public int getCurPage() {
		return curPage;
	}	
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

}
