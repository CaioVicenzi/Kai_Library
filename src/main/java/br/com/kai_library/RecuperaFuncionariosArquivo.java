package br.com.kai_library;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.kai_library.dao.EmployeeDao;
import br.com.kai_library.model.Employee;
import br.com.kai_library.model.Gender;
import br.com.kai_library.util.JPAUtil;

public class RecuperaFuncionariosArquivo {
	
	public static void main(String[] args) throws FileNotFoundException {
		// caminho onde está o arquivo com os dados que serão usados
        String file = "C:\\Users\\caiom\\OneDrive\\Área de Trabalho\\cai-workspace\\empregados.json";
        
        // criando um entity manager e a dao        
        EntityManager em = JPAUtil.getEntityManager();
        EmployeeDao dao = new EmployeeDao(em);
        
        List<Employee> listaEmpregados = null;
		try {
			listaEmpregados = extrairEmpregados(file);
        } catch (IOException | ParseException e) {
			e.printStackTrace();
			System.out.println("Arquivo não encontrado...");
		}
		
		// persistindo os dados no banco
		em.getTransaction().begin();
		listaEmpregados.forEach(empregado -> dao.salvar(empregado));
		em.getTransaction().commit();
		
	}

	
	// método cujo objetivo é receber um caminho para um arquivo e devolver uma lista de empregados
	private static List<Employee> extrairEmpregados(String file) throws IOException, ParseException, FileNotFoundException {
		JSONParser jsonp = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonp.parse(new FileReader(file));
		
		// essa parte do código basicamente extrai todos os objetos do tipo Employee do arquivo json cujo caminho
		// foi passado como argumento
		@SuppressWarnings("unchecked")
		List<JSONObject> listaEmpregadosJson = (List<JSONObject>) jsonObject.get("employees");
		List<Employee> listaEmpregados = new ArrayList<Employee>();
		for (JSONObject empregadoJson : listaEmpregadosJson) {
			Employee empregado = 
					new Employee(
							(String) empregadoJson.get("firstName"), 
							(String) empregadoJson.get("lastName"),
							Gender.valueOf((String) empregadoJson.get("gender")),
							(Integer) Math.toIntExact((Long) empregadoJson.get("age")) ,
							(String) empregadoJson.get("number"));
			listaEmpregados.add(empregado);
		}
		
		// retorna a lista de objetos do tipo Employee
		return listaEmpregados;
	}
}
