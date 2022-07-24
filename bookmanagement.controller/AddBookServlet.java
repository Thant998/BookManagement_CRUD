package bookmanagement.controller;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmanagement.dao.BookDAO;
import bookmanagement.dto.BookRequestDTO;
import bookmanagement.model.BookBean;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		BookBean bookObj=new BookBean();
		bookObj.setCode(request.getParameter("code"));
		bookObj.setTitle(request.getParameter("title"));
		bookObj.setAuthor(request.getParameter("author"));
		bookObj.setPrize(request.getParameter("prize"));
		if (bookObj.getCode().equals("") || bookObj.getTitle().equals("") || bookObj.getAuthor().equals("") || bookObj.getPrize().equals("") ) {
			request.setAttribute("error","Fill the blank!!!");
			request.setAttribute("bean",bookObj);
			request.getRequestDispatcher("addBook.jsp").forward(request,response);
		}else {
			BookDAO dao=new BookDAO();
			BookRequestDTO dto=new BookRequestDTO();
			dto.setBookCode(bookObj.getCode());
			dto.setBookTitle(bookObj.getTitle());
			dto.setBookAuthor(bookObj.getAuthor());
			dto.setBookPrice(Double.valueOf(bookObj.getPrize()));
			dao.insertData(dto);
			response.sendRedirect("DisplayBookServlet");
			
		}
		
	}

}
