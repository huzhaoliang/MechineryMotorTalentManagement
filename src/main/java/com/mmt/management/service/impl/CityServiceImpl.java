package com.mmt.management.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mmt.management.entity.City;
import com.mmt.management.repository.CityRepository;
import com.mmt.management.service.CityService;

@Service("CityService")
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityRepository cityRepository;

	@Override
	public City saveCity(City city) {
		return cityRepository.saveAndFlush(city);
	}

	@Override
	public List<City> getProvinces() {
		return cityRepository.findProvinces();
	}

	@Override
	public void deleteCities(List<City> cities) {
		cityRepository.deleteInBatch(cities);
	}

	@Override
	public City getOneCity(Long id) {
		return cityRepository.getOne(id);
	}

	@Override
	public Page<City> getCitysByQueries(Long parentId, String name, int pageNumber, int pageSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);
		Page<City> citys = null;
		if(parentId == -1 && "".equals(name)){
			citys = cityRepository.findAll(request);
		}else{
			Specification<City> spec = new Specification<City>() {
				public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
					Predicate p = null;
					if(!"".equals(name)){
						Path<String> nameAttribute = root.get("name");
						Predicate p1 = cb.like(nameAttribute, "%"+name+"%");
						p = cb.and(p1);
						if(parentId != -1) {
							Path<Integer> parentIdAttribute = root.get("parentId");
							Predicate p2 = cb.equal(parentIdAttribute, parentId);
							p = cb.and(p1, p2);
						}
					}else{
						if(parentId != -1) {
							Path<Integer> parentIdAttribute = root.get("parentId");
							Predicate p2 = cb.equal(parentIdAttribute, parentId);
							p = cb.and(p2);
						}
					}
					return p;
				}
			};
			citys = cityRepository.findAll(spec, request);
		}
		return citys;
	}

	@Override
	public void deleteCityById(Long id) {
		cityRepository.deleteById(id);
	}
}
