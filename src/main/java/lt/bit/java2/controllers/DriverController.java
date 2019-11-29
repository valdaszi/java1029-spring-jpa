package lt.bit.java2.controllers;

import lt.bit.java2.entities.Driver;
import lt.bit.java2.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mvc/driver")
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/{id}")
    String getDriver(@PathVariable int id, ModelMap model) {
        model.addAttribute("firstName", "Jonas");
        model.addAttribute("id", id);

        MyClass myClass = new MyClass();
        myClass.setA(123);
        myClass.setB("A456");
        model.addAttribute("my", myClass);

        return "driver";
    }

    @GetMapping("/list")
    ModelAndView getDrivers(
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "1", required = false) int page) {
        if (size < 3) size = 3;
        if (page < 1) page = 1;

        Page<Driver> driverPage = driverRepository.findAll(PageRequest.of(page - 1, size));

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("title", "Drivers");
        modelMap.put("time", LocalDateTime.now());
        modelMap.put("driverPage", driverPage);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("drivers");
        modelAndView.addAllObjects(modelMap);

        return modelAndView;
    }

    @GetMapping("/delete")
    RedirectView deleteById(
            @RequestParam int id,
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "1", required = false) int page,
            RedirectAttributes attributes) {
        driverRepository.deleteById(id);
        attributes.addAttribute("size", size);
        attributes.addAttribute("page", page);
        return new RedirectView("/mvc/driver/list");
    }
}

