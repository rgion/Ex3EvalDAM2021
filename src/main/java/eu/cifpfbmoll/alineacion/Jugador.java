/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cifpfbmoll.alineacion;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafa
 */
public class Jugador extends Persona {

    private int dorsal;
    private String demarcacion;

    public Jugador() {
    }
    public Jugador(Jugador copia) {
        super((Persona) copia);
        this.setDorsal(copia.getDorsal());
        this.setDemarcacion(copia.getDemarcacion());
    }
    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getDemarcacion() {
        return demarcacion;
    }

    public void setDemarcacion(String demarcacion) {
        this.demarcacion = demarcacion;
    }

    @Override
    public String toString() {
        return super.toString() + "Jugador{" + "orsal=" + dorsal + ", demarcaci\u00f3n=" + demarcacion + '}';
    }

    public static void insertarJugador(Connection con) throws SQLException {

        Jugador j1 = new Jugador();
        Scanner lector = new Scanner(System.in);
        PreparedStatement pst = null;
        String sql = "insert into jugador (dorsal, nombre, apellido, demarcacion) values (?,?,?,?)";

        solicitarDatosJugador(j1, lector);
        pst = (PreparedStatement) con.prepareStatement(sql);
        pst.setInt(1, j1.getDorsal());
        pst.setString(2, j1.getNombre());
        pst.setString(3, j1.getApellido());
        pst.setString(4, j1.getDemarcacion());
        pst.executeUpdate();
        pst.close();

    }

    public static void solicitarDatosJugador(Jugador j1, Scanner lector) {
        System.out.println("Dame el nombre del jugador");
        j1.setNombre(lector.nextLine());
        System.out.println("Dame el apellido del jugador");
        j1.setApellido(lector.nextLine());
        System.out.println("Dame la demarcación del jugador");
        j1.setDemarcacion(lector.nextLine());
        System.out.println("Dame el dorsal del jugador");
        j1.setDorsal(lector.nextInt());
        lector.nextLine();
    }

}
