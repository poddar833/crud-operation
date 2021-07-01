package com.microservicesdemo.crudoperation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservicesdemo.crudoperation.bean.CurrencyExchange;
import com.microservicesdemo.crudoperation.exceptions.NoRecordFoundException;
import com.microservicesdemo.crudoperation.services.CrudService;

@RestController
public class CrudController {
	
	@Autowired
	CrudService crudService;
	
	@Autowired
	private Environment env;
	

	

	@GetMapping("getAll-currency-mapping")
	public List<CurrencyExchange> retrieveAll() {
		String port =  env.getProperty("local.server.port");
		 List<CurrencyExchange> c = crudService.retrieveAll();
		 c.forEach((currency)->currency.setEnvironment("crud-services:" + port));
		 return c;
	}
	
	@GetMapping("get-by-id/{id}")
	public  Optional<CurrencyExchange> findById(@PathVariable Long id) {
		String port =  env.getProperty("local.server.port");
		String path = "/get-by-id/" + id;
		Optional<CurrencyExchange> c = crudService.findById(id);
		c.ifPresent((ce)->ce.setEnvironment("crud-services:" + port));
		if(!c.isPresent())
			throw new NoRecordFoundException(path);
			
		return c;
	}
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public Optional<CurrencyExchange>  findByFromAndTo(@PathVariable String from, @PathVariable String to) {
		String port =  env.getProperty("local.server.port");
		String path = "/currency-exchange/from/" + from + "/to/" + to;
		Optional<CurrencyExchange>  ce = crudService.findByFromAndTo(from, to);
		ce.ifPresent((c)->c.setEnvironment("crud-services:" + port));
		if(!ce.isPresent())
			throw new NoRecordFoundException(path);
		return ce;
	}
	
	@PostMapping("/add-currency-mapping")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CurrencyExchange save(@RequestBody CurrencyExchange currencyExchange) {
		String port =  env.getProperty("local.server.port");
		CurrencyExchange ce = null;
		try {
			ce = crudService.save(currencyExchange);
			ce.setEnvironment("crud-services:" + port);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return ce;
	}
	
	@PostMapping("update-currency-mapping")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CurrencyExchange updateCurrencyMapping(@RequestBody CurrencyExchange currencyExchange) {
		String port =  env.getProperty("local.server.port");
		CurrencyExchange ce = null;
		try {
			ce = crudService.updateCurrencyMapping(currencyExchange);
			ce.setEnvironment("crud-services:" + port);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return ce;
	}

	
	@PostMapping("/delete-by-id/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable Long id) {
		String port =  env.getProperty("local.server.port");
		String path = "/delete-by-id/" + id;
		try {
			crudService.deleteById(id);
		} catch (NoRecordFoundException e) {
			throw new NoRecordFoundException(path);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
}
