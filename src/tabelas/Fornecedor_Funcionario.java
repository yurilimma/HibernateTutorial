package tabelas;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="fornecedor_funcionario", schema="sakila")
public class Fornecedor_Funcionario {
	
	private int fornecedores_id;
	private int contatos_id;
	
	public Fornecedor_Funcionario(){
		
	}
	public int getFornecedores_id() {
		return fornecedores_id;
	}
	public void setFornecedores_id(int fornecedores_id) {
		this.fornecedores_id = fornecedores_id;
	}
	public int getContatos_id() {
		return contatos_id;
	}
	public void setContatos_id(int contatos_id) {
		this.contatos_id = contatos_id;
	}
	

}
