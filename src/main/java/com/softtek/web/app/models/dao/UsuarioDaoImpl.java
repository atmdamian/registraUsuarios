package com.softtek.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softtek.web.app.models.entity.Usuario;
/*marca la clase como un bean de acceso a datos, 
traduce con detalle las exceptiones*/
@Repository
public class UsuarioDaoImpl implements IUsuarioDao {
	//maneja las clases entity, ciclo de vida, persiste, actualiza,elimina
	//todas las operaciones a la base pero a nivel objeto entity.
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	/*marca el metodo transaccional de lectura. 
	toma el contenido del metodo iy lo envuelve dentro de una transaccion*/
	@Transactional(readOnly=true)
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Usuario").getResultList();
	}
	/*
	 * Toma el objeto usuario y lo guarda dentro del contxto de persistencia JPA
	 * una ves que se realiza en commit y flush sincroniza con la vase de datos y reliza el insetrt auotmaticamente
	 * */
	@Override
	//se marca el metodo de tipo escritutra
	@Transactional
	public void save(Usuario usuario) {
		em.persist(usuario);
		
	}

}
