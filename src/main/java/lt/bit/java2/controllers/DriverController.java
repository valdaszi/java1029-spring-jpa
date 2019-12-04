package lt.bit.java2.controllers;

import lt.bit.java2.ann.Auth;
import lt.bit.java2.entities.Account;
import lt.bit.java2.entities.Driver;
import lt.bit.java2.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mvc/driver")
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    @Auth("MANAGER")
    @GetMapping("/{id}")
    String getDriver(@PathVariable int id, ModelMap model) {
        model.addAttribute("driver", driverRepository.getOne(id));
        return "driver";
    }

    @GetMapping("/list")
    ModelAndView getDrivers(
            @RequestParam(defaultValue = "0", required = false) int size,
            @RequestParam(defaultValue = "0", required = false) int page,
            HttpServletRequest request, HttpServletResponse response) {

//        HttpSession session = request.getSession(false);
//        if (session == null || !(session.getAttribute("user") instanceof Account)) {
//            return new ModelAndView("redirect:/mvc/auth/login");
//        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (page <= 0 && cookie.getName().equals("page")) {
                    page = Integer.parseInt(cookie.getValue());
                }
                if (size <= 0 && cookie.getName().equals("size")) {
                    size = Integer.parseInt(cookie.getValue());
                }
            }
        }

        if (size < 1) size = 10;
        if (page < 1) page = 1;

        response.addCookie(new Cookie("size", String.valueOf(size)));
        response.addCookie(new Cookie("page", String.valueOf(page)));

        Page<Driver> driverPage = driverRepository.findAll(PageRequest.of(page - 1, size));

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("title", "Drivers");
        modelMap.put("time", LocalDateTime.now());
        modelMap.put("driverPage", driverPage);
//        modelMap.put("username", ((Account)session.getAttribute("user")).getName());


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("drivers");
        modelAndView.addAllObjects(modelMap);

        return modelAndView;
    }

    @Auth("MANAGER")
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

    @Auth({"BOSS", "MANAGER"})
    @GetMapping("/edit-form")
    String editForm(@RequestParam int id, ModelMap modelMap) {
        Driver driver = driverRepository.getOne(id);
        modelMap.addAttribute("driver", driver);
        return "edit-form";
    }

    @Auth({"BOSS", "MANAGER"})
    @PostMapping(value = "/edit-form",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    RedirectView saveEdit(Driver driver) {
        driverRepository.save(driver);
        return new RedirectView("/mvc/driver/list");
    }
}

