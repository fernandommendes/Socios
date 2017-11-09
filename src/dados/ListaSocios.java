package dados;

import java.util.ArrayList;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

public class ListaSocios {

	public static ArrayList<Socio> listaSocios = new ArrayList<Socio>();
	
	public static void incluirSocio(Socio objSocio){
		listaSocios.add(objSocio);
	}
	
	public void excluirSocio(Socio objSocio){
		listaSocios.remove(objSocio);
	}
	
	public static Socio pesqSocioCpf(String cpf) throws Exception{
		for(Socio objSocio : listaSocios){
			if(objSocio.getCpf().equals(cpf)){
				return objSocio;
			}
		}
		throw new Exception("Não Existe sócio para o CPF.");
	}
	
}
