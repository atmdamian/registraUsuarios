package com.softtek.web.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.softtek.web.app.response.ResponseMethod;
import com.softtek.web.app.service.UsuarioService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@RestController
@RequestMapping("api/reportes")
public class UsuarioController {
	
	@Autowired
	//evita la ambiguedad a l inyectar un bean anotando el nombre en @Repositori
	//@Qualifier("usuarioDaoJPA")
	private UsuarioService usuarioService;
	
	
	
	@GetMapping(value="/getUsuario")
    public @ResponseBody ResponseEntity<?> obtenerUsuarios() {
		
        try {            
            if(new Gson().toJson(usuarioService.findAll()) != null) {
                return new ResponseEntity<String>(new Gson().toJson(usuarioService.findAll()), HttpStatus.OK );
            }

        }catch(Exception e){
        	e.printStackTrace();
        }
		return new ResponseEntity<String>(new Gson().toJson(new ResponseMethod("NO ENCONTRADO","400")),HttpStatus.NOT_FOUND);


    }

}
