import controller.Controlador;
import model.Modelo;
import view.*;

public class Main {

    public static void main(String[] args) {

        //Creamos las vistas
        VistaAlumnos vistaAlumnos = new VistaAlumnos();
        VistaAnadirTutor vistaAnadirTutor = new VistaAnadirTutor();
        VistaAnadirAdministrativo vistaAnadirAdministrativo = new VistaAnadirAdministrativo();
        VistaAnadirEmpresa vistaAnadirEmpresa = new VistaAnadirEmpresa();
        VistaAsignarPracticas vistaAsignarPracticas = new VistaAsignarPracticas();
        VistaConfigFichero vistaConfigFichero = new VistaConfigFichero();
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
        VistaRegistro vistaRegistro = new VistaRegistro();
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
        vistaConfigFichero.setControlador(controlador);
        vistaConfiguracion.setControlador(controlador);
        vistaEmpresa.setControlador(controlador);
        vistaGrupos.setControlador(controlador);
        vistaMensaje.setControlador(controlador);
        vistaPersonal.setControlador(controlador);
        vistaPracticas.setControlador(controlador);
        vistaPrincipalAdministrativo.setControlador(controlador);
        vistaPrincipalTutor.setControlador(controlador);
        vistaRecuperarPswd.setControlador(controlador);
        vistaRegistro.setControlador(controlador);
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
        controlador.setVistaConfigFichero(vistaConfigFichero);
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
        controlador.setVistaRegistro(vistaRegistro);
        controlador.setVistaSuperUsuario(vistaSuperUsuario);
        controlador.setVistaTutores(vistaTutores);

        //Muestra la vista de inicio de sesión
        vistaLogin.setVisible(true);

        //Creamos el modelo
        Modelo modelo = new Modelo(vistaLogin);

        //Asignamos el modelo al controlador
        controlador.setModelo(modelo);

        //Asignamos las vistas al modelo
        modelo.setVistaAlumnos(vistaAlumnos);
        modelo.setVistaAsignarPracticas(vistaAsignarPracticas);
        modelo.setVistaLogin(vistaLogin);
        modelo.setVistaConfigFichero(vistaConfigFichero);
        modelo.setVistaConfiguracion(vistaConfiguracion);
        modelo.setVistaEmpresa(vistaEmpresa);
        modelo.setVistaGrupos(vistaGrupos);
        modelo.setVistaPracticas(vistaPracticas);
        modelo.setVistaPrincipalTutor(vistaPrincipalTutor);
        modelo.setVistaPrincipalAdministrativo(vistaPrincipalAdministrativo);
        modelo.setVistaTutores(vistaTutores);
        modelo.setVistaPersonal(vistaPersonal);
        modelo.setVistaRegistro(vistaRegistro);

    }

}
