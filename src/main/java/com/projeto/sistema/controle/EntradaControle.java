package com.projeto.sistema.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.sistema.modelos.ItemEntrada;
import com.projeto.sistema.modelos.Entrada;
import com.projeto.sistema.repositorios.EntradaRepositorio;
import com.projeto.sistema.repositorios.FornecedorRepositorio;
import com.projeto.sistema.repositorios.FuncionarioRepositorio;
import com.projeto.sistema.repositorios.ItemEntradaRepositorio;
import com.projeto.sistema.repositorios.ProdutoRepositorio;

@Controller
public class EntradaControle {
	@Autowired
	private EntradaRepositorio entradaRepositorio;
	@Autowired
	private ItemEntradaRepositorio itemEntradaRepositorio;
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	@Autowired
	private FornecedorRepositorio fornecedorRepositorio;
	
	private List<ItemEntrada> listaItemEntrada = new ArrayList<ItemEntrada>();
	
	@GetMapping("/cadastroEntrada")
	public ModelAndView cadastrar(Entrada entrada){
		ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
		mv.addObject("entrada", entrada);
		mv.addObject("listaItemEntrada", itemEntradaRepositorio.findAll());
		return mv;
	}
	@GetMapping("/listarEntrada")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/entrada/lista");
		mv.addObject("listaEntrada", entradaRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/editarEntrada/{id}")
	public ModelAndView editar(@PathVariable("id")Long id) {
		Optional<Entrada> entrada = entradaRepositorio.findById(id);
		return cadastrar(entrada.get());
	}	
	
	@GetMapping("/removerEntrada/{id}")
	public ModelAndView remover(@PathVariable("id")Long id) {
		Optional<Entrada> entrada = entradaRepositorio.findById(id);
		entradaRepositorio.delete(entrada.get());
		return listar();
	}	
	@PostMapping("/salvarEntrada")
	public ModelAndView salvar(Entrada entrada , BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(entrada);
		}
		entradaRepositorio.saveAndFlush(entrada);
		return cadastrar(new Entrada());
	}
}
