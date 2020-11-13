package com.softtek.web.app.models.dao;

import java.util.List;

import com.softtek.web.app.models.entity.Usuario;

public interface IUsuarioDao {
	public List<Usuario> findAll();
	public void save(Usuario usuario);

}
