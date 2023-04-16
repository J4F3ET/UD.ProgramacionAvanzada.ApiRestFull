document.querySelector(".btnCerrarModal").addEventListener("click", () =>cerrarModal());
document.querySelector("#btnModificarTarea").addEventListener("click", () => {
	modificarModal()
	Swal.fire({
		position: "top-end",
		icon: "success",
		title: "Modifique los campos que desee",
		showConfirmButton: false,
		timer: 2000,
		timerProgressBar: true,
	})
});
document.querySelector("#btnActualizarTarea").addEventListener("click", () => {
	$.ajax({
		url: "http://localhost:8080/task",
		type: "PUT",
		contentType: "application/json",
		async: true,
		data: JSON.stringify({
            id: document.querySelector("#txt_id_m").value,
			nombre: document.querySelector("#txt_nombre_m").value,
			descripcion: document.querySelector("#txt_descripcion_m").value,
			estado: "Pendiente",
			fechaFinalizacion: document.querySelector("#txt_fecha_fin_m").value,
		}),
		success: () => {
			Swal.fire({
				position: "top-end",
				icon: "success",
				title: "Tarea guardada con éxito",
				showConfirmButton: false,
				timer: 3000,
				timerProgressBar: true,
				didOpen: (toast) => {
					toast.addEventListener("mouseenter", Swal.stopTimer);
					toast.addEventListener("mouseleave", Swal.resumeTimer);
				},
			}).then(() => location.reload());
		},
		error: () => {
			Swal.fire({
				icon: "error",
				title: "Oops...",
				text: "Error al guardar la tarea",
			});
		},
	});
});
function cerrarModal(){
	document.querySelector("#txt_nombre_m").disabled = true;
	document.querySelector("#txt_descripcion_m").disabled = true;
	document.querySelector("#txt_fecha_fin_m").disabled = true;
	document.querySelector("#txt_fecha_reg").disabled = true;

	document.querySelector("#btnActualizarTarea").disabled = true;
	document.querySelector("#btnActualizarTarea").hidden = true;

	document.querySelector("#btnModificarTarea").classList.remove("collapsed");
	document.querySelector("#btnModificarTarea").hidden = false;
	document.querySelector("#btnModificarTarea").disabled = false;
};
function modificarModal(){
	document.querySelector("#txt_nombre_m").disabled = false;
	document.querySelector("#txt_descripcion_m").disabled = false;
	document.querySelector("#txt_fecha_fin_m").disabled = false;

	document.querySelector("#btnActualizarTarea").disabled = false;
	document.querySelector("#btnActualizarTarea").hidden = false;

	document.querySelector("#btnModificarTarea").classList.add("collapsed");
	document.querySelector("#btnModificarTarea").hidden = true;
	document.querySelector("#btnModificarTarea").disabled = true;
};
