package com.recodepro.conectatrabajoapi.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/") // <- Rota raiz
    public String home() {
        return "ConectaTrabajo API está rodando! 🚀";
    }
}