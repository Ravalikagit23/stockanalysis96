package com.ex.repo;


import com.ex.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepo extends JpaRepository<PortfolioEntity,String > {

}