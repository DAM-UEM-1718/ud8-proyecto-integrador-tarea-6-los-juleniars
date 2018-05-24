import controller.*;
import model.*;
import view.*;

public class Main {

    public static void main(String[] args) {

        //Creamos las vistas
        VistaAlumnos vistaAlumnos = new VistaAlumnos();
        VistaAnadirTutor vistaAnadirTutor = new VistaAnadirTutor();
        VistaAnadirAdministrativo vistaAnadirAdministrativo = new VistaAnadirAdministrativo();
        VistaAnadirEmpresa vistaAnadirEmpresa = new VistaAnadirEmpresa();
        VistaAsignarPracticas vistaAsignarPracticas = new VistaAsignarPracticas();
        VistaConfiguracion vistaConfiguracion = new VistaConfiguracion();
        VistaEmpresa vistaEmpresa = new VistaEmpresa();
        VistaGrupos vistaGrupos = new VistaGrupos();
        VistaMensaje vistaMensaje = new VistaMensaje();
        VistaPersonal vistaPersonal = new VistaPersonal();
        VistaPracticas vistaPracticas = new VistaPracticas();
        VistaLogin vistaLogin = new VistaLogin();
        VistaRecuperarPswd vistaRecuperarPswd = new VistaRecuperarPswd();
        VistaPrincipalAdministrativo vistaPrincipalAdministrativo = new VistaPrincipalAdministrativo();
        VistaPrincipalTutor vistaPrincipalTutor = new VistaPrincipalTutor();
        VistaSuperUsuario vistaSuperUsuario = new VistaSuperUsuario();
        VistaTutores vistaTutores = new VistaTutores();

        //Creamos el controlador
        Controlador controlador = new Controlador();

        //Asignamos el controlador a las vistas
        vistaAlumnos.setControlador(controlador);
        vistaAnadirTutor.setControlador(controlador);
        vistaAnadirAdministrativo.setControlador(controlador);
        vistaAnadirEmpresa.setControlador(controlador);
        vistaAsignarPracticas.setControlador(controlador);
        vistaConfiguracion.setControlador(controlador);
        vistaEmpresa.setControlador(controlador);
        vistaGrupos.setControlador(controlador);
        vistaMensaje.setControlador(controlador);
        vistaPersonal.setControlador(controlador);
        vistaPracticas.setControlador(controlador);
        vistaPrincipalAdministrativo.setControlador(controlador);
        vistaPrincipalTutor.setControlador(controlador);
        vistaRecuperarPswd.setControlador(controlador);
        vistaSuperUsuario.setControlador(controlador);
        vistaLogin.setControlador(controlador);
        vistaRecuperarPswd.setControlador(controlador);
        vistaTutores.setControlador(controlador);

        //Asignamos las vistas al controlador
        controlador.setVistaAlumnos(vistaAlumnos);
        controlador.setVistaAnadirTutor(vistaAnadirTutor);
        controlador.setVistaAnadirAdministrativo(vistaAnadirAdministrativo);
        controlador.setVistaAnadirEmpresa(vistaAnadirEmpresa);
        controlador.setVistaAsignarPracticas(vistaAsignarPracticas);
        controlador.setVistaConfiguracion(vistaConfiguracion);
        controlador.setVistaEmpresa(vistaEmpresa);
        controlador.setVistaGrupos(vistaGrupos);
        controlador.setVistaMensaje(vistaMensaje);
        controlador.setVistaPersonal(vistaPersonal);
        controlador.setVistaPracticas(vistaPracticas);
        controlador.setVistaLogin(vistaLogin);
        controlador.setVistaRecuperarPswd(vistaRecuperarPswd);
        controlador.setVistaPrincipalAdministrativo(vistaPrincipalAdministrativo);
        controlador.setVistaPrincipalTutor(vistaPrincipalTutor);
        controlador.setVistaRecuperarPswd(vistaRecuperarPswd);
        controlador.setVistaSuperUsuario(vistaSuperUsuario);
        controlador.setVistaTutores(vistaTutores);

        //Creamos el modelo
        Modelo modelo = new Modelo(vistaLogin);

        //Asignamos el modelo al controlador
        controlador.setModelo(modelo);

        //Asignamos las vistas al modelo
        modelo.setVistaAlumnos(vistaAlumnos);
        modelo.setVistaLogin(vistaLogin);
        modelo.setVistaRecuperarPswd(vistaRecuperarPswd);
        modelo.setVistaConfiguracion(vistaConfiguracion);
        modelo.setVistaEmpresa(vistaEmpresa);
        modelo.setVistaGrupos(vistaGrupos);
        modelo.setVistaPrincipalTutor(vistaPrincipalTutor);
        modelo.setVistaPrincipalAdministrativo(vistaPrincipalAdministrativo);
        modelo.setVistaTutores(vistaTutores);
        modelo.setVistaPersonal(vistaPersonal);

        //Muestra la vista de inicio de sesión
        vistaLogin.setVisible(true);

    }

}
