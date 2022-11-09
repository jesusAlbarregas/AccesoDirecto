/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Ave;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "AccesoBD", urlPatterns = {"/AccesoBD"})
public class AccesoBD extends HttpServlet {

    @Override
    public void init(ServletConfig config) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("No existe el driver");
        }

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = null;
        try {
            Connection conexion = null;
            Statement sentencia = null;
            PreparedStatement preparada = null;
            ResultSet resultado = null;
            Ave ave = null;
            List<Ave> aves = null;

            String cadenaConexion = "jdbc:mysql://localhost:3306/pruebasJAVA";
            conexion = DriverManager.getConnection(cadenaConexion, "java2023", "2023");
            String anilla = request.getParameter("anilla");

            String sql = null;
            String boton = request.getParameter("boton");

            switch (boton) {
                case "Anilla":
                    if (anilla != null) {

                        sql = "SELECT * FROM aves WHERE anilla = ?";
                        preparada = conexion.prepareStatement(sql);
                        preparada.setString(1, anilla);
                        try {
                            resultado = preparada.executeQuery();
                            resultado.next();
                            ave = new Ave();
                            ave.setAnilla(resultado.getString("anilla"));
                            ave.setEspecie(resultado.getString(2));
                            ave.setLugar(resultado.getString("lugar"));
                            ave.setFecha(resultado.getString("fecha"));
                            request.setAttribute("ave", ave);
                            url = "JSP/unRegistro.jsp";
                        } catch (SQLException e) {

                            request.setAttribute("aviso", "La anilla " + anilla + " no se encuentra en la base de datos");
                            url = "JSP/aviso.jsp";
                        } finally {
                            
                            if (conexion != null) {
                                conexion.close();
                            }
                        }
                    } else {
                        request.setAttribute("aviso", "Tienes que introducir una anilla");
                        url = "JSP/aviso.jsp";
                    }
                    break;

                case "Todas":
                    try {
                        sql = "SELECT * FROM aves";
                        sentencia = conexion.createStatement();
                        resultado = sentencia.executeQuery(sql);
                        aves = new ArrayList<>();
                        while (resultado.next()) {
                            ave = new Ave();
                            ave.setAnilla(resultado.getString(1));
                            ave.setEspecie(resultado.getString(2));
                            ave.setLugar(resultado.getString(3));
                            ave.setFecha(resultado.getString(4));
                            aves.add(ave);
                        }
                        request.setAttribute("aves", aves);
                        url = "JSP/variosRegistros.jsp";

                    } catch (SQLException e) {
                        if (e.getErrorCode() == 1146) {
                            request.setAttribute("error", "La tabla no existe");
                        } else {
                            request.setAttribute("error", "Ha ocurrido un error al acceder a la tabla");
                        }
                        url = "JSP/error.jsp";

                    } finally {
                        
                        if (conexion != null) {
                            conexion.close();
                        }
                    }
                    break;

                case "Algunas":
                    try {
                        int numero = Integer.parseInt(anilla);
                        if (numero > 0) {
                            sql = "SELECT * FROM aves ORDER BY rand() LIMIT " + numero;
                            try {
                                sentencia = conexion.createStatement();
                                resultado = sentencia.executeQuery(sql);
                                aves = new ArrayList<>();
                                while (resultado.next()) {
                                    ave = new Ave();
                                    ave.setAnilla(resultado.getString(1));
                                    ave.setEspecie(resultado.getString(2));
                                    ave.setLugar(resultado.getString(3));
                                    ave.setFecha(resultado.getString(4));
                                    aves.add(ave);
                                }
                                request.setAttribute("aves", aves);
                                url = "JSP/variosRegistros.jsp";
                            } catch (SQLException e) {
                                request.setAttribute("error", "Ha ocurrido un error al acceder a la tabla");
                                url = "JSP/error.jsp";
                            }
                        } else {
                            request.setAttribute("aviso", "El número tiene que ser mayor que 0");
                            url = "JSP/aviso.jsp";
                        }

                    } catch (NumberFormatException e) {
                        request.setAttribute("aviso", "Tienes que introducir un número válido");
                        url = "JSP/aviso.jsp";
                    } finally {
                        
                        if (conexion != null) {
                            conexion.close();
                        }
                    }
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ha ocurrido un error al acceder a la base de datos");
            url = "JSP/error.jsp";

        }

        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
