package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.model.NewsEntity;
import org.example.model.StockEntity;
import org.example.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class StockService {
    final
    StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockEntity> findAll() {
        log.info("All stocks have been found");
        return stockRepository.findAll();
    }
    public <S extends StockEntity> S save(S entity) {
        log.info(entity + " has been saved");
        return stockRepository.save(entity);
    }
    public Optional<StockEntity> findById(Integer integer) {
        log.info("Stock with id " + integer + " has been found");
        return stockRepository.findById(integer);
    }
    public void delete(StockEntity entity) {
        log.info("Stock with id" + entity.getId() + " has been removed");
        stockRepository.delete(entity);
    }


    public Page<StockEntity> findAllPageByStatus(Boolean bool, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        log.info("Stocks with " + pageNumber + " has been found. Status = " + bool);
        return stockRepository.findByStatus(bool, pageable);

    }
    //public Page<StockEntity> findAllPage(Integer pageNumber, Integer pageSize){
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        log.info("Users with " + pageNumber + " has been found");
//        return stockRepository.findAll(pageable);
//
//    }
}
