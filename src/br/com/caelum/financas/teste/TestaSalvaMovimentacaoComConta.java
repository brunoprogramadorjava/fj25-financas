package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestaSalvaMovimentacaoComConta {
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		MovimentacaoDao dao = new MovimentacaoDao(manager);

		Conta conta = new Conta();
		conta.setBanco("Banco Santander");
		conta.setNumero("99999-9");
		conta.setAgencia("999");
		conta.setTitular("Ana Maria");

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricaco("conta de luz - abril/2010");
		movimentacao.setValor(new BigDecimal("54"));
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);

		manager.getTransaction().begin();
		dao.adiciona(movimentacao);

		// Essa linha pode ser substituido pela anotacao
		// @ManyToOne(cascade=CascadeType.PERSIST)
		// manager.persist(conta);

		manager.getTransaction().commit();
		manager.close();

		System.out.println("Movimentacao gravada com sucesso!");

	}
}
