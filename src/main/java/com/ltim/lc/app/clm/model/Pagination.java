package com.ltim.lc.app.clm.model;

import java.util.List;
import java.util.Map;

import com.ltim.lc.app.clm.Constant.SearchType;
import com.ltim.lc.app.clm.Constant.SortType;

public class Pagination {
	private Integer pageSize;

	private List<String> search;

	private SearchType searchType;

	private Map<String, SortType> sort;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<String> getSearch() {
		return search;
	}

	public void setSearch(List<String> search) {
		this.search = search;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	public Map<String, SortType> getSort() {
		return sort;
	}

	public void setSort(Map<String, SortType> sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "PaginationBO [pageSize=" + pageSize + ", search=" + search + ", searchType=" + searchType + ", sort="
				+ sort + "]";
	}

}
