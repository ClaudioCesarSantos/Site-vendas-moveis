// scripts.js

// =======================
// Variáveis Globais
// =======================
let carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];

// =======================
// Utilitários
// =======================
function salvarCarrinho() {
    localStorage.setItem('carrinho', JSON.stringify(carrinho));
}

function formatarPreco(valor) {
    return valor.toFixed(2).replace('.', ',');
}

// =======================
// Carrinho
// =======================
function atualizarContadorCarrinho() {
    const contador = document.getElementById('contador-carrinho');
    if (contador) {
        contador.textContent = carrinho.length;
    }
}

function adicionarAoCarrinho(idProduto) {
    const existente = carrinho.find(item => item.id === idProduto);

    if (existente) {
        existente.quantidade++;
    } else {
        carrinho.push({
            id: idProduto,
            quantidade: 1,
            nome: `Produto ${idProduto}`,
            preco: 1500.00,
            imagem: '/images/produtos/placeholder.jpg'
        });
    }

    salvarCarrinho();
    atualizarContadorCarrinho();
    atualizarCarrinho();
}

function removerItem(index) {
    carrinho.splice(index, 1);
    salvarCarrinho();
    atualizarCarrinho();
    atualizarContadorCarrinho();
}

function atualizarQuantidade(index, quantidade) {
    carrinho[index].quantidade = Math.max(1, quantidade);
    salvarCarrinho();
    calcularTotal();
}

function atualizarCarrinho() {
    const lista = document.getElementById('lista-itens');
    if (!lista) return;

    lista.innerHTML = '';

    carrinho.forEach((item, index) => {
        lista.innerHTML += `
            <div class="item-carrinho">
                <img src="${item.imagem}" alt="${item.nome}">
                <div class="info-item">
                    <h4>${item.nome}</h4>
                    <p>R$ ${formatarPreco(item.preco)}</p>
                    <div class="controles">
                        <input 
                            type="number" 
                            value="${item.quantidade}" 
                            min="1"
                            onchange="atualizarQuantidade(${index}, this.value)"
                        >
                        <button onclick="removerItem(${index})">🗑️</button>
                    </div>
                </div>
            </div>
        `;
    });

    calcularTotal();
}

function calcularTotal() {
    const subtotal = carrinho.reduce((acc, item) => acc + item.preco * item.quantidade, 0);
    const frete = subtotal > 1000 ? 0 : 50;
    const total = subtotal + frete;

    const elSubtotal = document.getElementById('subtotal');
    const elFrete = document.getElementById('frete');
    const elTotal = document.getElementById('total');

    if (elSubtotal) elSubtotal.textContent = formatarPreco(subtotal);
    if (elFrete) elFrete.textContent = formatarPreco(frete);
    if (elTotal) elTotal.textContent = formatarPreco(total);
}

// =======================
// Checkout
// =======================
function finalizarCompra() {
    if (carrinho.length === 0) {
        Swal.fire('Carrinho vazio', 'Adicione itens antes de finalizar!', 'warning');
        return;
    }

    Swal.fire({
        icon: 'success',
        title: 'Compra realizada!',
        text: 'Obrigado pela sua compra!',
        confirmButtonText: 'Voltar à loja'
    }).then(() => {
        carrinho = [];
        salvarCarrinho();
        atualizarContadorCarrinho();
        atualizarCarrinho();
        window.location.href = '/';
    });
}

// =======================
// Pagamento - Mostrar Dados do Cartão
// =======================
function configurarPagamento() {
    const metodo = document.getElementById('metodo-pagamento');
    const dadosCartao = document.getElementById('dados-cartao');

    if (metodo && dadosCartao) {
        metodo.addEventListener('change', e => {
            dadosCartao.style.display = e.target.value === 'credito' ? 'block' : 'none';
        });
    }
}

// =======================
// Inicialização
// =======================
document.addEventListener('DOMContentLoaded', () => {
    atualizarContadorCarrinho();
    atualizarCarrinho();
    configurarPagamento();

    const formCheckout = document.getElementById('form-checkout');
    if (formCheckout) {
        formCheckout.addEventListener('submit', e => {
            e.preventDefault();
            finalizarCompra();
        });
    }
});
