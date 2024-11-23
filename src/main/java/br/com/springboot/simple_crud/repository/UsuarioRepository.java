package br.com.springboot.simple_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.simple_crud.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	
	/*upper(trim(u.nome)) retira a restrição de inserção de espaços e ignora a exigencia de colocar letras maisculas no
	 *  campo busca por nome sendo possivel inserir espaços e funcionar a busca*/
	
	List<Usuario> buscarPorNome(String nome);

}
