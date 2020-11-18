package com.softtek.web.app.view.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.softtek.web.app.models.entity.Usuario;
import com.softtek.web.app.service.UsuarioServiceImpl;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class IndexController {
	private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@GetMapping("/index")
	public String viewIndex(Map<String, String> map) {
		LOGGER.info("SOLICITA VISTA PRINCIPAL");
		map.put("titulo", "App Softtek");
		return "index";
	}

	@PostMapping(value = "/postRegistrarUsuario", consumes = "application/json")
	public @ResponseBody String registraUsuario(@RequestBody Usuario usuario, Model model) {
		LocalDateTime tiempoActual = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(tiempoActual);
		LOGGER.info("REGISTRA USUARIO");
		usuario.setFechaDeRegistro(timestamp);
		usuarioService.save(usuario);

		return "/index";
	}

	@GetMapping(value = "/eliminarUsuario")
	public @ResponseBody void elimnarUsuario(@RequestParam Integer idUsuario, Model model) {
		LOGGER.info("ELIMINA USUARIO");
		usuarioService.delete(idUsuario);
	}

	@GetMapping(value = "exportarReporte")
	public void exportPDF(ModelAndView model, HttpServletResponse response) throws JRException, IOException {
		List<Usuario> listUsuarios = usuarioService.findAll();

		File file = ResourceUtils.getFile("classpath:UsuariosRegistrados.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listUsuarios);
		Map<String, Object> params = new HashMap<>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		LOGGER.info("SOLICITA REPORTE PDF");

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=ReportUsuarios.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

}
