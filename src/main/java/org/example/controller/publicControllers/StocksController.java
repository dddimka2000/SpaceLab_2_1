package org.example.controller.publicControllers;

import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@Controller
public class StocksController {
    final
    StockService stockService;

    @Autowired
    public StocksController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/stock")
    public String showStocks(Model model) {
        model.addAttribute("stocks",stockService.findAll().stream().filter(stockEntity -> stockEntity.getStatus()==true).collect(Collectors.toList()));
        return "/public/stocks";
    }
    @GetMapping("/stock/{id}")
    public String showStock(Model model, @PathVariable Integer id) {
        model.addAttribute("info",stockService.findById(id).get());
        return "/public/showStock";
    }

}
