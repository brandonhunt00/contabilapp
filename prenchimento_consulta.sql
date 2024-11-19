-- Inserir dados na tabela Empresa_Cliente
INSERT INTO Empresa_Cliente (cnpj, razao_social, telefone, telefone2, email, rua, numero, municipio) VALUES
('12.345.678/0001-12', 'Empresa A Ltda', '(11) 1234-5678', '(11) 8765-4321', 'contato@empresaa.com', 'Rua A', '123', 'São Paulo'),
('98.765.432/0001-98', 'Empresa B S.A.', '(21) 4321-5678', '(21) 1234-9876', 'info@empresab.com', 'Av. B', '456', 'Rio de Janeiro'),
('22.333.444/0001-22', 'Comércio C & Cia', '(31) 3456-7890', '(31) 0987-6543', 'comercial@comercioc.com', 'Rua C', '789', 'Belo Horizonte'),
('44.555.666/0001-44', 'Serviços D LTDA', '(41) 5566-7788', '(41) 8877-6655', 'contato@servicosd.com', 'Av. D', '321', 'Curitiba'),
('55.666.777/0001-55', 'Indústria E S.A.', '(51) 6677-8899', '(51) 9988-7766', 'industria@industriae.com', 'Rua E', '654', 'Porto Alegre');

-- Inserir dados na tabela Imposto (já feito anteriormente para tipos de impostos)

-- Inserir dados na tabela Formalizam_Guia
INSERT INTO Formalizam_Guia (fk_Imposto, fk_Guia_de_Pagamento, data_de_emissao, data_de_vencimento, valor, status_de_pagamento, fk_Empresa_Cliente_cnpj) VALUES
('SN_COMM_FAIXA1', 'GP001', '2024-01-10', '2024-02-10', 1500.00, FALSE, '12.345.678/0001-12'),
('SN_SERV_FAIXA2', 'GP002', '2024-02-15', '2024-03-15', 2200.00, TRUE, '98.765.432/0001-98'),
('LP_SERV_ISS', 'GP003', '2024-01-20', '2024-02-20', 3100.00, FALSE, '22.333.444/0001-22'),
('LR_COMM_PIS', 'GP004', '2024-03-10', '2024-04-10', 2750.00, TRUE, '44.555.666/0001-44'),
('LR_SERV_IR', 'GP005', '2024-02-25', '2024-03-25', 3600.00, FALSE, '55.666.777/0001-55'),
('SN_COMM_FAIXA3', 'GP006', '2024-03-12', '2024-04-12', 4300.00, TRUE, '12.345.678/0001-12');

-- Inserir dados na tabela Declaracao_Fiscal
INSERT INTO Declaracao_Fiscal (cod_Declaracao_Fiscal, tipo, periodo_de_referencia, data_de_envio, situacao, fk_Empresa_Cliente_cnpj) VALUES
('DF001', 'Anual', '2023', '2024-01-05', 'Enviada', '12.345.678/0001-12'),
('DF002', 'Mensal', 'Janeiro 2024', '2024-01-15', 'Pendente', '98.765.432/0001-98'),
('DF003', 'Trimestral', '2024 Q1', '2024-04-01', 'Em Análise', '22.333.444/0001-22'),
('DF004', 'Anual', '2023', '2024-02-10', 'Aprovada', '44.555.666/0001-44'),
('DF005', 'Mensal', 'Fevereiro 2024', '2024-03-05', 'Rejeitada', '55.666.777/0001-55'),
('DF006', 'Semestral', 'H1 2024', '2024-07-01', 'Submetida', '12.345.678/0001-12');

-- Inserir dados na tabela Consultoria
INSERT INTO Consultoria (cod_Consultoria, tipo, data, descricao, fk_Empresa_Cliente_cnpj) VALUES
('C001', 'Financeira', '2024-01-20', 'Análise de fluxo de caixa', '12.345.678/0001-12'),
('C002', 'Tributária', '2024-02-05', 'Planejamento tributário', '98.765.432/0001-98'),
('C003', 'Recursos Humanos', '2024-03-15', 'Avaliação de desempenho', '22.333.444/0001-22'),
('C004', 'Tecnológica', '2024-04-10', 'Implementação de ERP', '44.555.666/0001-44'),
('C005', 'Estratégica', '2024-05-20', 'Expansão de mercado', '55.666.777/0001-55');

-- Inserir dados na tabela Nota_Fiscal
INSERT INTO Nota_Fiscal (cod_Nota, data_de_emissao, valor_total, descricao, fk_Empresa_Cliente_cnpj) VALUES
('NF001', '2024-01-25', 5000.00, 'Venda de produtos', '12.345.678/0001-12'),
('NF002', '2024-02-01', 12000.00, 'Prestação de serviços', '98.765.432/0001-98'),
('NF003', '2024-03-10', 7500.00, 'Exportação de mercadorias', '22.333.444/0001-22'),
('NF004', '2024-04-15', 3000.00, 'Venda no atacado', '44.555.666/0001-44'),
('NF005', '2024-05-05', 9500.00, 'Consultoria técnica', '55.666.777/0001-55');

-- Inserir dados na tabela Funcionario_Apura
INSERT INTO Funcionario_Apura (cpf, nome, departamento, telefone, email, cpf_gerente, fk_Nota_Fiscal_cod, fk_Nota_Fiscal_cnpj, fk_Imposto) VALUES
('12345678901', 'João Silva', 'Vendas', '(11) 2222-3333', 'joao.silva@empresa.com', NULL, 'NF001', '12.345.678/0001-12', 'SN_SERV_FAIXA1'),
('98765432109', 'Ana Pereira', 'Financeiro', '(21) 3333-4444', 'ana.pereira@empresa.com', '12345678901', 'NF002', '98.765.432/0001-98', 'LR_SERV_COFINS'),
('11223344556', 'Carlos Oliveira', 'Recursos Humanos', '(31) 4444-5555', 'carlos.oliveira@empresa.com', '12345678901', 'NF003', '22.333.444/0001-22', 'LP_SERV_ISS'),
('22334455667', 'Mariana Santos', 'Tecnologia', '(41) 5555-6666', 'mariana.santos@empresa.com', '12345678901', 'NF004', '44.555.666/0001-44', 'LR_COMM_PIS'),
('33445566778', 'Pedro Costa', 'Produção', '(51) 6666-7777', 'pedro.costa@empresa.com', '12345678901', 'NF005', '55.666.777/0001-55', 'LP_COMM_ICMS');

-- Inserir dados na tabela Simples_Nacional
INSERT INTO Simples_Nacional (tipo_de_calculo, fk_Empresa_Cliente_cnpj) VALUES
('Comércio', '12.345.678/0001-12'),
('Serviço', '44.555.666/0001-44');

-- Inserir dados na tabela Lucro_Presumido
INSERT INTO Lucro_Presumido (tipo_de_calculo, fk_Empresa_Cliente_cnpj) VALUES
('Serviço', '98.765.432/0001-98'),
('Comércio', '22.333.444/0001-22');

-- Inserir dados na tabela Lucro_real
INSERT INTO Lucro_real (tipo_de_calculo, fk_Empresa_Cliente_cnpj) VALUES
('Serviço', '55.666.777/0001-55');

-- 1. Selecionar todas as empresas
SELECT * FROM Empresa_Cliente;

-- 2. Consultar impostos de uma empresa específica usando seu `cnpj`
SELECT i.cod_imposto, i.tipo, i.aliquota, i.base_de_calculo
FROM Imposto i
JOIN Formalizam_Guia fg ON i.cod_imposto = fg.fk_Imposto
WHERE fg.fk_Empresa_Cliente_cnpj = '12.345.678/0001-12';

-- 3. Listar todas as declarações fiscais pendentes
SELECT cod_Declaracao_Fiscal, tipo, periodo_de_referencia, data_de_envio
FROM Declaracao_Fiscal
WHERE situacao = 'Pendente';

-- 4. Consultar todas as notas fiscais emitidas por uma empresa
SELECT nf.cod_Nota, nf.data_de_emissao, nf.valor_total, nf.descricao
FROM Nota_Fiscal nf
WHERE nf.fk_Empresa_Cliente_cnpj = '98.765.432/0001-98';

-- 5. Obter todos os funcionários e seus respectivos gerentes
SELECT fa1.cpf, fa1.nome AS Funcionario, fa2.nome AS Gerente
FROM Funcionario_Apura fa1
LEFT JOIN Funcionario_Apura fa2 ON fa1.cpf_gerente = fa2.cpf;

-- 6. Consultar guias de pagamento que ainda estão pendentes
SELECT fk_Guia_de_Pagamento, data_de_vencimento, valor
FROM Formalizam_Guia
WHERE status_de_pagamento = FALSE;

-- 7. Listar as consultorias realizadas por tipo e data
SELECT tipo, data, descricao
FROM Consultoria
ORDER BY data DESC;

-- 8. Verificar o tipo de regime tributário de cada empresa
SELECT ec.cnpj, ec.razao_social,
       CASE
           WHEN sn.fk_Empresa_Cliente_cnpj IS NOT NULL THEN 'Simples Nacional'
           WHEN lp.fk_Empresa_Cliente_cnpj IS NOT NULL THEN 'Lucro Presumido'
           WHEN lr.fk_Empresa_Cliente_cnpj IS NOT NULL THEN 'Lucro Real'
           ELSE 'Indefinido'
       END AS regime_tributario
FROM Empresa_Cliente ec
LEFT JOIN Simples_Nacional sn ON ec.cnpj = sn.fk_Empresa_Cliente_cnpj
LEFT JOIN Lucro_Presumido lp ON ec.cnpj = lp.fk_Empresa_Cliente_cnpj
LEFT JOIN Lucro_real lr ON ec.cnpj = lr.fk_Empresa_Cliente_cnpj;

-- 9. Obter o total de impostos a serem pagos por empresa
SELECT ec.razao_social, SUM(fg.valor) AS total_impostos
FROM Empresa_Cliente ec
JOIN Formalizam_Guia fg ON ec.cnpj = fg.fk_Empresa_Cliente_cnpj
WHERE fg.status_de_pagamento = FALSE
GROUP BY ec.razao_social;

-- 10. Consultar todas as consultorias de um tipo específico
SELECT cod_Consultoria, data, descricao
FROM Consultoria
WHERE tipo = 'Financeira';
