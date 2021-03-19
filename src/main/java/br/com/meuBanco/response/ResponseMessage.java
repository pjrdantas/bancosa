package br.com.meuBanco.response;

public class ResponseMessage {
	 
	private int codigo;
	private String mensagem;
 
	public ResponseMessage() {}
 
	public ResponseMessage(int codigo, String mensagem) {
		this.codigo   = codigo;
		this.mensagem =  mensagem;
	}
 
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensagem() {
		return mensagem;
	}
 
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
 
}