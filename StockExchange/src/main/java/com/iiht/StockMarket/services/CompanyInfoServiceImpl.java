package com.iiht.StockMarket.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.StockMarket.dto.CompanyDetailsDTO;
import com.iiht.StockMarket.exception.CompanyNotFoundException;
import com.iiht.StockMarket.model.CompanyDetails;
import com.iiht.StockMarket.repository.CompanyInfoRepository;
import com.iiht.StockMarket.utils.StockMarketUtility;

@Service
@Transactional
public class CompanyInfoServiceImpl implements CompanyInfoService {
	
	@Autowired
	private CompanyInfoRepository repository; 
	
	public CompanyDetailsDTO saveCompanyDetails(CompanyDetailsDTO companyDetailsDTO) {
        CompanyDetails companyDetails= repository.save(StockMarketUtility.convertToCompanyDetails(companyDetailsDTO));
		return StockMarketUtility.convertToCompanyDetailsDTO(companyDetails);
	}
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO deleteCompany(Long companyCode) {
        Optional<CompanyDetails>  companyDetails =repository.findById(companyCode);
        if(companyDetails.isPresent())
        {
            //repository.deleteById(companyCode);
            repository.deleteByCompanyCode(companyCode);
            return StockMarketUtility.convertToCompanyDetailsDTO(companyDetails.get());
        }
        else
        {
            throw new CompanyNotFoundException("No company exist with comany code: "+companyCode);
        }
		
    }
	//----------------------------------------------------------------------------
	public CompanyDetailsDTO getCompanyInfoById(Long companyCode) {
		Optional<CompanyDetails>  companyDetails =repository.findById(companyCode);
        if(companyDetails.isPresent())
        {
            return StockMarketUtility.convertToCompanyDetailsDTO(companyDetails.get());
        }
        else
        {
            throw new CompanyNotFoundException("No company exist with comany code: "+companyCode);
        }
		
	}
	
	//----------------------------------------------------------------------------
	public List<CompanyDetailsDTO> getAllCompanies() {
        List<CompanyDetails> listCompanies=repository.findAll();
        if(listCompanies.size()!=0)
        return StockMarketUtility.convertToCompanyDetailsDtoList(listCompanies);
        else
        throw new CompanyNotFoundException("No company exists.");
    }
}