package model;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.RandomStringUtils;
import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Vector;

public class Modelo {

    private final String DATABASE = "gestionpracticas";
    private final String FICHERO;
    private VistaAlumnos vistaAlumnos;
    private VistaLogin vistaLogin;
    private VistaConfiguracion vistaConfiguracion;
    private VistaConfigFichero vistaConfigFichero;
    private VistaEmpresa vistaEmpresa;
    private VistaGrupos vistaGrupos;
    private VistaPersonal vistaPersonal;
    private VistaPrincipalTutor vistaPrincipalTutor;
    private VistaPrincipalAdministrativo vistaPrincipalAdministrativo;
    private VistaTutores vistaTutores;
    private VistaRegistro vistaRegistro;
    private String MAILGUN_API_KEY;
    private String USER;
    private String PASSWORD;
    //Cambiar por la IP del servidor de la base de datos
    private String URL;
    private String HOST;
    private Connection connection;
    private String nombreUsuario;
    private String nombreUsuarioFormal;
    private byte tipoUsuario;
    private byte intentos;
    private int codGrupo;
    private Properties propiedades;
    private FileInputStream entrada;
    private DefaultTableModel tablaAlumnos;

    public Modelo(VistaLogin vistaLogin) {
        FICHERO = "config.ini";
        propiedades = new Properties();
        intentos = 0;
        this.vistaLogin = vistaLogin;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            entrada = new FileInputStream(new File(FICHERO));
            propiedades.load(entrada);
            USER = propiedades.getProperty("user");
            PASSWORD = propiedades.getProperty("password");
            HOST = propiedades.getProperty("host");
            URL = "jdbc:mysql://" + HOST + "/";
            MAILGUN_API_KEY = propiedades.getProperty("mailgun");
            connection = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
        } catch (Exception e) {
            this.vistaLogin.errorConexion();
            e.printStackTrace();
        }
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

    public void setVistaLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
    }

    public void setVistaRecuperarPswd(VistaRecuperarPswd vistaRecuperarPswd) {
    }

    //Método de inicio de sesión
    public void iniciarSesion(String user, String password) {
        try {
            password = hash256(password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT PWD, ROLE, NOMBRE FROM USERS WHERE USR = ?;");
            preparedStatement.setString(1, user.toLowerCase());
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
                        vistaLogin.errorInicioSesion();
                    } else {
                        vistaLogin.intentosSuperados();
                    }
                }
            } else {
                vistaLogin.errorInicioSesion();
            }
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
            vistaLogin.errorGenerarContrasena();
        }
    }

    //Método que realiza una petición HTTP POST para enviar un mail a través de la API de Mailgun
    private void enviarMail(String mailEnvio, String asunto, String contenido) {
        final String DOMINIO = "mg.julen.gq";
        try {
            HttpResponse<String> request = Unirest.post("https://api.mailgun.net/v3/" + DOMINIO + "/messages")
                    .basicAuth("api", MAILGUN_API_KEY)
                    .queryString("from", "Gestión Prácticas CFGS - UEM <noreply@uemgestionpracticas.com>")
                    .queryString("to", mailEnvio)
                    .queryString("subject", asunto)
                    .queryString("html", contenido)
                    .asString();
            //System.out.println(request.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    //Método que crea un usuario, lo mete en la base de datos y le envía una contraseña aleatoria
    public void generarUsuario(String expediente, String mail, byte role) {
        try {
            String contrasenaAleatoria = contrasenaAleatoria();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS (USR, PWD, ROLE, MAIL) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, expediente.toLowerCase());
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
            vistaLogin.errorCrearUsuario();
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
            vistaConfiguracion.contrasenaCambiada();
        } catch (Exception e) {
            e.printStackTrace();
            vistaConfiguracion.errorCambio();
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
        String[] arrayNombres = {"N. Matrícula", "Nombre", "Apellidos", "DNI"};
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ESTUDIANTE.NUM_MAT, NOM, CONCAT(APELL1, CONCAT(' ', APELL2)), DNI FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND  COD_GRUPO = ?;");
            preparedStatement.setInt(1, codGrupo);
            return crearModelo(arrayNombres, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DefaultTableModel modeloPracticas() {
        String[] arrayNombres = {"Estudiante", "Empresa", "Tutor Emp.", "F. Inicio", "F. Fin", "Horario", "Localización", "Erasmus", "Estado"};
        try {
            return crearModelo(arrayNombres, connection.prepareStatement("SELECT ESTUDIANTE.NOM, NOM_EMPR, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO FROM EMPRESA_ESTUDIANTE, ESTUDIANTE, EMPRESA WHERE ESTUDIANTE.NUM_MAT = EMPRESA_ESTUDIANTE.NUM_MAT AND EMPRESA.NUM_CONV = EMPRESA_ESTUDIANTE.NUM_CONV;"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private DefaultTableModel crearModelo(String[] arrayNombres, PreparedStatement preparedStatement) throws SQLException {
        Vector<String> nombreColumnas = new Vector<>(Arrays.asList(arrayNombres));
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
        vistaPrincipalTutor.getComboBox().setModel(new DefaultComboBoxModel(new Modelo.ComboItem[]{}));
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT NOM_GRUPO, COD_GRUPO FROM GRUPO WHERE USR = ?;");
            preparedStatement.setString(1, nombreUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            int contador = 0;
            while (resultSet.next()) {
                if (contador == 0) {
                    codGrupo = resultSet.getInt(2);
                }
                vistaPrincipalTutor.getComboBox().addItem(new ComboItem(resultSet.getString(1), resultSet.getString(2)));
                contador++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cambiarGrupoTutor() {
        ComboItem comboItem = (ComboItem) vistaPrincipalTutor.getComboBox().getSelectedItem();
        codGrupo = Integer.parseInt(comboItem.value);
        mostrarPracticasTutor();

    }

    public void mostrarPracticasTutor() {
        String[] arrayNombres = {"Prácticas Asignadas", "Prácticas por asignar"};
        Vector<String> nombreColumnas = new Vector<>(Arrays.asList(arrayNombres));
        try {
            PreparedStatement statementAsignadas = connection.prepareStatement("SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);");
            statementAsignadas.setInt(1, codGrupo);
            ResultSet asignadas = statementAsignadas.executeQuery();
            PreparedStatement statementPorAsignar = connection.prepareStatement("SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);");
            statementPorAsignar.setInt(1, codGrupo);
            ResultSet porAsignar = statementPorAsignar.executeQuery();
            Vector<Vector<Object>> data = new Vector<>();
            boolean asignadasBool = true;
            boolean porAsignarBool = true;
            int numeroAsignados = 0;
            int numeroPorAsignar = 0;
            while (asignadasBool || porAsignarBool) {
                Vector<Object> vector = new Vector<>();
                if (asignadas.next()) {
                    vector.add(asignadas.getString(1) + " " + asignadas.getString(2) + " " + asignadas.getString(3));
                    numeroAsignados++;
                } else {
                    asignadasBool = false;
                }
                if (porAsignar.next()) {
                    if (vector.size() == 0) {
                        vector.add("");
                    }
                    numeroPorAsignar++;
                    vector.add(porAsignar.getString(1) + " " + porAsignar.getString(2) + " " + porAsignar.getString(3));
                } else {
                    porAsignarBool = false;
                }
                if (vector.size() != 0) {
                    data.add(vector);
                }
            }
            vistaPrincipalTutor.getLblNumeroAsignados().setText(Integer.toString(numeroAsignados));
            vistaPrincipalTutor.getLblNumeroPorAsignar().setText(Integer.toString(numeroPorAsignar));
            vistaPrincipalTutor.getTable().setModel(new DefaultTableModel(data, nombreColumnas));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Carga la tabla del Dashboard del director
    public void mostrarDashboardDirector() {
        String[] arrayNombre = {"Grupo", "Tutor", "Alumnos por Asignar"};
        Vector<String> nombreColumnas = new Vector<>(Arrays.asList(arrayNombre));
        Vector<Vector<Object>> data = new Vector<>();
        try {
            PreparedStatement stmtCodigoTutores = connection.prepareStatement("SELECT COD_GRUPO FROM GRUPO;");
            ResultSet resultSet = stmtCodigoTutores.executeQuery();
            int alumnosPorAsignar = 0;
            int clasesPorAsignar = 0;
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<>();
                PreparedStatement stmtFilas = connection.prepareStatement("SELECT NOM_GRUPO, NOMBRE FROM GRUPO,USERS WHERE GRUPO.USR=USERS.USR AND ROLE=0 AND COD_GRUPO=?;");
                stmtFilas.setInt(1, resultSet.getInt(1));
                PreparedStatement stmtSinAsignar = connection.prepareStatement("SELECT COUNT(*) FROM (SELECT ESTUDIANTE.NUM_MAT FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE) AND COD_GRUPO=? AND ESTUDIANTE.NUM_MAT=GRUPO_ESTUDIANTE.NUM_MAT) AS CONSULTA;");
                stmtSinAsignar.setInt(1, resultSet.getInt(1));
                ResultSet resultSetFilas = stmtFilas.executeQuery();
                ResultSet resultSetSinAsignar = stmtSinAsignar.executeQuery();
                if (resultSetFilas.next() && resultSetSinAsignar.next()) {
                    vector.add(resultSetFilas.getString(1));
                    vector.add(resultSetFilas.getObject(2));
                    int alumnosPorAsignarGrupo = resultSetSinAsignar.getInt(1);
                    alumnosPorAsignar += alumnosPorAsignarGrupo;
                    vector.add(alumnosPorAsignarGrupo);
                    if (alumnosPorAsignarGrupo > 0)
                        clasesPorAsignar++;
                }
                data.add(vector);
            }
            vistaPrincipalAdministrativo.getLblAlumnosPorAsignar().setText(Integer.toString(alumnosPorAsignar));
            vistaPrincipalAdministrativo.getLblClases().setText(Integer.toString(clasesPorAsignar));
            vistaPrincipalAdministrativo.getTable().setModel(new DefaultTableModel(data, nombreColumnas));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarTutores() {
        String[] nombreColumnas = {"Nombre", "Usuario", "Mail", "NIF"};
        try {
            vistaTutores.getTable().setModel(crearModelo(nombreColumnas, connection.prepareStatement("SELECT NOMBRE, USR, MAIL,NIF FROM USERS WHERE ROLE = 0;")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarGrupos() {
        String[] nombreColumnas = {"Código", "Nombre", "Nombre del Ciclo", "Tutor"};
        try {
            vistaGrupos.getTable().setModel(crearModelo(nombreColumnas, connection.prepareStatement("SELECT COD_GRUPO, NOM_GRUPO, NOM_CICLO, NOMBRE FROM GRUPO, CICLO, USERS WHERE CICLO.CLAVE_CICLO = GRUPO.CLAVE_CICLO AND GRUPO.USR = USERS.USR;")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarEmpresas() {
        String[] nombreColumnas = {"N. Convenio", "Nombre", "F. Firma", "Dirección", "Localidad", "Representante", "Mail"};
        try {
            vistaEmpresa.getTable().setModel(crearModelo(nombreColumnas, connection.prepareStatement("SELECT NUM_CONV, NOM_EMPR, F_FIRMA, DIRECCION, LOCALIDAD, REPR_EMPR, CORREO_EMPR FROM EMPRESA;")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarPersonal() {
        String[] nombreColumnas = {"Nombre", "Usuario", "Mail", "NIF"};
        try {
            vistaPersonal.getTable().setModel(crearModelo(nombreColumnas, connection.prepareStatement("SELECT NOMBRE, USR, MAIL,NIF FROM USERS WHERE ROLE = 1;")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Carga la tabla de almunos
    public void cargarAlumnos() {
        String[] nombreColumnas = {"N. Matrícula", "Nombre", "Apellidos", "DNI"};
        try {
            tablaAlumnos = crearModelo(nombreColumnas, connection.prepareStatement("SELECT NUM_MAT, NOM, CONCAT(APELL1, CONCAT(' ', APELL2)), DNI FROM ESTUDIANTE;"));
            vistaAlumnos.getTable().setModel(tablaAlumnos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Escribe la configuración en el fichero config.ini
    public void escribirConfiguracion(String user, String password, String host) {
        try {
            propiedades.setProperty("user", user);
            propiedades.setProperty("password", password);
            propiedades.setProperty("host", host);
            FileOutputStream salida = new FileOutputStream(FICHERO);
            propiedades.store(salida, "Editado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registro(String nombre, String usuario, String password, String mail, String nif) {
        try {
            password = hash256(password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS (NOMBRE, USR, PWD, ROLE, MAIL, NIF) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, usuario);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5, mail);
            preparedStatement.setString(6, nif);
            preparedStatement.executeUpdate();
            enviarMail(mail, "Gestión Prácticas CFGS", "Bienvenido al software gestor de prácticas de CFGS. Su usuario es: " + usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVistaTutores(VistaTutores vistaTutores) {
        this.vistaTutores = vistaTutores;
    }

    public void setVistaEmpresa(VistaEmpresa vistaEmpresa) {
        this.vistaEmpresa = vistaEmpresa;
    }

    public void setVistaGrupos(VistaGrupos vistaGrupos) {
        this.vistaGrupos = vistaGrupos;
    }

    public void setVistaPersonal(VistaPersonal vistaPersonal) {
        this.vistaPersonal = vistaPersonal;
    }

    public void setVistaAlumnos(VistaAlumnos vistaAlumnos) {
        this.vistaAlumnos = vistaAlumnos;
    }

    public void setVistaRegistro(VistaRegistro vistaRegistro) {
        this.vistaRegistro = vistaRegistro;
    }

    public void setVistaConfigFichero(VistaConfigFichero vistaConfigFichero) {
        this.vistaConfigFichero = vistaConfigFichero;
    }

    //Clase interna para los objetos de las comboBoxes
    public class ComboItem {
        private String key;
        private String value;

        public ComboItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

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
