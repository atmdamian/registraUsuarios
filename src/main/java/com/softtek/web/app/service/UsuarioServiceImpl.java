package com.softtek.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.web.app.models.dao.UsuarioDao;
import com.softtek.web.app.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;

	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public void delete(Integer idUsuario) {
		usuarioDao.delete(idUsuario);
		
	}

	@Override
	public Usuario findOne(Integer idUsuario) {
		
		return usuarioDao.findOne(idUsuario);
	}

	

}
