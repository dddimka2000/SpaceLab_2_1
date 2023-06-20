package org.example.controller.adminControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.ImagineEntity;
import org.example.service.ImagineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Controller
@RequestMapping("admin/banners")
public class BannersAdminController {
    final
    ImagineService imagineService;

    @Autowired
    public BannersAdminController(ImagineService imagineService) {
        this.imagineService = imagineService;


    }

    @PostMapping("/carousel")
    public String carouselAdmin(@RequestParam(value = "filesCarousel", required = false) List<MultipartFile> files,
                                @RequestParam(value = "deleteItem", required = false) Integer deleteItemId,
                                @RequestParam(value = "Url", required = false) List<String> urlList,
                                @RequestParam(value = "photoId", required = false) List<Integer> photoId) {
        String path = "C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\";
        File uploadDir = new File(path);
        int i = -1;

        for (MultipartFile file : files) {
            i++;
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                List<ImagineEntity> imagineEntityOldList = imagineService.findByName("Карусель");
                if (i < imagineEntityOldList.size()) {
                    Optional<ImagineEntity> imagineEntityOld = Optional.ofNullable(imagineEntityOldList.get(i));
                    if (imagineEntityOld.isPresent()) {
                        File oldFile = new File(path + imagineEntityOld.get().getPath().replaceAll("/photos", ""));
                        if (oldFile.exists()) {
                            oldFile.delete();
                            log.info("deleted" + oldFile);
                        }
                        imagineEntityOld.get().setPath("/photos/" + resultFilename);
                        imagineService.save(imagineEntityOld.get());
                    }
                } else {
                    ImagineEntity imagineEntity = new ImagineEntity();
                    imagineEntity.setName("Карусель");
                    imagineEntity.setPath("/photos/" + resultFilename);
                    imagineService.save(imagineEntity);
                }
            }
        }
        int num = 0;
        if (!urlList.isEmpty()) {
            for (String url : urlList) {
                if (!url.equals("")) {
                    log.info("url set " + url);
                    log.info("photoId " + photoId);
                    ImagineEntity imagineEntity;
                    if (photoId.size()<num+1) {
                         imagineEntity = new ImagineEntity();
                    } else {
                         imagineEntity = imagineService.findById(photoId.get(num)).get();
                        if (imagineEntity.getPath().contains("/photos/")) {
                            File oldFile = new File("C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\" + imagineEntity.getPath().replaceAll("/photos/", ""));
                            if (oldFile.exists()) {
                                oldFile.delete();
                                log.info("deleted " + oldFile);
                            }
                        }
                    }
                    imagineEntity.setName("Карусель");

                    imagineEntity.setPath(url);
                    log.info(url);
                    imagineService.save(imagineEntity);
                }
                num++;
            }
        }
        if (deleteItemId != null) {
            Optional<ImagineEntity> imagineEntity = imagineService.findById(deleteItemId);
            if (imagineEntity.isPresent()) {
                File oldFile = new File("C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\" + imagineEntity.get().getPath().replaceAll("/photos/", ""));
                if (oldFile.exists()) {
                    oldFile.delete();
                    log.info("deleted " + oldFile);
                }
                imagineService.delete(imagineEntity.get());
            }
        }
        return "redirect:/admin/banners";
    }

    @PostMapping("/background")
    public String backgroundAdmin(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File("C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img");
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            try {
                file.transferTo(new File("C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\" + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ArrayList<ImagineEntity> imagineEntityList = imagineService.findByName("Главная страница");
            if (imagineEntityList.isEmpty()) {
                ImagineEntity imagineEntityNew = new ImagineEntity();
                imagineEntityNew.setName("Главная страница");
                imagineEntityNew.setPath("/photos/" + resultFilename);
                imagineService.save(imagineEntityNew);
            } else {
                Optional<ImagineEntity> imagineEntity = Optional.ofNullable(imagineEntityList.get(0));
                File oldFile = new File("C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\"
                        + imagineEntity.get().getPath().replaceAll("/photos/", ""));
                if (oldFile.exists()) {
                    oldFile.delete();
                    log.info(oldFile + " deleted");
                }
                imagineEntity.get().setPath("/photos/" + resultFilename);
                imagineService.save(imagineEntity.get());
            }
        }
        return "redirect:/admin/banners";
    }

    @GetMapping
    public String banners(Model model) {
        List<ImagineEntity> imagineEntities = imagineService.findByName("Главная страница");
        String imagePath = "";
        if (!imagineEntities.isEmpty()) {
            imagePath = imagineEntities.get(0).getPath();
        }
        model.addAttribute("backgroundImg", imagePath);

        ArrayList<ImagineEntity> list = imagineService.findByName("Карусель");
        model.addAttribute("carouselItems", list);

        ArrayList<ImagineEntity> list2 = imagineService.findByName("НовостиАкции");
        model.addAttribute("NewsStocks", list2);
        return "/admin/banners";
    }

    @PostMapping("/newsStocks")
    public String newsStocksAdmin(@RequestParam(value = "filesCarousel", required = false) List<MultipartFile> files, @RequestParam(value = "deleteItem", required = false) Integer deleteItemId,
                                @RequestParam(value = "Url", required = false) List<String> urlList, @RequestParam(value = "photoId", required = false) List<Integer> photoId) {
        String path = "C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\";
        File uploadDir = new File(path);
        int i = -1;

        for (MultipartFile file : files) {
            i++;
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                List<ImagineEntity> imagineEntityOldList = imagineService.findByName("НовостиАкции");
                if (i < imagineEntityOldList.size()) {
                    Optional<ImagineEntity> imagineEntityOld = Optional.ofNullable(imagineEntityOldList.get(i));
                    if (imagineEntityOld.isPresent()) {
                        File oldFile = new File(path + imagineEntityOld.get().getPath().replaceAll("/photos", ""));
                        if (oldFile.exists()) {
                            oldFile.delete();
                            log.info("deleted" + oldFile);
                        }
                        imagineEntityOld.get().setPath("/photos/" + resultFilename);
                        imagineService.save(imagineEntityOld.get());
                    }
                } else {
                    ImagineEntity imagineEntity = new ImagineEntity();
                    imagineEntity.setName("НовостиАкции");
                    imagineEntity.setPath("/photos/" + resultFilename);
                    imagineService.save(imagineEntity);
                }
            }
        }
        int num = 0;
        if (!urlList.isEmpty()) {
            for (String url : urlList) {
                if (!url.equals("")) {
                    log.info("url set " + url);
                    log.info("photoId " + photoId);
                    ImagineEntity imagineEntity;
                    if (photoId.size()<num+1) {
                        imagineEntity = new ImagineEntity();
                    } else {
                        imagineEntity = imagineService.findById(photoId.get(num)).get();
                        if (imagineEntity.getPath().contains("/photos/")) {
                            File oldFile = new File("C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\" + imagineEntity.getPath().replaceAll("/photos/", ""));
                            if (oldFile.exists()) {
                                oldFile.delete();
                                log.info("deleted " + oldFile);
                            }
                        }
                    }
                    imagineEntity.setName("НовостиАкции");

                    imagineEntity.setPath(url);
                    log.info(url);
                    imagineService.save(imagineEntity);
                }
                num++;
            }
        }
        if (deleteItemId != null) {
            Optional<ImagineEntity> imagineEntity = imagineService.findById(deleteItemId);
            if (imagineEntity.isPresent()) {
                File oldFile = new File("C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\" + imagineEntity.get().getPath().replaceAll("/photos/", ""));
                if (oldFile.exists()) {
                    oldFile.delete();
                    log.info("deleted " + oldFile);
                }
                imagineService.delete(imagineEntity.get());
            }
        }
        return "redirect:/admin/banners";
    }


}
