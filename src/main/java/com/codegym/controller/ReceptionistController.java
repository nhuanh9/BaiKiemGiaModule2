package com.codegym.controller;

import com.codegym.model.Receptionist;
import com.codegym.model.ReceptionistForm;
import com.codegym.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@PropertySource("classpath:upload_file.properties")
public class ReceptionistController {

    @Autowired
    Environment env;

    @Autowired
    ReceptionistService receptionistService;

    @GetMapping("/receptionists")
    public ModelAndView showReceptionList(@RequestParam("search") Optional<String> search) {
        List<Receptionist> receptionists;
        if (search.isPresent()) {
            receptionists = receptionistService.findByName(search.get());
        } else {
            receptionists = receptionistService.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("/receptionist/list");
        modelAndView.addObject("receptionists", receptionists);
        return modelAndView;
    }

    @GetMapping("/create-receptionist")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/receptionist/create");
        modelAndView.addObject("receptionistForm", new ReceptionistForm());
        return modelAndView;
    }

    @PostMapping("/create-receptionist")
    public ModelAndView saveReceptionist(@ModelAttribute("receptionistForm") ReceptionistForm receptionistForm) {
        MultipartFile multipartFile = receptionistForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(receptionistForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Receptionist receptionist = new Receptionist(receptionistForm.getId(), receptionistForm.getName(),
                receptionistForm.getAge(), receptionistForm.getAddress(), receptionistForm.getHobby(), fileName);
        receptionistService.addElement(receptionist);
        ModelAndView modelAndView = new ModelAndView("/receptionist/create");
        modelAndView.addObject("receptionistForm", new ReceptionistForm());
        modelAndView.addObject("message", "Created new receptionist successfully");
        return modelAndView;
    }

    @GetMapping("/edit-receptionist/{id}")
    public ModelAndView showEditForm(@PathVariable int id) {
        Receptionist receptionist = receptionistService.findById(id);
        if (receptionist != null) {
            ReceptionistForm receptionistForm = new ReceptionistForm(receptionist.getId(), receptionist.getName(),
                    receptionist.getAge(), receptionist.getAddress(), receptionist.getHobby(), null);
            ModelAndView modelAndView = new ModelAndView("/receptionist/edit");
            modelAndView.addObject("receptionistForm", receptionistForm);
            modelAndView.addObject("receptionist", receptionist);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-receptionist")
    public ModelAndView updateReceptionist(@ModelAttribute("receptionist") ReceptionistForm receptionistForm) {
        MultipartFile multipartFile = receptionistForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            FileCopyUtils.copy(receptionistForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Receptionist receptionist = new Receptionist(receptionistForm.getId(), receptionistForm.getName(),
                receptionistForm.getAge(), receptionistForm.getAddress(), receptionistForm.getHobby(), fileName);
        receptionistService.updateElement(receptionist.getId(), receptionist);
        ModelAndView modelAndView = new ModelAndView("/receptionist/edit");
        modelAndView.addObject("receptionistForm", receptionistForm);
        modelAndView.addObject("message", "Updated new receptionist information successfully");
        return modelAndView;
    }
    @GetMapping("/delete-receptionist/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id) {
        Receptionist receptionist = receptionistService.findById(id);
        if (receptionist != null) {
            ReceptionistForm receptionistForm = new ReceptionistForm(receptionist.getId(), receptionist.getName(),
                    receptionist.getAge(), receptionist.getAddress(), receptionist.getHobby(), null);
            ModelAndView modelAndView = new ModelAndView("/receptionist/delete");
            modelAndView.addObject("receptionist", receptionist);
            modelAndView.addObject("receptionistForm", receptionistForm);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-receptionist")
    public String deleteReceptionist(@ModelAttribute("receptionistForm") Receptionist receptionist) {
        receptionistService.removeElement(receptionist.getId());
        return "redirect:receptionists";
    }

    @GetMapping("/view-receptionist/{id}")
    public ModelAndView viewReceptionist(@PathVariable int id) {
        Receptionist receptionist = receptionistService.findById(id);
        if (receptionist != null) {
            ReceptionistForm receptionistForm = new ReceptionistForm(receptionist.getId(), receptionist.getName(),
                    receptionist.getAge(), receptionist.getAddress(), receptionist.getHobby(), null);
            ModelAndView modelAndView = new ModelAndView("/receptionist/view");
            modelAndView.addObject("receptionistForm", receptionistForm);
            modelAndView.addObject("receptionist", receptionist);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

}