// Função genérica para redirecionar
function redirecionar(url) {
  window.location.href = url;
}

// Função para confirmar saída
function sair() {
  if (confirm("Deseja realmente sair?")) {
    window.close();
  }
}

// Função para limpar campos de um formulário
function limparFormulario(formId) {
  const form = document.getElementById(formId);
  if (form) {
    form.reset();
  }
}

// Função de alerta simples de cadastro
function cadastrarMensagem(mensagem = "Cadastro realizado com sucesso!") {
  alert(mensagem);
}

// Função de simulação de login (ajustar para backend real depois)
function simularLogin(usuarioInputId, senhaInputId) {
  const usuarios = [
    { usuario: "admin", senha: "123", perfil: "A" },
    { usuario: "operador", senha: "456", perfil: "O" },
    { usuario: "vendedor", senha: "789", perfil: "O"}
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

// Event listener de login (se existir o formulário)
document.addEventListener("DOMContentLoaded", function () {
  const formLogin = document.getElementById("formLogin");
  if (formLogin) {
    formLogin.addEventListener("submit", function (e) {
      e.preventDefault();
      simularLogin("usuario", "senha");
    });
  }
});

document.addEventListener("DOMContentLoaded", function () {
    const lista = JSON.parse(localStorage.getItem("categorias") || "[]");
    const tbody = document.querySelector("#tabela-categorias tbody");

    lista.forEach(cat => {
        const row = document.createElement("tr");
        row.innerHTML = `<td>${cat.nome}</td><td>R$ ${parseFloat(cat.valor).toFixed(2)}</td>`;
        tbody.appendChild(row);
    });
});

