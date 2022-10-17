package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    /*Nosso primeiro metodo de API*/
    @GetMapping(value = "listatodos")
    @ResponseBody /*Retorna os dados para o corpo da Resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios =  usuarioRepository.findAll();
    
    	/*Retorna a lista em JSON*/
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PostMapping(value = "salvar")/*mapeia a url*/
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){/*Recebe os dados para salvar*/
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "delete")/*mapeia a url*/
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long iduser){/*Recebe os dados para salvar*/
    	
    	usuarioRepository.deleteById(iduser);;
    	
    	return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    }
    
}
