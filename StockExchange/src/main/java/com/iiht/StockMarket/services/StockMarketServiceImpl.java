package com.iiht.StockMarket.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.StockMarket.dto.StockPriceDetailsDTO;
import com.iiht.StockMarket.dto.StockPriceIndexDTO;
import com.iiht.StockMarket.exception.CompanyNotFoundException;
import com.iiht.StockMarket.exception.InvalidStockException;
import com.iiht.StockMarket.exception.StockNotFoundException;
import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.model.StockPriceDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;
import com.iiht.StockMarket.repository.StockPriceRepository;
import com.iiht.StockMarket.utils.StockMarketUtility;

@Service
@Transactional
public class StockMarketServiceImpl implements StockMarketService {

	@Autowired
    private StockPriceRepository stockRepository;

	@Autowired
    private CompanyInfoRepository companyRepository;
	
	//----------------------------------------------------------------------------
	public StockPriceDetailsDTO saveStockPriceDetails(StockPriceDetailsDTO stockPriceDetailsDTO) {
		Long companyCode=stockPriceDetailsDTO.getCompanyCode();
		Optional<CompanyDetails>  companyDetails =companyRepository.findById(companyCode);
		if(companyDetails.isPresent()) {
			StockPriceDetails stockPriceDetails= stockRepository.save(StockMarketUtility.convertToStockPriceDetails(stockPriceDetailsDTO));
			return StockMarketUtility.convertToStockPriceDetailsDTO(stockPriceDetails);
		}
		else
			throw new InvalidStockException("No company exist with company code: "+companyCode+".You can't add stoc details.");
		
	}
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> deleteStock(Long companyCode) {
		List<StockPriceDetails>  listStockPriceDetails =stockRepository.findStockByCompanyCode(companyCode);
		 if(listStockPriceDetails.size()>0) {
		 stockRepository.deleteStockByCompanyCode(companyCode);
		 return StockMarketUtility.convertToStockPriceDetailsDtoList(listStockPriceDetails);
		 }
		 else
	        	throw new StockNotFoundException("No details exist with comany code: "+companyCode);
		
	}
	//----------------------------------------------------------------------------
	public List<StockPriceDetailsDTO> getStockByCode(Long companyCode){
		List<StockPriceDetails>  listStockPriceDetails =stockRepository.findStockByCompanyCode(companyCode);
		 if(listStockPriceDetails.size()!=0)
		        return StockMarketUtility.convertToStockPriceDetailsDtoList(listStockPriceDetails);
		 else
	        	throw new StockNotFoundException("No details exist with comany code: "+companyCode);
	};
	//----------------------------------------------------------------------------
	public StockPriceDetailsDTO getStockPriceDetailsDTO(StockPriceDetails stockDetails)	{
		return null;
	}	
	//----------------------------------------------------------------------------
	public Double getMaxStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findMaxStockPrice(companyCode, startDate, endDate);
	}
	public Double getAvgStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findAvgStockPrice(companyCode, startDate, endDate);
	}
	public Double getMinStockPrice(Long companyCode, LocalDate startDate, LocalDate endDate) {
		return stockRepository.findMinStockPrice(companyCode, startDate, endDate);
	}
	
	public StockPriceIndexDTO getStockPriceIndex(Long companyCode, LocalDate startDate, LocalDate endDate) {
		StockPriceIndexDTO stockPriceIndexDTO=new StockPriceIndexDTO();
		
		stockPriceIndexDTO.setMaxStockPrice(getMaxStockPrice(companyCode, startDate, endDate));
		stockPriceIndexDTO.setAvgStockPrice(getAvgStockPrice(companyCode, startDate, endDate));
		stockPriceIndexDTO.setMinStockPrice(getMinStockPrice(companyCode, startDate, endDate));
		
		CompanyDetails ComapnyDetails= companyRepository.findCompanyDetailsById(companyCode);
		stockPriceIndexDTO.setCompanyDto(StockMarketUtility.convertToCompanyDetailsDTO(ComapnyDetails));
		
		List<StockPriceDetails> listStockPriceDetails=stockRepository.findStockByCompanyCode(companyCode);
		stockPriceIndexDTO.setStockPriceList(StockMarketUtility.convertToStockPriceDetailsDtoList(listStockPriceDetails));
		return stockPriceIndexDTO;
	}
}