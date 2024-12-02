package Controller;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Model.Administrador;
import Model.AdministradorDAO;
import Model.PessoaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdministradorController")
public class AdministradorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdministradorDAO aDAO;
	private PessoaDAO pDAO;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoTrab3");
	
	@Override
	public void init() throws ServletException{
		aDAO = new AdministradorDAO(emf);
		pDAO = new PessoaDAO(emf);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacao = request.getParameter("operacao");
		operacao = operacao.toLowerCase();
		
		switch(operacao) {
		case "sair":
			logoutAdm(request,response);
			break;
		case "excluir":
			excluirAdm(request,response);
			break;
		default :
			System.out.println("//doGet-administradorController");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacao = request.getParameter("operacao");
		operacao = operacao.toLowerCase();
		
		switch(operacao) {
		case "cadastrar":
			cadastrarAdministrador(request, response);
			break;
		case "logar":
			fazerLogin(request,response);
			break;
		case "excluir":
			excluirAdm(request,response);
			break;

		default:
			System.out.println("Erro! Operação não encontrada. //doPost-AdministradorController");
		}
	}
	
	private void fazerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Administrador adm = aDAO.procurarAdministrador(login);
		
		if(adm == null|| !adm.getSenha().equals(senha)) {
			System.out.println("Nao ha administradores com estas informacoes");

			request.setAttribute("loginError", "Não há login com esses credenciais!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginAdm.jsp");
			dispatcher.forward(request, response);
			
		}
		else if(adm.getLogin().equals(login) && adm.getSenha().equals(senha)) {
			HttpSession session = request.getSession();
			session.setAttribute("administrador", adm);
			

			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			
		}
	}

	private void cadastrarAdministrador(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nome = request.getParameter("nome");		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		
		Administrador adm = aDAO.inserirAdministrador(login, senha, nome,cpf,email,telefone);
		
		HttpSession session = request.getSession();
		session.setAttribute("administrador", adm);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	private void logoutAdm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginAdm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void excluirAdm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String login = ((Administrador) session.getAttribute("administrador")).getLogin();
		
		pDAO.excluirPessoas(aDAO.procurarAdministrador(login));
		aDAO.excluirAdm(login);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginAdm.jsp");
		dispatcher.forward(request, response);
	}
}


