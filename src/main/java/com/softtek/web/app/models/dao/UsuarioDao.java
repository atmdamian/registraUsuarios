package com.softtek.web.app.models.dao;

import java.util.List;

import com.softtek.web.app.models.entity.Usuario;

public interface UsuarioDao {
	public List<Usuario> findAll();

	public void save(Usuario usuario);

	public void update(Usuario usuario);

	public void delete(Integer idUsuario);
	
	public Usuario findOne(Integer idUsuario);

}
