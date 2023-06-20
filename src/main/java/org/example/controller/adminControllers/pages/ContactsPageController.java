package org.example.controller.adminControllers.pages;

import lombok.extern.log4j.Log4j2;
import org.example.model.ContactCinemaEntity;
import org.example.model.MainPageEntity;
import org.example.service.pages.ContactsCinemaService;
import org.example.service.pages.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/pages/contacts")
@Log4j2
public class ContactsPageController {
    String path = "C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\";
    final
    ContactsCinemaService contactsCinemaService;
    final
    MainPageService mainPageService;

    @Autowired
    public ContactsPageController(ContactsCinemaService contactsCinemaService, MainPageService mainPageService) {
        this.contactsCinemaService = contactsCinemaService;
        this.mainPageService = mainPageService;
    }


    @GetMapping
    public String showContactPageAdmin(Model model) {
        model.addAttribute("contact_cinemas", contactsCinemaService.findAll());
        model.addAttribute("page", mainPageService.findFirstByNamePage("Контакты").get());
        return "/admin/pages/contacts";
    }

    @PostMapping
    public String showContactPagePostAdmin(@RequestParam("id") List<Integer> id,
                                      @RequestParam(name = "name", required = false) List<String> name,
                                      @RequestParam(name = "address", required = false) List<String> address,
                                      @RequestParam(name = "map_coordinates", required = false) List<String> mapCoordinates,
                                      @RequestParam(name = "logo", required = false) List<MultipartFile> logo,
                                      @RequestParam(name = "switchValue") List<Integer> switchValue,
                                      @RequestParam(name = "createButton", required = false) Optional<Boolean> create,
                                      @RequestParam(name = "url", required = false) String url,
                                      @RequestParam(name = "title", required = false) String title,
                                      @RequestParam(name = "keywords", required = false) String keywords,
                                      @RequestParam(name = "description", required = false) String description

    ) {
        if (create.isPresent()) {
            ContactCinemaEntity contactCinemaEntity = new ContactCinemaEntity();
            contactsCinemaService.save(contactCinemaEntity);
            return "redirect:/admin/pages/contacts";
        }
        MainPageEntity mainPage = mainPageService.findFirstByNamePage("Контакты").get();
        mainPage.setKeywords(keywords);
        mainPage.setTitle(title);
        mainPage.setUrl(url);
        mainPage.setDescription(description);
        mainPageService.saveMainPage(mainPage);
        String uuidFile;
        String resultFilename;
        String dbNameLogo;
        int i = 0;

        for (Integer d : id) {
            ContactCinemaEntity contactCinemaEntity = contactsCinemaService.findById(d).get();
            contactCinemaEntity.setName(name.get(i));
            contactCinemaEntity.setAddress(address.get(i));
            contactCinemaEntity.setMapCoordinates(mapCoordinates.get(i));
            if(switchValue.stream().anyMatch(element -> element.equals(d))){
                contactCinemaEntity.setStatus(true);
            }
            else {
                contactCinemaEntity.setStatus(false);

            }

            if (!logo.get(i).isEmpty()) {
                uuidFile = UUID.randomUUID().toString();
                resultFilename = uuidFile + "." + logo.get(i).getOriginalFilename();
                try {
                    logo.get(i).transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                dbNameLogo = "/photos/" + resultFilename;
                contactCinemaEntity.setLogo(dbNameLogo);
            }
            i++;
            contactsCinemaService.save(contactCinemaEntity);
        }
        return "redirect:/admin/pages/contacts";
    }


}
