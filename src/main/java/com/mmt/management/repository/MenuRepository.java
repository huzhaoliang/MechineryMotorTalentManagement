package com.mmt.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmt.management.entity.Menu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaSpecificationExecutor<Menu>,JpaRepository<Menu, Long>{
    @Query(value="select a.* from menu a where a.level=1", nativeQuery = true)
    List<Menu> findFirstLevelMenus();
}
