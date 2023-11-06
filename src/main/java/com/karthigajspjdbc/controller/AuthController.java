package com.karthigajspjdbc.controller;

import com.karthigajspjdbc.dao.TodoDao;
import com.karthigajspjdbc.dao.UserDao;
import com.karthigajspjdbc.db.DBConnection;
import com.karthigajspjdbc.models.Todo;
import com.karthigajspjdbc.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AuthController extends HttpServlet {


    private final UserDao userDao;
    private final TodoDao todoDao;
    public AuthController() {
        userDao = new UserDao();
        todoDao = new TodoDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("login")!=null)
        {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User loggedInUser = userDao.loggedInUser(username,password);
            if(loggedInUser!=null)
            {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("id",loggedInUser.getId());
                String userId = req.getSession().getAttribute("id").toString();
                String name = userDao.getName(Integer.parseInt(userId));
                req.setAttribute("name",name);
                List<Todo> items = todoDao.selectAllTodos(Integer.parseInt(userId));
                req.setAttribute("items", items);
                RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
                dispatcher.forward(req,resp);
            }
            else
            {
                req.setAttribute("error",true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req,resp);
            }

        }
        else if(req.getParameter("register")!=null)
        {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String confirmpassword = req.getParameter("confirmpassword");
            if(password.equals(confirmpassword))
            {
                boolean isExistingUser = userDao.getUser(username);
                if(!isExistingUser)
                {
                    userDao.registerUser(username,password);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                    dispatcher.forward(req,resp);
                }
                else
                {
                    req.setAttribute("existingerror",true);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                    dispatcher.forward(req,resp);
                }

            }
            else
            {
                req.setAttribute("registererror",true);
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req,resp);
            }

        }

    }

}
