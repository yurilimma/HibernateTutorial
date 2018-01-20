package tabelas;

public class ValorPorFornecedor {
	
	private Fornecedor fornecedor;
	private boolean pago;
	private Double soma;
	
	
	public ValorPorFornecedor(Fornecedor _fornecedor, Boolean _pago, Double _soma){
		this.fornecedor = _fornecedor;
		this.pago = _pago;
		this.soma = _soma;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	public boolean isPago() {
		return pago;
	}


	public void setPago(boolean pago) {
		this.pago = pago;
	}


	public Double getSoma() {
		return soma;
	}


	public void setSoma(Double soma) {
		this.soma = soma;
	}
	
	

}
