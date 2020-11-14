package com.softtek.web.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.web.app.models.entity.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() {
		return em.createQuery("from Usuario AS us ORDER BY us.fechaDeRegistro DESC").getResultList();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		//try {
		   //em.getTransaction().begin();
		      em.persist(usuario);
		   //em.getTransaction().commit();
		  //}
		  //finally {
		    //if (em.getTransaction().isActive())
		    //em.getTransaction().rollback();
		 // }
		
	}

	@Override
	@Transactional
	public void update(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		}finally {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
		
	}

	@Override
	@Transactional
	public void delete(Integer idUsuario) {
		//try {
			Usuario usuarioEliminar = em.find(Usuario.class, idUsuario);
			//em.getTransaction().begin();
			em.remove(usuarioEliminar);
			//em.getTransaction().commit();
			
		//}finally {
			//em.getTransaction().isActive();
			//em.getTransaction().rollback();
		//}
	}

	@Override
	public Usuario findOne(Integer idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}

}
