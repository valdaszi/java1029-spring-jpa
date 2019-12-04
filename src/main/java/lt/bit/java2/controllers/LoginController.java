package lt.bit.java2.controllers;

import lt.bit.java2.entities.Account;
import lt.bit.java2.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mvc/auth")
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    RedirectView loginAction(@RequestParam String username,
                             @RequestParam String password,
                             HttpServletRequest httpRequest) {
        Account account = accountRepository.findByEmail(username);
        if (account == null || !account.getPassword().equals(password)) {
            return new RedirectView("/mvc/auth/login");
        }
        // sukuriam sesija
        HttpSession session = httpRequest.getSession();
        session.setAttribute("user", account);
        return new RedirectView("/mvc/driver/list");
    }
}
