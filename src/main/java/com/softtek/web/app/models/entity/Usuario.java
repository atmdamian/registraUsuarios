package com.softtek.web.app.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String email;
	/*@Temporal NO ES COMPATIBLE CON LOCALDATETIME
	 * solo se puede aplicar a atributos de tipo java.util.Date y java.util.Calendar .
	@Temporal(TemporalType.TIMESTAMP)*/
	@Column(name="fecha_registro")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime fechaDeRegistro;
	
	@Enumerated(EnumType.STRING)
	private Estatus estatus;

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public LocalDateTime getFechaDeRegistro() {
		return fechaDeRegistro;
	}



	public void setFechaDeRegistro(LocalDateTime fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}



	public Estatus getEstatus() {
		return estatus;
	}



	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
