package controller;

import model.Modelo;
import view.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase controlador de la aplicaci贸n necesaria por el patr贸n MVC
 *
 * @author Los Juleniars
 */
public class Controlador {

    private VistaAlumnos vistaAlumnos;
    private VistaAnadirAlumno vistaAnadirAlumno;
    private VistaAnadirTutor vistaAnadirTutor;
    private VistaAnadirDirector vistaAnadirDirector;
    private VistaAnadirEmpresa vistaAnadirEmpresa;
    private VistaAnadirGrupo vistaAnadirGrupo;
    private VistaAsignarPracticas vistaAsignarPracticas;
    private VistaModificarPracticas vistaModificarPracticas;
    private VistaConfigFichero vistaConfigFichero;
    private VistaConfiguracion vistaConfiguracion;
    private VistaEmpresa vistaEmpresa;
    private VistaGrupos vistaGrupos;
    private VistaLogin vistaLogin;
    private VistaDirectores vistaDirectores;
    private VistaPracticas vistaPracticas;
    private VistaContenedorPrincipal vistaContenedorPrincipal;
    private VistaPrincipalDirector vistaPrincipalDirector;
    private VistaPrincipalTutor vistaPrincipalTutor;
    private VistaRecuperarPswd vistaRecuperarPswd;
    private VistaRegistro vistaRegistro;
    private VistaSuperUsuario vistaSuperUsuario;
    private VistaTutores vistaTutores;


    private Modelo modelo;
    private byte tipoUsuario;

    /**
     * Llama al modelo con las credenciales de usuario obtenidas desde la vista
     * para iniciar sesi贸n
     */
    public void iniciarSesion() {
        modelo.iniciarSesion(vistaLogin.getTxtUsuario(), vistaLogin.getPswContrasena());
    }

    /**
     * Muestra la vista de recuperaci贸n de contrase帽a
     */
    public void mostrarRecuperarContrasena() {
        vistaRecuperarPswd.setVisible(true);
    }

    /**
     * Cierra la vista de recuperar contrae帽a y llama al modelo con el nombre de usuario
     * obtenido desde la vista
     */
    public void recuperarContrasena() {
        vistaRecuperarPswd.setVisible(false);
        modelo.recuperarContrasena(vistaRecuperarPswd.getTxtNombreUsuario());
    }

    /**
     * Muestra la vista principal de cada usuario al iniciar sesi贸n y carga
     * sus correspondientes pesta帽as
     */
    public void mostrarVistaSesion() {
        tipoUsuario = modelo.getTipoUsuario();
        switch (tipoUsuario) {
            //En caso de ser tutor
            case 0:
                modelo.cargarAnosAcademicos();
                modelo.mostrarGrupoTutor();
                modelo.cargarTablaPrincipalTutor();
                vistaPrincipalTutor.setLblBienvenido();
                //vistaPrincipalTutor.setVisible(true);
                ArrayList<ListaVistas> vistasTutor = new ArrayList<>();
                vistasTutor.add(new ListaVistas("Principal", vistaPrincipalTutor));
                vistasTutor.add(new ListaVistas("Detalle Pr醕ticas", vistaPracticas));
                vistasTutor.add(new ListaVistas("Mis Alumnos", vistaAlumnos));
                vistaContenedorPrincipal.cargarPestanas(vistasTutor);
                vistaContenedorPrincipal.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            //En caso de ser director
            case 1:
                modelo.cargarAnosAcademicos();
                modelo.mostrarDashboardDirector();
                vistaPrincipalDirector.setLblBienvenido();
                //vistaPrincipalDirector.setVisible(true);

                ArrayList<ListaVistas> vistasDirector = new ArrayList<>();
                vistasDirector.add(new ListaVistas("Principal", vistaPrincipalDirector));
                vistasDirector.add(new ListaVistas("Pr醕ticas", vistaPracticas));
                vistasDirector.add(new ListaVistas("Tutores", vistaTutores));
                vistasDirector.add(new ListaVistas("Grupos", vistaGrupos));
                vistasDirector.add(new ListaVistas("Empresas", vistaEmpresa));
                vistaContenedorPrincipal.cargarPestanas(vistasDirector);
                vistaContenedorPrincipal.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            //En caso de ser superusuario
            case 2:
                ArrayList<ListaVistas> vistasRoot = new ArrayList<>();
                vistasRoot.add(new ListaVistas("Directores", vistaDirectores));
                vistasRoot.add(new ListaVistas("Tutores", vistaTutores));
                vistasRoot.add(new ListaVistas("Alumnos", vistaAlumnos));
                vistasRoot.add(new ListaVistas("Cerrar Sesi髇", new VistaSuperUsuario()));
                modelo.cargarDirectores();
                //vistaSuperUsuario.setVisible(true);
                vistaContenedorPrincipal.cargarPestanas(vistasRoot);
                vistaContenedorPrincipal.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            default:
                break;
        }
    }

    /**
     * Llama al modelo para cargar la tabla de alumnos
     */
    public void mostrarListaAlumnos() {
        //Si el usuario es tutor
        if (tipoUsuario == 0) {
            modelo.cargarAlumnosTutor();
        //Si el usuario es superusuario
        } else if (tipoUsuario == 2)
            modelo.cargarAlumnosDirector();
    }

    /**
     * Muestra la vista de configuraci贸n
     */
    public void mostarConfiguracion() {
        vistaConfiguracion.setVisible(true);
    }

    /**
     * Llama al modelo para cargar la tabla de pr谩cticas
     */
    public void mostrarPracticas() {
        modelo.cargarPracticas();
    }

    /**
     * Llama al modelo para cargar la tabla de tutores
     */
    public void mostrarTutores() {
        modelo.cargarTutores();
    }

    /**
     * Llama al modelo para cargar la tabla de grupos
     */
    public void mostrarGrupos() {
        modelo.cargarGrupos();
    }

    /**
     * Llama al modelo para cargar la tabla de emrpesas
     */
    public void mostrarEmpresas() {
        modelo.cargarEmpresas();
    }

    /**
     * Cierra la sesi贸n del usuario
     */
    public void cerrarSesion() {
        cerrarPanelUsuario();
        modelo.cerrarSesion();
        tipoUsuario = -1;
        vistaLogin.setVisible(true);
    }

    /**
     * Cierra la ventana de configuraci贸n
     */
    public void cerrarConfiguracion() {
        vistaConfiguracion.setVisible(false);
    }

    /**
     * Muestra la ventana de asignaci贸n de pr谩cticas
     */
    public void mostrarAsignarPracticas() {
        modelo.cargarAsignarPracticas();
        vistaAsignarPracticas.setVisible(true);
    }

    /**
     * Cierra la ventana de asignaci贸n de pr谩cticas
     */
    public void cerrarAsignarPracticas() {
        vistaAsignarPracticas.setVisible(false);
    }

    /**
     * Coge los datos introducidos y llama al m茅tofo asignarPracticas del modelo
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
     * Coge la fila seleccionada y llama al m茅todo eliminarPracticas del modelo para eliminarla
     */
    public void eliminarPracticas() {
        modelo.eliminarPracticas(vistaPracticas.getFilaSeleccionada());
    }

    /**
     * Coge los datos de cada columna de la fila seleccionada en la vista y los carga
     * en la vista de modificaci贸n de pr谩cticas
     */
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

    /**
     * Cierra la ventana de modificaci贸n de pr谩cticas
     */
    public void cerrarModificarPracticas() {
        vistaModificarPracticas.setVisible(false);
    }

    /**
     * Coge los datos de la vista y llama al controlador para modificar las pr谩cticas
     */
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

    /**
     * Carga la tabla de directores
     */
    public void mostrarDirectores() {
        modelo.cargarDirectores();
    }

    /**
     * Abre el panel de usuario
     */
    private void abrirPanelUsuario() {
        vistaContenedorPrincipal.setVisible(true);
    }

    /**
     * Cierra el panel de usuario
     */
    private void cerrarPanelUsuario() {
        vistaContenedorPrincipal.setVisible(false);
    }

    public void cambiarGrupo() {
        modelo.cambiarGrupoTutor(vistaPrincipalTutor.getCmbGrupos());
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

    public void setVistaDirectores(VistaDirectores vistaDirectores) {
        this.vistaDirectores = vistaDirectores;
    }

    public void anadirTutor() {
        modelo.generarUsuario(vistaAnadirTutor.getNombre(), vistaAnadirTutor.getUsuario(), vistaAnadirTutor.getMail(), vistaAnadirTutor.getNIF(), (byte) 0);
        vistaAnadirTutor.setVisible(false);
    }

    public void anadirDirector() {
        modelo.generarUsuario(vistaAnadirDirector.getNombre(), vistaAnadirDirector.getUsuario(), vistaAnadirDirector.getMail(), vistaAnadirDirector.getNIF(), (byte) 1);
        vistaAnadirDirector.setVisible(false);
    }

    public void mostrarAnadirTutor() {
        vistaAnadirTutor.setAnadir();
        vistaAnadirTutor.setVisible(true);
    }

    public void mostrarAnadirDirector() {
        vistaAnadirDirector.setAnadir();
        vistaAnadirDirector.setVisible(true);
    }

    public void mostrarModificarUsuario() {
        String usuario = vistaTutores.getUsuarioSeleccionado();
        String nombre = vistaTutores.getNombreSeleccionado();
        String mail = vistaTutores.getMailSeleccionado();
        String nif = vistaTutores.getNIFSeleccionado();
        vistaAnadirTutor.setModificar(nombre, usuario, mail, nif);
        vistaAnadirTutor.setVisible(true);
    }

    public void mostrarModificarDirector() {
        String usuario = vistaDirectores.getUsuarioSeleccionado();
        String nombre = vistaDirectores.getNombreSeleccionado();
        String mail = vistaDirectores.getMailSeleccionado();
        String nif = vistaDirectores.getNIFSeleccionado();
        vistaAnadirDirector.setModificar(nombre, usuario, mail, nif);
        vistaAnadirDirector.setVisible(true);
    }

    public void modificarTutor() {
        modelo.modificarUsuario(vistaAnadirTutor.getNombre(), vistaAnadirTutor.getUsuario(), vistaAnadirTutor.getMail(), vistaAnadirTutor.getNIF());
        vistaAnadirTutor.setVisible(false);
    }

    public void modificarDirector() {
        modelo.modificarUsuario(vistaAnadirDirector.getNombre(), vistaAnadirDirector.getUsuario(), vistaAnadirDirector.getMail(), vistaAnadirDirector.getNIF());
        vistaAnadirDirector.setVisible(false);
    }

    public void eliminarTutor() {
        modelo.eliminarUsuario(vistaTutores.getUsuarioSeleccionado());
        modelo.cargarTutores();
    }

    public void eliminarDirector() {
        modelo.eliminarUsuario(vistaDirectores.getUsuarioSeleccionado());
        modelo.cargarDirectores();
    }

    public void mostrarAnadirGrupo() {
        modelo.cargarAnadirGrupo();
        vistaAnadirGrupo.setAnadir();
        vistaAnadirGrupo.setVisible(true);
    }

    public void mostrarModificarGrupo() {
        modelo.cargarAnadirGrupo();
        vistaAnadirGrupo.setModificar(vistaGrupos.getNombreSeleccionado(), vistaGrupos.getCodigoSeleccionado());
        vistaAnadirGrupo.setVisible(true);
    }

    public void anadirGrupo() {
        String nombre = vistaAnadirGrupo.getNombre();
        int codigo = vistaAnadirGrupo.getCodigo();
        int claveCiclo = vistaAnadirGrupo.getCiclo();
        String usuarioTutor = vistaAnadirGrupo.getTutor();
        modelo.anadirGrupo(nombre, codigo, claveCiclo, usuarioTutor);
        modelo.cargarGrupos();
        vistaAnadirGrupo.setVisible(false);
    }

    public void modificarGrupo() {
        modelo.modificarGrupo(vistaAnadirGrupo.getNombre(), vistaAnadirGrupo.getCodigo(), vistaAnadirGrupo.getCiclo(), vistaAnadirGrupo.getTutor());
        vistaAnadirGrupo.setVisible(false);
    }

    public void eliminarGrupo() {
        modelo.eliminarGrupo(vistaGrupos.getCodigoSeleccionado());
    }

    public void mostrarAnadirEmpresa() {
        vistaAnadirEmpresa.setAnadir();
        vistaAnadirEmpresa.setVisible(true);
    }

    public void mostrarModificarEmpresa() {
        int numConv = vistaEmpresa.getNumConvenioSeleccionado();
        String nombre = vistaEmpresa.getNombreSeleccionad();
        Date fechaFirma = vistaEmpresa.getFechaSeleccionada();
        String localidad = vistaEmpresa.getLocalidadSeleccionad();
        String direccion = vistaEmpresa.getDireccionSeleccionad();
        String representante = vistaEmpresa.getRepresentanteSeleccionado();
        String mail = vistaEmpresa.getMailSeleccionado();
        vistaAnadirEmpresa.setModificar(nombre, fechaFirma, numConv, mail, direccion, localidad, representante);
        vistaAnadirEmpresa.setVisible(true);
    }

    public void anadirEmpresa() {
        int numConv = vistaAnadirEmpresa.getConvenio();
        String nombre = vistaAnadirEmpresa.getTxtNombre();
        Date fechaFirma = vistaAnadirEmpresa.getFechaFirma();
        String localidad = vistaAnadirEmpresa.getTxtLocalidad();
        String direccion = vistaAnadirEmpresa.getTxtDireccion();
        String representante = vistaAnadirEmpresa.getTxtRepresentante();
        String mail = vistaAnadirEmpresa.getTxtMail();
        modelo.anadirEmpresa(numConv, nombre, fechaFirma, localidad, direccion, representante, mail);
        vistaAnadirEmpresa.setVisible(false);
    }

    public void modificarEmpresa() {
        int numConv = vistaEmpresa.getNumConvenioSeleccionado();
        String nombre = vistaEmpresa.getNombreSeleccionad();
        Date fechaFirma = vistaAnadirEmpresa.getFechaFirma();
        String localidad = vistaEmpresa.getLocalidadSeleccionad();
        String direccion = vistaEmpresa.getDireccionSeleccionad();
        String representante = vistaEmpresa.getRepresentanteSeleccionado();
        String mail = vistaEmpresa.getMailSeleccionado();
        modelo.modificarEmpresa(numConv, nombre, fechaFirma, localidad, direccion, representante, mail);
        vistaAnadirEmpresa.setVisible(false);
    }

    public void eliminarEmpresa() {
        modelo.eliminarEmpresa(vistaEmpresa.getNumConvenioSeleccionado());
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
        vistaAnadirDirector.setVisible(true);
    }

    public void setVistaAnadirDirector(VistaAnadirDirector vistaAnadirDirector) {
        this.vistaAnadirDirector = vistaAnadirDirector;
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

    public void cambiarAno() {
        switch (tipoUsuario) {
            case 0:
                modelo.cambiarAnoAcademico(vistaPrincipalTutor.getAnoAcademico());
                break;
            case 1:
                modelo.cambiarAnoAcademico(vistaPrincipalDirector.getAnoAcademico());
                break;
        }
    }

    public void mostarAnadirAlumno() {
        vistaAnadirAlumno.setAnadir();
        vistaAnadirAlumno.setVisible(true);
    }

    public void insertarAlumno() {
        modelo.insertarAlumno(vistaAnadirAlumno.getNumMat(), vistaAnadirAlumno.getNombre(), vistaAnadirAlumno.getApellido1(), vistaAnadirAlumno.getApellido2(), vistaAnadirAlumno.getDNI());
        vistaAnadirAlumno.setVisible(false);
    }

    public void mostrarModificarAlumno() {
        int numMat = vistaAlumnos.getNumMatSeleccionado();
        String nombre = vistaAlumnos.getNombreSeleccionado();
        String apellido1 = vistaAlumnos.getApellido1Seleccionado();
        String apellido2 = vistaAlumnos.getApellido2Seleccionado();
        String dni = vistaAlumnos.getDniSeleccionado();
        vistaAnadirAlumno.setModificar(numMat, nombre, apellido1, apellido2, dni);
        vistaAnadirAlumno.setVisible(true);
    }

    public void modificarAlumno() {
        modelo.modificarAlumno(vistaAnadirAlumno.getNumMat(), vistaAnadirAlumno.getNombre(), vistaAnadirAlumno.getApellido1(), vistaAnadirAlumno.getApellido2(), vistaAnadirAlumno.getDNI());
        vistaAnadirAlumno.setVisible(false);
    }

    public void eliminarAlumno() {
        modelo.eliminarAlumno(vistaAlumnos.getNumMatSeleccionado());
    }

    //Getters y setters

    public void setVistaAnadirEmpresa(VistaAnadirEmpresa vistaAnadirEmpresa) {
        this.vistaAnadirEmpresa = vistaAnadirEmpresa;
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

    public void setVistaContenedorPrincipal(VistaContenedorPrincipal vistaContenedorPrincipal) {
        this.vistaContenedorPrincipal = vistaContenedorPrincipal;
    }

    public void setVistaAnadirAlumno(VistaAnadirAlumno vistaAnadirAlumno) {
        this.vistaAnadirAlumno = vistaAnadirAlumno;
    }

    public void setVistaAnadirGrupo(VistaAnadirGrupo vistaAnadirGrupo) {
        this.vistaAnadirGrupo = vistaAnadirGrupo;
    }

    public void setVistaSuperUsuario(VistaSuperUsuario vistaSuperUsuario) {
        this.vistaSuperUsuario = vistaSuperUsuario;
    }

    public void setVistaPrincipalTutor(VistaPrincipalTutor vistaPrincipalTutor) {
        this.vistaPrincipalTutor = vistaPrincipalTutor;
    }

    public void setVistaPrincipalDirector(VistaPrincipalDirector vistaPrincipalDirector) {
        this.vistaPrincipalDirector = vistaPrincipalDirector;
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

    public void setVistaAsignarPracticas(VistaAsignarPracticas vistaAsignarPracticas) {
        this.vistaAsignarPracticas = vistaAsignarPracticas;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setVistaLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
    }

    public void setVistaRecuperarPswd(VistaRecuperarPswd vistaRecuperarPswd) {
        this.vistaRecuperarPswd = vistaRecuperarPswd;
    }

}
