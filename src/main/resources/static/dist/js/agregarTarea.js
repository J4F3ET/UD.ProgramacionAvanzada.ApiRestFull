document.querySelector('#btnAgregarTarea').addEventListener('click', ()=> {
    var tarea = {
        nombre: document.querySelector('#txt_nombre').value,
        descripcion: document.querySelector('#txt_descripcion').value,
        estado: 'Pendiente',
        fechaFinalizacion: document.querySelector('#txt_fecha_fin').value,
        fechaCreacion: new Date(),
    };

    var url = 'http://localhost:8080/task';
    var settings = {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(tarea)
    };

    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        async: true,
        data: JSON.stringify(tarea),
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    })
})