package usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import dados.Dependente;
import dados.ListaSocios;
import dados.Socio;
import utilitarios.Console;
import utilitarios.LtpUtil;

public class Usuario {

	public static void main(String[] args) {
		if(new File ("SOCIOS.OBJ").exists()){
			lerObjeto();
		}
		menu();
		System.out.println("\nFinal do aplicativo.");
		gravarObjeto();
		System.exit(0);

	}
	private static void lerObjeto() {
		try {
			ObjectInputStream ler = new ObjectInputStream (new FileInputStream("SOCIOS.OBJ"));
			ListaSocios.listaSocios = (ArrayList<Socio>)ler.readObject();
			ler.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());			
			System.exit(1);
		}	
	}

	private static void gravarObjeto() {
		try {
			ObjectOutputStream gravar = new ObjectOutputStream (new FileOutputStream("SOCIOS.OBJ"));
			gravar.writeObject(ListaSocios.listaSocios);
			gravar.close();
		} catch (Exception e) {
		System.out.println(e.getMessage());			
		}	
	}

	private static void menu() {
		int opcao=0;
		do {
			System.out.println("\nCADASTRO DE SÓCIOS");
			System.out.println("1-INCLUIR SÓCIO E DEPENDENTES");
			System.out.println("2-PESQUISAR SÓCIO PELO CPF");
			System.out.println("0-SAIR");
			opcao = Console.readInt("INFORME A OPÇÃO: ");
			switch (opcao) {
			case 1:
				incluir();
				break;
			case 2:
				pesqCPF();
				break;
			case 0: break;	
			default:
				System.out.println("Opção Inválida.");
				break;
			}
		} while (opcao!=0);
		
	}

	private static void pesqCPF() {
		System.out.println("\nPESQUISAR SÓCIO PELO CPF\n");
		try {
			String cpf = Console.readLine("INFORME O CPF: ");
			Socio objSocio = ListaSocios.pesqSocioCpf(cpf);
			System.out.println(objSocio.toString());
			int numDep = objSocio.getListaDep().size();
			if (numDep > 0) {
				System.out.println("\nDependentes do Sócio\n");
				for (Dependente objDep : objSocio.getListaDep()) {
					System.out.println(objDep.toString());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void incluir() {
		System.out.println("\nCADASTRAR SÓCIO E SEUS DEPENDENTES\n");
		String cpf;
		String nome;
		GregorianCalendar entrada = new GregorianCalendar();
		while (true) {
			cpf = Console.readLine("CPF: ");
			if (!LtpUtil.validarCPF(cpf)) {
				System.out.println("CPF inválido.");
				continue;
			}
			try {
				Socio objSocio = ListaSocios.pesqSocioCpf(cpf);
				System.out.println("JÁ EXISTE SÓCIO PARA O CPF - " + objSocio.getNome() );
			} catch (Exception e) {
				break;
			}
		}
		while (true) {
			nome = Console.readLine("NOME: ").trim().toUpperCase();
			if (nome.isEmpty() || LtpUtil.contarPalavras(nome) <= 1) {
				System.out.println("Nome inválido.");
			} else break;
		}
		while (true) {
			String data = Console.readLine("ENTRADA: ");
			if (!LtpUtil.validarData(data, entrada)) {
				System.out.println("Data Inválida.");
				continue;
			}
			if (entrada.after(new GregorianCalendar())) {
				System.out.println("Data entrada superior a data corrente.");
			} else break;
		}
		Socio objSoc = new Socio(nome, cpf, entrada);
		
		ArrayList<Dependente> listaDep = new ArrayList<Dependente>();
		
		int numDep = Console.readInt("INFORME A QUANTIDADE DE DEPENDENTES: ");
		
		if (numDep > 0) {
			
			String nomeDep;
			String tipoDependencia;
			GregorianCalendar nascimento = new GregorianCalendar();
			
			for (int i = 1; i <= numDep; i++) {
				nomeDep:
				while (true){
					nomeDep = Console.readLine("NOME DO DEPENDENTE: ").trim().toUpperCase();
					if (nomeDep.isEmpty()|| LtpUtil.contarPalavras(nomeDep)==1) {
						System.out.println("Nome do dependente inválido.");
						continue;
					}
					for (Dependente objDep : listaDep) {
						if (objDep.getNome().equalsIgnoreCase(nomeDep)) {
							System.out.println("Este dependente já foi informado.");
							continue nomeDep;
						}
					}
					break;

				} 
				
				while (true) {
					tipoDependencia = Console.readLine("TIPO DE DEPENDÊNCIA: ");
					if (tipoDependencia.isEmpty()) {
						System.out.println("Tipo de dependência inválido.");
					} else break;	
				}
				
				while (true) {
					String data = Console.readLine("NASCIMENTO: ");
					if (!LtpUtil.validarData(data, nascimento)) {
						System.out.println("Data Inválida.");
						continue;
					}
					if (nascimento.after(new GregorianCalendar())) {
						System.out.println("Data nascimento superior a data corrente.");
					} else break;
				}
				
				listaDep.add(new Dependente(nomeDep, tipoDependencia, nascimento));
				
			}
			objSoc.setListaDep(listaDep);
			
		}
		
		ListaSocios.incluirSocio(objSoc);
		System.out.println("\nSócio cadastrado.");
		
	}
		
}

