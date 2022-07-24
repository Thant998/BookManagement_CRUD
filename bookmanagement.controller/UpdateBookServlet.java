package bookmanagement.controller;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmanagement.dao.BookDAO;
import bookmanagement.dto.BookRequestDTO;
import bookmanagement.dto.BookResponseDTO;
import bookmanagement.model.BookBean;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookRequestDTO dto=new BookRequestDTO();
		dto.setBookCode(request.getParameter("code"));
		BookDAO dao=new BookDAO();
		BookResponseDTO res=dao.selectOne(dto);
		request.setAttribute("res",res);
		request.getRequestDispatcher("updateBook.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookBean bookObj=new BookBean();
		String code=request.getParameter("code");
		bookObj.setCode(code);
		bookObj.setTitle(request.getParameter("title"));
		bookObj.setAuthor(request.getParameter("author"));
		bookObj.setPrize(request.getParameter("prize"));
		if (bookObj.getCode().equals("") || bookObj.getTitle().equals("") || bookObj.getAuthor().equals("") || bookObj.getPrize().equals("") ) {
			request.setAttribute("error","Fill the blank!!!");
			request.setAttribute("bean",bookObj);
			request.getRequestDispatcher("updateBook.jsp").forward(request,response);
		}else {
			BookDAO dao=new BookDAO();
			BookRequestDTO dto=new BookRequestDTO();
			dto.setBookCode(bookObj.getCode());
			dto.setBookTitle(bookObj.getTitle());
			dto.setBookAuthor(bookObj.getAuthor());
			dto.setBookPrice(Double.valueOf(bookObj.getPrize()));
			dao.updateData(dto);
			response.sendRedirect("DisplayBookServlet");
		}
	}
}
