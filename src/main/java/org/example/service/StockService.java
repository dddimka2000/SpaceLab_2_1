package org.example.service;

import org.example.model.StockEntity;
import org.example.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    final
    StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockEntity> findAll(){
        return stockRepository.findAll();
    };


    public <S extends StockEntity> S save(S entity){
        return stockRepository.save(entity);
    };

    public Optional<StockEntity> findById(Integer integer){
        return stockRepository.findById(integer);
    };


    public void delete(StockEntity entity){
        stockRepository.delete(entity);
    };

}
