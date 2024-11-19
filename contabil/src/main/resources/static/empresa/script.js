let isUpdating = false;

function mostrarEmpresas() {
    const conteudo = document.getElementById("conteudo");

    conteudo.innerHTML = `
    <h2>Gerenciamento de Empresas</h2>
    <form id="empresaForm">
      <label>CNPJ:
        <input type="text" id="cnpj" required>
      </label>
      <label>Razão Social:
        <input type="text" id="razaoSocial" required>
      </label>
      <label>Telefone:
        <input type="text" id="telefone">
      </label>
      <label>Telefone Secundário:
        <input type="text" id="telefone2">
      </label>
      <label>Email:
        <input type="email" id="email">
      </label>
      <label>Rua:
        <input type="text" id="rua">
      </label>
      <label>Número:
        <input type="text" id="numero">
      </label>
      <label>Município:
        <input type="text" id="municipio">
      </label>
      <button type="submit"><i class="fas fa-save"></i> Salvar</button>
    </form>

    <table>
      <thead>
        <tr>
          <th>CNPJ</th>
          <th>Razão Social</th>
          <th>Telefone</th>
          <th>Email</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody id="tabelaEmpresas">
        <!-- Dados serão carregados dinamicamente -->
      </tbody>
    </table>
  `;

    carregarEmpresas();

    const form = document.getElementById("empresaForm");
    form.addEventListener("submit", inserirOuAtualizarEmpresa);
}

function carregarEmpresas() {
    fetch("http://localhost:8080/empresa/listar")
        .then(response => response.json())
        .then(dados => {
            const tabela = document.getElementById("tabelaEmpresas");
            tabela.innerHTML = ""; // Limpa a tabela

            dados.forEach(empresa => {
                const row = document.createElement("tr");
                row.innerHTML = `
          <td>${empresa.cnpj}</td>
          <td>${empresa.razaoSocial}</td>
          <td>${empresa.telefone}</td>
          <td>${empresa.email}</td>
          <td>
            <button onclick="editarEmpresa('${empresa.cnpj}')"><i class="fas fa-edit"></i> Editar</button>
            <button onclick="deletarEmpresa('${empresa.cnpj}')"><i class="fas fa-trash"></i> Deletar</button>
          </td>
        `;
                tabela.appendChild(row);
            });
        });
}

let isUpdatingEmpresa = false;

function inserirOuAtualizarEmpresa(event) {
    event.preventDefault();

    const empresa = {
        cnpj: document.getElementById("cnpj").value,
        razaoSocial: document.getElementById("razaoSocial").value,
        telefone: document.getElementById("telefone").value,
        telefone2: document.getElementById("telefone2").value,
        email: document.getElementById("email").value,
        rua: document.getElementById("rua").value,
        numero: document.getElementById("numero").value,
        municipio: document.getElementById("municipio").value
    };

    const method = isUpdatingEmpresa ? 'PUT' : 'POST';
    const url = isUpdatingEmpresa ? `http://localhost:8080/empresa/atualizar` : `http://localhost:8080/empresa/inserir`;

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(empresa)
    })
        .then(response => {
            if (response.ok) {
                alert("Empresa salva com sucesso!");
                carregarEmpresas();
                document.getElementById("empresaForm").reset();
                document.getElementById("cnpj").disabled = false;
                isUpdatingEmpresa = false;
            } else {
                response.text().then(text => {
                    alert("Erro ao salvar empresa: " + text);
                });
            }
        });
}

function editarEmpresa(cnpj) {
    fetch(`http://localhost:8080/empresa/buscar/${cnpj}`)
        .then(response => response.json())
        .then(empresa => {
            document.getElementById("cnpj").value = empresa.cnpj;
            document.getElementById("razaoSocial").value = empresa.razaoSocial;
            document.getElementById("telefone").value = empresa.telefone;
            document.getElementById("telefone2").value = empresa.telefone2;
            document.getElementById("email").value = empresa.email;
            document.getElementById("rua").value = empresa.rua;
            document.getElementById("numero").value = empresa.numero;
            document.getElementById("municipio").value = empresa.municipio;

            document.getElementById("cnpj").disabled = true;
            isUpdatingEmpresa = true;
        });
}


function deletarEmpresa(cnpj) {
    if (confirm("Tem certeza que deseja deletar esta empresa?")) {
        fetch(`http://localhost:8080/empresa/deletar/${cnpj}`, { method: "DELETE" })
            .then(response => {
                if (response.ok) {
                    alert("Empresa deletada com sucesso!");
                    carregarEmpresas();
                } else {
                    alert("Erro ao deletar empresa.");
                }
            });
    }
}

function logout() {
    alert("Usuário deslogado!");
    // Redirecionar para a página de login
}

function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const mainContent = document.querySelector('.main-content');

    sidebar.classList.toggle('collapsed');

    if (sidebar.classList.contains('collapsed')) {
        mainContent.style.marginLeft = '0';
    } else {
        mainContent.style.marginLeft = '250px';
    }
}

let isUpdatingNotaFiscal = false;

function mostrarNotasFiscais() {
    const conteudo = document.getElementById("conteudo");

    conteudo.innerHTML = `
    <h2>Gerenciamento de Notas Fiscais</h2>
    <form id="notaFiscalForm">
      <label>Código da Nota:
        <input type="text" id="codNota" required>
      </label>
      <label>Data de Emissão:
        <input type="date" id="dataDeEmissao" required>
      </label>
      <label>Valor Total:
        <input type="number" step="0.01" id="valorTotal" required>
      </label>
      <label>Descrição:
        <textarea id="descricao"></textarea>
      </label>
      <label>Empresa (CNPJ):
        <select id="fkEmpresaClienteCnpj" required>
          <!-- Opções serão carregadas dinamicamente -->
        </select>
      </label>
      <button type="submit"><i class="fas fa-save"></i> Salvar</button>
    </form>

    <table>
      <thead>
        <tr>
          <th>Código</th>
          <th>Data de Emissão</th>
          <th>Valor Total</th>
          <th>Empresa (CNPJ)</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody id="tabelaNotasFiscais">
        <!-- Dados serão carregados dinamicamente -->
      </tbody>
    </table>
  `;

    carregarEmpresasNoSelect();
    carregarNotasFiscais();

    const form = document.getElementById("notaFiscalForm");
    form.addEventListener("submit", inserirOuAtualizarNotaFiscal);
}

function carregarEmpresasNoSelect() {
    fetch("http://localhost:8080/empresa/listar")
        .then(response => response.json())
        .then(dados => {
            const select = document.getElementById("fkEmpresaClienteCnpj");
            select.innerHTML = '<option value="">Selecione uma empresa</option>';
            dados.forEach(empresa => {
                const option = document.createElement("option");
                option.value = empresa.cnpj;
                option.text = `${empresa.razaoSocial} (${empresa.cnpj})`;
                select.add(option);
            });
        });
}

function carregarNotasFiscais() {
    fetch("http://localhost:8080/nota-fiscal/listar")
        .then(response => response.json())
        .then(dados => {
            const tabela = document.getElementById("tabelaNotasFiscais");
            tabela.innerHTML = ""; // Limpa a tabela

            dados.forEach(nota => {
                const row = document.createElement("tr");
                row.innerHTML = `
          <td>${nota.codNota}</td>
          <td>${new Date(nota.dataDeEmissao).toLocaleDateString()}</td>
          <td>R$ ${nota.valorTotal.toFixed(2)}</td>
          <td>${nota.fkEmpresaClienteCnpj}</td>
          <td>
            <button onclick="editarNotaFiscal('${nota.codNota}', '${nota.fkEmpresaClienteCnpj}')"><i class="fas fa-edit"></i> Editar</button>
            <button onclick="deletarNotaFiscal('${nota.codNota}', '${nota.fkEmpresaClienteCnpj}')"><i class="fas fa-trash"></i> Deletar</button>
          </td>
        `;
                tabela.appendChild(row);
            });
        });
}

function inserirOuAtualizarNotaFiscal(event) {
    event.preventDefault();

    const notaFiscal = {
        codNota: document.getElementById("codNota").value,
        dataDeEmissao: document.getElementById("dataDeEmissao").value,
        valorTotal: parseFloat(document.getElementById("valorTotal").value),
        descricao: document.getElementById("descricao").value,
        fkEmpresaClienteCnpj: document.getElementById("fkEmpresaClienteCnpj").value
    };

    const url = isUpdatingNotaFiscal ? `http://localhost:8080/nota-fiscal/atualizar` : `http://localhost:8080/nota-fiscal/inserir`;

    fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(notaFiscal)
    })
        .then(response => {
            if (response.ok) {
                alert("Nota fiscal salva com sucesso!");
                carregarNotasFiscais();
                document.getElementById("notaFiscalForm").reset();
                document.getElementById("codNota").disabled = false;
                document.getElementById("fkEmpresaClienteCnpj").disabled = false;
                isUpdatingNotaFiscal = false;
            } else {
                response.text().then(text => {
                    alert("Erro ao salvar nota fiscal: " + text);
                });
            }
        });
}

function editarNotaFiscal(codNota, cnpj) {
    fetch(`http://localhost:8080/nota-fiscal/buscar/${codNota}/${cnpj}`)
        .then(response => response.json())
        .then(nota => {
            document.getElementById("codNota").value = nota.codNota;
            document.getElementById("dataDeEmissao").value = nota.dataDeEmissao.substring(0,10);
            document.getElementById("valorTotal").value = nota.valorTotal;
            document.getElementById("descricao").value = nota.descricao;
            document.getElementById("fkEmpresaClienteCnpj").value = nota.fkEmpresaClienteCnpj;

            document.getElementById("codNota").disabled = true;
            document.getElementById("fkEmpresaClienteCnpj").disabled = true;
            isUpdatingNotaFiscal = true;
        });
}

function deletarNotaFiscal(codNota, cnpj) {
    if (confirm("Tem certeza que deseja deletar esta nota fiscal?")) {
        fetch(`http://localhost:8080/nota-fiscal/deletar/${codNota}/${cnpj}`, { method: "DELETE" })
            .then(response => {
                if (response.ok) {
                    alert("Nota fiscal deletada com sucesso!");
                    carregarNotasFiscais();
                } else {
                    alert("Erro ao deletar nota fiscal.");
                }
            });
    }
}

document.getElementById('toggleSidebar').addEventListener('click', toggleSidebar);
