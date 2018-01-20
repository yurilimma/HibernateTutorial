package tabelas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Endereco", schema="sakila")
public class Endereco {
	
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Obs: lembrar de sempre que adicionar uma nova classe 
	 * vinculada ao banco de dados, adiciona-la no arquivo
	 * hibernate.cfg.xml
	 */
	@OneToOne(mappedBy="endereco")
	private Funcionario funcionario;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="rua")
	private String rua;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="cep")
	private String cep;
	
	public Endereco(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	

}
