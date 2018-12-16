package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // opcional
public class AutorDao {

	@PersistenceContext
	private EntityManager manager;

	@TransactionAttribute(TransactionAttributeType.MANDATORY) // precisa de um contexto transacional
	public void salva(Autor autor) {
		manager.persist(autor);
	}
	
	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.manager.find(Autor.class ,autorId);
		return autor;
	}
	
	@PostConstruct
	private void aposCriacao() {
		System.out.println("[INFO] AutorDao foi criado.");
	}
	
}
