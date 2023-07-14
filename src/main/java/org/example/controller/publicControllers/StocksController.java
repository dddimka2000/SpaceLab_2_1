package org.example.controller.publicControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.CinemaEntity;
import org.example.model.HallEntity;
import org.example.model.StockEntity;
import org.example.model.UserEntity;
import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Log4j2
public class StocksController {
    final
    StockService stockService;

    @Autowired
    public StocksController(StockService stockService) {
        this.stockService = stockService;
    }
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/stock")
    public String showStocks(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 3;

        Page<StockEntity> userPage = stockService.findAllPageByStatus(true, page, pageSize);


        List<String> dateList = new ArrayList<>();

        userPage.getContent()
                .stream()
                .forEach(newsEntity -> {
                    String dateString = newsEntity.getDate().toString();
                    try {
                        Date date = inputFormat.parse(dateString);
                        String formattedDate = outputFormat.format(date);
                        dateList.add(formattedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });

        model.addAttribute("dateList", dateList);
        model.addAttribute("stocks", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());



        return "/public/stocks";
    }

    @PostMapping("/stock")
    public String nextPageUsers(@RequestParam("page") int page) {
        return "redirect:/stock/?page=" + page;
    }

    @GetMapping("/stock/{id}")
    public String showStock(Model model, @PathVariable Integer id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        model.addAttribute("info", stockService.findById(id).get());
        StockEntity stockEntityCarousel = stockService.findById(id).get();
        List<String> carousel = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Method getImgMethod = stockEntityCarousel.getClass().getMethod("getImg" + i);
            if (getImgMethod.invoke(stockEntityCarousel) != null) {
                carousel.add((String) getImgMethod.invoke(stockEntityCarousel));
                log.warn((String) getImgMethod.invoke(stockEntityCarousel));
            }
        }
        model.addAttribute("carouselItems", carousel);


        return "/public/showStock";
    }

}
