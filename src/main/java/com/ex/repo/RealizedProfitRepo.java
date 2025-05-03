package com.ex.repo;

import com.ex.entity.RealizedProfitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealizedProfitRepo extends JpaRepository<RealizedProfitEntity,String> {

}