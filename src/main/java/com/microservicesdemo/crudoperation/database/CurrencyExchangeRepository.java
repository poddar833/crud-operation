package com.microservicesdemo.crudoperation.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicesdemo.crudoperation.bean.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{
	public Optional<CurrencyExchange> findByFromAndTo(String from, String to);
	public void  deleteByFromAndTo(String from, String to);	
}