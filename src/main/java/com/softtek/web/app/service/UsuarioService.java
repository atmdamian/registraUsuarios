package com.softtek.web.app.service;

import java.util.List;

import com.softtek.web.app.models.entity.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();

	public void save(Usuario usuario);

	public void delete(Integer idUsuario);
	
	public Usuario findOne(Integer idUsuario);
}
