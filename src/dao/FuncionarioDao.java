package dao;




import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import tabelas.Funcionario;
import tabelas.FuncionarioTransformer;

public class FuncionarioDao 
{
	private Session session;
	
	
	
	public FuncionarioDao(Session s)
	{
		this.session=s;
	}
	/*public int countNumeroFuncionarios()
	{	String hql = "count(*) from Funcionario";
		query = session.createQuery(hql);
		return (int) query.getSingleResult();
	}*/
	public List<Funcionario> listaComSql(){
		String sql= "SELECT nome,email,usuario,senha FROM funcionario";
		SQLQuery q= session.createSQLQuery(sql);
		q.setMaxResults(10);
		q.setFirstResult(5);
		q.setResultTransformer(new FuncionarioTransformer());
		
		return q.list();
	}
	/** 
	 * Faz o mesmo que o método acima, passando por parametro a classe funcionario para o Transformers.aliasToBean
	 * especificando que deseja retornar uma lista de funcionarios.
	 * **/
	public List<Funcionario> listaComSqlAliasBean(){
		String sql= "SELECT nome,email,usuario,senha FROM funcionario";
		SQLQuery q= session.createSQLQuery(sql);
		q.setResultTransformer(Transformers.aliasToBean(Funcionario.class));
		
		return q.list();
	}
	/** 
	 * Faz o mesmo que o método acima, passando por parametro a classe funcionario na query especificando que a mesma
	 * e uma entidade
	 * 
	 * Obs: Diferente das anteriores, essa maneira o resultado da query deverá conter exatamente 
	 * os mesmos campos da tabela Funcionario, percebe-se que foi utilizado o *.
	 * **/
	public List<Funcionario> listaComSqlAddEntity(){
		String sql= "SELECT * FROM funcionario";
		SQLQuery q= session.createSQLQuery(sql);
		q.addEntity(Funcionario.class);
		
		return q.list();
	}
	
	public List<Funcionario> buscarFuncionarioEmailComHQL(String usuario, String email){
		
		
		String hql= "FROM Funcionario";
		
		boolean temCondicao = false;
		if(usuario!= null){
			hql = hql + " WHERE lower(usuario) LIKE :usuario";
			temCondicao = true;
		}
		if(email!= null){
			if(temCondicao){
				hql = hql + " AND ";
				
			}else{
				hql = hql + " WHERE ";
			}
			hql = hql + " WHERE lower(email) like :email";
			temCondicao = true;
		}
		
		Query query = session.createQuery(hql);
		
		if(usuario!= null){
			query.setString("usuario", "%" + usuario.toLowerCase() + "%");
		}
		if(email!= null){
			query.setString("email", "%" + email.toLowerCase() + "%");
		}
		
		return query.list();
	}
	
	/**
	 * Percebe-se que o método abaixo faz a mesma coisa que o método acima
	 * (buscarFuncionarioEmailComHQL),
	 * porém utilizando criteria vemos que fica mais prático e fácil pois não
	 * precisamos verificar campo a campo se está preenchido para criar a sintaxe
	 * certa do SQL(cláusulas AND e WHERE). Porém com HQL temos mais liberdade na 
	 * criação do SQL.
	 * **/
	public List<Funcionario> buscarFuncionarioEmailComCriteria(String usuario, String email){
		
		
		String hql= "FROM Funcionario";
		
		Criteria criteria = session.createCriteria(Funcionario.class);
		
		if(usuario!= null){
			criteria.add(Restrictions.ilike("usuario", usuario, MatchMode.ANYWHERE));
		}
		if(email!= null){
			criteria.add(Restrictions.ilike("email", email, MatchMode.ANYWHERE));
		}
		
		return criteria.list();
	}
	/**
	 * Realiza a busca levando em consideração o funcionário passado por parâmetro,
	 * verifica todos os atributos do objeto funcionario que estiverem preenchidos.
	 * MatchMode.ANYWHERE = % string % (qualquer coisa antes ou depois)
	 * 
	 * Obs: Recomendado utilizar essa maneira caso a verificação
	 * de campos preenchidos fique muito grande.
	 * **/
	public List<Funcionario> buscaPorExemplo(Funcionario f){
		Example example = Example.create(f)
								.ignoreCase()
								.enableLike(MatchMode.ANYWHERE);
		
		return session.createCriteria(Funcionario.class).add(example).list();
	}
	
	public Funcionario load(long id)
	{
		return (Funcionario) session.load(Funcionario.class, id);
	}
	public void save(Funcionario f)
	{	org.hibernate.Transaction tx = session.beginTransaction();
		session.saveOrUpdate(f);
		
		tx.commit();
	}
	public void delete(Funcionario f)
	{
		session.delete(f);
	}

}
