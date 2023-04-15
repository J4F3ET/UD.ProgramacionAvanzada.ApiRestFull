document.querySelector('#btnAgregarTarea').addEventListener('click', ()=> {
    $.ajax({
			url: "http://localhost:8080/task",
			type: "POST",
			contentType: "application/json",
			async: true,
			data: JSON.stringify({
				nombre: document.querySelector("#txt_nombre").value,
				descripcion: document.querySelector("#txt_descripcion").value,
				estado: "Pendiente",
				fechaFinalizacion: document.querySelector("#txt_fecha_fin").value,
				fechaCreacion: new Date(),
			}),
			success: ()=> {
				Swal.fire({
					icon: "success",
					title: "Tarea guardada con éxito",
					position: "top-end",
					showConfirmButton: false,
					timer: 3000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener("mouseenter", Swal.stopTimer);
						toast.addEventListener("mouseleave", Swal.resumeTimer);
					},
				}).then(() => location.reload());
			},
			error:()=> {
				Swal.fire({
					icon: "error",
					title: "Oops...",
					text: "Error al guardar la tarea",
				});
			},
		});
})