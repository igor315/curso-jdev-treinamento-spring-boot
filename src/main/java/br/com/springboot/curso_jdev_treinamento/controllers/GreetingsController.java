package br.com.springboot.curso_jdev_treinamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;


@RestController
public class GreetingsController {
    
	@Autowired /*Injecao de dependencia*/
	private UsuarioRepository usuarioRepository;
	
	
    @RequestMapping(value = "/mostrarnome/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String nome) {
        return "Curso Spring Boot API: " + nome + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornoOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	/*grava no banco de dados*/
    	usuarioRepository.save(usuario);
    	
    	return "Olá Mundo com Spring Boot " + nome + " :)";
    }
    
    
}
