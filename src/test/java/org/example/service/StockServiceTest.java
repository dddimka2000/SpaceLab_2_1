package org.example.service;

import org.example.model.StockEntity;
import org.example.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StockServiceTest {
    @InjectMocks
    StockService stockService;
    @Mock
    StockRepository stockRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<StockEntity> list=new ArrayList<>();
        list.add(new StockEntity());
        list.add(new StockEntity());
        stockRepository.save(list.get(0));
        stockRepository.save(list.get(1));
        when(stockRepository.findAll()).thenReturn(list);
        assertEquals(stockRepository.findAll(),list);
    }

    @Test
    void save() {
        StockEntity stockEntity = new StockEntity();
        stockService.save(stockEntity);
        verify(stockRepository).save(stockEntity);
    }

    @Test
    void findById() {
        Integer stockId = 1;
        StockEntity stockEntity = new StockEntity();
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stockEntity));
        Optional<StockEntity> result = stockService.findById(stockId);
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(stockEntity, result.get());
        verify(stockRepository).findById(stockId);
    }

    @Test
    void delete() {
        StockEntity stockEntity = new StockEntity();
        stockService.delete(stockEntity);
        verify(stockRepository).delete(stockEntity);
    }
    @Test
    void findAllPageByStatus() {
        boolean status = true;
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<StockEntity> stockList = new ArrayList<>();
        stockList.add(new StockEntity());
        stockList.add(new StockEntity());
        Page<StockEntity> expectedPage = new PageImpl<>(stockList, pageable, stockList.size());
        when(stockRepository.findByStatus(status, pageable)).thenReturn(expectedPage);
        Page<StockEntity> resultPage = stockService.findAllPageByStatus(status, pageNumber, pageSize);
        assertNotNull(resultPage);
        assertEquals(expectedPage, resultPage);
        verify(stockRepository).findByStatus(status, pageable);
    }
}