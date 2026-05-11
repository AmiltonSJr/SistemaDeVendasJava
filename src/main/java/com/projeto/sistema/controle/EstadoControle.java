package com.projeto.sistema.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema.modelos.Estado;
import com.projeto.sistema.repositorios.EstadoRepositorio;

@Controller
public class EstadoControle {
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	@GetMapping("cadastroEstado")
	public ModelAndView cadastrar(Estado estado){
		ModelAndView mv = new ModelAndView("administrativo/estado/cadastro");
		mv.addObject("estado", estado);
		return mv;	
	}
}
