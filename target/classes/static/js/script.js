// ---------------- Funções Utilitárias ----------------
function redirecionar(url) {
  window.location.href = url;
}

function sair() {
  if (confirm("Deseja realmente sair?")) {
    window.close();
  }
}

function limparFormulario(formId) {
  const form = document.getElementById(formId);
  if (form) {
    form.reset();
  }
}

function cadastrarMensagem(mensagem = "Cadastro realizado com sucesso!") {
  alert(mensagem);
}

// ---------------- Login Simulado ----------------
function simularLogin(usuarioInputId, senhaInputId) {
  const usuarios = [
    { usuario: "admin", senha: "123", perfil: "A" },
    { usuario: "operador", senha: "456", perfil: "O" },
    { usuario: "vendedor", senha: "789", perfil: "O" }
  ];

  const usuario = document.getElementById(usuarioInputId).value.trim();
  const senha = document.getElementById(senhaInputId).value.trim();

  if (usuario === "" || senha === "") {
    alert("Todos os campos devem ser preenchidos.");
    return;
  }

  const user = usuarios.find(u => u.usuario === usuario && u.senha === senha);

  if (user) {
    alert(`Seja bem-vindo(a), ${user.perfil === "A" ? "ADMINISTRADOR" : "OPERADOR"}!`);
    redirecionar(user.perfil === "A" ? "administrador-modulo.html" : "operador-modulo.html");
  } else {
    alert("Usuário ou senha inválidos. Cadastre um novo usuário.");
    document.getElementById(usuarioInputId).value = "";
    document.getElementById(senhaInputId).value = "";
    document.getElementById(usuarioInputId).focus();
  }
}

// ---------------- Eventos ao Carregar Documento ----------------
document.addEventListener("DOMContentLoaded", function () {
  // Login
  const formLogin = document.getElementById("formLogin");
  if (formLogin) {
    formLogin.addEventListener("submit", function (e) {
      e.preventDefault();
      simularLogin("usuario", "senha");
    });
  }

  // Listagem de Categorias (localStorage)
  const tbodyCategorias = document.querySelector("#tabela-categorias tbody");
  if (tbodyCategorias) {
    const lista = JSON.parse(localStorage.getItem("categorias") || "[]");
    lista.forEach(cat => {
      const row = document.createElement("tr");
      row.innerHTML = `<td>${cat.nome}</td><td>R$ ${parseFloat(cat.valor).toFixed(2)}</td>`;
      tbodyCategorias.appendChild(row);
    });
  }

  // Cadastro de Cargo
  const formCargo = document.getElementById("formCargo");
  if (formCargo) {
    formCargo.addEventListener("submit", function (e) {
      e.preventDefault();
      const cargo = document.getElementById("cargo").value.trim();
      if (!cargo) {
        alert("Por favor, informe o nome do cargo.");
        return;
      }
      alert("Cargo cadastrado com sucesso!");
      formCargo.reset();
    });

    const btnLimpar = document.getElementById("limpar");
    if (btnLimpar) {
      btnLimpar.addEventListener("click", () => {
        document.getElementById("cargo").value = "";
      });
    }
  }

  // Cadastro de Equipamento
  const formEquipamento = document.getElementById("formEquipamento");
  if (formEquipamento) {
    formEquipamento.addEventListener("submit", function (e) {
      e.preventDefault();
      const nome = document.getElementById("nome").value.trim();
      const descricao = document.getElementById("descricao").value.trim();
      const categoria = document.getElementById("categoria").value;
      if (!nome || !descricao || !categoria) {
        alert("Preencha todos os campos!");
        return;
      }
      alert("Equipamento cadastrado com sucesso!");
      formEquipamento.reset();
    });
  }

  // Listagem de Usuários com LocalStorage
  const tabelaUsuarios = document.getElementById("tabelaUsuarios");
  if (tabelaUsuarios) {
    carregarUsuariosLocal();
  }

  // Cadastro e Listagem de Usuários via API
  const formUsuario = document.getElementById("formUsuario");
  if (formUsuario) {
    formUsuario.addEventListener("submit", salvarUsuarioAPI);
    carregarUsuariosAPI();
  }
});

// ---------------- Usuários com LocalStorage ----------------
function carregarUsuariosLocal() {
  const usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
  const tabela = document.getElementById("tabelaUsuarios");
  if (!tabela) return;

  tabela.innerHTML = "";
  usuarios.forEach((usuario, index) => {
    tabela.innerHTML += `
      <tr>
        <td>${index + 1}</td>
        <td>${usuario.usuario}</td>
        <td>${usuario.perfil === "A" ? "Administrador" : "Operador"}</td>
        <td><button onclick="removerUsuarioLocal(${index})">Remover</button></td>
      </tr>`;
  });
}

function removerUsuarioLocal(index) {
  if (confirm("Deseja realmente remover este usuário?")) {
    let usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];
    usuarios.splice(index, 1);
    localStorage.setItem("usuarios", JSON.stringify(usuarios));
    carregarUsuariosLocal();
  }
}

// ---------------- Usuários com API (AJAX) ----------------
const API_URL = "/api/usuarios";

async function carregarUsuariosAPI() {
  try {
    const resp = await fetch(API_URL);
    const usuarios = await resp.json();
    const container = document.getElementById("usuarios");
    if (!container) return;

    container.innerHTML = "";
    usuarios.forEach(u => {
      const div = document.createElement("div");
      div.innerHTML = `
        <p><strong>ID:</strong> ${u.id} - <strong>Nome:</strong> ${u.nome} - <strong>Usuário:</strong> ${u.usuario}
        <button onclick="editarUsuario(${u.id}, '${u.nome}', '${u.usuario}')">Editar</button>
        <button onclick="removerUsuarioAPI(${u.id})">Remover</button></p>`;
      container.appendChild(div);
    });
  } catch (err) {
    console.error("Erro ao carregar usuários da API:", err);
  }
}

async function salvarUsuarioAPI(event) {
  event.preventDefault();
  const id = document.getElementById("id").value;
  const nome = document.getElementById("nome").value;
  const usuario = document.getElementById("usuario").value;
  const senha = document.getElementById("senha").value;
  const metodo = id ? "PUT" : "POST";
  const url = id ? `${API_URL}/${id}` : API_URL;

  await fetch(url, {
    method: metodo,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ id, nome, usuario, senha })
  });

  document.getElementById("formUsuario").reset();
  carregarUsuariosAPI();
}

async function removerUsuarioAPI(id) {
  if (confirm("Deseja realmente excluir este usuário?")) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    carregarUsuariosAPI();
  }
}

function editarUsuario(id, nome, usuario) {
  document.getElementById("id").value = id;
  document.getElementById("nome").value = nome;
  document.getElementById("usuario").value = usuario;
}
