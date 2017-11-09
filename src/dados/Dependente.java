package dados;

import java.io.Serializable;
import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

public class Dependente implements Serializable{

	private String nome;
	private String tipoDependenica;
	private GregorianCalendar nascimento;
	
	public Dependente(String nome, String tipoDependenica,
			GregorianCalendar nascimento) {
		this.nome = nome;
		this.tipoDependenica = tipoDependenica;
		this.nascimento = nascimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipoDependenica() {
		return tipoDependenica;
	}
	public void setTipoDependenica(String tipoDependenica) {
		this.tipoDependenica = tipoDependenica;
	}
	public GregorianCalendar getNascimento() {
		return nascimento;
	}
	public void setNascimento(GregorianCalendar nascimento) {
		this.nascimento = nascimento;
	}
	@Override
	public String toString() {
		return "NOME: " + nome + "\n" +
				"TIPO: " + tipoDependenica + "\n" +
				"NASCIMENTO: " + LtpUtil.formatarData(nascimento, "dd/MM/yyyy") + "\n";
	}
	
	
}
