package com.mmt.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mmt.management.entity.City;

public interface CityService {
	City saveCity(City city);
	
	List<City> getProvinces();
	
	void deleteCities(List<City> cities);
	
	City getOneCity(Long id);
	
	Page<City> getCitysByQueries(String parentId, String name, int pageNumber, int pageSize);
	
	void deleteCityById(Long id);

	List<City> getCityByParent(Long parentId);
}
