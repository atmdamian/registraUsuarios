$(document).ready(function() {
	TablaUsuarios();
});
function registroUsuario() {
		var nombre;
		var email;
		var estatus;
		var datos = {
			nombre : document.getElementById("nombre").value,
			email : document.getElementById("email").value,
			estatus : document.getElementById("estatus").value,
		};
		$.ajax({
			type : "POST",
			contentType : "application/json",
			//dataType : 'json',
			url : "postRegistrarUsuario",
			data : JSON.stringify(datos),
			success : function(response) {
				TablaUsuarios();
				$(".input").val("");
			},
			error : function() {

			},
			async : true,
			cache : false
		});

	}
	function TablaUsuarios() {
		console.log("Actualiza tabla");
		$
				.ajax({
					url : "/api/reportes/getUsuario",
					data : {},
					success : function(data) {
						console.log(data);
						var dataJSON = data;
						dataJSON = JSON.parse(dataJSON);
						var tablaUsuario = '';
						if (jQuery.isEmptyObject(dataJSON)) {
							$("#tableUsuarios tbody").html('');
						} else {
							for (var i = 0; i < dataJSON.length; i++) {
								var idTrNombre = "nombre" + dataJSON[i].id;
								var idTrEmail = "email" + dataJSON[i].id;
								var idTrEstatus = "estatus" + dataJSON[i].id;
								var modificaBtn = "botonModificar"
										+ dataJSON[i].id;
								var modificaTd = "tdModificar" + dataJSON[i].id;
								var eliminaBtn = "botonEliminar"
										+ dataJSON[i].id;
								tablaUsuario += '<tr>' + '<td>'
										+ dataJSON[i].id
										+ '</td>'
										+ '<td id='+ idTrNombre +'>'
										+ dataJSON[i].nombre
										+ '</td>'
										+ '<td id='+ idTrEmail +'>'
										+ dataJSON[i].email
										+ '</td>'
										+ '<td id='+ idTrEstatus +'>'
										+ dataJSON[i].estatus
										+ '</td>'
										+ '<td>'
										+ dataJSON[i].fechaDeRegistro
										+ '</td>'
										+ '<td id='+modificaTd+'>'
										+ '<button id="'
										+ modificaBtn
										+ '" class="btn btn-primary" onclick="modificarUsuario(this)" data-value="'
										+ dataJSON[i].id
										+ '">  Editar  </button>'
										+ '</td>'
										+ '<td>'
										+ '<button id="'
										+ eliminaBtn
										+ '" class="btn btn-danger" onclick="eliminarUsuario(this)" data-value="'
										+ dataJSON[i].id
										+ '"> Eliminar </button>' + '</td>'

										+ '</tr>';
								$("#tableUsuarios tbody").html(tablaUsuario);
							}
						}
					},
					error : function() {

					},
					async : true,
					cache : false
				});
	}

	function modificarUsuario(id) {
		var idUsuario = $(id).data('value');
		$
				.ajax({
					url : "/api/reportes/getUsuario",
					data : {},
					success : function(data) {
						var dataJSON = data;
						dataJSON = JSON.parse(dataJSON);
						var tablaUsuarioTr = '';
						if (jQuery.isEmptyObject(dataJSON)) {
							$("#tableUsuarios tbody").html('');
						} else {
							for (var i = 0; i < dataJSON.length; i++) {
								var indice = dataJSON[i].id;
								if (indice == idUsuario) {

									$("#nombre" + idUsuario)
											.html(
													'<input type="text" name="innombre'+idUsuario+'" id="innombre'+idUsuario+'" value="'+dataJSON[i].nombre+'">');
									$("#email" + idUsuario)
											.html(
													'<input type="email" name="inemail'+idUsuario+'" id="inemail'+idUsuario+'" value="'+dataJSON[i].email+'">');
							
									$("#estatus" + idUsuario)
											.html(
													
													'<select name="inestatus'+idUsuario+'" id="inestatus'+idUsuario+'">'
													+'<option selected="true" disabled="disabled" value="'+dataJSON[i].estatus+'">'+dataJSON[i].estatus+'</option>'
													+'<option id="estatusACTIVO" value="ACTIVO">ACTIVO</option>'
													+'<option id="estatusINACTIVO" value="INACTIVO">INACTIVO</option>'
													+'<option id="estatusSUSPENDIDO" value="SUSPENDIDO">SUSPENDIDO</option>'
												    +'</select>'
											
											
											
											);
									
									

									$("#tdModificar" + idUsuario)
											.html(
													'<button class="btn btn-success" onclick="validarInputsGuardar(this);" data-value="'
															+ dataJSON[i].id
															+ '">  Guardar </button>');
									$('#botonEliminar' + idUsuario).attr(
											"disabled", true);

								} else {
									$('#botonModificar' + indice).attr(
											"disabled", true);
									$('#botonEliminar' + indice).attr(
											"disabled", true);
								}
								$('#inestatus'+idUsuario+' option[id="estatus'+dataJSON[i].estatus+'"]').remove();
							}
						}
					},
					error : function() {

					},
					async : true,
					cache : false
				});

	}
	function eliminarUsuario(id) {
		var idUsuario = $(id).data('value');
		$.ajax({
			type : "GET",
			url : "eliminarUsuario",
			data : {
				idUsuario : idUsuario
			},
			success : function(data) {
				TablaUsuarios();
			},
			error : function() {

			},
			async : false,
			cache : false
		});

	}

	function actualizaUsuario(id) {
		var idUsuario = id;
		var id;
		var nombre;
		var email;
		var estatus;
		var idNombre = "innombre" + idUsuario;
		var idEmail = "inemail" + idUsuario;
		var idEstatus = "inestatus" + idUsuario;

		var datos = {
			id : idUsuario,
			nombre : document.getElementById(idNombre).value,
			email : document.getElementById(idEmail).value,
			estatus : document.getElementById(idEstatus).value
		};
		$.ajax({
			type : "POST",
			contentType : "application/json",
			//dataType : 'json',
			url : "postRegistrarUsuario",
			data : JSON.stringify(datos),
			success : function(response) {
				TablaUsuarios();
				$(".input").val("");
			},
			error : function() {

			},
			async : true,
			cache : false
		});

	}
