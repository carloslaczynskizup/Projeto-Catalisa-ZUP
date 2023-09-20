// Função para carregar a lista de produtos
async function carregarProdutos() {
    const response = await fetch('http://localhost:8080/produtos');
    const produtos = await response.json();

    const produtoList = document.getElementById('produto-list');

    produtos.forEach(produto => {
        const li = document.createElement('li');

        const precoFormatado = `R$ ${produto.precoVenda.toFixed(2)}`;
        const quantidadeFormatada = `${produto.quantidadeEstoque} unidades no estoque`;
        const movimentaçãoFormatada = `Tipo de Movimentação: ${produto.tipoMovimentacao}`;

        li.textContent = `${produto.nomeProduto} - ${produto.marca} - ${precoFormatado} - ${quantidadeFormatada} - ${movimentaçãoFormatada}`;
        produtoList.appendChild(li);
    });
}

// Chamada para carregar os produtos quando a página carregar
carregarProdutos();
