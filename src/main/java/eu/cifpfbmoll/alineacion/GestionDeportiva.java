/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cifpfbmoll.alineacion;

import static eu.cifpfbmoll.alineacion.Jugador.solicitarDatosJugador;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafa
 */
public class GestionDeportiva {

    private final static String user = "Dam4";
    private final static String password = "Dam2021";
    private final static String database = "jdbc:mysql://51.178.152.234:3306/examen";
    private static final int MAXJUG = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner lector = new Scanner(System.in);
        boolean salir = false;
        Connection con = null;
        try {
            con = establecerConexion();
            try {
                mostrarMenu(salir, lector, con);
            } catch (SQLException ex) {
                System.out.println("Error SQLExcepction - " + ex.getMessage()
                        + "SQLState:" + ex.getSQLState());
            } catch (Exception ex) {
                System.out.println("Error no controlado: " + ex.getMessage());
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            System.out.println("Error SQLExcepction - " + ex.getMessage()
                    + "SQLState:" + ex.getSQLState());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error SQLExcepction - " + ex.getMessage()
                        + "SQLState:" + ex.getSQLState());
            }
        }
    }

    public static void mostrarMenu(boolean salir, Scanner lector, Connection con) throws SQLException, IOException {
        int opcion;
        while (!salir) {

            System.out.println("1. Opcion 1 - Insertar jugador");
            System.out.println("2. Opcion 2 - Crear alineación");
            System.out.println("3. Opcion 3 - Consultar Alineación");
            System.out.println("4. Opcion 4 - Salir");

            System.out.println("Escribe una de las opciones");
            opcion = lector.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("1. Opcion 1 - Insertar jugador");
                    Jugador.insertarJugador((com.mysql.jdbc.Connection) con);

                    break;
                case 2:
                    System.out.println("2. Opcion 2 - Crear alineación");
                    crearAlineacion((com.mysql.jdbc.Connection) con);
                    break;
                case 3:
                    System.out.println("3. Opcion 3 - Consultar Alineación");

                    consultarAlineacion((com.mysql.jdbc.Connection) con);

                    break;

                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Las opciones disponibles son 1,2,3 y 4. "
                            + "Vuelva a intentarlo");
                    break;
            }

        }
    }

    private static Connection establecerConexion() throws SQLException {

        return DriverManager.getConnection(database, user, password);
    }

    public static void crearAlineacion(com.mysql.jdbc.Connection con) {
        try {
            Scanner lector = new Scanner(System.in);
            System.out.println("Dame el id de la alineación");
            int idAux = lector.nextInt();
            lector.nextLine();
            com.mysql.jdbc.PreparedStatement pst = null;

            String sql = "insert into alineacion (id,dorsal) values (?,?)";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 0; i < MAXJUG; i++) {
                System.out.println("Dame el número de dorsal");
                int dorsalAux = lector.nextInt();
                lector.nextLine();
                pst.setInt(1, idAux);
                pst.setInt(2, dorsalAux);
                pst.executeUpdate();
            }
            con.commit();

        } catch (SQLException ex) {
            try {
                System.out.println("Excepcion en transacción. SQLException" + ex.getMessage()
                        + "SQLStateCode" + ex.getSQLState());
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Excepcion en rollback. SQLException" + ex1.getMessage()
                        + "SQLStateCode" + ex1.getSQLState());
            }
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Excepcion en transacción. SQLException" + ex.getMessage()
                        + "SQLStateCode" + ex.getSQLState());
            }
        }

    }

    public static void consultarAlineacion(com.mysql.jdbc.Connection con) throws IOException, SQLException {

        Scanner lector = new Scanner(System.in);
        System.out.println("Dame el id de la alineación");
        int idAux = lector.nextInt();
        lector.nextLine();
        com.mysql.jdbc.PreparedStatement pst = null;

        String sql = "select * from alineacion where id=?";
        pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
        pst.setInt(1, idAux);
        ResultSet rs = pst.executeQuery();
        escribirFichero(rs);
        rs.close();
        pst.close();
    }

    private static void escribirFichero(ResultSet rs) throws SQLException, IOException {
        File nomFich = new File("alineacion.txt");
        String cabecera = "==============Alineación========================";
        String asteriscos = "***********************************************";
        String columnas;
        BufferedWriter fichero = null;

        fichero = new BufferedWriter(new FileWriter(nomFich));
        fichero.write(cabecera);
        fichero.newLine();
        fichero.write(asteriscos);
        fichero.newLine();

        while (rs.next()) {
            rs = null;//provocando un error
            columnas = "id: " + rs.getInt(1);
            fichero.write(columnas);
            columnas = "     dorsal: " + rs.getInt(2);
            fichero.write(columnas);
            fichero.newLine();
        }
        fichero.close();
    }
}
