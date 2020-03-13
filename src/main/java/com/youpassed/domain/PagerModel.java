package com.youpassed.domain;

public class PagerModel {

	public static final int BUTTONS_TO_SHOW = 3;
	public static final int INITIAL_PAGE = 0;
	public static final int INITIAL_PAGE_SIZE = 5;
	public static final int[] PAGE_SIZES = {5, 10};

	private int buttonsToShow = 5;
	private int startPage;
	private int endPage;

	public PagerModel(int totalPages, int currentPage, int buttonsToShow) {
		setButtonsToShow(buttonsToShow);
		int halfPagesToShow = getButtonsToShow() / 2;
		if (totalPages <= getButtonsToShow()) {
			setStartPage(1);
			setEndPage(totalPages);
		} else if (currentPage - halfPagesToShow <= 0) {
			setStartPage(1);
			setEndPage(getButtonsToShow());
		} else if (currentPage + halfPagesToShow == totalPages) {
			setStartPage(currentPage - halfPagesToShow);
			setEndPage(totalPages);
		} else if (currentPage + halfPagesToShow > totalPages) {
			setStartPage(totalPages - getButtonsToShow() + 1);
			setEndPage(totalPages);
		} else {
			setStartPage(currentPage - halfPagesToShow);
			setEndPage(currentPage + halfPagesToShow);
		}
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

