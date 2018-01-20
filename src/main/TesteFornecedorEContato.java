package main;

import org.hibernate.Session;

import dao.FornecedorDao;
import dao.FuncionarioDao;
import tabelas.Fornecedor;
import tabelas.Funcionario;

public class TesteFornecedorEContato {
	private static Session session;
	public static void main(String[] args) {
		session= util.HibernateUtils.getSession();
		
		
		FornecedorDao fDAO = new FornecedorDao(session);
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Kalunga");
		
		FuncionarioDao funcionarioDAO = new FuncionarioDao(session);
		Funcionario zezinho = new Funcionario();
		
		zezinho.setNome("zezinho");
		
		zezinho.getFornecedores().add(fornecedor);
		fornecedor.getContatos().add(zezinho);
		
		/**
		 * Obs: como o mappedBy está em funcionario, o mesmo vira a entidade
		 * mais forte e tem que ser persistido primeiro
		 */
		funcionarioDAO.save(zezinho);
		fDAO.save(fornecedor);

	}

}
