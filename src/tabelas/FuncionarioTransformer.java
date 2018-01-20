package tabelas;

import java.util.List;

import org.hibernate.transform.ResultTransformer;

public class FuncionarioTransformer implements ResultTransformer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2700144642161138670L;

	public Object transformTuple(Object [] values, String[] aliases){
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome((String) values[0]);
		funcionario.setEmail((String) values[1]);
		funcionario.setUsuario((String) values[2]);
		funcionario.setSenha((String) values[3]);
		
		return funcionario;
		
	}
	
	public List transformList(List list){
		return list;
	}

}
