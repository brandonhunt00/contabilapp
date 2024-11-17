document.getElementById("notaFiscalForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const notaFiscal = {
        codNota: document.getElementById("codNota").value,
        dataDeEmissao: document.getElementById("dataEmissao").value,
        valorTotal: parseFloat(document.getElementById("valorTotal").value),
        descricao: document.getElementById("descricao").value,
        fkEmpresaClienteCnpj: document.getElementById("fkEmpresaClienteCnpj").value
    };

    try {
        const response = await fetch("/nota-fiscal/inserir", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(notaFiscal)
        });
        if (response.ok) {
            alert("Nota fiscal inserida com sucesso!");
            carregarNotasFiscais();
        } else {
            alert("Erro ao inserir nota fiscal.");
        }
    } catch (error) {
        console.error(error);
        alert("Erro de conexão.");
    }
});

async function carregarNotasFiscais() {
    try {
        const response = await fetch("/nota-fiscal/listar");
        const notasFiscais = await response.json();
        const tableBody = document.getElementById("notaFiscalTableBody");
        tableBody.innerHTML = "";
        notasFiscais.forEach((nota) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${nota.codNota}</td>
                <td>${nota.dataDeEmissao}</td>
                <td>${nota.valorTotal}</td>
                <td>${nota.descricao}</td>
                <td>${nota.fkEmpresaClienteCnpj}</td>
            `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error(error);
        alert("Erro ao carregar notas fiscais.");
    }
}

// Carregar notas fiscais na inicialização
carregarNotasFiscais();
