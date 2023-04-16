const tareas = [];
var btnModals = document.querySelectorAll("[data-task-id-modal]");
$.ajax({
	url: "http://localhost:8080/task",
	type: "GET",
	contentType: "application/json",
	async: true,
	success: (response) => {
		tareas.push(...response);
		agregarTareas(tareas);
		eliminarTarea();
		cambiarEstadoTarea();
	},
	error: () => {
		alert("Error al guardar la tarea");
	},
});
document.querySelector("#btnFiltrarHoy").addEventListener("click", () => {
	filtrarTareas();
});
function agregarTareas(tareas) {
	tareas.forEach((tarea) => {
		document.querySelector("#tablaTareas").appendChild(crearTarea(tarea));
	});
	document.querySelectorAll(".btnModificarTarea").forEach((btn) => {
		btn.addEventListener("click", () => {
			cerrarModal();
			var id = btn.getAttribute("data-task-id-modal");
			var tarea = tareas.find((tarea) => tarea.id == id);
			dataToModal(tarea);
		});
	});
}
function crearTarea(tarea) {
	var tupla = document.createElement("tr");
	tupla.classList.add("tuplaTarea");

	var celda1 = document.createElement("td");
	celda1.innerHTML = tarea.id;

	var celda2 = document.createElement("td");
	var buttonC2 = document.createElement("button");
	buttonC2.innerHTML = tarea.nombre;
	buttonC2.setAttribute("data-toggle", "modal");
	buttonC2.setAttribute("data-target", "#modalTarea");
	buttonC2.setAttribute("data-task-id-modal", tarea.id);
	buttonC2.classList.add("btnModificarTarea", "btn", "btn-outline-light");

	celda2.appendChild(buttonC2);

	var celda3 = document.createElement("td");
	var spanC3 = document.createElement("span");
	spanC3.innerHTML = tarea.descripcion;
	celda3.appendChild(spanC3);

	var celda4 = document.createElement("td");
	var spanC4 = document.createElement("span");
	fechaCreacion = new Date(tarea.fechaCreacion);
	fechaFinalizacion = new Date(tarea.fechaFinalizacion);
	var dias = Math.round(
		(fechaFinalizacion - fechaCreacion) / (1000 * 60 * 60 * 24)
	);
	dias <= 0
		? spanC4.setAttribute("class", "badge bg-success")
		: spanC4.setAttribute("class", "badge bg-warning");
	spanC4.innerHTML = dias + " Días";
	celda4.appendChild(spanC4);

	var celda5 = document.createElement("td");
	var spanC5 = document.createElement("span");
	spanC5.classList.add("spanFechaFinalizacion");
	spanC5.innerHTML = tarea.fechaFinalizacion;
	celda5.appendChild(spanC5);

	var celda6 = document.createElement("td");
	var buttonC6 = document.createElement("button");
	buttonC6.setAttribute("data-task-id", tarea.id);
	buttonC6.classList.add("btnEstadoTarea");
	buttonC6.classList.add("btn");
	dias <= 0
		? buttonC6.classList.add("bg-olive")
		: buttonC6.classList.add("btn-outline-warning");

	if (tarea.estado == "Pendiente" && dias <= 0) {
		buttonC6.classList.remove("bg-olive");
		buttonC6.classList.add("btn-danger");
	}
	buttonC6.innerHTML = tarea.estado;
	celda6.appendChild(buttonC6);

	var celda7 = document.createElement("td");
	celda7.innerHTML = `<button data-task-id="${tarea.id}" class="btnEliminarTarea btn btn-outline-danger"><i class="fa fa-times"></i></button>`;
	tupla.appendChild(celda1);
	tupla.appendChild(celda2);
	tupla.appendChild(celda3);
	tupla.appendChild(celda4);
	tupla.appendChild(celda5);
	tupla.appendChild(celda6);
	tupla.appendChild(celda7);
	return tupla;
}
function dataToModal(tarea) {
	document.querySelector("#txt_id_m").value = tarea.id;
	document.querySelector("#txt_nombre_m").value = tarea.nombre;
	document.querySelector("#txt_descripcion_m").value = tarea.descripcion;
	let fechaFinalizacion = new Date(tarea.fechaFinalizacion);
	let fechaFinalizacionISO = fechaFinalizacion.toISOString().substring(0, 10);
	document.querySelector("#txt_fecha_fin_m").value = fechaFinalizacionISO;
	let fechaCreacion = new Date(tarea.fechaCreacion);
	let fechaCreacionISO = fechaCreacion.toISOString().substring(0, 10);
	document.querySelector("#txt_fecha_reg").value = fechaCreacionISO;
	document
		.querySelector("#btnActualizarTarea")
		.setAttribute("data-btn-id", tarea.id);
}
function cerrarModal() {
	document.querySelector("#txt_nombre_m").disabled = true;
	document.querySelector("#txt_descripcion_m").disabled = true;
	document.querySelector("#txt_fecha_fin_m").disabled = true;
	document.querySelector("#txt_fecha_reg").disabled = true;

	document.querySelector("#btnActualizarTarea").disabled = true;
	document.querySelector("#btnActualizarTarea").hidden = true;

	document.querySelector("#btnModificarTarea").classList.remove("collapsed");
	document.querySelector("#btnModificarTarea").hidden = false;
	document.querySelector("#btnModificarTarea").disabled = false;
}
function eliminarTarea() {
	document.querySelectorAll(".btnEliminarTarea").forEach((btn) => {
		btn.addEventListener("click", () => {
			id = btn.getAttribute("data-task-id");
			$.ajax({
				url: "http://localhost:8080/task/" + id,
				type: "DELETE",
				contentType: "application/json",
				async: true,
				success: (response) => {
					response
						? notificacion("success", "Tarea eliminada con éxito")
						: notificacion("error", "Error al eliminar la tarea");
				},
				error: () => {
					alert("Error al eliminar la tarea");
				},
			});
		});
	});
}
function notificacion(icon, title) {
	Swal.fire({
		position: "top-end",
		icon,
		title,
		showConfirmButton: false,
		timer: 3000,
		timerProgressBar: true,
		didOpen: (toast) => {
			toast.addEventListener("mouseenter", Swal.stopTimer);
			toast.addEventListener("mouseleave", Swal.resumeTimer);
		},
	}).then(() => {
		location.reload();
	});
}
function cambiarEstadoTarea() {
	document.querySelectorAll(".btnEstadoTarea").forEach((btn) => {
		btn.addEventListener("click", () => {
			id = btn.getAttribute("data-task-id");
			btn.innerHTML == "Pendiente"
				? (estado = "Finalizado")
				: (estado = "Pendiente");
			$.ajax({
				url: "http://localhost:8080/task/" + id,
				type: "PATCH",
				contentType: "application/json",
				async: true,
				data: JSON.stringify({estado}),
				success: (response) => {
					response
						? notificacion("success", "Tarea actualizada con éxito")
						: notificacion("error", "Error al actualizar la tarea");
				},
				error: () => {
					alert("Error al actualizar la tarea");
				},
			});
		});
	});
}
function borrarTabla(){
	var table = document.querySelector("#tablaTareas");
	var rowCount = table.rows.length;
	for (var x = rowCount - 1; x > 0; x--) {
		table.deleteRow(x);
	}
}
function filtrarTareas(){
	const fechaHoy = new Date().toISOString().split('T')[0];
	var tuplas = document.querySelectorAll(".tuplaTarea");
	tuplas.forEach((tupla) => {
		tupla.querySelector(".spanFechaFinalizacion").innerHTML == fechaHoy? tupla.hidden = false : tupla.hidden = true;
	});
};
