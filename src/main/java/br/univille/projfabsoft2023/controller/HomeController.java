package br.univille.projfabsoft2023.controller;

import java.util.HashMap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ModelAndView index(){
        /*HashMap<String, String> map = new HashMap<>();
        map.put("apelido","valor");
        map.put("nome","zezinho");*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return new ModelAndView("home/index", "currentuser",currentPrincipalName);
    }
}
