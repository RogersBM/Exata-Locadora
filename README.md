# 🎯 Projeto Exata-Locadora

Sistema Java Web desenvolvido para controle de locação de equipamentos. Possui funcionalidades completas de cadastro, edição, encerramento de aluguéis, gerenciamento de equipamentos e geração de valores com base no tempo de uso.

---

## 🚧 Status do Projeto
✅ Projeto em desenvolvimento contínuo  
✅ Funcionalidades principais implementadas  
🛠️ Em constante aprimoramento e versionamento

---

## 📌 Funcionalidades Implementadas

### 📁 Gestão de Equipamentos
- Cadastro de equipamento com nome, descrição, valor de diária e status de disponibilidade.
- Edição e exclusão de equipamentos.
- Exibição de todos os equipamentos cadastrados com seus respectivos dados.

### 🤝 Locação de Equipamentos
- Cadastro de aluguel com cliente, funcionário e equipamento vinculado.
- Cálculo automático do valor total ao encerrar aluguel (com base na data de início, fim e valor da diária).
- Edição, exclusão e encerramento de aluguéis.

### 📊 Listagem de Aluguéis
- Tabela com:
  - Cliente
  - Funcionário
  - Equipamento
  - Data de Início
  - Data de Encerramento
  - Valor Total
  - Ações (Editar | Excluir | Encerrar)
- Status dos aluguéis exibido dinamicamente (Aberto ou Encerrado).

---

## ✅ Tecnologias Utilizadas

| Camada         | Tecnologia                    |
|----------------|-------------------------------|
| Linguagem      | Java                          |
| IDE            | NetBeans                      |
| Banco de Dados | MySQL                         |
| ORM            | JPA / Hibernate               |
| Frontend       | HTML + CSS + Bootstrap        |
| Template Engine| Thymeleaf                     |
| Gerenciamento  | Maven                         |
| Versionamento  | Git + GitHub                  |


## 🖼️ Imagens do Sistema

### 🔧 Cadastro de Equipamentos  
![Cadastro Equipamento](docs/img/cadastro-equipamento.png)

### 📋 Listagem de Aluguéis com Total  
![Listagem Aluguéis](docs/img/listagem-alugueis.png)

### ⏱️ Encerramento do Aluguel  
![Encerrar Aluguel](docs/img/encerrar-aluguel.png)

### ✅ Aluguel Encerrado com Sucesso  
![Aluguel Encerrado](docs/img/sucesso-encerramento.png)

---

## 🧑‍💻 Time de Desenvolvimento

- **Backend e Banco de Dados:** Rogers BM  
- **Frontend e Integração de Views:** Rogers BM  
- **Gestão de Versões e Documentação:** Rogers BM  

---

## 📁 Histórico de Versionamento

### Último Commit:
- `feat: cria tela de encerramento de aluguel e atualiza listagens com total e status`
- Inclui: `locacao-encerrar-form.html`, cálculo do total via Service, exibição na listagem.

### Histórico completo:
Acesse os [commits aqui](https://github.com/RogersBM/Exata-Locadora/commits/master)

---

## 📦 Como Executar o Projeto

1. Clone o repositório:  
   `git clone https://github.com/RogersBM/Exata-Locadora.git`

2. Abra no NetBeans ou IDE compatível com Maven.

3. Configure o banco de dados MySQL com script `schema.sql` (em desenvolvimento).

4. Execute a aplicação e acesse via navegador:  
   `http://localhost:8080`

---

## 📚 Licença

Projeto educacional desenvolvido para a disciplina de **Versionamento de Código** do curso de TDS.

---


