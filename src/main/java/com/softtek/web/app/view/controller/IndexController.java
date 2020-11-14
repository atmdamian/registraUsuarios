package com.softtek.web.app.view.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.softtek.web.app.models.entity.Usuario;
import com.softtek.web.app.service.UsuarioServiceImpl;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class IndexController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@GetMapping("/index")
	public String viewIndex(Map<String, String> map) {
		map.put("titulo", "App Softtek");
		return "index";
	}

	@PostMapping(value = "/postRegistrarUsuario", consumes = "application/json")
	public @ResponseBody String registraUsuario(@RequestBody @Valid Usuario usuario, BindingResult resultado,
			Model model) {
		LocalDateTime tiempoActual = LocalDateTime.now();
		if (resultado.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			resultado.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El camp".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "/index";
		}
		usuario.setFechaDeRegistro(tiempoActual);
		usuarioService.save(usuario);

		return "/index";
	}
	
	@GetMapping(value = "/eliminarUsuario")
	public @ResponseBody void elimnarUsuario(@RequestParam Integer idUsuario,Model model) {
		usuarioService.delete(idUsuario);
	}
	
	
	
	@GetMapping(value = "exportarReporte")
	public void exportPDF(ModelAndView model, HttpServletResponse response) throws JRException, IOException {

		InputStream jasperStream = this.getClass().getResourceAsStream("/ReportWs.jasper");
		Map<String, Object> params = new HashMap<>();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=ReportUsuarios.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

}
