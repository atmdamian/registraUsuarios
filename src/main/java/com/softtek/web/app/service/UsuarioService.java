package com.softtek.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.softtek.web.app.models.dao.IUsuarioDao;
import com.softtek.web.app.models.entity.Usuario;

@Service
public class UsuarioService {

@Autowired
IUsuarioDao usuarioDao;

public List<Usuario> findAll(){
	return usuarioDao.findAll();
}

public void save(Usuario usuario) {
	usuarioDao.save(usuario);
}
}
