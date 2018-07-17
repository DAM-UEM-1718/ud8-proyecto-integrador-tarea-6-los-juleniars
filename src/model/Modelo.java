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
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

/**
 * Clase Modelo de la aplicación necesaria por el patrón MVC
 *
 * @author Los Juleniars
 */
public class Modelo {

    private final String FICHERO;

    private VistaAlumnos vistaAlumnos;
    private VistaAnadirGrupo vistaAnadirGrupo;
    private VistaAsignarPracticas vistaAsignarPracticas;
    private VistaModificarPracticas vistaModificarPracticas;
    private VistaLogin vistaLogin;
    private VistaConfiguracion vistaConfiguracion;
    private VistaConfigFichero vistaConfigFichero;
    private VistaEmpresa vistaEmpresa;
    private VistaGrupos vistaGrupos;
    private VistaDirectores vistaDirectores;
    private VistaPracticas vistaPracticas;
    private VistaPrincipalTutor vistaPrincipalTutor;
    private VistaPrincipalDirector vistaPrincipalDirector;
    private VistaTutores vistaTutores;
    private VistaRegistro vistaRegistro;

    private String MAILGUN_API_KEY;
    private String USER;
    private String PASSWORD;
    private String URL;
    private Connection connection;
    private String nombreUsuario;
    private String nombreUsuarioFormal;
    private byte tipoUsuario;
    private byte intentos;
    private int codGrupo;
    private int anoAcademico;
    private Date fechaLimite;
    private Properties propiedades;
    private FileInputStream entrada;


    private DefaultComboBoxModel<ComboItem> modeloGrupos;
    private DefaultComboBoxModel<ComboItem> modeloCmbAlumnos;
    private DefaultComboBoxModel<ComboItem> modeloCmbEmpresas;
    private DefaultComboBoxModel<Integer> modeloCmbAnos;
    private DefaultComboBoxModel<ComboItem> modeloCmbCiclos;
    private DefaultComboBoxModel<ComboItem> modeloCmbTutores;

    private int numeroAsignados;
    private int numeroPorAsignar;
    private int clasesPorAsignar;
    private DefaultTableModel tablaAlumnos;
    private DefaultTableModel tablaPracticas;
    private DefaultTableModel tablaPracticasTutor;
    private DefaultTableModel tablaDashboardDirector;
    private DefaultTableModel tablaTutores;
    private DefaultTableModel tablaGrupos;
    private DefaultTableModel tablaEmpresas;
    private DefaultTableModel tablaDirectores;

    //Queries SELECT
    private String queryInicioSesion = "SELECT PWD, ROLE, NOMBRE FROM USERS WHERE USR = ?;";
    private String queryMail = "SELECT MAIL FROM USERS WHERE USR = ?;";
    private String queryAlumnosTutor = "SELECT ESTUDIANTE.NUM_MAT, NOM, CONCAT(APELL1, CONCAT(' ', APELL2)), DNI FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND  COD_GRUPO = ?;";
    private String queryPracticasTutor = "SELECT CONCAT(ESTUDIANTE.NOM, CONCAT(' ', CONCAT(ESTUDIANTE.APELL1, CONCAT(' ', ESTUDIANTE.APELL2)))), NOM_EMPR, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO, ESTUDIANTE.NUM_MAT, EMPRESA.NUM_CONV, ANEXO_2, ANEXO_3, ANEXO_4, ANEXO_5 FROM EMPRESA_ESTUDIANTE, ESTUDIANTE, EMPRESA, GRUPO_ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT = EMPRESA_ESTUDIANTE.NUM_MAT AND EMPRESA.NUM_CONV = EMPRESA_ESTUDIANTE.NUM_CONV AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND COD_GRUPO = ? AND ANO_ACADEMICO = ?;";
    private String queryPracticasDirector = "SELECT CONCAT(ESTUDIANTE.NOM, CONCAT(' ', CONCAT(ESTUDIANTE.APELL1, CONCAT(' ', ESTUDIANTE.APELL2)))), NOM_EMPR, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO, ESTUDIANTE.NUM_MAT, EMPRESA.NUM_CONV, ANEXO_2, ANEXO_3, ANEXO_4, ANEXO_5 FROM EMPRESA_ESTUDIANTE, ESTUDIANTE, EMPRESA WHERE ESTUDIANTE.NUM_MAT = EMPRESA_ESTUDIANTE.NUM_MAT AND EMPRESA.NUM_CONV = EMPRESA_ESTUDIANTE.NUM_CONV AND ANO_ACADEMICO = ?;";
    private String queryGruposTutor = "SELECT NOM_GRUPO, COD_GRUPO FROM GRUPO WHERE USR = ?;";
    private String queryGruposDirector = "SELECT COD_GRUPO FROM GRUPO;";
    private String queryAsignadosTutor = "SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);";
    private String queryPorAsignarTutor = "SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);";
    private String queryFilasDirector = "SELECT NOM_GRUPO, NOMBRE FROM GRUPO,USERS WHERE GRUPO.USR=USERS.USR AND ROLE=0 AND COD_GRUPO=?;";
    private String querySinAsignarDirector = "SELECT COUNT(*) FROM (SELECT ESTUDIANTE.NUM_MAT FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE) AND COD_GRUPO=? AND ESTUDIANTE.NUM_MAT=GRUPO_ESTUDIANTE.NUM_MAT) AS CONSULTA;";
    private String queryTutores = "SELECT NOMBRE, USR, MAIL, NIF FROM USERS WHERE ROLE = 0;";
    private String queryGrupos = "SELECT COD_GRUPO, NOM_GRUPO, NOM_CICLO, NOMBRE FROM GRUPO, CICLO, USERS WHERE CICLO.CLAVE_CICLO = GRUPO.CLAVE_CICLO AND GRUPO.USR = USERS.USR;";
    private String queryEmpresas = "SELECT NUM_CONV, NOM_EMPR, F_FIRMA, DIRECCION, LOCALIDAD, REPR_EMPR, CORREO_EMPR FROM EMPRESA;";
    private String queryDirectores = "SELECT NOMBRE, USR, MAIL,NIF FROM USERS WHERE ROLE = 1;";
    private String queryAlumnosDirector = "SELECT NUM_MAT, NOM, CONCAT(APELL1, CONCAT(' ', APELL2)), DNI FROM ESTUDIANTE;";
    private String queryCargarAsignarPracticasTutor = "SELECT CONCAT(NOM, CONCAT(' ', CONCAT(APELL1, CONCAT(' ', APELL2)))), ESTUDIANTE.NUM_MAT FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);";
    private String queryCargarAsignarPracticasDirector = "SELECT CONCAT(NOM, CONCAT(' ', CONCAT(APELL1, CONCAT(' ', APELL2)))), ESTUDIANTE.NUM_MAT FROM ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);";
    private String queryNombreEmpresas = "SELECT NOM_EMPR, NUM_CONV FROM EMPRESA;";
    private String queryComprobacionRegistroTutor = "SELECT * FROM USERS WHERE USR = ? OR NIF = ?;";
    private String queryAnoAcademico = "SELECT ANO FROM ANO_ACADEMICO ORDER BY ANO DESC;";
    private String queryFechaLimite = "SELECT FECHA_LIMITE FROM ANO_ACADEMICO WHERE ANO = ?;";

    private String queryComboCiclos = "SELECT NOM_CICLO, CLAVE_CICLO FROM CICLO;";
    private String queryComboTutor = "SELECT NOMBRE, USR FROM USERS WHERE ROLE = 0;";


    //Queries INSERT
    private String queryAsignarPracticas = "INSERT INTO EMPRESA_ESTUDIANTE (NUM_MAT, NUM_CONV, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private String queryInsertarUsuario = "INSERT INTO USERS (NOMBRE, USR, PWD, ROLE, MAIL, NIF) VALUES (?, ?, ?, ?, ?, ?);";
    private String queryRegistrotutor = "INSERT INTO USERS (NOMBRE, USR, PWD, ROLE, MAIL, NIF) VALUES (?, ?, ?, ?, ?, ?);";

    private String queryInsertarAlumno = "INSERT INTO ESTUDIANTE (NUM_MAT, NOM, APELL1, APELL2, DNI) VALUES (?, ?, ?, ?, ?);";
    private String queryInsertarGrupoEstudiante = "INSERT INTO GRUPO_ESTUDIANTE (COD_GRUPO, NUM_MAT) VALUES (?, ?);";

    private String queryAnadirGrupo = "INSERT INTO GRUPO (NOM_GRUPO, COD_GRUPO, CLAVE_CICLO, USR) VALUES (?, ?, ?, ?)";

    private String queryAnadirEmpresa = "INSERT INTO EMPRESA (NUM_CONV, NOM_EMPR, F_FIRMA, LOCALIDAD, DIRECCION, REPR_EMPR, CORREO_EMPR) VALUES (?, ?, ?, ?, ?, ?, ?)";

    //Queries UPDATE
    private String queryCambiarContrasena = "UPDATE USERS SET PWD = ? WHERE USR = ?;";
    private String queryModificarPracticas = "UPDATE EMPRESA_ESTUDIANTE SET FECHA_INICIO = ?, FECH_FIN = ?, TUT_EMPR = ?, HORARIO = ?, LOCALIZACION = ?, ERASMUS = ?, ESTADO = ? WHERE NUM_MAT = ? AND NUM_CONV = ?;";

    private String queryModificarAlumno = "UPDATE ESTUDIANTE SET NOM = ?, APELL1 = ?, APELL2 = ?, DNI = ? WHERE NUM_MAT = ?";

    private String queryModificarUsuario = "UPDATE USERS SET NOMBRE = ?, MAIL = ?, NIF = ? WHERE USR = ?;";

    private String queryModificarGrupo = "UPDATE GRUPO SET NOM_GRUPO = ?, CLAVE_CICLO = ?, USR = ? WHERE COD_GRUPO = ?;";

    private String queryModificarEmpresa = "UPDATE EMPRESA SET NOM_EMPR = ?, F_FIRMA = ?, LOCALIDAD = ?, DIRECCION = ?, REPR_EMPR = ?, CORREO_EMPR = ? WHERE NUM_CONV = ?;";

    //Queries DELETE
    private String queryEliminarPracticas = "DELETE FROM EMPRESA_ESTUDIANTE WHERE NUM_MAT = ? AND NUM_CONV = ?;";

    private String queryEliminarGrupoEstudiante = "DELETE FROM GRUPO_ESTUDIANTE WHERE NUM_MAT = ?;";
    private String queryEliminarAlumno = "DELETE FROM ESTUDIANTE WHERE NUM_MAT = ?;";

    private String queryEliminarUsuario = "DELETE FROM USERS WHERE USR = ?;";

    private String queryEliminarGrupo = "DELETE FROM GRUPO WHERE COD_GRUPO = ?;";

    private String queryEliminarEmpresa = "DELETE FROM EMPRESA WHERE NUM_CONV = ?;";


    /**
     * Constructor de la clase Modelo
     *
     * @param vistaLogin Se debe indicar la vista de login en el momento de su instanciación
     */
    public Modelo(VistaLogin vistaLogin) {
        //Ruta del fichero de configuración respecto al proyecto
        FICHERO = "config.ini";
        propiedades = new Properties();
        //Intentos de inicio de sesión
        intentos = 0;
        this.vistaLogin = vistaLogin;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            entrada = new FileInputStream(new File(FICHERO));
            //Carga las propiedades del fichero de configuración
            propiedades.load(entrada);
            USER = propiedades.getProperty("user");
            PASSWORD = propiedades.getProperty("password");
            URL = propiedades.getProperty("url");
            //Carga la API Key de Mailgun
            MAILGUN_API_KEY = propiedades.getProperty("mailgun");
            //Crea la conexión a la base de datos MySQL
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null)
                vistaLogin.conectado();
        } catch (Exception e) {
            this.vistaLogin.errorConexion();
            //e.printStackTrace();
        }
    }

    /**
     * Método para generar un hash de la contraseña utilizando el algoritmo SHA-256
     */
    private static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    /**
     * Convierte un array de bytes a un String
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    /**
     * Intenta iniciar sesión en la aplicación comparando las credenciales pasadas como parámetros
     * con las existentes en la base de datos
     *
     * @param user     Nombre de usuario
     * @param password Contraseña del usuario
     */
    public void iniciarSesion(String user, String password) {
        try {
            //Convierte la contraseña en texto plano en un hash SHA-256 para compararlo con el de la base de datos
            password = hash256(password);
            //Statement que devuelve el rol del usuario, su nombre formal y su contraseña dado su nombre de usuario
            PreparedStatement preparedStatement = connection.prepareStatement(queryInicioSesion);
            preparedStatement.setString(1, user.toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            //Comprueba si el statement ha devuelto algún usuario
            if (resultSet.next()) {
                //Comprueba si la contraseña introducida se correspond con la de la base de datos
                if (resultSet.getString(1).equals(password)) {
                    //Guarda el tipo de usuario
                    tipoUsuario = resultSet.getByte(2);
                    //Resetea el contador de intentos de inicio de sesión
                    intentos = 0;
                    //Guarda el nombre de usuario
                    nombreUsuario = user;
                    //Guarda el nombre formal del usuario
                    nombreUsuarioFormal = resultSet.getString("NOMBRE");
                    //Avisa a la vista de que se ha iniciado sesión correctamente
                    vistaLogin.sesionIniciada();
                } else {
                    //En caso de que la contraseña no sea correcta se ejecuta el método que avisará a la vista
                    errorInicioSesion();
                }
            } else {
                //En caso de que el usuario no exista se ejecuta el método que avisará a la vista
                errorInicioSesion();
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * Método para generar una contraseña aleatoria
     *
     * @return Contrase´ña aleatoria generada
     */
    private String contrasenaAleatoria() {
        //Array de chars con los carácteres que pueden aparecer en la contraseña generada
        char[] caracteresPosibles = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
        //Devuleve un String de 7 carácteres aleatorios del array anterior utilizando un método de la librería Apache Commons Lang
        return RandomStringUtils.random(7, 0, caracteresPosibles.length - 1, false, false, caracteresPosibles, new SecureRandom());
    }

    /**
     * Método que genera una nueva contraseña para un usuario, se la envía por mail y la inserta en la base de datos
     *
     * @param user Nombre de usuario a recuperar
     */
    public void recuperarContrasena(String user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryMail);
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String mail = resultSet.getString(1);
            //Crea una contraseña aleatoria en texto plano
            String nuevaContrasena = contrasenaAleatoria();
            //Convierte la contraseña aleatorio en texto plano a un hash SHA-256
            String hashContrasena = hash256(nuevaContrasena);

            //Enviar nueva contraseña por mail
            enviarMail(mail, "Nueva contrase�a.", "Se le ha asignado la siguiente contrase�a: " + nuevaContrasena);

            //Insertar nueva contraseña en la base de datos después de haberla enviado
            PreparedStatement prstm = connection.prepareStatement(queryCambiarContrasena);
            prstm.setString(1, hashContrasena);
            prstm.setString(2, user);
            int rows = prstm.executeUpdate();

            vistaLogin.contrasenaEnviada();
        } catch (Exception e) {
            //e.printStackTrace();
            vistaLogin.errorGenerarContrasena();
        }
    }

    /**
     * Método que realiza una petición HTTP POST para enviar un mail a través de la API de Mailgun
     *
     * @param mailEnvio Mail del destinatario
     * @param asunto    Asunto del correo
     * @param contenido Contenido del correo
     */
    private void enviarMail(String mailEnvio, String asunto, String contenido) {
        //Dominio de Mailgun
        final String DOMINIO = "mg.julen.gq";
        try {
            //Petición HTTP POST a través de la librería de Unirest
            HttpResponse<String> peticion = Unirest.post("https://api.mailgun.net/v3/" + DOMINIO + "/messages")
                    .basicAuth("api", MAILGUN_API_KEY)
                    .queryString("from", "Gesti�n Pr�cticas CFGS - UEM <noreply@uemgestionpracticas.com>")
                    .queryString("to", mailEnvio)
                    .queryString("subject", asunto)
                    .queryString("html", contenido)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que crea un usuario, lo mete en la base de datos y le envía una contraseña aleatoria
     *
     * @param nombre        Nombre formal
     * @param nombreUsuario Nombre de usuario
     * @param mail          Correo electrónico
     * @param dni           DNI del usuario
     * @param role          Rol del usuario (0=tutor, 1=director, 2=root)
     */
    public void generarUsuario(String nombre, String nombreUsuario, String mail, String dni, byte role) {
        try {
            String contrasenaAleatoria = contrasenaAleatoria();
            PreparedStatement preparedStatement = connection.prepareStatement(queryInsertarUsuario);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, nombreUsuario.toLowerCase());
            preparedStatement.setString(3, hash256(contrasenaAleatoria));
            preparedStatement.setInt(4, role);
            preparedStatement.setString(5, mail);
            preparedStatement.setString(6, dni);
            String contenidoMail = "<html><body>Bienvenido a la aplicaci�n de Gesti�n de Pr�cticas de CFGS de la Universidad Europea. <br>" +
                    "Su usuario es: " + nombreUsuario + "<br>" +
                    "Su contrase�a es: " + contrasenaAleatoria +
                    "</body></html>";
            enviarMail(mail, "Nuevo Usuario", contenidoMail);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            //e.printStackTrace();
            vistaLogin.errorCrearUsuario();
        }

    }

    /**
     * Modifica un usuario dados los parámetros
     *
     * @param nombre        Nombre formal
     * @param nombreUsuario Nombre de usuario
     * @param mail          Correo electrónico
     * @param dni           DNI del usuario
     */
    public void modificarUsuario(String nombre, String nombreUsuario, String mail, String dni) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryModificarUsuario);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, mail);
            preparedStatement.setString(3, dni);
            preparedStatement.setString(4, nombreUsuario);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
        }

    }

    /**
     * Elimina un usuario dado su nombre de usuario
     *
     * @param nombreUsuario Nombre de usuario a eliminar
     */
    public void eliminarUsuario(String nombreUsuario) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryEliminarUsuario);
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            vistaTutores.errorEliminar();
            //e.printStackTrace();
        }
    }

    /**
     * Cambia la contraseña del usuario en la base de datos
     *
     * @param nuevaContrasena Nueva contraseña a asignar
     */
    public void cambiarContrasena(String nuevaContrasena) {
        try {
            //Convierte la contraseña en texto plano a un hash SHA-256
            String nuevoHash = hash256(nuevaContrasena);
            PreparedStatement preparedStatement = connection.prepareStatement(queryCambiarContrasena);
            preparedStatement.setString(1, nuevoHash);
            preparedStatement.setString(2, nombreUsuario);
            preparedStatement.executeUpdate();
            vistaConfiguracion.contrasenaCambiada();
        } catch (Exception e) {
            e.printStackTrace();
            vistaConfiguracion.errorCambio();
        }
    }

    /**
     * Vacía los datos del modelo específicos del usuario
     */
    public void cerrarSesion() {
        tipoUsuario = -1;
        nombreUsuarioFormal = null;
        nombreUsuario = null;
    }

    /**
     * Carga la tabla de alumnos para el usuario tutor
     */
    public void cargarAlumnosTutor() {
        String[] arrayNombres = {"N. Matr�cula", "Nombre", "Apellidos", "DNI"};
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAlumnosTutor);
            preparedStatement.setInt(1, codGrupo);
            tablaAlumnos = crearModelo(arrayNombres, preparedStatement);
            vistaAlumnos.cargarTabla();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de prácticas
     */
    public void cargarPracticas() {
        //Array con los nombres de las columnas
        String[] arrayNombres = {"Estudiante", "Empresa", "Tutor Emp.", "F. Inicio", "F. Fin", "Horario", "Localizaci�n", "Erasmus", "Estado", "N. Matrícula", "N. Convenio", "Anex. 2", "Anex. 3", "Anex. 4", "Anex. 5"};
        try {
            //Statement que carga la fecha límite de las prácticas del año académico seleccionado
            PreparedStatement stmtFechaLimite = connection.prepareStatement(queryFechaLimite);
            stmtFechaLimite.setInt(1, anoAcademico);
            ResultSet resultSet = stmtFechaLimite.executeQuery();
            if (resultSet.next())
                fechaLimite = resultSet.getDate(1);
            //Ejecuta un statement diferente dependiendo del tipo de usuario
            switch (tipoUsuario) {
                case 0:
                    //Statement que filtra por grupo y año académico
                    PreparedStatement stmtTutor = connection.prepareStatement(queryPracticasTutor);
                    stmtTutor.setInt(1, codGrupo);
                    stmtTutor.setInt(2, anoAcademico);
                    tablaPracticas = crearModelo(arrayNombres, stmtTutor);
                    break;
                case 1:
                    //Sattement que filtra sólo por año académico
                    PreparedStatement stmtDirector = connection.prepareStatement(queryPracticasDirector);
                    stmtDirector.setInt(1, anoAcademico);
                    tablaPracticas = crearModelo(arrayNombres, stmtDirector);
                    break;
            }
            vistaPracticas.cargarTablas();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }


    /**
     * Crea un modelo dados los parámetros
     *
     * @param arrayNombres      Array con los nombres de las columnas
     * @param preparedStatement PreparedStatement que devolverá los datos de las filas
     * @return Devuelve un modelo de tabla
     */
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

        return new DefaultTableModel(data, nombreColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

    }

    /**
     * Popula el ComboBox de selección de grupo en la vista del tutor
     */
    public void mostrarGrupoTutor() {
        try {
            //Vector que contendrá la lista de grupos
            Vector<ComboItem> grupos = new Vector<>();
            //Statement que devuelve la lista de grupos
            PreparedStatement preparedStatement = connection.prepareStatement(queryGruposTutor);
            preparedStatement.setString(1, nombreUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            int contador = 0;
            //Bucle que añade los grupos devueltos por el SELECT al vector
            while (resultSet.next()) {
                if (contador == 0) {
                    codGrupo = resultSet.getInt(2);
                }
                grupos.add(new ComboItem(resultSet.getString(1), resultSet.getString(2)));
                contador++;
            }
            modeloGrupos = new DefaultComboBoxModel<>(grupos);
            vistaPrincipalTutor.cargarGrupos();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Cambia el grupo del tutor
     *
     * @param seleccionado Grupo seleccionado por el tutor
     */
    public void cambiarGrupoTutor(ComboItem seleccionado) {
        codGrupo = Integer.parseInt(seleccionado.value);
        //Vuelve a cargar la tabla principal del tutor con el grupo nuevo
        cargarTablaPrincipalTutor();
    }

    /**
     * Cambia el año académico en el modelo según el seleccionaod por el usuario
     *
     * @param anoAcademico Nuevo año académico
     */
    public void cambiarAnoAcademico(int anoAcademico) {
        this.anoAcademico = anoAcademico;
    }

    /**
     * Carga la tabla de la vista principal del director con los datos del grupo sobre las prácticas
     */
    public void cargarTablaPrincipalTutor() {
        //Array con el nombre de las columnas
        String[] arrayNombres = {"Pr�cticas Asignadas", "Pr�cticas por asignar"};
        //Convierte el array en un vector
        Vector<String> nombreColumnas = new Vector<>(Arrays.asList(arrayNombres));
        try {
            //Statement que devuelve los alumnos con prácticas
            PreparedStatement statementAsignadas = connection.prepareStatement(queryAsignadosTutor);
            statementAsignadas.setInt(1, codGrupo);
            ResultSet asignadas = statementAsignadas.executeQuery();
            //Statement que devuelve los alumnos sin prácticas
            PreparedStatement statementPorAsignar = connection.prepareStatement(queryPorAsignarTutor);
            statementPorAsignar.setInt(1, codGrupo);
            ResultSet porAsignar = statementPorAsignar.executeQuery();
            //Vector que contendrá las filas de la tabla
            Vector<Vector<Object>> filas = new Vector<>();
            boolean asignadasBool = true;
            boolean porAsignarBool = true;
            numeroAsignados = 0;
            numeroPorAsignar = 0;
            //Bucle que se repite el número de filas que tenga el statement más alrgo
            while (asignadasBool || porAsignarBool) {
                //Vector que contiene los datos de cada fila
                Vector<Object> datosFila = new Vector<>();
                if (asignadas.next()) {
                    datosFila.add(asignadas.getString(1) + " " + asignadas.getString(2) + " " + asignadas.getString(3));
                    numeroAsignados++;
                } else {
                    asignadasBool = false;
                }
                if (porAsignar.next()) {
                    if (datosFila.size() == 0) {
                        datosFila.add("");
                    }
                    numeroPorAsignar++;
                    datosFila.add(porAsignar.getString(1) + " " + porAsignar.getString(2) + " " + porAsignar.getString(3));
                } else {
                    porAsignarBool = false;
                }
                //Comprueba que al fila no esté vacía antes de añadirla al vector
                if (datosFila.size() != 0) {
                    filas.add(datosFila);
                }
            }
            tablaPracticasTutor = new DefaultTableModel(filas, nombreColumnas);
            vistaPrincipalTutor.cargarTabla();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }


    /**
     * Popula el ComboBox de años académicos
     */
    public void cargarAnosAcademicos() {
        //Vector que contendrá la lista de años
        Vector<Integer> anos = new Vector<>();
        PreparedStatement stmtAnos;
        try {
            stmtAnos = connection.prepareStatement(queryAnoAcademico);
            ResultSet resultSetAnos = stmtAnos.executeQuery();
            if (resultSetAnos.next()) {
                anos.add(resultSetAnos.getInt(1));
                anoAcademico = resultSetAnos.getInt(1);
            }
            while (resultSetAnos.next()) {
                anos.add(resultSetAnos.getInt(1));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        //Instancia el ComboBoxModel con los datos del vector
        modeloCmbAnos = new DefaultComboBoxModel<>(anos);
        //Carga el ComboBoxModel en su correspondiente ComboBox dependiendo de si el usuario es tutor o director
        switch (tipoUsuario) {
            case 0:
                vistaPrincipalTutor.cargarAnoAcademico();
                break;
            case 1:
                vistaPrincipalDirector.cargarAnoAcademico();
                break;
        }
    }

    /**
     * Carga la tabla de la vista principal del director`con los datos de cada grupo
     */
    public void mostrarDashboardDirector() {
        //Array con el nombre de cada fila
        String[] arrayNombre = {"Grupo", "Tutor", "Alumnos por Asignar"};
        //Convierte el array a un vector
        Vector<String> nombreColumnas = new Vector<>(Arrays.asList(arrayNombre));
        //Vector para los datos de cada fila
        Vector<Vector<Object>> filas = new Vector<>();
        try {
            PreparedStatement stmtCodigoTutores = connection.prepareStatement(queryGruposDirector);
            ResultSet resultSet = stmtCodigoTutores.executeQuery();
            numeroPorAsignar = 0;
            clasesPorAsignar = 0;
            while (resultSet.next()) {
                //Vector con los datos de cada columna de la fila
                Vector<Object> datosFila = new Vector<>();
                //Statement para los datos de cada grupo
                PreparedStatement stmtFilas = connection.prepareStatement(queryFilasDirector);
                stmtFilas.setInt(1, resultSet.getInt(1));
                //Statement para el número de alumnos sin prácticas asignadas de cada grupo
                PreparedStatement stmtSinAsignar = connection.prepareStatement(querySinAsignarDirector);
                stmtSinAsignar.setInt(1, resultSet.getInt(1));
                ResultSet resultSetFilas = stmtFilas.executeQuery();
                ResultSet resultSetSinAsignar = stmtSinAsignar.executeQuery();
                //Bucle
                if (resultSetFilas.next() && resultSetSinAsignar.next()) {
                    datosFila.add(resultSetFilas.getString(1));
                    datosFila.add(resultSetFilas.getObject(2));
                    int alumnosPorAsignarGrupo = resultSetSinAsignar.getInt(1);
                    numeroPorAsignar += alumnosPorAsignarGrupo;
                    datosFila.add(alumnosPorAsignarGrupo);
                    if (alumnosPorAsignarGrupo > 0)
                        clasesPorAsignar++;
                }
                filas.add(datosFila);
            }
            tablaDashboardDirector = new DefaultTableModel(filas, nombreColumnas);
            vistaPrincipalDirector.cargarTabla();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de tutores utilizando el método crearModelo
     */
    public void cargarTutores() {
        String[] nombreColumnas = {"Nombre", "Usuario", "Mail", "NIF"};
        try {
            tablaTutores = crearModelo(nombreColumnas, connection.prepareStatement(queryTutores));
            vistaTutores.cargarTabla();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de grupos utilizando el método crearModelo
     */
    public void cargarGrupos() {
        String[] nombreColumnas = {"C�digo", "Nombre", "Nombre del Ciclo", "Tutor"};
        try {
            tablaGrupos = crearModelo(nombreColumnas, connection.prepareStatement(queryGrupos));
            vistaGrupos.cargarTabla();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de empresas utilizando el método crearModelo
     */
    public void cargarEmpresas() {
        String[] nombreColumnas = {"N. Convenio", "Nombre", "F. Firma", "Direcci�n", "Localidad", "Representante", "Mail"};
        try {
            tablaEmpresas = crearModelo(nombreColumnas, connection.prepareStatement(queryEmpresas));
            vistaEmpresa.cargarTabla();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de directores utilizando el método crearModelo
     */
    public void cargarDirectores() {
        String[] nombreColumnas = {"Nombre", "Usuario", "Mail", "NIF"};
        try {
            tablaDirectores = crearModelo(nombreColumnas, connection.prepareStatement(queryDirectores));
            vistaDirectores.cargarTabla();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de alumnos utilizando el método crearModelo
     */
    public void cargarAlumnosDirector() {
        String[] nombreColumnas = {"N. Matr�cula", "Nombre", "Apellidos", "DNI"};
        try {
            tablaAlumnos = crearModelo(nombreColumnas, connection.prepareStatement(queryAlumnosDirector));
            vistaAlumnos.cargarTabla();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Popula los combo box de asignar prácticas
     */
    public void cargarAsignarPracticas() {
        //Crea los vectores necesarios para añadir los datos
        Vector<ComboItem> alumnos = new Vector<>();
        Vector<ComboItem> empresas = new Vector<>();
        try {
            PreparedStatement stmtAlumnos = null;
            switch (tipoUsuario) {
                case 0:
                    stmtAlumnos = connection.prepareStatement(queryCargarAsignarPracticasTutor);
                    stmtAlumnos.setInt(1, codGrupo);
                    break;
                case 1:
                    stmtAlumnos = connection.prepareStatement(queryCargarAsignarPracticasDirector);
                    break;
            }
            ResultSet resultSetAlumnos = stmtAlumnos.executeQuery();
            while (resultSetAlumnos.next()) {
                alumnos.add(new ComboItem(resultSetAlumnos.getString(1), resultSetAlumnos.getString(2)));
            }
            PreparedStatement stmtEmpresas = connection.prepareStatement(queryNombreEmpresas);
            ResultSet resultSetEmpresas = stmtEmpresas.executeQuery();
            while (resultSetEmpresas.next()) {
                empresas.add(new ComboItem(resultSetEmpresas.getString(1), resultSetEmpresas.getString(2)));
            }
            //Asigna el valor de los vectores a cada ComboBoxModel
            modeloCmbAlumnos = new DefaultComboBoxModel<ComboItem>(alumnos);
            modeloCmbEmpresas = new DefaultComboBoxModel<>(empresas);
            //Carga los modelos en los ComboBox
            vistaAsignarPracticas.cargarCmbs();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Popula los ComboBox de añadir grupo
     */
    public void cargarAnadirGrupo() {
        //Crea los vectores necesarios para añadir los datos
        Vector<ComboItem> ciclos = new Vector<>();
        Vector<ComboItem> tutores = new Vector<>();
        try {
            PreparedStatement stmtCiclos = connection.prepareStatement(queryComboCiclos);
            ResultSet resultSetAlumnos = stmtCiclos.executeQuery();
            while (resultSetAlumnos.next()) {
                ciclos.add(new ComboItem(resultSetAlumnos.getString(1), resultSetAlumnos.getString(2)));
            }
            PreparedStatement stmtEmpresas = connection.prepareStatement(queryComboTutor);
            ResultSet resultSetEmpresas = stmtEmpresas.executeQuery();
            while (resultSetEmpresas.next()) {
                tutores.add(new ComboItem(resultSetEmpresas.getString(1), resultSetEmpresas.getString(2)));
            }
            //Asigna el valor de los vectores a cada ComboBoxModel
            modeloCmbCiclos = new DefaultComboBoxModel<>(ciclos);
            modeloCmbTutores = new DefaultComboBoxModel<>(tutores);
            vistaAnadirGrupo.cargarCmbs();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Añade un grupo a la base de datos dados los parámetros
     *
     * @param nombre
     * @param codGrupo
     * @param claveCiclo
     * @param usuarioTutor
     */
    public void anadirGrupo(String nombre, int codGrupo, int claveCiclo, String usuarioTutor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAnadirGrupo);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, codGrupo);
            preparedStatement.setInt(3, claveCiclo);
            preparedStatement.setString(4, usuarioTutor);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifica un grupo en la base de datos datos los parámetros
     */
    public void modificarGrupo(String nombre, int codGrupo, int claveCiclo, String usuarioTutor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryModificarGrupo);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, claveCiclo);
            preparedStatement.setString(3, usuarioTutor);
            preparedStatement.setInt(4, codGrupo);
            preparedStatement.executeUpdate();
            cargarGrupos();
        } catch (SQLException e) {
            vistaGrupos.errorEliminar();
            e.printStackTrace();
        }
    }

    /**
     * Elimina el grupo con el código indicado
     */
    public void eliminarGrupo(int codGrupo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryEliminarGrupo);
            preparedStatement.setInt(1, codGrupo);
            preparedStatement.executeUpdate();
            cargarGrupos();
        } catch (SQLException e) {
            vistaGrupos.errorEliminar();
            e.printStackTrace();
        }
    }

    /**
     * Inserta una empresa en la base de datos dados los parámetros
     */
    public void anadirEmpresa(int numConv, String nombre, Date fechaFirma, String localidad, String direccion, String representante, String mail) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAnadirEmpresa);
            //NUM_CONV, NOM_EMPR, F_FIRMA, LOCALIDAD, DIRECCION, REPR_EMPR, CORREO_EMPR
            preparedStatement.setInt(1, numConv);
            preparedStatement.setString(2, nombre);
            preparedStatement.setDate(3, new java.sql.Date(fechaFirma.getTime()));
            preparedStatement.setString(4, localidad);
            preparedStatement.setString(5, direccion);
            preparedStatement.setString(6, representante);
            preparedStatement.setString(7, mail);
            preparedStatement.executeUpdate();
            cargarEmpresas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifica una empresa dados los parámetros
     */
    public void modificarEmpresa(int numConv, String nombre, Date fechaFirma, String localidad, String direccion, String representante, String mail) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryModificarEmpresa);
            preparedStatement.setString(1, nombre);
            preparedStatement.setDate(2, new java.sql.Date(fechaFirma.getTime()));
            preparedStatement.setString(3, localidad);
            preparedStatement.setString(4, direccion);
            preparedStatement.setString(5, representante);
            preparedStatement.setString(6, mail);
            preparedStatement.setInt(7, numConv);
            preparedStatement.executeUpdate();
            cargarEmpresas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una emrpesa dado su número de convenio
     *
     * @param numConv
     */
    public void eliminarEmpresa(int numConv) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryEliminarEmpresa);
            preparedStatement.setInt(1, numConv);
            preparedStatement.executeUpdate();
            cargarEmpresas();
        } catch (SQLException e) {
            e.printStackTrace();
            vistaEmpresa.errorEliminar();
        }
    }

    /**
     * Asigna las prácticas al alumno seleccionado y las inserta en la base de datos
     */
    public void asignarPracticas(ComboItem comboEstudiante, ComboItem comboEmpresa, Date fechaInicio, Date fechaFin, String tutorEmpresa, String horario, String localizacion, boolean erasmus, String estado) {
        String nombreEstudiante = comboEstudiante.getKey();
        int numMatEstudiante = Integer.parseInt(comboEstudiante.getValue());
        String nombreEmpresa = comboEmpresa.getKey();
        int numConvEmpresa = Integer.parseInt(comboEmpresa.getValue());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAsignarPracticas);
            preparedStatement.setInt(1, numMatEstudiante);
            preparedStatement.setInt(2, numConvEmpresa);
            preparedStatement.setString(3, tutorEmpresa);
            preparedStatement.setDate(4, new java.sql.Date(fechaInicio.getTime()));
            preparedStatement.setDate(5, new java.sql.Date(fechaFin.getTime()));
            preparedStatement.setString(6, horario);
            preparedStatement.setString(7, localizacion);
            preparedStatement.setBoolean(8, erasmus);
            preparedStatement.setString(9, estado);
            preparedStatement.executeUpdate();
            cargarPracticas();
            vistaAsignarPracticas.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta un alumno en la base de datos
     */
    public void insertarAlumno(int numMat, String nombre, String apellido1, String apellido2, String dni) {
        try {
            PreparedStatement stmtEstudiante = connection.prepareStatement(queryInsertarAlumno);
            stmtEstudiante.setInt(1, numMat);
            stmtEstudiante.setString(2, nombre);
            stmtEstudiante.setString(3, apellido1);
            stmtEstudiante.setString(4, apellido2);
            stmtEstudiante.setString(5, dni);
            PreparedStatement stmtGrupoEstudiante = connection.prepareStatement(queryInsertarGrupoEstudiante);
            stmtGrupoEstudiante.setInt(1, codGrupo);
            stmtGrupoEstudiante.setInt(2, numMat);
            stmtEstudiante.executeUpdate();
            stmtGrupoEstudiante.executeUpdate();
            cargarAlumnos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Modifica un alumno dados los parámetros
     */
    public void modificarAlumno(int numMat, String nombre, String apellido1, String apellido2, String dni) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryModificarAlumno);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido1);
            preparedStatement.setString(3, apellido2);
            preparedStatement.setString(4, dni);
            preparedStatement.setInt(5, numMat);
            preparedStatement.executeUpdate();
            cargarAlumnos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un alumno dado su número de matrícula
     */
    public void eliminarAlumno(int numMat) {
        try {
            PreparedStatement stmtGrupoEstudiante = connection.prepareStatement(queryEliminarGrupoEstudiante);
            stmtGrupoEstudiante.setInt(1, numMat);
            stmtGrupoEstudiante.executeUpdate();
            PreparedStatement stmtAlumno = connection.prepareStatement(queryEliminarAlumno);
            stmtAlumno.setInt(1, numMat);
            stmtAlumno.executeUpdate();
            cargarAlumnos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la tabla de alumnos dependiendo del tipo de usuario
     */
    private void cargarAlumnos() {
        switch (tipoUsuario) {
            case 0:
                cargarAlumnosTutor();
                break;
            case 1:
                cargarAlumnosDirector();
                break;
            case 2:
                cargarAlumnosDirector();
                break;
        }
    }

    /**
     * Elimina las prácticas seleccionadas
     *
     * @param fila Fila a eliminar
     */
    public void eliminarPracticas(int fila) {
        int numeroMatricula = (Integer) tablaPracticas.getValueAt(fila, 9);
        int numeroConvenio = (Integer) tablaPracticas.getValueAt(fila, 10);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryEliminarPracticas);
            preparedStatement.setInt(1, numeroMatricula);
            preparedStatement.setInt(2, numeroConvenio);
            preparedStatement.executeUpdate();
            cargarPracticas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifica unas prácticas en la base de datos dados los parámetros
     */
    public void modificarPracticas(int fila, int numMat, int numConv, Date fechaInicio, Date fechaFin, String tutorEmpresa, String horario, String localizacion, boolean erasmus, String estado) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryModificarPracticas);
            preparedStatement.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(fechaFin.getTime()));
            preparedStatement.setString(3, tutorEmpresa);
            preparedStatement.setString(4, horario);
            preparedStatement.setString(5, localizacion);
            preparedStatement.setBoolean(6, erasmus);
            preparedStatement.setString(7, estado);
            preparedStatement.setInt(8, numMat);
            preparedStatement.setInt(9, numConv);
            preparedStatement.executeUpdate();
            cargarPracticas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Escribe la configuración en el fichero config.ini
     *
     * @param user     Usuario de la BBDD
     * @param password Contraseña de la BBDD
     * @param url      Dirección de conexión de la BBDD
     */
    public void escribirConfiguracion(String user, String password, String url) {
        try {
            propiedades.setProperty("user", user);
            propiedades.setProperty("password", password);
            propiedades.setProperty("url", url);
            FileOutputStream salida = new FileOutputStream(FICHERO);
            propiedades.store(salida, "Editado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprueba si el usuario de tipo tutor indicado ya se encuentra en la base de datos, y en caso negativo lo introduce y le manda un mail de bienvenida
     *
     * @param nombre   Nombre formal
     * @param usuario  Nombre de usuario
     * @param password Contraseña
     * @param mail     Correo electrónico
     * @param nif      DNI del tutor
     */
    public void registroTutor(String nombre, String usuario, String password, String mail, String nif) {
        try {
            //Comprobar que el usuario no existe
            PreparedStatement stmtComprobacion = connection.prepareStatement(queryComprobacionRegistroTutor);
            stmtComprobacion.setString(1, usuario);
            stmtComprobacion.setString(2, nif);
            ResultSet resultSet = stmtComprobacion.executeQuery();
            if (!resultSet.next()) {
                password = hash256(password);
                PreparedStatement preparedStatement = connection.prepareStatement(queryRegistrotutor);
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, usuario);
                preparedStatement.setString(3, password);
                preparedStatement.setInt(4, 0);
                preparedStatement.setString(5, mail);
                preparedStatement.setString(6, nif);
                preparedStatement.executeUpdate();
                enviarMail(mail, "Gesti�n Pr�cticas CFGS", "Bienvenido al software gestor de pr�cticas de CFGS. Su usuario es: " + usuario);
                vistaRegistro.registrado();
            } else {
                vistaRegistro.errorUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Suma un intento al inicio de sesión, si llega a 3 intentos cierra la aplicación y avisa
     * a la vista de que se ha producido un error al iniciar sesión
     */
    private void errorInicioSesion() {
        intentos++;
        if (intentos < 3) {
            vistaLogin.errorInicioSesion();
        } else {
            vistaLogin.intentosSuperados();
        }
    }

    //Getters y setters

    public String getNombreUsuarioFormal() {
        return nombreUsuarioFormal;
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

    public void setVistaDirectores(VistaDirectores vistaDirectores) {
        this.vistaDirectores = vistaDirectores;
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

    public void setVistaAsignarPracticas(VistaAsignarPracticas vistaAsignarPracticas) {
        this.vistaAsignarPracticas = vistaAsignarPracticas;
    }

    public void setVistaPracticas(VistaPracticas vistaPracticas) {
        this.vistaPracticas = vistaPracticas;
    }

    public DefaultTableModel getTablaAlumnos() {
        return tablaAlumnos;
    }

    public DefaultTableModel getTablaPracticas() {
        return tablaPracticas;
    }

    public DefaultComboBoxModel<ComboItem> getModeloGrupos() {
        return modeloGrupos;
    }

    public DefaultTableModel getTablaPracticasTutor() {
        return tablaPracticasTutor;
    }

    public int getNumeroAsignados() {
        return numeroAsignados;
    }

    public int getNumeroPorAsignar() {
        return numeroPorAsignar;
    }

    public int getClasesPorAsignar() {
        return clasesPorAsignar;
    }

    public DefaultTableModel getTablaDashboardDirector() {
        return tablaDashboardDirector;
    }

    public DefaultTableModel getTablaTutores() {
        return tablaTutores;
    }

    public DefaultTableModel getTablaGrupos() {
        return tablaGrupos;
    }

    public DefaultTableModel getTablaEmpresas() {
        return tablaEmpresas;
    }

    public DefaultTableModel getTablaDirectores() {
        return tablaDirectores;
    }

    public DefaultComboBoxModel<ComboItem> getModeloCmbAlumnos() {
        return modeloCmbAlumnos;
    }

    public DefaultComboBoxModel<ComboItem> getModeloCmbEmpresas() {
        return modeloCmbEmpresas;
    }

    public void setVistaModificarPracticas(VistaModificarPracticas vistaModificarPracticas) {
        this.vistaModificarPracticas = vistaModificarPracticas;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getURL() {
        return URL;
    }

    public byte getTipoUsuario() {
        return tipoUsuario;
    }

    public DefaultComboBoxModel<Integer> getModeloCmbAnos() {
        return modeloCmbAnos;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public DefaultComboBoxModel<ComboItem> getModeloCmbCiclos() {
        return modeloCmbCiclos;
    }

    public DefaultComboBoxModel<ComboItem> getModeloCmbTutores() {
        return modeloCmbTutores;
    }

    public void setVistaAnadirGrupo(VistaAnadirGrupo vistaAnadirGrupo) {
        this.vistaAnadirGrupo = vistaAnadirGrupo;
    }

    public void setVistaLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
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

    public void setVistaPrincipalDirector(VistaPrincipalDirector vistaPrincipalDirector) {
        this.vistaPrincipalDirector = vistaPrincipalDirector;
    }

    /**
     * Clase interna para los objetos de las comboBoxes
     */
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
