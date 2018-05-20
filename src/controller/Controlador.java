package controller;

import model.Modelo;
import view.*;

public class Controlador {


    private VistaAlumnos vistaAlumnos;
    private VistaAnadirTutor vistaAnadirTutor;
    private VistaAnadirAdministrativo vistaAnadirAdministrativo;
    private VistaAnadirEmpresa vistaAnadirEmpresa;
    private VistaAsignarPracticas vistaAsignarPracticas;
    private VistaConfiguracion vistaConfiguracion;
    private VistaEmpresa vistaEmpresa;
    private VistaGrupos vistaGrupos;
    public VistaLogin vistaLogin;
    private VistaMensaje vistaMensaje;
    private VistaPersonal vistaPersonal;
    private VistaPracticas vistaPracticas;
    private VistaPrincipalAdministrativo vistaPrincipalAdministrativo;
    private VistaPrincipalTutor vistaPrincipalTutor;
    private VistaRecuperarPswd vistaRecuperarPswd;
    private VistaSuperUsuario vistaSuperUsuario;
    private VistaTutores vistaTutores;


    public Modelo modelo;
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
        modelo.iniciarSesion(vistaLogin.getTxtUsuario().getText(), new String(vistaLogin.getPswContrasena().getPassword()));
    }

    public void mostrarRecuperarContrasena() {
        vistaRecuperarPswd.setVisible(true);
    }

    public void recuperarContrasena(String usuario) {
        vistaRecuperarPswd.setVisible(false);
        modelo.recuperarContrasena(usuario);
    }

    public void mostrarVistaSesion() {
        tipoUsuario = modelo.getTipoUsuario();
        switch (tipoUsuario) {
            case 0:
                vistaPrincipalTutor.getLblBienvenido().setText("Bienvenido " + modelo.getNombreUsuario());
                vistaPrincipalTutor.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            case 1:
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
        vistaAlumnos.getTable().setModel(modelo.modeloAlumnos());
        vistaAlumnos.setVisible(true);
        if (tipoUsuario == 0)
            vistaPrincipalTutor.setVisible(false);
        else if (tipoUsuario == 2)
            vistaSuperUsuario.setVisible(false);
    }

    public void mostarConfiguracion() {
        vistaConfiguracion.setVisible(true);
    }

    public void mostrarPracticas() {
        vistaPracticas.getTable().setModel(modelo.modeloPracticas());
        vistaPrincipalAdministrativo.setVisible(false);
        vistaPracticas.setVisible(true);
    }

    public void mostrarTutores() {
        cerrarPanelUsuario();
        vistaTutores.setVisible(true);
    }

    public void mostrarGrupos() {
        vistaPrincipalAdministrativo.setVisible(false);
        vistaGrupos.setVisible(true);
    }

    public void mostrarEmpresas() {
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
        vistaAsignarPracticas.setVisible(true);
    }

    public void cerrarAsignarPracticas() {
        vistaAsignarPracticas.setVisible(false);
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

    public void setVistaPersonal(VistaPersonal vistaPersonal) {
        this.vistaPersonal = vistaPersonal;
    }

    public void setVistaMensaje(VistaMensaje vistaMensaje) {
        this.vistaMensaje = vistaMensaje;
    }

    public void cerrarMensaje() {
        vistaMensaje.setVisible(false);
    }

    public void anadirTutor(String expediente, String mail) {
        modelo.generarUsuario(expediente, mail, (byte) 0);
        vistaAnadirTutor.setVisible(false);
    }

    public void anadirAdministrativo(String expediente, String mail) {
        modelo.generarUsuario(expediente, mail, (byte) 1);
        vistaAnadirAdministrativo.setVisible(false);
    }

    public void mostrarAnadirUsuario() {
        vistaTutores.setVisible(false);
        vistaAnadirTutor.setVisible(true);
    }

    public void cambiarContrasena(String nuevaContrasena) {
        modelo.cambiarContrasena(nuevaContrasena);
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

    public void nombreMostrarUsuario() {
        modelo.getNombreUsuario();
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

}
