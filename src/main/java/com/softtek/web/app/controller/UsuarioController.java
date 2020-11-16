package com.softtek.web.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.softtek.web.app.response.ResponseMethod;
import com.softtek.web.app.service.UsuarioService;
import com.softtek.web.app.view.controller.IndexController;

@RestController
@RequestMapping("api/reportes")
public class UsuarioController {
	private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/getUsuario")
	public @ResponseBody ResponseEntity<?> obtenerUsuarios() {

		try {
			LOGGER.info("OBTIENE USUARIO");
			if (new Gson().toJson(usuarioService.findAll()) != null) {
				return new ResponseEntity<String>(new Gson().toJson(usuarioService.findAll()), HttpStatus.OK);
			}

		} catch (Exception e) {
			LOGGER.error("ERROR AL OBTENER USUARIO");
			e.printStackTrace();
		}
		return new ResponseEntity<String>(new Gson().toJson(new ResponseMethod("NO ENCONTRADO", "400")),
				HttpStatus.NOT_FOUND);

	}

}
