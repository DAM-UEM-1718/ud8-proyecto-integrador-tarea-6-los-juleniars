package model;

import org.apache.commons.lang3.RandomStringUtils;
import view.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Vector;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.swing.table.DefaultTableModel;

public class Modelo {

    VistaLogin vistaLogin;
    VistaRecuperarPswd vistaRecuperarPswd;
    VistaConfiguracion vistaConfiguracion;
    VistaPrincipalTutor vistaPrincipalTutor;
    VistaPrincipalAdministrativo vistaPrincipalAdministrativo;

    private final String DATABASE = "gestionpracticas";
    private final String USER = "root";
    private final String PASSWORD = "root";
    //Cambiar por la IP del servidor de la base de datos
    private final String HOST = "192.168.33.10";
    private final String URL = "jdbc:mysql://" + HOST + "/";
    private Connection connection;

    private String usuarioActual;
    private String nombreUsuario;
    private byte tipoUsuario;
    private byte intentos;

    public Modelo(VistaLogin vistaLogin) {
        intentos = 0;
        this.vistaLogin = vistaLogin;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
        } catch (Exception e) {
            this.vistaLogin.error("No ha sido posible conectarse a la base de datos.");
            e.printStackTrace();
        }
    }

    public void setVistaLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
    }

    public void setVistaRecuperarPswd(VistaRecuperarPswd vistaRecuperarPswd) {
        this.vistaRecuperarPswd = vistaRecuperarPswd;
    }

    //Método para generar un hash de la contraseña utilizando el algoritmo SHA-256
    private static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    //Convierte un array de bytes a un String
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    //Método de inicio de sesión
    public void iniciarSesion(String user, String password) {
        try {
            password = hash256(password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT PWD, ROLE, COUNT(*), NOMBRE FROM USERS WHERE USR = ?;");
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(3) > 0) {
                if (resultSet.getString(1).equals(password)) {
                    tipoUsuario = resultSet.getByte(2);
                    intentos = 0;
                    usuarioActual = user;
                    nombreUsuario = resultSet.getString("NOMBRE");
                    switch (tipoUsuario) {
                        case 0:
                            vistaPrincipalTutor.getLblBienvenido().setText("Bienvenido " + nombreUsuario);
                            break;
                        case 1:
                            vistaPrincipalAdministrativo.getLblBienvenido().setText("Bienvenido " + nombreUsuario);
                    }
                    vistaLogin.sesionIniciada(tipoUsuario);
                } else {
                    intentos++;
                    if (intentos < 3) {
                        vistaLogin.error("Usuario o contraseña incorrectos");
                    } else {
                        vistaLogin.intentosSuperados();
                    }
                }
            } else {
                vistaLogin.error("Usuario o contraseña incorrectos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Método para generar una contraseña aleatoria
    private String contrasenaAleatoria() {
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
        String randomStr = RandomStringUtils.random(7, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
        return randomStr;
    }

    //Método que genera una nueva contraseña para un usuario, se la envía por mail y la inserta en la base de datos
    public void recuperarContrasena(String user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAIL FROM USERS WHERE USR = ?;");
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String mail = resultSet.getString(1);
            String nuevaContrasena = contrasenaAleatoria();
            String hashContrasena = hash256(nuevaContrasena);

            //Enviar nueva contraseña por mail
            enviarMail(mail, "Nueva contraseña.", "Se le ha asignado la siguiente contraseña: " + nuevaContrasena);

            //Insertar nueva contraseña en la base de datos después de haberla enviado
            PreparedStatement prstm = connection.prepareStatement("UPDATE USERS SET PWD = ? WHERE USR = ?;");
            prstm.setString(1, hashContrasena);
            prstm.setString(2, user);
            int rows = prstm.executeUpdate();

            vistaLogin.contrasenaEnviada();
            ;
        } catch (Exception e) {
            e.printStackTrace();
            vistaLogin.error("Error al generar la nueva contraseña.");
        }
    }

    //Método que realiza una petición HTTP POST para enviar un mail a través de la API de Mailgun
    private void enviarMail(String mailEnvio, String asunto, String contenido) {
        final String DOMINIO = "mg.julen.gq";
        final String MAILGUN_API_KEY = "key-7679fa660b4aa69e9bf7a624030c591b";
        try {
            HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + DOMINIO + "/messages")
                    .basicAuth("api", MAILGUN_API_KEY)
                    .queryString("from", "Gestión Prácticas CFGS - UEM <noreply@uemgestionpracticas.com>")
                    .queryString("to", mailEnvio)
                    .queryString("subject", asunto)
                    .queryString("html", contenido)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    //Método que crea un usuario, lo mete en la base de datos y le envía una contraseña aleatoria
    public void generarUsuario(String expediente, String mail, byte role) {
        try {
            String contrasenaAleatoria = contrasenaAleatoria();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS (USR, PWD, ROLE, MAIL) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, expediente);
            preparedStatement.setString(2, hash256(contrasenaAleatoria));
            preparedStatement.setInt(3, role);
            preparedStatement.setString(4, mail);
            String contenidoMail = "<html><body>Bienvenido a la aplicación de Gestión de Prácticas de CFGS de la Universidad Europea. <br>" +
                    "Su usuario es: " + expediente + "<br>" +
                    "Su contraseña es: " + contrasenaAleatoria +
                    "</body></html>";
            enviarMail(mail, "Nuevo Usuario", contenidoMail);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            vistaLogin.error("Error al crear el usuario.");
        }

    }

    //Método para cambar la contraseña de un usuario
    public void cambiarContrasena(String nuevaContrasena) {
        try {
            String nuevoHash = hash256(nuevaContrasena);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USERS SET PWD = ? WHERE USR = ?;");
            preparedStatement.setString(1, nuevoHash);
            preparedStatement.setString(2, usuarioActual);
            preparedStatement.executeUpdate();
            vistaConfiguracion.error("Contraseña cambiada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            vistaConfiguracion.error("No ha sido posible cambiar la contraseña.");
        }
    }

    public void setVistaConfiguracion(VistaConfiguracion vistaConfiguracion) {
        this.vistaConfiguracion = vistaConfiguracion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setVistaPrincipalTutor(VistaPrincipalTutor vistaPrincipalTutor) {
        this.vistaPrincipalTutor = vistaPrincipalTutor;
    }

    public void setVistaPrincipalAdministrativo(VistaPrincipalAdministrativo vistaPrincipalAdministrativo) {
        this.vistaPrincipalAdministrativo = vistaPrincipalAdministrativo;
    }

    public void cerrarSesion() {
        tipoUsuario = -1;
        nombreUsuario = null;
        usuarioActual = null;
    }

    public DefaultTableModel modeloAlumnos() {
        try {
            return crearModelo(connection.prepareStatement("SELECT NUM_MAT FROM ESTUDIANTE;"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DefaultTableModel modeloPracticas() {
        try {
            return crearModelo(connection.prepareStatement("SELECT * FROM EMPRESA_ESTUDIANTE;"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private DefaultTableModel crearModelo(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();

        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(resultSet.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

}
