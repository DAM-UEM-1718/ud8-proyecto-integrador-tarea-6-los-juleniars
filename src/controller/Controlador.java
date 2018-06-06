package controller;

import model.Modelo;
import view.*;

import java.util.ArrayList;
import java.util.Date;

/**
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
    private VistaMensaje vistaMensaje;
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
                modelo.cargarAnosAcademicos();
                modelo.mostrarGrupoTutor();
                modelo.mostrarPracticasTutor();
                vistaPrincipalTutor.setLblBienvenido();
                //vistaPrincipalTutor.setVisible(true);
                ArrayList<ListaVistas> vistasTutor = new ArrayList<>();
                vistasTutor.add(new ListaVistas("Principal", vistaPrincipalTutor));
                vistasTutor.add(new ListaVistas("Detalle Prácticas", vistaPracticas));
                vistasTutor.add(new ListaVistas("Mis Alumnos", vistaAlumnos));
                vistaContenedorPrincipal.cargarPestanas(vistasTutor);
                vistaContenedorPrincipal.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            case 1:
                modelo.cargarAnosAcademicos();
                modelo.mostrarDashboardDirector();
                vistaPrincipalDirector.setLblBienvenido();
                //vistaPrincipalDirector.setVisible(true);

                ArrayList<ListaVistas> vistasDirector = new ArrayList<>();
                vistasDirector.add(new ListaVistas("Principal", vistaPrincipalDirector));
                vistasDirector.add(new ListaVistas("Prácticas", vistaPracticas));
                vistasDirector.add(new ListaVistas("Tutores", vistaTutores));
                vistasDirector.add(new ListaVistas("Grupos", vistaGrupos));
                vistasDirector.add(new ListaVistas("Empresas", vistaEmpresa));
                vistaContenedorPrincipal.cargarPestanas(vistasDirector);
                vistaContenedorPrincipal.setVisible(true);
                vistaLogin.setVisible(false);
                break;
            case 2:
                ArrayList<ListaVistas> vistasRoot = new ArrayList<>();
                vistasRoot.add(new ListaVistas("Directores", vistaDirectores));
                vistasRoot.add(new ListaVistas("Tutores", vistaTutores));
                vistasRoot.add(new ListaVistas("Alumnos", vistaAlumnos));
                modelo.cargarPersonal();
                //vistaSuperUsuario.setVisible(true);
                vistaContenedorPrincipal.cargarPestanas(vistasRoot);
                vistaContenedorPrincipal.setVisible(true);
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

    public void mostrarListaAlumnos() {
        if (tipoUsuario == 0) {
            modelo.cargarAlumnosTutor();
        } else if (tipoUsuario == 2)
            modelo.cargarAlumnosDirector();
        vistaSuperUsuario.setVisible(false);
    }

    public void mostarConfiguracion() {
        vistaConfiguracion.setVisible(true);
    }

    public void mostrarPracticas() {
        //cerrarPanelUsuario();
        modelo.cargarPracticas();
        //vistaPracticas.setVisible(true);
    }

    public void mostrarTutores() {
        modelo.cargarTutores();
        //cerrarPanelUsuario();
        //vistaTutores.setVisible(true);
    }

    public void mostrarGrupos() {
        modelo.cargarGrupos();
        //vistaPrincipalDirector.setVisible(false);
        //vistaGrupos.setVisible(true);
    }

    public void mostrarEmpresas() {
        modelo.cargarEmpresas();
        //vistaPrincipalDirector.setVisible(false);
        //vistaEmpresa.setVisible(true);
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

    public void mostrarPersonal() {
        modelo.cargarPersonal();
        //cerrarPanelUsuario();
        //vistaDirectores.setVisible(true);
    }

    public void cerrarPersonal() {
        abrirPanelUsuario();
        vistaDirectores.setVisible(false);
    }

    private void abrirPanelUsuario() {
        switch (tipoUsuario) {
            case 0:
                vistaContenedorPrincipal.setVisible(true);
                //vistaPrincipalTutor.setVisible(true);
                break;
            case 1:
                vistaContenedorPrincipal.setVisible(true);
                //vistaPrincipalDirector.setVisible(true);
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
                vistaContenedorPrincipal.setVisible(false);
                //vistaPrincipalTutor.setVisible(false);
                break;
            case 1:
                vistaContenedorPrincipal.setVisible(false);
                //vistaPrincipalDirector.setVisible(false);
                break;
            case 2:
                vistaSuperUsuario.setVisible(false);
                break;
            default:
                break;
        }
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

    public void setVistaMensaje(VistaMensaje vistaMensaje) {
        this.vistaMensaje = vistaMensaje;
    }

    public void cerrarMensaje() {
        vistaMensaje.setVisible(false);
    }

    public void anadirTutor() {
        modelo.generarUsuario(vistaAnadirTutor.getNombre(), vistaAnadirTutor.getUsuario(), vistaAnadirTutor.getTxtMail(), vistaAnadirTutor.getNIF(), (byte) 0);
        vistaAnadirTutor.setVisible(false);
    }

    public void anadirAdministrativo() {
        modelo.generarUsuario(vistaAnadirDirector.getNombre(), vistaAnadirDirector.getTxtExpediente(), vistaAnadirDirector.getTxtMail(), vistaAnadirDirector.getNIF(), (byte) 1);
        vistaAnadirDirector.setVisible(false);
    }

    public void mostrarAnadirUsuario() {
        //vistaTutores.setVisible(false);
        vistaAnadirTutor.setAnadir();
        vistaAnadirTutor.setVisible(true);
    }

    public void mostrarModificarUsuario() {
        //vistaTutores.setVisible(false);
        String usuario = vistaTutores.getUsuarioSeleccionado();
        String nombre = vistaTutores.getNombreSeleccionado();
        String mail = vistaTutores.getMailSeleccionado();
        String nif = vistaTutores.getNIFSeleccionado();
        vistaAnadirTutor.setModificar(nombre, usuario, mail, nif);
        vistaAnadirTutor.setVisible(true);
    }

    public void modificarTutor() {
        modelo.modificarUsuario(vistaAnadirTutor.getNombre(), vistaAnadirTutor.getUsuario(), vistaAnadirTutor.getTxtMail(), vistaAnadirTutor.getNIF());
        vistaAnadirTutor.setVisible(false);
    }

    public void eliminarTutor() {
        modelo.eliminarUsuario(vistaTutores.getUsuarioSeleccionado());
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
}
