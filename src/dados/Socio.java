package dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

public class Socio  implements Serializable{

	private int codigo;
	private String nome;
	private String cpf;
	private GregorianCalendar entrada;
	private GregorianCalendar saida;
	
	private ArrayList<Dependente> listaDep = new ArrayList<Dependente>();
	
	private static int seq = 0;

	public Socio(String nome, String cpf, GregorianCalendar entrada) {
		codigo = ++seq;
		this.nome = nome;
		this.cpf = cpf;
		this.entrada = entrada;	
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public GregorianCalendar getEntrada() {
		return entrada;
	}

	public void setEntrada(GregorianCalendar entrada) {
		this.entrada = entrada;
	}

	public GregorianCalendar getSaida() {
		return saida;
	}

	public void setSaida(GregorianCalendar saida) {
		this.saida = saida;
	}

	public ArrayList<Dependente> getListaDep() {
		return listaDep;
	}

	public void setListaDep(ArrayList<Dependente> listaDep) {
		this.listaDep = listaDep;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String toString(){
		return
				"Código: " + codigo + "\n"+
				"Nome: " + nome + "\n" +
				"CPF: " + LtpUtil.formatarCPF(cpf) + "\n" +
				"Entrada: " + LtpUtil.formatarData(entrada, "dd/MM/yyyy") + "\n" +
				"Saida: " + (saida==null ? "" : LtpUtil.formatarData(saida, "dd/MM/yyyy")) + "\n";
	}
	
	private void incluirDep(Dependente objDep){
		listaDep.add(objDep);
	}
	
	private void removerDep(Dependente objDep){
		listaDep.remove(objDep);
	}
	
	private Dependente pesqDepNome(String nome) throws Exception{
		for (Dependente objDep : listaDep){
			if (objDep.getNome().equalsIgnoreCase(nome)){
				return objDep;
			}
		}
		throw new Exception("Não Existe Dependente para o nome.");
	}
	
}
