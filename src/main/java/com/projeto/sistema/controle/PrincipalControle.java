package com.projeto.sistema.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalControle {  // ← PascalCase

    @GetMapping("/administrativo")
    public String acessoPrincipal() {
        return "administrativo/home";
    }
}