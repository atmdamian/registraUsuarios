	function validarInputs() {
		if ($("#nombre").val() == '') {
			$("#nombre").css("background-color", "pink");
			$("#mi-modal").modal('show');
			$("#modal-btn-no").hide();
			$("#msjModal").text('Ingrese un Nombre');
			$("#si").text('Aceptar');

			$("#modal-btn-si").on("click", function() {
				$("#mi-modal").modal('hide');
			});
			return false;
		}
		if ($("#email").val().indexOf('@', 0) == -1
				|| $("#email").val().indexOf('.', 0) == -1) {
			$("#email").css("background-color", "pink");
			$("#nombre").css("background-color", "");
			$("#mi-modal").modal('show');
			$("#modal-btn-no").hide();
			$("#msjModal").text('Ingrese un Correo valido');
			$("#si").text('Aceptar');
			$("#modal-btn-si").on("click", function() {
				$("#mi-modal").modal('hide');
			});
			return false;
		}
		if ($("#estatus option:selected").val() == '0') {
			$("#estatus").css("background-color", "pink");
			$("#email").css("background-color", "");
			$("#mi-modal").modal('show');
			$("#modal-btn-no").hide();
			$("#msjModal").text('Ingrese un Estatus');
			$("#si").text('Aceptar');
			$("#modal-btn-si").on("click", function() {
				$("#mi-modal").modal('hide');
			});
			return false;
		}
		$("#nombre").css("background-color", "");
		$("#email").css("background-color", "");
		$("#estatus").css("background-color", "");
		registroUsuario();

	}
	
	
	
	function confirmarEliminar(id) {
		var idUsuario = $(id).data('value');
		console.log("Datos eliminados", idUsuario)
		TablaUsuarios();

		var modalConfirm = function(callback) {
			$("#mi-modal").modal('show');
			$("#msjModal").text('¿Desea Eliminar el Usuario? ');
			$("#modal-btn-no").show();
			$("#si").text('SI');
			$("#no").text('NO');

			$("#modal-btn-si").on("click", function() {
				var idsEliminar = new Array();
				idsEliminar.push(idUsuario);
				console.log("usuario eliminar", idsEliminar);
				callback(true);
				$("#mi-modal").modal('hide');
			});

			$("#modal-btn-no").on("click", function() {
				console.log("no callback")
				callback(false);
				$("#mi-modal").modal('hide');
			});
		};

		modalConfirm(function(confirm) {
			if (confirm) {
				eliminarUsuario(idsEliminar);
			}
		});

	}
	
	function validarInputsGuardar(id) {
		var idUsuario = $(id).data('value');
		var idNombre = "#innombre" + idUsuario;
		var idEmail = "#inemail" + idUsuario;
		var idEstatus = "#inestatus" + idUsuario;
		if ($(idNombre).val() == '') {
			$(idNombre).css("background-color", "pink");
			$("#mi-modal").modal('show');
			$("#modal-btn-no").hide();
			$("#msjModal").text('Ingrese un Nombre a Guardar');
			$("#si").text('Aceptar');

			$("#modal-btn-si").on("click", function() {
				$("#mi-modal").modal('hide');
			});
			return false;
		}
		if ($(idEmail).val().indexOf('@', 0) == -1
				|| $(idEmail).val().indexOf('.', 0) == -1) {
			$(idEmail).css("background-color", "pink");
			$(idNombre).css("background-color", "");
			$("#mi-modal").modal('show');
			$("#modal-btn-no").hide();
			$("#msjModal").text('Ingrese  un Correo valido a Guardar ');
			$("#si").text('Aceptar');
			$("#modal-btn-si").on("click", function() {
				$("#mi-modal").modal('hide');
			});
			return false;
		}

		
		$(idNombre).css("background-color", "");
		$(idEmail).css("background-color", "");
		$(idEstatus).css("background-color", "");
		actualizaUsuario(idUsuario);

	}