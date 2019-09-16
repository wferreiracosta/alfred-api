package br.com.wferreiracosta.loja.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.wferreiracosta.loja.domain.enums.EstadoPagamento;
import br.com.wferreiracosta.loja.domain.enums.TipoCliente;

public class TestPedido {
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Test
	public void exibirPedido() throws ParseException{
		Categoria categoria = new Categoria(null, "Informática");
		
		Produto produto = new Produto(null, "Computador", 2000.00);

		categoria.getProdutos().addAll(Arrays.asList(produto));
		produto.getCategorias().addAll(Arrays.asList(categoria));

		Estado estado = new Estado(null, "São Paulo");

		Cidade cidade = new Cidade(null, "São Paulo", estado);

		estado.getCidades().addAll(Arrays.asList(cidade));

		Cliente cliente = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA, pe.encode("123"));

		cliente.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco endereco = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cliente, cidade);

		cliente.getEnderecos().addAll(Arrays.asList(endereco));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido pedido = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente, endereco);

		Pagamento pagamento = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido, sdf.parse("20/10/2017 00:00"),
				null);
		pedido.setPagamento(pagamento);

		cliente.getPedidos().addAll(Arrays.asList(pedido));

		ItemPedido itemPedido = new ItemPedido(pedido, produto, 100.00, 1, 800.00);

		pedido.getItens().addAll(Arrays.asList(itemPedido));
		produto.getItens().addAll(Arrays.asList(itemPedido));

		System.out.println(pedido);
	}
}
