package com.softtek.web.app;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


import com.softtek.web.app.models.dao.UsuarioDao;
import com.softtek.web.app.models.entity.Usuario;
import com.softtek.web.app.service.UsuarioServiceImpl;

public class UsuarioServiceImplTest {

	@InjectMocks
	private UsuarioServiceImpl usuarioService;
	@Mock
	UsuarioDao usuarioDao;
	
	@BeforeEach
	void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOne() {
		Usuario usuario = new Usuario();
		usuario.setId(1);

		when(usuarioDao.findOne(anyInt())).thenReturn(usuario);
		Usuario usuarioTest = usuarioService.findOne(1);
		assertNotNull(usuarioTest);
	}

}
