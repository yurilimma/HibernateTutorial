package tabelas;
// Generated 22/01/2017 13:41:04 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name="Contapagar", schema="sakila")
public class Contapagar
{
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Fornecedor fornecedor;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="pago")
	private Boolean pago;
	
	@Column(name="valor")
	private Double valor;

	public Contapagar() {
	}

	public Contapagar(int id) {
		this.id = id;
	}

	public Contapagar(int id, Fornecedor fornecedor, Date data, String descricao, Boolean pago, Double valor) {
		this.id = id;
		this.fornecedor = fornecedor;
		this.data = data;
		this.descricao = descricao;
		this.pago = pago;
		this.valor = valor;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getPago() {
		return this.pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
