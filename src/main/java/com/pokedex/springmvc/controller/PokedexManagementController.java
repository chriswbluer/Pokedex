package com.pokedex.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PokedexManagementController {
    
    @RequestMapping(value = "/pokedexmanagement", method = RequestMethod.GET)
    public String getPokedexManagementPage() {
        return "PokedexManagement";
    }
}
