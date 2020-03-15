package com.youpassed.domain;

import java.util.Arrays;

public class PagerModel {

	public static final int DEFAULT_BUTTONS_NUM = 10;
	public static final int INITIAL_PAGE_NUM = 1;
	public static final int INITIAL_PAGE_SIZE = 5;
	public static final Integer[] PAGE_SIZES = {5, 10, 20};

	private int buttonsToShow;
	private int startPage;
	private int endPage;

	public PagerModel(int totalPages, int currentPage) {
		buttonsToShow = DEFAULT_BUTTONS_NUM;
		int halfPagesToShow = buttonsToShow / 2;
		if (totalPages <= buttonsToShow) {
			startPage = INITIAL_PAGE_NUM;
			endPage = totalPages;
		} else if (currentPage - halfPagesToShow <= 0) {
			startPage = INITIAL_PAGE_NUM;
			endPage = buttonsToShow;
		} else if (currentPage + halfPagesToShow == totalPages) {
			startPage = currentPage - halfPagesToShow;
			endPage = totalPages;
		} else if (currentPage + halfPagesToShow > totalPages) {
			startPage = (totalPages - buttonsToShow + 1);
			endPage = totalPages;
		} else {
			startPage = currentPage - halfPagesToShow;
			endPage = currentPage + halfPagesToShow;
		}
	}

	public static int parsePageSize(String pageSizeStr) {
		int pageSize;
		try {
			if (pageSizeStr == null) {
				throw new NumberFormatException();
			}
			pageSize = Integer.parseInt(pageSizeStr);
			if (!Arrays.asList(PAGE_SIZES).contains(pageSize)) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			pageSize = INITIAL_PAGE_SIZE;
		}
		return pageSize;
	}

	//		final int firstPage = 1;
	public static int parsePageNumber(String pageNumStr) {
		int pageNum;
		try {
			if (pageNumStr == null) {
				throw new NumberFormatException();
			}
			pageNum = Integer.parseInt(pageNumStr);
			if (pageNum < 1) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			pageNum = INITIAL_PAGE_NUM;
		}
		return pageNum;
	}


	public int getButtonsToShow() {
		return buttonsToShow;
	}

	public void setButtonsToShow(int buttonsToShow) {
		if (buttonsToShow % 2 != 0) {
			this.buttonsToShow = buttonsToShow;
		} else {
			throw new IllegalArgumentException("Must be an odd value!");
		}
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "Pager [startPage=" + startPage + ", endPage=" + endPage + "]";
	}
}

