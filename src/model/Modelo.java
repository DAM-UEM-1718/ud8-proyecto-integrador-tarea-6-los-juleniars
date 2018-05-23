package model;

import org.apache.commons.lang3.RandomStringUtils;
import view.*;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Vector;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.swing.table.DefaultTableModel;

public class Modelo {

    private VistaLogin vistaLogin;
    private VistaRecuperarPswd vistaRecuperarPswd;
    private VistaConfiguracion vistaConfiguracion;
    private VistaPrincipalTutor vistaPrincipalTutor;
    private VistaPrincipalAdministrativo vistaPrincipalAdministrativo;

    private final String DATABASE = "gestionpracticas";
    private String USER;
    private String PASSWORD;
    //Cambiar por la IP del servidor de la base de datos
    private String HOST;
    private String URL;
    private Connection connection;

    private String nombreUsuario;
    private String nombreUsuarioFormal;
    private byte tipoUsuario;
    private byte intentos;
    private int codGrupo;

    public Modelo(VistaLogin vistaLogin) {
        leerConfiguracion();
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
        StringBuilder result = new StringBuilder();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    //Método de inicio de sesión
    public void iniciarSesion(String user, String password) {
        try {
            password = hash256(password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT PWD, ROLE, NOMBRE FROM USERS WHERE USR = ?;");
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString(1).equals(password)) {
                    tipoUsuario = resultSet.getByte(2);
                    intentos = 0;
                    nombreUsuario = user;
                    nombreUsuarioFormal = resultSet.getString("NOMBRE");
                    vistaLogin.sesionIniciada();
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

    private void leerConfiguracion() {
        Properties propiedades = new Properties();
        try {
            FileInputStream ficheroPropiedades = new FileInputStream(new File("config.ini"));
            propiedades.load(ficheroPropiedades);
            USER = propiedades.getProperty("user");
            PASSWORD = propiedades.getProperty("password");
            HOST = propiedades.getProperty("host");
            URL = "jdbc:mysql://" + HOST + "/";
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //Método para generar una contraseña aleatoria
    @SuppressWarnings("RedundantStringConstructorCall")
    private String contrasenaAleatoria() {
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
        return RandomStringUtils.random(7, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
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
            preparedStatement.setString(2, nombreUsuario);
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
        return nombreUsuarioFormal;
    }

    public void setVistaPrincipalTutor(VistaPrincipalTutor vistaPrincipalTutor) {
        this.vistaPrincipalTutor = vistaPrincipalTutor;
    }

    public void setVistaPrincipalAdministrativo(VistaPrincipalAdministrativo vistaPrincipalAdministrativo) {
        this.vistaPrincipalAdministrativo = vistaPrincipalAdministrativo;
    }

    public void cerrarSesion() {
        tipoUsuario = -1;
        nombreUsuarioFormal = null;
        nombreUsuario = null;
    }

    public DefaultTableModel modeloAlumnos() {
        try {
            return crearModelo(new Vector(), connection.prepareStatement("SELECT NUM_MAT FROM ESTUDIANTE;"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DefaultTableModel modeloPracticas() {
        String[] arrayNombres = {"Estudiante", "Empresa", "Tutor Emp.", "F. Inicio", "F. Fin", "Horario", "Localización", "Erasmus", "Estado"};
        Vector<String> nombreColumnas = new Vector<>(Arrays.asList(arrayNombres));
        try {
            return crearModelo(nombreColumnas, connection.prepareStatement("SELECT ESTUDIANTE.NOM, NOM_EMPR, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO FROM EMPRESA_ESTUDIANTE, ESTUDIANTE, EMPRESA;"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private DefaultTableModel crearModelo(Vector nombreColumnas, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numeroColumnas = metaData.getColumnCount();

        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= numeroColumnas; columnIndex++) {
                vector.add(resultSet.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, nombreColumnas);

    }

    public byte getTipoUsuario() {
        return tipoUsuario;
    }

    public void mostrarGrupoTutor() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT NOM_GRUPO, COD_GRUPO FROM GRUPO WHERE USR = ?;");
            preparedStatement.setString(1, nombreUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vistaPrincipalTutor.getComboBox().addItem(new ComboItem(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPracticasTutor() {
        String[] arrayNombres = {"Prácticas Asignadas", "Prácticas por asignar"};
        Vector<String> nombreColumnas = new Vector<>(Arrays.asList(arrayNombres));
        try {
            PreparedStatement stmtGrupo = connection.prepareStatement("SELECT COD_GRUPO FROM GRUPO, USERS WHERE USERS.USR = ?;");
            stmtGrupo.setString(1, nombreUsuario);
            ResultSet resultSetGrupo = stmtGrupo.executeQuery();
            if (resultSetGrupo.next()) {
                codGrupo = resultSetGrupo.getInt(1);
            }
            PreparedStatement statementAsignadas = connection.prepareStatement("SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, EMPRESA_ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ASIGNADAS = TRUE AND COD_GRUPO = ?;");
            statementAsignadas.setInt(1, codGrupo);
            ResultSet asignadas = statementAsignadas.executeQuery();
            PreparedStatement statementPorAsignar = connection.prepareStatement("SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, EMPRESA_ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ASIGNADAS = FALSE AND COD_GRUPO = ?;");
            statementPorAsignar.setInt(1, codGrupo);
            ResultSet porAsignar = statementPorAsignar.executeQuery();
            Vector<Vector<Object>> data = new Vector<>();
            boolean asignadasBool = true;
            boolean porAsignarBool = true;
            while (asignadasBool || porAsignarBool) {
                Vector<Object> vector = new Vector<>();
                if (asignadas.next()) {
                    vector.add(asignadas.getString(1) + " " + asignadas.getString(2) + " " + asignadas.getString(3));
                } else {
                    asignadasBool = false;
                }
                if (porAsignar.next()) {
                    if (vector.size() == 0) {
                        vector.add("");
                    }
                    vector.add(porAsignar.getString(1) + " " + porAsignar.getString(2) + " " + porAsignar.getString(3));
                } else {
                    porAsignarBool = false;
                }
                if (vector.size() != 0) {
                    data.add(vector);
                }
            }
            vistaPrincipalTutor.getTable().setModel(new DefaultTableModel(data, nombreColumnas));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class ComboItem {
        private String key;
        private String value;

        public ComboItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

}
