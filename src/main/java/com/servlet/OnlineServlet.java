package com.servlet;
import com.servlet.model.Todo;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.MalformedInputException;
import java.util.Optional;
import javax.faces.bean.ManagedBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet(
    urlPatterns = "/onlineServlet"
)
@Component
@ManagedBean(name = "onlineServlet")
public class OnlineServlet extends HttpServlet{
    
    static final long serialVersionUID = 35L;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rest = "";
        Writer responseWriter = resp.getWriter();
        try{
            Optional<String> optName = Optional.ofNullable(req.getParameter("id"));

            int id = Integer.valueOf(optName.get());
            Todo info = Service.getTodo(id);
            List<Todo> todoList = new ArrayList<Todo>();
            resp.setStatus(HttpServletResponse.SC_OK);
            todoList.add(info);
            responseWriter.write(Service.todosToHTMLTable(todoList));
            
        }catch(Exception e){
            if(e instanceof FileNotFoundException){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                rest="no existe un item con el identificador dado";
            }
            else if(e instanceof MalformedInputException){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                rest="Error interno en el Servidor ";
            }
            else{
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                rest= "Requerimiento Inválido: debe ingresar un numero";
            }
        }finally{
            responseWriter.write(rest);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> optId = Optional.ofNullable(req.getParameter("id"));
        int id = optId.isPresent() ? Integer.parseInt(optId.get()) : -1;
        Writer responseWriter = resp.getWriter();
        responseWriter.write("Hiciste un POST! " + id);

    }
}