package controller;

import model.Modelo;
import view.*;

import java.util.Date;

/**
 * @author Los Juleniars
 */
public class Controlador {

    private VistaAlumnos vistaAlumnos;
    private VistaAnadirTutor vistaAnadirTutor;
    private VistaAnadirAdministrativo vistaAnadirAdministrativo;
    private VistaAnadirEmpresa vistaAnadirEmpresa;
    private VistaAsignarPracticas vistaAsignarPracticas;
    private VistaModificarPracticas vistaModificarPracticas;
    private VistaConfigFichero vistaConfigFichero;
    private VistaConfiguracion vistaConfiguracion;
    private VistaEmpresa vistaEmpresa;
    private VistaGrupos vistaGrupos;
    private VistaLogin vistaLogin;
    private VistaMensaje vistaMensaje;
    private VistaPersonal vistaPersonal;
    private VistaPracticas vistaPracticas;
    private VistaPrincipalAdministrativo vistaPrincipalAdministrativo;
    private VistaPrincipalTutor vistaPrincipalTutor;
    private VistaRecuperarPswd vistaRecuperarPswd;
    private VistaRegistro vistaRegistro;
    private VistaSuperUsuario vistaSuperUsuario;
    private VistaTutores vistaTutores;


    private Modelo modelo;
    private byte tipoUsuario;

    public void setVistaLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
    }

    public void setVistaRecuperarPswd(VistaRecuperarPswd vistaRecuperarPswd) {
        this.vistaRecuperarPswd = vistaRecuperarPswd;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void iniciarSesion() {
        modelo.iniciarSesion(vistaLogin.getTxtUsuario(), vistaLogin.getPswContrasena());
    }

    public void mostrarRecuperarContrasena() {
        vistaRecuperarPswd.setVisible(true);
    }

    public void recuperarContrasena() {
        vistaRecuperarPswd.setVisible(false);
        modelo.recuperarContrasena(vistaRecuperarPswd.getTxtNombreUsuario());
    }

    public void mostrarVistaSesion() {
        tipoUsuario = modelo.getTipoUsuario();
        switch (tipoUsuario) {
            case 0:
                modelo.mostrarGrupoTutor();
                modelo.mostrarPracticasTutor();
                vistaPrincipalTutor.getLblBienvenido().setText("Bienvenido " + modelo.getNombreUsuario());
                vistaPrincipalTutor.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            case 1:
                modelo.mostrarDashboardDirector();
                vistaPrincipalAdministrativo.getLblBienvenido().setText("Bienvenido " + modelo.getNombreUsuario());
                vistaPrincipalAdministrativo.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            case 2:
                vistaSuperUsuario.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            default:
                break;
        }
    }


    public void setVistaSuperUsuario(VistaSuperUsuario vistaSuperUsuario) {
        this.vistaSuperUsuario = vistaSuperUsuario;
    }

    public void setVistaPrincipalTutor(VistaPrincipalTutor vistaPrincipalTutor) {
        this.vistaPrincipalTutor = vistaPrincipalTutor;
    }

    public void setVistaPrincipalAdministrativo(VistaPrincipalAdministrativo vistaPrincipalAdministrativo) {
        this.vistaPrincipalAdministrativo = vistaPrincipalAdministrativo;
    }

    public void setVistaAlumnos(VistaAlumnos vistaAlumnos) {
        this.vistaAlumnos = vistaAlumnos;
    }

    public void setVistaTutores(VistaTutores vistaTutores) {
        this.vistaTutores = vistaTutores;
    }

    public void setVistaConfiguracion(VistaConfiguracion vistaConfiguracion) {
        this.vistaConfiguracion = vistaConfiguracion;
    }

    public void setVistaEmpresa(VistaEmpresa vistaEmpresa) {
        this.vistaEmpresa = vistaEmpresa;
    }

    public void setVistaGrupos(VistaGrupos vistaGrupos) {
        this.vistaGrupos = vistaGrupos;
    }

    public void setVistaPracticas(VistaPracticas vistaPracticas) {
        this.vistaPracticas = vistaPracticas;
    }

    public void mostrarListaAlumnos() {
        if (tipoUsuario == 0) {
            modelo.cargarAlumnosTutor();
            vistaPrincipalTutor.setVisible(false);
        } else if (tipoUsuario == 2)
            modelo.cargarAlumnosDirector();
        vistaSuperUsuario.setVisible(false);
        vistaAlumnos.setVisible(true);
    }

    public void mostarConfiguracion() {
        vistaConfiguracion.setVisible(true);
    }

    public void mostrarPracticas() {
        cerrarPanelUsuario();
        modelo.cargarPracticas();
        vistaPracticas.setVisible(true);
    }

    public void mostrarTutores() {
        modelo.cargarTutores();
        cerrarPanelUsuario();
        vistaTutores.setVisible(true);
    }

    public void mostrarGrupos() {
        modelo.cargarGrupos();
        vistaPrincipalAdministrativo.setVisible(false);
        vistaGrupos.setVisible(true);
    }

    public void mostrarEmpresas() {
        modelo.cargarEmpresas();
        vistaPrincipalAdministrativo.setVisible(false);
        vistaEmpresa.setVisible(true);
    }

    public void cerrarSesion() {
        //Insertar Código de cierre de sesión
        cerrarPanelUsuario();
        tipoUsuario = -1;
        vistaLogin.setVisible(true);
    }

    public void cerrarAlumnos() {
        vistaAlumnos.setVisible(false);
        abrirPanelUsuario();
    }

    public void cerrarConfiguracion() {
        vistaConfiguracion.setVisible(false);
    }

    public void setVistaAsignarPracticas(VistaAsignarPracticas vistaAsignarPracticas) {
        this.vistaAsignarPracticas = vistaAsignarPracticas;
    }

    public void mostrarAsignarPracticas() {
        modelo.cargarAsignarPracticas();
        vistaAsignarPracticas.setVisible(true);
    }

    public void cerrarAsignarPracticas() {
        vistaAsignarPracticas.setVisible(false);
    }

    /**
     * Coge los datos introducidos y llama al métofo asignarPracticas del modelo
     */
    public void asignarPracticas() {
        Modelo.ComboItem estudiante = (Modelo.ComboItem) vistaAsignarPracticas.getComboItemAlumno();
        Modelo.ComboItem empresa = (Modelo.ComboItem) vistaAsignarPracticas.getComboItemEmpresa();
        Date fechaInicio = vistaAsignarPracticas.getDateInicio();
        Date fechafin = vistaAsignarPracticas.getDateFin();
        String tutorEmpresa = vistaAsignarPracticas.getTxtTutorEmpresa();
        String horario = vistaAsignarPracticas.getTxtHorario();
        String localizacion = vistaAsignarPracticas.getTxtLocalizacion();
        boolean erasmus = vistaAsignarPracticas.getChckbxErasmus();
        String estado = vistaAsignarPracticas.getTxtEstado();
        modelo.asignarPracticas(estudiante, empresa, fechaInicio, fechafin, tutorEmpresa, horario, localizacion, erasmus, estado);
        vistaAsignarPracticas.limpiarCampos();
    }

    /**
     * Coge la fila seleccionada y llama al método eliminarPracticas del modelo para eliminarla
     */
    public void eliminarPracticas() {
        modelo.eliminarPracticas(vistaPracticas.getFilaSeleccionada());
    }

    public void mostrarModificarPracticas() {
        String nombreAlumno = vistaPracticas.getNombreAlumnoSeleccionado();
        String nombreEmpresa = vistaPracticas.getNombreEmpresaSeleccionada();
        String tutorEmpresa = vistaPracticas.getTutorEmpresaSeleccionado();
        Date fechaInicio = vistaPracticas.getFechaInicioSeleccionada();
        Date fechaFin = vistaPracticas.getFechaFinSeleccionada();
        String horario = vistaPracticas.getHorarioSeleccionado();
        String localizacion = vistaPracticas.getLocalizacionSeleccionada();
        boolean erasmus = vistaPracticas.getErasmusSeleccionada();
        String estado = vistaPracticas.getEstadoSeleccionado();
        int numMat = vistaPracticas.getNumMatSeleccionado();
        int numConv = vistaPracticas.getNumConvSeleccionado();
        int fila = vistaPracticas.getFilaSeleccionada();
        vistaModificarPracticas.cargarDatos(numMat, numConv, nombreAlumno, nombreEmpresa, tutorEmpresa, fechaInicio, fechaFin, horario, localizacion, erasmus, estado, fila);
        vistaModificarPracticas.setVisible(true);
    }

    public void cerrarModificarPracticas() {
        vistaModificarPracticas.setVisible(false);
    }

    public void modificarPracticas() {
        int fila = vistaModificarPracticas.getFila();
        int numMat = vistaModificarPracticas.getNumMat();
        int numConv = vistaModificarPracticas.getNumConv();
        Date fechaInicio = vistaModificarPracticas.getDateInicio();
        Date fechafin = vistaModificarPracticas.getDateFin();
        String tutorEmpresa = vistaModificarPracticas.getTxtTutorEmpresa();
        String horario = vistaModificarPracticas.getTxtHorario();
        String localizacion = vistaModificarPracticas.getTxtLocalizacion();
        boolean erasmus = vistaModificarPracticas.getChckbxErasmus();
        String estado = vistaModificarPracticas.getTxtEstado();
        modelo.modificarPracticas(fila, numMat, numConv, fechaInicio, fechafin, tutorEmpresa, horario, localizacion, erasmus, estado);
        vistaModificarPracticas.setVisible(false);
    }

    public void cerrarTutores() {
        vistaTutores.setVisible(false);
        abrirPanelUsuario();
    }

    public void cerrarGrupos() {
        vistaGrupos.setVisible(false);
        abrirPanelUsuario();
    }

    public void cerrarEmpresas() {
        vistaEmpresa.setVisible(false);
        abrirPanelUsuario();
    }

    public void cerrarPracticas() {
        vistaPracticas.setVisible(false);
        abrirPanelUsuario();
    }

    public void mostrarPersonal() {
        modelo.cargarPersonal();
        cerrarPanelUsuario();
        vistaPersonal.setVisible(true);
    }

    public void cerrarPersonal() {
        abrirPanelUsuario();
        vistaPersonal.setVisible(false);
    }

    private void abrirPanelUsuario() {
        switch (tipoUsuario) {
            case 0:
                vistaPrincipalTutor.setVisible(true);
                break;
            case 1:
                vistaPrincipalAdministrativo.setVisible(true);
                break;
            case 2:
                vistaSuperUsuario.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void cerrarPanelUsuario() {
        switch (tipoUsuario) {
            case 0:
                vistaPrincipalTutor.setVisible(false);
                break;
            case 1:
                vistaPrincipalAdministrativo.setVisible(false);
                break;
            case 2:
                vistaSuperUsuario.setVisible(false);
                break;
            default:
                break;
        }
    }

    public void cambiarGrupo() {
        modelo.cambiarGrupoTutor((Modelo.ComboItem) vistaPrincipalTutor.getCmbGrupos());
    }

    public void mostrarRegistro() {
        vistaRegistro.setVisible(true);
    }

    public void registrar() {
        String nombre = vistaRegistro.getTxtNombre();
        String usuario = vistaRegistro.getTxtUser();
        String password = vistaRegistro.getPswContrasena();
        String confimar = vistaRegistro.getPswConfirmar();
        String mail = vistaRegistro.getTxtMail();
        String nif = vistaRegistro.getTxtNIF();
        if (password.equals(confimar)) {
            modelo.registroTutor(nombre, usuario, password, mail, nif);
        } else {
            vistaRegistro.errorPassword();
        }
    }

    public void registrado() {
        vistaRegistro.limpiarCampos();
        vistaRegistro.setVisible(false);
    }

    public void setVistaPersonal(VistaPersonal vistaPersonal) {
        this.vistaPersonal = vistaPersonal;
    }

    public void setVistaMensaje(VistaMensaje vistaMensaje) {
        this.vistaMensaje = vistaMensaje;
    }

    public void cerrarMensaje() {
        vistaMensaje.setVisible(false);
    }

    public void anadirTutor() {
        modelo.generarUsuario(vistaAnadirTutor.getTxtExpediente(), vistaAnadirTutor.getTxtMail(), (byte) 0);
        vistaAnadirTutor.setVisible(false);
    }

    public void anadirAdministrativo() {
        modelo.generarUsuario(vistaAnadirAdministrativo.getTxtExpediente(), vistaAnadirAdministrativo.getTxtMail(), (byte) 1);
        vistaAnadirAdministrativo.setVisible(false);
    }

    public void mostrarAnadirUsuario() {
        //vistaTutores.setVisible(false);
        vistaAnadirTutor.setVisible(true);
    }

    public void cambiarContrasena() {
        if (vistaConfiguracion.getPswContrasena().equals(vistaConfiguracion.getPswConfirmarContrasena())) {
            modelo.cambiarContrasena(vistaConfiguracion.getPswContrasena());
        } else {
            vistaConfiguracion.errorContrasenas();
        }
    }

    public void setVistaAnadirTutor(VistaAnadirTutor vistaAnadirTutor) {
        this.vistaAnadirTutor = vistaAnadirTutor;
    }

    public void mostrarAnadirPersonal() {
        vistaAnadirAdministrativo.setVisible(true);
    }

    public void setVistaAnadirAdministrativo(VistaAnadirAdministrativo vistaAnadirAdministrativo) {
        this.vistaAnadirAdministrativo = vistaAnadirAdministrativo;
    }

    public void mostrarConfigFichero() {
        vistaConfigFichero.setConfiguracion();
        vistaConfigFichero.setVisible(true);
    }

    public void escribirFichero() {
        String user = vistaConfigFichero.getTxtUser();
        String password = vistaConfigFichero.getPswContrasena();
        String url = vistaConfigFichero.getTxtUrl();
        modelo.escribirConfiguracion(user, password, url);
        vistaConfigFichero.setVisible(false);
    }

    public void setVistaAnadirEmpresa(VistaAnadirEmpresa vistaAnadirEmpresa) {
        this.vistaAnadirEmpresa = vistaAnadirEmpresa;
    }

    public void mostrarAnadirEmpresa() {
        vistaAnadirEmpresa.setVisible(true);
    }

    public void anadirEmpresa() {
        vistaAnadirEmpresa.setVisible(false);
    }

    public void setVistaRegistro(VistaRegistro vistaRegistro) {
        this.vistaRegistro = vistaRegistro;
    }

    public void setVistaConfigFichero(VistaConfigFichero vistaConfigFichero) {
        this.vistaConfigFichero = vistaConfigFichero;
    }

    public void setVistaModificarPracticas(VistaModificarPracticas vistaModificarPracticas) {
        this.vistaModificarPracticas = vistaModificarPracticas;
    }
}
