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
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String email;
	@Column(name = "fecha_registro")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime fechaDeRegistro;

	@Enumerated(EnumType.STRING)
	private Estatus estatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

}
