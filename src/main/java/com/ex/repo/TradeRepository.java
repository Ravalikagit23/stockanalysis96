package com.ex.repo;

import com.ex.entity.TradeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends MongoRepository<TradeEntity, String > {

}
