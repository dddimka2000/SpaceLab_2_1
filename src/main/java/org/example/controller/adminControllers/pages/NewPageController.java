package org.example.controller.adminControllers.pages;

import lombok.extern.log4j.Log4j2;
import org.example.model.PageEntity;
import org.example.service.pages.NewPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("admin/pages/")
@Log4j2
public class NewPageController {
    String path = "C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\";

    final
    NewPageService newPageService;
    @Autowired
    public NewPageController(NewPageService newPageService) {
        this.newPageService = newPageService;
    }

  @GetMapping("/{name}")
    public String showPageDB(@PathVariable String name, Model model){
        PageEntity pageEntity=newPageService.findByName(name).get();
        model.addAttribute("info", pageEntity);
        return "admin/pages/editNewPage";
    }

    @PostMapping("/{name}/edit")
    public String newPagePostEdit(@PathVariable String name, @RequestParam("name") String new_name,
                                  @RequestParam("description") String description,
                                  @RequestParam("url") String url,
                                  @RequestParam("title") String title,
                                  @RequestParam("keywords") String keywords,
                                  @RequestParam("descriptionSeo") String descriptionSeo,
                                  @RequestParam(name = "mainImagine", required = false) MultipartFile mainImagine,
                                  @RequestParam(name = "images[]", required = false) List<MultipartFile> images,
                                  @RequestParam (name = "switchValue",required = false) Optional<Boolean> switchValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        PageEntity pageEntity=newPageService.findByName(name).get();
        pageEntity.setStatus(switchValue.isPresent());
        pageEntity.setName(new_name);
        pageEntity.setDate(new Date());
        pageEntity.setDescription(description);
        pageEntity.setUrl(url);
        pageEntity.setTitle(title);
        pageEntity.setKeywords(keywords);
        pageEntity.setDescription_seo(descriptionSeo);

        String uuidFile;
        String resultFilename;
        if (mainImagine != null && !mainImagine.isEmpty()) {
            uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String mainImaginePath = "/photos/" + resultFilename;


            pageEntity.setImg(mainImaginePath);
        }

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
                setImgMethod = pageEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(pageEntity, img);
            }
            setImgNum++;
        }

        newPageService.save(pageEntity);


        return "redirect:/admin/pages";
    }
















    @GetMapping("/newPage")
    public String newPage() {
        return "admin/pages/newPage";
    }

    @PostMapping("/newPage/create")
    public String newPagePost(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("url") String url,
                              @RequestParam("title") String title,
                              @RequestParam("keywords") String keywords,
                              @RequestParam("descriptionSeo") String descriptionSeo,
                              @RequestParam(name = "mainImagine", required = false) MultipartFile mainImagine,
                              @RequestParam(name = "images[]", required = false) List<MultipartFile> images,
                              @RequestParam(name = "switchValue",required = false) Boolean switchValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        PageEntity pageEntity=new PageEntity();
        if (switchValue != null) {
            pageEntity.setStatus(switchValue);
        } else {
            pageEntity.setStatus(false);
        }
        pageEntity.setName(name);
        pageEntity.setDate(new Date());
        pageEntity.setDescription(description);
        pageEntity.setUrl(url);
        pageEntity.setTitle(title);
        pageEntity.setKeywords(keywords);
        pageEntity.setDescription_seo(descriptionSeo);

        String uuidFile;
        String resultFilename;
        if (mainImagine != null && !mainImagine.isEmpty()) {
            uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String mainImaginePath = "/photos/" + resultFilename;


            pageEntity.setImg(mainImaginePath);
        }

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
                setImgMethod = pageEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(pageEntity, img);
            }
            setImgNum++;
        }

        newPageService.save(pageEntity);


        return "redirect:/admin/pages";
    }

}
