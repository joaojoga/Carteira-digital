package Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Model.Administrador;
import Model.Pessoa;
import Model.PessoaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PessoaDAO pDAO;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoTrab3");
	
	@Override
	public void init() throws ServletException{
		pDAO = new PessoaDAO(emf);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operacao = request.getParameter("operacao");
		operacao = operacao.toLowerCase();
		
		switch(operacao){
			case "listar":
				listarPessoa(request,response);
				break;
			case "remover":
				removerPessoa(request,response);
				break;
			case "buscar":
				buscarPessoa(request,response);
				break;
			case "operacoes":
				String cpf =request.getParameter("cpf");
				request.setAttribute("cpf",cpf );
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Operacoes.jsp");
				dispatcher.forward(request, response);
				break;
			case "finalizar":
				String opcao = request.getParameter("metodo");
				String cpf2 = request.getParameter("cpf2");
				Pessoa p = pDAO.procurarPessoa(cpf2);
				switch(opcao) {
				
				case "1":
					request.setAttribute("metodo", p.calcSalarioAnual());
					request.setAttribute("resposta", "Salário Anual");;
					break;
				case "2":
					request.setAttribute("metodo", p.calcSalarioAnualImposto());
					request.setAttribute("resposta", "Salário Anual - Pós Imposto");
					break;
				case "3":	
					request.setAttribute("metodo", p.calcAposentadoria());
					request.setAttribute("resposta", "Aposentadoria");
					break;
				case "4":
					request.setAttribute("metodo", p.calcSalarioAposentadoria());
					request.setAttribute("resposta", "Salário Aposentadoria");
					break;		
				}
				request.setAttribute("pessoa",p);

				RequestDispatcher dispatcherOperacao = request.getRequestDispatcher("/CarteiraDigital.jsp");
				dispatcherOperacao.forward(request, response);
				break;
			default:
				System.out.println("Erro! Escolha uma operação valida!");
				break;
				
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operacao = request.getParameter("operacao");
		operacao = operacao.toLowerCase();
		
		switch(operacao){
			case "atualizar": 
				atualizarPessoa(request,response);
				break;
			case "cadastrar":
				cadastrarPessoa(request,response);
				break;
			default:
				System.out.println("Erro! Escolha uma operação valida!");
				break;
		}	
	
	}
	
	private void listarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Administrador administrador = (Administrador) session.getAttribute("administrador"); 
		
		ArrayList<Pessoa> listaPessoas = pDAO.consultarPessoas(administrador);
		request.setAttribute("pessoasBanco", listaPessoas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Listar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void cadastrarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String  nome = request.getParameter("nome");
		
		char sexo =  request.getParameter("sexo").charAt(0);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate dataNasc =  LocalDate.parse(request.getParameter("dataNasc"),formatter);
	
		String rg = request.getParameter("rg");
		
		String cpf = request.getParameter("cpf");
		
		double salario = Double.parseDouble(request.getParameter("salario"));
		
		int tempoContribuicao = Integer.parseInt(request.getParameter("tempoContribuicao"));
		
		String areaTrabalho = request.getParameter("areaTrabalho");
		Administrador administrador = (Administrador) session.getAttribute("administrador"); 
		
		boolean inseriu = pDAO.inserirPessoa(nome, sexo, dataNasc, rg, cpf, salario, tempoContribuicao, areaTrabalho,administrador);
		
		request.setAttribute("status", inseriu);
		request.setAttribute("operacao", "cadastrada");
		
		ArrayList<Pessoa> listaPessoas = pDAO.consultarPessoas(administrador);
		request.setAttribute("pessoasBanco", listaPessoas);
		
		if (inseriu) {		    		
			String nomeEncode = URLEncoder.encode(nome, "UTF-8");
	        String rgEncode = URLEncoder.encode(rg, "UTF-8");
	        String cpfEncode = URLEncoder.encode(cpf, "UTF-8");
	        
			Cookie nomeCookie = new Cookie("nome"+administrador.getLogin(), nomeEncode);
			Cookie rgCookie = new Cookie("rg"+administrador.getLogin(), rgEncode);
			Cookie cpfCookie = new Cookie("cpf"+administrador.getLogin(), cpfEncode);
			
	        nomeCookie.setMaxAge(60 * 60 * 24); 
	        nomeCookie.setPath("/"); 
	        
	        rgCookie.setMaxAge(60 * 60 * 24); 
	        rgCookie.setPath("/"); 
	        
	        cpfCookie.setMaxAge(60 * 60 * 24); 
	        cpfCookie.setPath("/"); 
	        
	        response.addCookie(nomeCookie);
	        response.addCookie(rgCookie);
	        response.addCookie(cpfCookie);
		        
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Listar.jsp");
		dispatcher.forward(request, response);
		
	}
	private void removerPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Administrador administrador = (Administrador) session.getAttribute("administrador"); 
		
		String cpf = request.getParameter("cpf");
		boolean excluiu = pDAO.excluirPessoa(cpf);
		
		request.setAttribute("status", excluiu);
		request.setAttribute("operacao", "removida");
		
		ArrayList<Pessoa> listaPessoas = pDAO.consultarPessoas(administrador);
		request.setAttribute("pessoasBanco", listaPessoas);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Listar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void buscarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String cpf = request.getParameter("cpf");
		Pessoa pessoaBanco = pDAO.procurarPessoa(cpf); 
		
		request.setAttribute("pessoa", pessoaBanco);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/atualizarPessoa.jsp");
		dispatcher.forward(request,response);
	}
	
	private void atualizarPessoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		Administrador administrador = (Administrador) session.getAttribute("administrador");
		String nome = request.getParameter("nome");
		char sexo = request.getParameter("sexo").charAt(0);
		String rg = request.getParameter("rg");
		LocalDate dataNasc = LocalDate.parse( request.getParameter("dataNasc"));
		double salario = Double.parseDouble(request.getParameter("salario"));
		int tempoContribuicao = Integer.parseInt(request.getParameter("tempoContribuicao"));
		String areaTrabalho = request.getParameter("areaTrabalho");
		String cpf = request.getParameter("cpf");
		
		boolean atualizou = pDAO.modificarPessoa(nome,sexo,dataNasc,rg,cpf,salario,tempoContribuicao, areaTrabalho,administrador);
		
		ArrayList<Pessoa> listaPessoas = pDAO.consultarPessoas(administrador);
		request.setAttribute("pessoasBanco", listaPessoas);
		
		request.setAttribute("status", atualizou);
		request.setAttribute("operacao", "atualizada");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Listar.jsp");
		dispatcher.forward(request,response);
	}

}
