// Função para carregar as empresas cadastradas
function carregarEmpresas() {
    fetch('/empresa/listar')
        .then(response => response.json())
        .then(empresas => {
            const tabelaBody = document.querySelector("#tabela-empresas tbody");
            tabelaBody.innerHTML = ""; // Limpa a tabela antes de carregar os dados
            empresas.forEach(empresa => {
                const linha = document.createElement("tr");
                linha.innerHTML = `
                    <td>${empresa.cnpj}</td>
                    <td>${empresa.razaoSocial}</td>
                    <td>${empresa.telefone}</td>
                    <td>${empresa.telefone2 || "-"}</td>
                    <td>${empresa.email}</td>
                    <td>${empresa.rua}</td>
                    <td>${empresa.numero}</td>
                    <td>${empresa.municipio}</td>
                    <td>
                        <button class="edit" onclick="editarEmpresa('${empresa.cnpj}')">Editar</button>
                        <button class="delete" onclick="deletarEmpresa('${empresa.cnpj}')">Deletar</button>
                    </td>
                `;
                tabelaBody.appendChild(linha);
            });
        })
        .catch(error => alert("Erro ao carregar empresas."));
}

// Função para inserir uma nova empresa
document.querySelector("#empresa-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const empresa = {
        cnpj: document.querySelector("#cnpj").value,
        razaoSocial: document.querySelector("#razaoSocial").value,
        telefone: document.querySelector("#telefone").value,
        telefone2: document.querySelector("#telefone2").value,
        email: document.querySelector("#email").value,
        rua: document.querySelector("#rua").value,
        numero: document.querySelector("#numero").value,
        municipio: document.querySelector("#municipio").value,
    };

    fetch('/empresa/inserir', {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(empresa),
    })
        .then(response => {
            if (response.ok) {
                alert("Empresa inserida com sucesso!");
                carregarEmpresas(); // Recarrega a tabela
                document.querySelector("#empresa-form").reset(); // Limpa o formulário
            } else {
                alert("Erro ao inserir empresa.");
            }
        })
        .catch(error => alert("Erro ao inserir empresa."));
});

// Função para editar uma empresa (exibir dados no formulário para edição)
function editarEmpresa(cnpj) {
    fetch(`/empresa/buscar/${cnpj}`)
        .then(response => response.json())
        .then(empresa => {
            document.querySelector("#cnpj").value = empresa.cnpj;
            document.querySelector("#razaoSocial").value = empresa.razaoSocial;
            document.querySelector("#telefone").value = empresa.telefone;
            document.querySelector("#telefone2").value = empresa.telefone2;
            document.querySelector("#email").value = empresa.email;
            document.querySelector("#rua").value = empresa.rua;
            document.querySelector("#numero").value = empresa.numero;
            document.querySelector("#municipio").value = empresa.municipio;
        })
        .catch(error => alert("Erro ao buscar empresa."));
}

// Função para deletar uma empresa
function deletarEmpresa(cnpj) {
    if (confirm("Tem certeza que deseja deletar esta empresa?")) {
        fetch(`/empresa/deletar/${cnpj}`, { method: "DELETE" })
            .then(response => {
                if (response.ok) {
                    alert("Empresa deletada com sucesso!");
                    carregarEmpresas(); // Recarrega a tabela
                } else {
                    alert("Erro ao deletar empresa.");
                }
            })
            .catch(error => alert("Erro ao deletar empresa."));
    }
}

// Carregar as empresas ao carregar a página
document.addEventListener("DOMContentLoaded", carregarEmpresas);
