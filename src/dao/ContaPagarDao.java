package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import tabelas.Contapagar;
import tabelas.ValorPorFornecedor;
import util.HibernateUtils;

public class ContaPagarDao 
{
	private Session session;
	
	public ContaPagarDao(Session s)
	{
		this.session=s;
	}
	
	public Contapagar load(int id)
	{
		return (Contapagar) session.load(Contapagar.class, id);
	}
	public void save(Contapagar cp)
	{
		session.saveOrUpdate(cp);
		org.hibernate.Transaction tx = session.beginTransaction();
		tx.commit();
	}
	public void delete(Contapagar cp)
	{
		session.delete(cp);
		org.hibernate.Transaction tx = session.beginTransaction();
		tx.commit();
	}
	public void listarTodasContas(){
		Criteria c = this.session.createCriteria(Contapagar.class);
		c.add(Restrictions.ilike("descricao", "%", MatchMode.ANYWHERE));
		List<Contapagar> listaDeContas = c.list();
		for(Contapagar cp : listaDeContas){
			System.out.println("Descrição da Conta: " + cp.getDescricao() + "Nome Fornecedor: " + cp.getFornecedor().getNome());;
		}
	}
	public List<Contapagar> buscarConta(Contapagar cp){
		Criteria c = this.session.createCriteria(Contapagar.class);
		c.add(Restrictions.ilike("descricao", cp.getDescricao(), MatchMode.ANYWHERE));
		return c.list();
	}
	public List<Contapagar> ordenarBuscaConta(Contapagar cp){
		Criteria c = this.session.createCriteria(Contapagar.class);
		c.add(Restrictions.ilike("descricao", cp.getDescricao(), MatchMode.ANYWHERE));
		c.addOrder(Order.asc("descricao"));
		return c.list();
	}
	public List<Contapagar> buscaAnd(Contapagar cp){
		Criteria c = this.session.createCriteria(Contapagar.class);
		c.add(Restrictions.gt("valor", cp.getValor()));
		c.add(Restrictions.ilike("descricao", cp.getDescricao(),MatchMode.ANYWHERE));
		return c.list();
	}
	public List<Contapagar> buscaOr(Contapagar cp){
		Criteria c = this.session.createCriteria(Contapagar.class);
		Disjunction dj = Restrictions.disjunction(); //instacia o objeto disjunction 
		//adiciona o primeiro parametro da query que sera criada, valor maior que(GreatThan)
		dj.add(Restrictions.gt("valor", cp.getValor()));
		//adiciona o segundo parametro da query que sera criada, com % % antes e depois por causa do MatchMode.ANYWHERE
		dj.add(Restrictions.ilike("descricao", cp.getDescricao(),MatchMode.ANYWHERE));
		c.add(dj);
		return c.list();
	}
	public List<Contapagar> buscaPeloNomeFornecedor(String nome){
		String hql= "from Contapagar cp where cp.fornecedor.nome like :param";
		Query query = this.session.createQuery(hql);
		query.setParameter("param","%" + nome + "%");
		return query.list();
	}
	public List<Contapagar> buscaPeloNomeFornecedorUpper(String nome){
		String hql= "from Contapagar cp where cp.fornecedor.nome like lower(:param)";
		Query query = this.session.createQuery(hql);
		query.setParameter("param","%" + nome + "%");
		return query.list();
	}
	
	public List<ValorPorFornecedor> getValoresPorFornecedor(int _quantidade){
		String hql = "select new tabelas.ValorPorFornecedor(cp.fornecedor, cp.pago, sum(cp.valor))" + "from Contapagar as cp group by cp.fornecedor, cp.pago";
		Query q = this.session.createQuery(hql);
		q.setMaxResults(_quantidade);
		return q.list();
	}
	/**
	 * Retorna quantas contas tem valor maior que 2
	 * @param valor
	 * @return
	 */
	public Long getQtdContasMaior(Double valor){
		DetachedCriteria criteria = DetachedCriteria.forClass(Contapagar.class)
														.add(Restrictions.gt("valor", valor))
														.setProjection(Projections.rowCount());
		
		Session session=HibernateUtils.getSession();
		
		return (Long)criteria.getExecutableCriteria(session).uniqueResult();
		
	}
	/**
	 * Retorna quais contas possuem o valor acima da média
	 * 
	 * Obs: percebe-se que foi utilizado uma subquery antes para pegar a média
	 * de valores das contas.(subquery)
	 * @param valor
	 * @return
	 */
	
	public List<Contapagar> getContasValorAcimaMedia(){
		DetachedCriteria subquery = DetachedCriteria.forClass(Contapagar.class)
														.setProjection(Projections.avg("valor"));
		
		List<Contapagar> lista = session.createCriteria(Contapagar.class)
											.add(Property.forName("valor").gt(subquery))
											.list();
		
		Session session=HibernateUtils.getSession();
		
		return lista;
		
	}

}
