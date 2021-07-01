package com.microservicesdemo.crudoperation.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservicesdemo.crudoperation.bean.CurrencyExchange;
import com.microservicesdemo.crudoperation.database.CurrencyExchangeRepository;
import com.microservicesdemo.crudoperation.exceptions.NoRecordFoundException;

@Service
public class CrudService {
	
	@Autowired
	CurrencyExchangeRepository currencyExchangeRepository;

	public CurrencyExchange save(CurrencyExchange currencyExchange) {
		String from = currencyExchange.getFrom();
		String to = currencyExchange.getTo();
		Optional<CurrencyExchange>  ce = findByFromAndTo(from,to);
		if(ce.isPresent())
			throw new RuntimeException("Record already exists");
		
		 return currencyExchangeRepository.save(currencyExchange);
	}

	public List<CurrencyExchange> retrieveAll() {
		return currencyExchangeRepository.findAll();
	}
	
	public CurrencyExchange updateCurrencyMapping(CurrencyExchange currencyExchange) {
		Optional<CurrencyExchange> ce = findByFromAndTo(currencyExchange.getFrom(),currencyExchange.getTo() );
		ce.ifPresent((c)->{
			c.setFrom(currencyExchange.getFrom());
			c.setTo(currencyExchange.getTo());
			c.setConversionMultiple(currencyExchange.getConversionMultiple());	
		});
		
		CurrencyExchange c2 = currencyExchangeRepository.save(ce.get());
		return c2;
	}
	
	public Optional<CurrencyExchange> findById(Long id) {
		return currencyExchangeRepository.findById(id);
		
	}
	public void deleteById(Long id) {
		Optional<CurrencyExchange> ce = findById(id);
		if(ce.isPresent())
		currencyExchangeRepository.deleteById(id);
		else
			throw new NoRecordFoundException("No Record Present to delete");
	}
	public void deleteByFromAndTo( String from, String to) {
		currencyExchangeRepository.deleteByFromAndTo(from, to);
	}
	public Optional<CurrencyExchange>  findByFromAndTo(String from, String to) {
		return currencyExchangeRepository.findByFromAndTo(from, to);
	}
}
