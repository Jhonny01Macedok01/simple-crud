package br.com.springboot.simple_crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.simple_crud.model.Usuario;
import br.com.springboot.simple_crud.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso spring boot API: " + name + "!";
    }
    
    @RequestMapping(value = "/Olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario);
    	
    	return "Olá mundo " + nome;
    }
    
    @GetMapping(value = "listatodos")
    @ResponseBody /*Retorna os dados para o corpo da reposta*/
    public ResponseEntity<List<Usuario>> listaUsuario() {
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); /*Executa consulta no DB*/
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*Conulta o DB e rotorna a lista em JASON*/
    }	
    
    @PostMapping(value = "salvar") /*mapeia a url*/
    @ResponseBody /*vai fazer a descrição da Resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ /*Recebe os dados para salvar*/
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	 
    }
    
    @PutMapping(value = "atualizar") /*mapeia a url*/
    @ResponseBody /*vai fazer a descrição da Resposta*/
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){ /*Recebe os dados para salvar*/
    	
    	if (usuario.getId() == null) {	
        	return new ResponseEntity<String>("Id do usuário não foi informado, para atualização!", HttpStatus.CONFLICT);

    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario); /*salva e já roda diretamente no BD sem precisar finalizar pra dar o commit*/
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	 
    }
    
    @DeleteMapping(value = "delete") /*mapeia a url*/
    @ResponseBody /*vai fazer a descrição da Resposta*/
    public ResponseEntity<String> delete(@RequestParam Long iduser){ /*Recebe os dados para salvar*/
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Usuário deletado com sucesso!", HttpStatus.OK);
    	 
    }
    
    @GetMapping(value = "buscaruserid") /*mapeia a url*/
    @ResponseBody /*vai fazer a descrição da Resposta*/
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){ /*Recebe os dados para salvar*/
    	Usuario  usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	 
    }
    
    @GetMapping(value = "buscarPorNome") /*mapeia a url*/
    @ResponseBody /*vai fazer a descrição da Resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){ /*Recebe os dados para salvar*/
    	List<Usuario>  usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.FOUND);
    	 
    }
    
}


 