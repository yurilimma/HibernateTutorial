package main;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.HibernateError;
import org.hibernate.Session;

import dao.FornecedorDao;
import dao.FuncionarioDao;
import tabelas.Actor;
import tabelas.Book;
import tabelas.Fornecedor;
import tabelas.Funcionario;
import util.HibernateUtils;


public class Programa 
{
	private static Session session;
	
	public static void main(String[] args) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		session= util.HibernateUtils.getSession();
		
		
		
		
		//ehCache();
		
		
		FornecedorDao fDAO = new FornecedorDao(session);
		
		Fornecedor fornecedor = fDAO.load(3);
		
		System.out.println("O fornecedor: " + fornecedor.getNome() + 
				" possui " + fornecedor.getContas().size() +
				" vinculadas a ele");
		
		
		
		
		
		
	
		
		
		
		
		
		/*
		
		
		for(Contapagar listaResult : cpDao.getContasValorAcimaMedia()){
			System.out.println("Descrição da Conta: " + listaResult.getDescricao() + 
							"\nValor:  " + listaResult.getValor() + 
							"\nId Conta: " + listaResult.getId() +
							"\nFornecedor: " + listaResult.getFornecedor().getNome());
		}
		*/
		
		/*Funcionario f = new Funcionario();
		f.setUsuario("joa");
		f.setEmail("j");
		FuncionarioDao fDao = new FuncionarioDao(session);
		List<Funcionario> lista = fDao.buscaPorExemplo(f);
		
		for(Funcionario percorre : lista){
		
			System.out.println(percorre.getNome() + " - " + percorre.getEmail() + " - " + percorre.getUsuario());
		}*/
		
		
		/*FornecedorDao fornecedor = new FornecedorDao(session);
		Fornecedor hp = new Fornecedor();
		hp.setNome("H");
		
		
		
		List<ValorPorFornecedor> lista = cpDao.getValoresPorFornecedor(10);
		for(ValorPorFornecedor valorPFornecedor : lista){
			Fornecedor f = valorPFornecedor.getFornecedor();
			boolean pago = valorPFornecedor.isPago();
			String situacao = pago ? "Pagas" : "Não pagas";
			System.out.println(f.getNome() + " - " + f.getDescricao() + " - " + situacao);
			System.out.println(valorPFornecedor.getSoma());
		}*/
		
		/*
		
		
		*/
		/*
		;
		
		
		ContaPagarDao novaConta = new ContaPagarDao(session);
		Contapagar contaX = new Contapagar();
		contaX.setDescricao("Conta 2");
		contaX.setPago(true);
		contaX.setValor(15.50);
		contaX.setFornecedor(idFornecedor.load(3));
		
		novaConta.save(contaX);
		
		
		
		/*
		List<Fornecedor> lista = resultFornecedores.buscaPeloNome("%");
		
		
		
		for (Fornecedor f : lista){
			System.out.println("Nome do Fornecedor:" + f.getNome() + "Id: " + f.getId());
		}
		
		
		
		/*
		int id =1;
		
		Dao<Book>  bookDao = new Dao<Book>(session, Book.class); 
		Book b = bookDao.load(id);
		
		FornecedorDao fornecedordao = new FornecedorDao(session);
		
		List<Fornecedor> lista = fornecedordao.buscaPeloNome("Micro");
		for(Fornecedor fornecedor: lista)
		{
			System.out.println("Nome do fornecedor: " + fornecedor.getNome());
		}
		*/
		
		session.close();
		System.exit(0);
	}
	public static void buscarBook(int id)
	{
		Book b = (Book) session.load(Book.class, id);
		System.out.println("Nome do livro: " + b.getTitle());
		
	}
	public static void buscarActor()
	{	
		
		short id=1;
		Actor a = session.load(Actor.class,id);
		System.out.println("Nome do livro: " + a.getFirstName());
		
	}
	public static void ehCache(){
		Session s1 = HibernateUtils.getSession();
		Session s2 = HibernateUtils.getSession();
		
		Funcionario f1= (Funcionario) s1.load(Funcionario.class, new Long(19));
		System.out.println("Primeiro Funcionario lido: " + f1.getNome());
		s1.close();
		
		Funcionario f2= (Funcionario) s2.load(Funcionario.class, new Long(19));
		System.out.println("Lendo de novo: " + f2.getNome());
		s2.close();
	}
	
}
