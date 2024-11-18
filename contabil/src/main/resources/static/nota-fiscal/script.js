// Função para formatar datas no formato "dd/MM/yyyy"
function formatarData(dataISO) {
    const data = new Date(dataISO);
    const dia = String(data.getDate()).padStart(2, '0');
    const mes = String(data.getMonth() + 1).padStart(2, '0'); // Janeiro é 0
    const ano = data.getFullYear();
    return `${dia}/${mes}/${ano}`;
}

// Função para carregar as notas fiscais na tabela
function carregarNotas() {
    fetch('/nota-fiscal/listar')
        .then(response => response.json())
        .then(notas => {
            const tabelaBody = document.querySelector("#tabela-notas tbody");
            tabelaBody.innerHTML = ""; // Limpa a tabela antes de carregar os dados
            notas.forEach(nota => {
                const linha = document.createElement("tr");
                linha.innerHTML = `
                    <td>${nota.codNota}</td>
                    <td>${formatarData(nota.dataDeEmissao)}</td>
                    <td>${nota.valorTotal}</td>
                    <td>${nota.descricao}</td>
                    <td>${nota.fkEmpresaClienteCnpj}</td>
                    <td>
                        <button class="edit" onclick="editarNota('${nota.codNota}')">Editar</button>
                        <button class="delete" onclick="deletarNota('${nota.codNota}')">Deletar</button>
                    </td>
                `;
                tabelaBody.appendChild(linha);
            });
        })
        .catch(error => alert("Erro ao carregar notas fiscais."));
}

// Função para inserir uma nova nota fiscal
document.querySelector("#nota-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const notaFiscal = {
        codNota: document.querySelector("#codNota").value,
        dataDeEmissao: document.querySelector("#dataDeEmissao").value,
        valorTotal: parseFloat(document.querySelector("#valorTotal").value),
        descricao: document.querySelector("#descricao").value,
        fkEmpresaClienteCnpj: document.querySelector("#fkEmpresaClienteCnpj").value,
    };

    fetch('/nota-fiscal/inserir', {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(notaFiscal),
    })
        .then(response => {
            if (response.ok) {
                alert("Nota fiscal inserida com sucesso!");
                carregarNotas(); // Recarrega a tabela
                document.querySelector("#nota-form").reset(); // Limpa o formulário
            } else {
                alert("Erro ao inserir nota fiscal.");
            }
        })
        .catch(error => alert("Erro ao inserir nota fiscal."));
});

// Função para editar uma nota fiscal (abrir modal com os dados)
function editarNota(codNota) {
    fetch(`/nota-fiscal/buscar/${codNota}`)
        .then(response => response.json())
        .then(nota => {
            document.querySelector("#editCodNota").value = nota.codNota;
            document.querySelector("#editDataDeEmissao").value = nota.dataDeEmissao;
            document.querySelector("#editValorTotal").value = nota.valorTotal;
            document.querySelector("#editDescricao").value = nota.descricao;
            document.querySelector("#editFkEmpresaClienteCnpj").value = nota.fkEmpresaClienteCnpj;
            document.querySelector("#edit-modal").style.display = "block";
        })
        .catch(error => alert("Erro ao buscar nota fiscal."));
}

// Função para salvar as alterações de uma nota fiscal
document.querySelector("#edit-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const notaFiscal = {
        codNota: document.querySelector("#editCodNota").value,
        dataDeEmissao: document.querySelector("#editDataDeEmissao").value,
        valorTotal: parseFloat(document.querySelector("#editValorTotal").value),
        descricao: document.querySelector("#editDescricao").value,
        fkEmpresaClienteCnpj: document.querySelector("#editFkEmpresaClienteCnpj").value,
    };

    fetch('/nota-fiscal/atualizar', {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(notaFiscal),
    })
        .then(response => {
            if (response.ok) {
                alert("Nota fiscal atualizada com sucesso!");
                carregarNotas(); // Recarrega a tabela
                document.querySelector("#edit-modal").style.display = "none"; // Fecha o modal
            } else {
                alert("Erro ao atualizar nota fiscal.");
            }
        })
        .catch(error => alert("Erro ao atualizar nota fiscal."));
});

// Função para deletar uma nota fiscal
function deletarNota(codNota) {
    if (confirm("Tem certeza que deseja deletar esta nota fiscal?")) {
        fetch(`/nota-fiscal/deletar/${codNota}`, { method: "DELETE" })
            .then(response => {
                if (response.ok) {
                    alert("Nota fiscal deletada com sucesso!");
                    carregarNotas(); // Recarrega a tabela
                } else {
                    alert("Erro ao deletar nota fiscal.");
                }
            })
            .catch(error => alert("Erro ao deletar nota fiscal."));
    }
}

// Fechar o modal de edição
document.querySelector("#close-modal").addEventListener("click", function () {
    document.querySelector("#edit-modal").style.display = "none";
});

// Carregar as notas fiscais ao carregar a página
document.addEventListener("DOMContentLoaded", carregarNotas);
