package com.softtek.web.app.models.dao;

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
		if (usuario.getId() != null && usuario.getId() > 0) {
			em.merge(usuario);
		} else {
			em.persist(usuario);
		}

	}

	@Override
	@Transactional
	public void delete(Integer idUsuario) {
		Usuario usuarioEliminar = em.find(Usuario.class, idUsuario);
		em.remove(usuarioEliminar);

	}

	@Override
	public Usuario findOne(Integer idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}

}
