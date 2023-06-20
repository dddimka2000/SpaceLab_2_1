package org.example.controller.adminControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.NewsEntity;
import org.example.model.StockEntity;
import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Log4j2
@Controller
@RequestMapping("/admin/stocks")
public class StockController {
    String path = "C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\";

    final
    StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping
    public String showStocks(Model model) {
        List<StockEntity> stockEntities = stockService.findAll();
        model.addAttribute("news", stockEntities);
        return "/admin/stocks/stocks";
    }


    @GetMapping("/createStock")
    public String createNews() {
        return "/admin/stocks/newStock";
    }


    @PostMapping("/createStock")
    public String createNewsPost(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("link") String link,
                                 @RequestParam("url") String url,
                                 @RequestParam("title") String title,
                                 @RequestParam("keywords") String keywords,
                                 @RequestParam("descriptionSeo") String descriptionSeo,
                                 @RequestParam(name = "mainImagine", required = false) MultipartFile mainImagine,
                                 @RequestParam(name = "images[]", required = false) List<MultipartFile> images,
                                 @RequestParam(name = "switchValue", required = false) Optional<Boolean> switchValue,
                                 @RequestParam(name = "selectedDate") Optional<String> selectedDate)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        StockEntity stockEntity = new StockEntity();
        stockEntity.setName(name);
        stockEntity.setDescription(description);
        stockEntity.setLink(link);
        stockEntity.setUrl(url);
        stockEntity.setTitle(title);
        stockEntity.setDescriptionSeo(descriptionSeo);
        stockEntity.setKeywords(keywords);
        stockEntity.setStatus(switchValue.isPresent());


        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + mainImagine.getOriginalFilename();
        try {
            mainImagine.transferTo(new File(path + resultFilename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String mainImaginePath = "/photos/" + resultFilename;

        stockEntity.setImg(mainImaginePath);

        String img;
        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                uuidFile = UUID.randomUUID().toString();
                resultFilename = uuidFile + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                img = "/photos/" + resultFilename;
                setImgMethod = stockEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(stockEntity, img);
            }
            setImgNum++;
        }
        log.info(selectedDate);
        if (selectedDate.isPresent()&& !selectedDate.get().isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parsedDate = dateFormat.parse(selectedDate.get());
                stockEntity.setDate(parsedDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            stockEntity.setDate(new Date());
        }
        stockService.save(stockEntity);
        return "redirect:/admin/stocks";
    }

    @GetMapping("/{id}")
    public String showModelNews(@PathVariable Integer id, Model model) {
        StockEntity stockEntity = stockService.findById(id).get();
        model.addAttribute("info", stockEntity);
        return "/admin/stocks/editStock";
    }

    @PostMapping("/{id}")
    public String saveModelNews(@PathVariable Integer id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("link") String link,
                                @RequestParam("url") String url,
                                @RequestParam("title") String title,
                                @RequestParam("keywords") String keywords,
                                @RequestParam("descriptionSeo") String descriptionSeo,
                                @RequestParam(name = "mainImagine", required = false) MultipartFile mainImagine,
                                @RequestParam(name = "images[]", required = false) List<MultipartFile> images,
                                @RequestParam(name = "switchValue", required = false) Optional<Boolean> switchValue,
                                @RequestParam(name = "selectedDate",required = false) Optional<String> selectedDate) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StockEntity stockEntity = stockService.findById(id).get();
        stockEntity.setName(name);
        stockEntity.setDescription(description);
        stockEntity.setLink(link);
        stockEntity.setUrl(url);
        stockEntity.setTitle(title);
        stockEntity.setDescriptionSeo(descriptionSeo);
        stockEntity.setKeywords(keywords);
        stockEntity.setStatus(switchValue.isPresent());
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + mainImagine.getOriginalFilename();
        try {
            mainImagine.transferTo(new File(path + resultFilename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String mainImaginePath = "/photos/" + resultFilename;

        stockEntity.setImg(mainImaginePath);

        String img;
        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                uuidFile = UUID.randomUUID().toString();
                resultFilename = uuidFile + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                img = "/photos/" + resultFilename;
                setImgMethod = stockEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(stockEntity, img);
            }
            setImgNum++;
        }
        log.info(selectedDate);
        if (selectedDate.isPresent()&& !selectedDate.get().isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parsedDate = dateFormat.parse(selectedDate.get());
                stockEntity.setDate(parsedDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        stockService.save(stockEntity);


        return "redirect:/admin/stocks";
    }

    @PostMapping("/delete/{id}")
    public String deleteNews(@PathVariable Integer id){
        stockService.delete(stockService.findById(id).get());
        return "redirect:/admin/stocks";
    }

}
