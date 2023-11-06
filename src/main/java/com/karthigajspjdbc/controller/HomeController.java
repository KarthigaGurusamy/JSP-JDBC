package com.karthigajspjdbc.controller;

import com.karthigajspjdbc.dao.TodoDao;
import com.karthigajspjdbc.dao.UserDao;
import com.karthigajspjdbc.models.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeController extends HttpServlet {

    private final TodoDao todoDao;
    private final UserDao userDao;
    public HomeController() {
        todoDao = new TodoDao();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            todoDao.deleteTodo(Integer.parseInt(id));
        }

        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        String userId = req.getSession().getAttribute("id").toString();
        String item = req.getParameter("item");

        if(item!=null && item.trim().length()>0)
        {
            todoDao.AddItem(item, Integer.parseInt(userId));
        }

        String name = userDao.getName(Integer.parseInt(userId));
        List<Todo> items = todoDao.selectAllTodos(Integer.parseInt(userId));
        req.setAttribute("items", items);
        dispatcher.forward(req, resp);
    }


}
