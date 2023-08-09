package servlet;

import db.database;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

@WebServlet(name="register_Servlet",urlPatterns ={"/doRegister"})

public class register_Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //解决乱码问题
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取请求参数
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String pwd = request.getParameter("pwd");
        PrintWriter out = response.getWriter();
        out.println(name + " " + pwd + " " + sex + "\n");
        try {
            //连接数据库，将注册成功人员加入表内
            database da=new database();
            if(da.register(name)==null)
            {
                da.my_insert(name,pwd,sex);
                out.println("注册成功！"+"<br>"+"<a href=\"http://localhost:8080/exp_four_war_exploded/login\">进行登录</a>");
            }
            else
                out.println("该人员已经注册过了，请登录！"+"<br>"+"<a href=\"http://localhost:8080/exp_four_war_exploded/login\">进行登录</a>");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("注册失败1！"+e);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            out.println("注册失败2！"+throwables);
        }

    }

        protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
        response) throws javax.servlet.ServletException, IOException {
            doPost(request, response);
        }
}
