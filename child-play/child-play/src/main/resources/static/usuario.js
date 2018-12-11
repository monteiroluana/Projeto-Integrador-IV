let listUsuarios = []

$(document).ready(function () {
    listarUsuarios();
});


//------Modal ver mais detalhes do usuario ---------

function abrirModal(idUsuario) {
    for (var i = 0; i <= listUsuarios.length; i++) {
        if (idUsuario == listUsuarios[i].idUsuario) {

            document.querySelector(".idUsuario").value = idUsuario;
            document.querySelector(".idUsuario").disabled = true;

            document.querySelector(".modal-title").innerHTML = "Editar" + listUsuarios[i].nome;

            document.querySelector(".nomeEditar").value = listUsuarios[i].nome;

            document.querySelector(".loginEditar").value = listUsuarios[i].login;
            document.querySelector(".loginEditar").disabled = true;

            document.querySelector(".funcaoEditar").value = listUsuarios[i].funcao;

            $('#detalhesUsuario').modal();

        }
    }

}

function atualizar() {
    let idUsuario = document.querySelector(".idUsuario").value;
    let nome = document.querySelector(".nome").value;
    let login = document.querySelector(".login").value;
    let senha = document.querySelector(".senha").value;
    let funcao = document.querySelector(".funcao").value;

    let obj = {
        "idUsuario": idUsuario,
        "nome": nome,
        "login": login,
        "senha": senha,
        "funcao": funcao
    }

    $.ajax({
        url: '/usuario/update',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (data) {
            swal("Success!", data.data, "success");
            $('#detalhesUsuario').modal('hide');
            listarUsuarios();
        }
    });
}

function excluir(idUsuario) {
    swal({
        title: "Tem certeza?",
        text: "O usuário (" + listUsuarios[idUsuario - 1].nome + ") será apagado!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                let obj = {
                    "idUsuario": idUsuario,
                }

                $.ajax({
                    url: '/usuario/enable',
                    type: 'post',
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (data) {
                        swal("Success!", data.data, "success");
                        listarUsuarios();
                    }
                });
            }
        });
}

function listarUsuarios() {
    document.querySelector(".bodyTableUsuario").innerHTML = '';

    $.ajax({
        url: '/usuario/list-usuario', method: 'GET', success: function (data) {
            listUsuarios = data.data;
            console.log(listUsuarios);
            for (var i = 0; i < listUsuarios.length; i++) {
                $('#bodyTableUsuario').append(
                    '    <tr>' +
                    '        <td>' +
                    '            <span>' + listUsuarios[i].idUsuario + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listUsuarios[i].nome + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listUsuarios[i].login + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '            <span>' + listUsuarios[i].funcao + '</span>' +
                    '        </td >' +
                    '        <td>' +
                    '           <button type="button" class="btn btn-outline-success" onclick="abrirModal(' + listUsuarios[i].idUsuario + ')" title="Editar ' + listUsuarios[i].nome + '">Detalhes</button>' +
                    '           <button type="button" class="btn btn-outline-danger" onclick="excluir(' + listUsuarios[i].idUsuario + ')" title="Excluir o usuário ' + listUsuarios[i].nome + '">Excluir</button>' +
                    '        </td>' +
                    '    </tr >'

                );
            }
        }
    });
}