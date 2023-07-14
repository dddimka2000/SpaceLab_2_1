package org.example.controller.adminControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.CinemaEntity;
import org.example.model.HallEntity;
import org.example.service.CinemaService;
import org.example.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/cinemas")
@Log4j2
public class CinemaAdminController {
    @Value("${spring.regex}")
    String regex;
    @Value("${spring.pathImg}")
    String path;
    int id = 1;
    private final
    CinemaService cinemaService;

    private final
    HallService hallService;


    @Autowired
    public CinemaAdminController(CinemaService cinemaService, HallService hallService) {
        this.cinemaService = cinemaService;
        this.hallService = hallService;
    }
@PostMapping("/{idCinema}/delete")
public String deleteCinema(@PathVariable Integer idCinema){
        List<HallEntity> hallEntityList=hallService.findByCinemaId(idCinema);
        hallEntityList.forEach(i->hallService.remove(i.getIdHall()));
        cinemaService.remove(idCinema);
        return "redirect:/admin/cinemas";
}

    @GetMapping("/newCinema/edit/{idHall}")
    public String newCinemasHallAdminEdit(@PathVariable Integer idHall, Model model) {
        model.addAttribute("info", hallService.findById(idHall).get());
        return "admin/cinemas/editHall";
    }

    @GetMapping("/edit/{idCinema}/edit/{idHall}")
    public String editHallIdCinema(@PathVariable Integer idCinema, @PathVariable Integer idHall, Model model){
        model.addAttribute("info",hallService.findById(idHall).get());
        return "/admin/cinemas/editHallidCinema";
    }
    @PostMapping("/edit/{idCinema}/edit/{idHall}")
    public String editHallIdCinemaPost(@PathVariable Integer idCinema, @PathVariable Integer idHall,
                                       @RequestParam("nameHall") String nameHall,
                                       @RequestParam("descriptionHall")
                                           String descriptionHall,
                                       @RequestParam("schema_hall")
                                           MultipartFile schemaHall,
                                       @RequestParam("mainImagine")
                                           MultipartFile mainImagine,
                                       @RequestParam("images[]")
                                           List<MultipartFile> images,
                                       @RequestParam("url")
                                           String url,
                                       @RequestParam("title")
                                           String title,
                                       @RequestParam("keywords")
                                           String keywords,
                                       @RequestParam("descriptionSeo")
                                           String descriptionSeo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HallEntity hallEntity = hallService.findById(idHall).get();
        hallEntity.setName(nameHall);
        hallEntity.setDescription(descriptionHall);
        hallEntity.setUrl(url);
        hallEntity.setTitle(title);
        hallEntity.setKeywords(keywords);
        hallEntity.setDescriptionSeo(descriptionSeo);

        String resultFilename;
        String name;
        if (!schemaHall.isEmpty() && schemaHall != null) {
            resultFilename = UUID.randomUUID().toString() + schemaHall.getOriginalFilename();
            try {
                schemaHall.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            hallEntity.setSchemeHall(resultFilename);
        }
        if (!mainImagine.isEmpty() && mainImagine != null) {
            resultFilename = UUID.randomUUID().toString() + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            hallEntity.setTopBanner(name);
        }

        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                resultFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                name = regex + resultFilename;
                setImgMethod = hallEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(hallEntity, name);
            }
            setImgNum++;
        }
        hallService.save(hallEntity);
        return "redirect:/admin/cinemas/edit/{idCinema}";
    }

    @PostMapping("/newCinema/edit/{idHall}")
    public String newCinemasHallAdminEditPost(@PathVariable Integer idHall,
                                              @RequestParam("nameHall") String nameHall,
                                              @RequestParam("descriptionHall")
                                              String descriptionHall,
                                              @RequestParam("schema_hall")
                                              MultipartFile schemaHall,
                                              @RequestParam("mainImagine")
                                              MultipartFile mainImagine,
                                              @RequestParam("images[]")
                                              List<MultipartFile> images,
                                              @RequestParam("url")
                                              String url,
                                              @RequestParam("title")
                                              String title,
                                              @RequestParam("keywords")
                                              String keywords,
                                              @RequestParam("descriptionSeo")
                                              String descriptionSeo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HallEntity hallEntity = hallService.findById(idHall).get();
        hallEntity.setName(nameHall);
        hallEntity.setDescription(descriptionHall);
        hallEntity.setUrl(url);
        hallEntity.setTitle(title);
        hallEntity.setKeywords(keywords);
        hallEntity.setDescriptionSeo(descriptionSeo);

        String resultFilename;
        String name;
        if (!schemaHall.isEmpty() && schemaHall != null) {
            resultFilename = UUID.randomUUID().toString() + schemaHall.getOriginalFilename();
            try {
                schemaHall.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            hallEntity.setSchemeHall(name);
        }
        if (!mainImagine.isEmpty() && mainImagine != null) {
            resultFilename = UUID.randomUUID().toString() + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            hallEntity.setTopBanner(name);
        }

        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                resultFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                name = regex + resultFilename;
                setImgMethod = hallEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(hallEntity, name);
            }
            setImgNum++;
        }
        hallService.save(hallEntity);
        return "redirect:/admin/cinemas/newCinema";

    }
    @PostMapping("/newCinema/delete/{idHall}")
    public String newCinemasHallAdminDelete(@PathVariable Integer idHall){
        hallService.remove(idHall);
        return "redirect:/admin/cinemas/newCinema";
    }

    //post cinema todo
    @GetMapping("/edit/{idCinema}")
    public String cinemasEditAdmin(Model model, @PathVariable Integer idCinema) {
        Optional<CinemaEntity> cinemaEntity = cinemaService.findById(idCinema);
        List<HallEntity> hallEntityList = hallService.findByCinemaId(idCinema);
        model.addAttribute("hallList", hallEntityList);
        model.addAttribute("info", cinemaEntity.get());
        return "admin/cinemas/editCinema";
    }

    @PostMapping("/edit/{idCinema}")
    public String cinemasEditAdminPost(@PathVariable Integer idCinema,
                                       @RequestParam("nameCinema") String nameCinema,
                                       @RequestParam("description") String description,
                                       @RequestParam("conditions") String conditions,
                                       @RequestParam("logoPath") MultipartFile logo,
                                       @RequestParam("topBannerPath") MultipartFile mainImagine,
                                       @RequestParam("images[]") List<MultipartFile> images,
                                       @RequestParam("url") String url,
                                       @RequestParam("title") String title,
                                       @RequestParam("keywords") String keywords,
                                       @RequestParam("descriptionSeo") String descriptionSeo,
                                       @RequestParam(value = "deleteHall",required = false) Integer deleteHall) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(deleteHall!=null){
            hallService.remove(deleteHall);
            return "redirect:/admin/cinemas/edit/{idCinema}";
        }
        CinemaEntity cinemaEntity=cinemaService.findById(idCinema).get();
        cinemaEntity.setName(nameCinema);
        cinemaEntity.setDescription(description);
        cinemaEntity.setConditions(conditions);
        cinemaEntity.setUrl(url);
        cinemaEntity.setTitle(title);
        cinemaEntity.setKeywords(keywords);
        cinemaEntity.setDescriptionSeo(descriptionSeo);
        String resultFilename;
        String name;
        if (!logo.isEmpty() && logo != null) {
            resultFilename = UUID.randomUUID().toString() + logo.getOriginalFilename();
            try {
                logo.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            cinemaEntity.setLogoPath(name);
        }
        if (!mainImagine.isEmpty() && mainImagine != null) {
            resultFilename = UUID.randomUUID().toString() + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            cinemaEntity.setTopBannerPath(name);
        }

        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                resultFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                name = regex + resultFilename;
                setImgMethod = cinemaEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(cinemaEntity, name);
            }
            setImgNum++;
        }
        cinemaService.save(cinemaEntity);
        return "redirect:/admin/cinemas";
    }


    @GetMapping("/edit/{idCinema}/newHall")
    public String cinemasNewHallAdmin(Model model, @PathVariable Integer idCinema) {
        model.addAttribute("info", idCinema);
        return "admin/cinemas/newHallidCinema";
    }

    @PostMapping("/edit/{idCinema}/newHall")
    public String cinemasEditNewHall(Model model, @PathVariable Integer idCinema,
                                     @RequestParam("nameHall") String nameHall,
                                     @RequestParam("descriptionHall")
                                     String descriptionHall,
                                     @RequestParam("schema_hall")
                                     MultipartFile schemaHall,
                                     @RequestParam("mainImagine")
                                     MultipartFile mainImagine,
                                     @RequestParam("images[]")
                                     List<MultipartFile> images,
                                     @RequestParam("url")
                                     String url,
                                     @RequestParam("title")
                                     String title,
                                     @RequestParam("keywords")
                                     String keywords,
                                     @RequestParam("descriptionSeo")
                                     String descriptionSeo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HallEntity hallEntity = new HallEntity();
        hallEntity.setName(nameHall);
        hallEntity.setDescription(descriptionHall);
        hallEntity.setUrl(url);
        hallEntity.setTitle(title);
        hallEntity.setKeywords(keywords);
        hallEntity.setDescriptionSeo(descriptionSeo);

        String resultFilename;
        String name;
        if (!schemaHall.isEmpty() && schemaHall != null) {
            resultFilename = UUID.randomUUID().toString() + schemaHall.getOriginalFilename();
            try {
                schemaHall.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            hallEntity.setSchemeHall(name);
        }
        if (!mainImagine.isEmpty() && mainImagine != null) {
            resultFilename = UUID.randomUUID().toString() + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            hallEntity.setTopBanner(name);
        }

        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                resultFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                name = regex + resultFilename;
                setImgMethod = hallEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(hallEntity, name);
            }
            setImgNum++;
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        hallEntity.setDate(currentDateTime);
        hallEntity.setCinemaEntity(cinemaService.findById(idCinema).get());
        hallService.save(hallEntity);
        return "redirect:/admin/cinemas/edit/{idCinema}";

    }

    //show
    @GetMapping
    public String cinemasAdmin(Model model) {
        log.info(path);
        List<CinemaEntity> cinemas = cinemaService.findAll();
        if (!cinemas.isEmpty()) {
            cinemas.remove(cinemas.size() - 1);
        }
        model.addAttribute("listCinema", cinemas);
        return "admin/cinemas/cinemas";
    }

    //new
    @GetMapping("/newCinema/newHall")
    public String newCinemasHallAdmin() {
        return "admin/cinemas/newHall";
    }

    @PostMapping("/newCinema/newHall")
    public String newPostCinemasHallAdmin(@RequestParam("nameHall") String nameHall,
                                          @RequestParam("descriptionHall")
                                          String descriptionHall,
                                          @RequestParam("schema_hall")
                                          MultipartFile schemaHall,
                                          @RequestParam("mainImagine")
                                          MultipartFile mainImagine,
                                          @RequestParam("images[]")
                                          List<MultipartFile> images,
                                          @RequestParam("url")
                                          String url,
                                          @RequestParam("title")
                                          String title,
                                          @RequestParam("keywords")
                                          String keywords,
                                          @RequestParam("descriptionSeo")
                                          String descriptionSeo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HallEntity hallEntity = new HallEntity();
        hallEntity.setName(nameHall);
        hallEntity.setDescription(descriptionHall);
        hallEntity.setUrl(url);
        hallEntity.setTitle(title);
        hallEntity.setKeywords(keywords);
        hallEntity.setDescriptionSeo(descriptionSeo);


        String resultFilename;
        String name;
        if (!schemaHall.isEmpty() && schemaHall != null) {
            resultFilename = UUID.randomUUID().toString() + schemaHall.getOriginalFilename();
            try {
                schemaHall.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            hallEntity.setSchemeHall(name);
        }
        if (!mainImagine.isEmpty() && mainImagine != null) {
            resultFilename = UUID.randomUUID().toString() + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            hallEntity.setTopBanner(name);
        }

        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                resultFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                name = regex + resultFilename;
                setImgMethod = hallEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(hallEntity, name);
            }
            setImgNum++;
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        hallEntity.setDate(currentDateTime);
        hallService.save(hallEntity);

        return "redirect:/admin/cinemas/newCinema";
    }

    @GetMapping("/newCinema")
    public String newCinemasAdmin(Model model) {
        List<HallEntity> hallList = null;
        if (!hallService.findByEmptyCinemaId().isEmpty()) {
            hallList = hallService.findByEmptyCinemaId();
        }
        model.addAttribute("hallList", hallList);

        return "admin/cinemas/newCinema";
    }

    @PostMapping("/newCinema")
    public String newPostCinemaAdmin(@RequestParam("nameCinema") String nameCinema,
                                     @RequestParam("description") String description,
                                     @RequestParam("conditions") String conditions,
                                     @RequestParam("logo") MultipartFile logo,
                                     @RequestParam("mainImagine") MultipartFile mainImagine,
                                     @RequestParam("images[]") List<MultipartFile> images,
                                     @RequestParam("url") String url,
                                     @RequestParam("title") String title,
                                     @RequestParam("keywords") String keywords,
                                     @RequestParam("descriptionSeo") String descriptionSeo,
                                     @RequestParam(value = "deleteHall",required = false) Integer deleteHall) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(deleteHall!=null){
            hallService.remove(deleteHall);
            return "redirect:/admin/cinemas/newCinema";
        }
        CinemaEntity cinemaEntity;
        if (cinemaService.findLast().isPresent()) {
            cinemaEntity = cinemaService.findLast().get();
        } else {
            cinemaEntity = new CinemaEntity();
        }
        cinemaEntity.setName(nameCinema);
        cinemaEntity.setDescription(description);
        cinemaEntity.setConditions(conditions);
        cinemaEntity.setUrl(url);
        cinemaEntity.setTitle(title);
        cinemaEntity.setKeywords(keywords);
        cinemaEntity.setDescriptionSeo(descriptionSeo);
        String resultFilename;
        String name;
        if (!logo.isEmpty() && logo != null) {
            resultFilename = UUID.randomUUID().toString() + logo.getOriginalFilename();
            try {
                logo.transferTo(new File(path + resultFilename));
                log.info(path + resultFilename);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            log.info(name);
            cinemaEntity.setLogoPath(name);
        }
        if (!mainImagine.isEmpty() && mainImagine != null) {
            resultFilename = UUID.randomUUID().toString() + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            name = regex + resultFilename;
            cinemaEntity.setTopBannerPath(name);
        }

        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                resultFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                name = regex + resultFilename;
                setImgMethod = cinemaEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(cinemaEntity, name);
            }
            setImgNum++;
        }
        List<HallEntity> hallEntityList = hallService.findByEmptyCinemaId();
        cinemaEntity.setHallEntityList(hallEntityList);
        cinemaService.save(cinemaEntity);
        hallEntityList.forEach(hallEntity -> hallEntity.setCinemaEntity(cinemaEntity));
        hallEntityList.forEach(i -> hallService.save(i));
        if (cinemaService.findLast().isPresent()) {
            id = cinemaService.findLast().get().getIdCinema() + 1;
            while (true) {
                if (cinemaService.findById(id).isPresent()) {
                    id++;
                } else {
                    break;
                }
            }
        } else {
            id = 1;
        }
        CinemaEntity cinemaNew = new CinemaEntity();
        cinemaEntity.setIdCinema(id);
        cinemaService.save(cinemaNew);


        return "redirect:/admin/cinemas";
    }

}
