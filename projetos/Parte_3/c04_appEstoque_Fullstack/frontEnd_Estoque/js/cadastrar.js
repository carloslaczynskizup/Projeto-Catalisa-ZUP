// Função para enviar o formulário de cadastro

document.getElementById('product-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const nomeProduto = document.getElementById('nomeProduto').value;
    const marca = document.getElementById('marca').value;
    const descricaoProduto = document.getElementById('descricaoProduto').value;
    const categoriaProduto = document.getElementById('categoriaProduto').value;

    const precoVendaInput = document.getElementById('precoVenda');
    const precoVendaValue = precoVendaInput.value.replace(',', '.');
    const precoVenda = parseFloat(precoVendaValue);

    const quantidadeEstoque = parseInt(document.getElementById('quantidadeEstoque').value);

    const tipoMovimentacao = document.getElementById('tipoMovimentacao').value;



    const novoProduto = {
        nomeProduto,
        marca,
        descricaoProduto,
        categoriaProduto,
        precoVenda,
        quantidadeEstoque,
        tipoMovimentacao
    };

    const response = await fetch('http://localhost:8080/produtos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoProduto)
    });

    if (response.status === 200) {
        alert('Produto cadastrado com sucesso!');
        // Redirecionar para a página de listagem de produtos após o cadastro
        window.location.href = './listar.html';
    } else {
        alert('Erro ao cadastrar o produto. Por favor, tente novamente.');
    }
});
