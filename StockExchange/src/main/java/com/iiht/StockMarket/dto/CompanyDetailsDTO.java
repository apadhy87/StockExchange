package com.iiht.StockMarket.dto;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CompanyDetailsDTO {

	private Long companyCode;

	@NotEmpty(message = "Please enter stock exchange.")
	@Size(min = 3, max = 30,message = "Stock exchange should be minimun 3 charecters and maximum 100 characters")
	private String stockExchange;

	@NotEmpty(message = "Please enter company name.")
	@Size(min = 3, max = 30,message = "Company name should be minimun 3 charecters and maximum 100 characters")
	private String companyName;

	@NotEmpty(message = "Please enter company CEO.")
	@Size(min = 5, max = 30,message = "Company CEO name should be minimun 5 charecters and maximum 100 characters.")
	private String companyCEO;
	
	@NotNull(message = "Please enter company turnover")
	@Digits(integer = 10, fraction = 2, message = "Turnover must have precision 10 and factional part of 2 decimals.")
	private Double turnover;

	@NotEmpty(message = "Please enter company board of directors.")
	@Size(min = 5, max = 30,message = "Company board of directors should be minimun 5 charecters and maximum 200 characters.")
	private String boardOfDirectors;

	@NotEmpty(message = "Please enter company profile.")
	@Size(min = 5, max = 30,message = "Company profile should be minimun 5 charecters and maximum 255 characters.")
	private String companyProfile;
	
	//---------------------------------------------------------------------------------------------------------------------------------
	public CompanyDetailsDTO() {
		super();
	}
	public CompanyDetailsDTO(Long companyCode, String stockExchange, String companyName, String companyCEO, Double turnover, String boardOfDirectors, String companyProfile) {
		super();
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.turnover = turnover;
		this.boardOfDirectors = boardOfDirectors;
		this.companyProfile = companyProfile;
	}

	//---------------------------------------------------------------------------------------------------------------------------------
	public Long getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public Double getTurnover() {
		return turnover;
	}
	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}
	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}
	//---------------------------------------------------------------------------------------------------------------------------------
	public String getCompanyProfile() {
		return companyProfile;
	}
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}
}