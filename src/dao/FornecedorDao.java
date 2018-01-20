package dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


import tabelas.Fornecedor;

public class FornecedorDao 
{
	private Session session;
	
	public FornecedorDao(Session s)
	{
		this.session=s;
	}
	public Fornecedor load(int id)
	{
		return (Fornecedor) session.load(Fornecedor.class, id);
	}
	public void save(Fornecedor f)
	{	org.hibernate.Transaction tx = session.beginTransaction();
		session.saveOrUpdate(f);
		
		tx.commit();
	}
	public void delete(Fornecedor f)
	{
		session.delete(f);
	}
	public List<Fornecedor> buscaPeloNome(String nome)
	{
		String hql = "FROM Fornecedor WHERE nome LIKE :param";
		org.hibernate.Query query= this.session.createQuery(hql);
		query.setParameter("param", "%" + nome + "%");
		
		return query.list();
	}
	public void listarFornecedores(List<Fornecedor> listaFornecedores){
		for(Fornecedor f : listaFornecedores){
			System.out.println("Nome do Fornecedor: " + f.getNome() + "Id: " + f.getId());
		}
	}
	public void listarTodosFornecedores(){
		Criteria c = this.session.createCriteria(Fornecedor.class);
		c.add(Restrictions.ilike("nome", "%", MatchMode.ANYWHERE));
		List<Fornecedor> listaDeFornecedores = c.list();
		for(Fornecedor f : listaDeFornecedores){
			System.out.println("Nome do Fornecedor: " + f.getNome() + "Id: " + f.getId());
		}
	}
}
