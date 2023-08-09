package servlet;

import db.database;
import module.userdata;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "login_Servlet",urlPatterns ={"/doLogin"})
public class login_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取请求参数
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        PrintWriter out = response.getWriter();
        out.println(name + " " + pwd  + "\n");
        try {
            //连接数据库，将注册成功人员加入表内
            database da=new database();
            if(da.login(name,pwd)==null)
                out.println("该人员不存在或者密码错误，选择注册！"+"<br>"+"<a href=\"http://localhost:8080/exp_four_war_exploded/register\">进行注册</a>");
            else {
                userdata myda=da.login(name,pwd);
                out.println("@"+myda.getName()+" "+myda.getPassword()+" "+myda.getSex()+" "+"欢迎登陆！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("登录失败1！"+e);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            out.println("登录失败2！"+throwables);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
