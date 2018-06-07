package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * Contenedor principal con un JTabbedPane que nos permitirá la navegación
 * entre pestañas
 */
public class VistaContenedorPrincipal extends JFrame implements Vista {

    private Modelo modelo;
    private Controlador controlador;

    private JTabbedPane jTabbedPane;
    private ChangeListener changeListener;

    public VistaContenedorPrincipal() {
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipalDirector.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(jTabbedPane);
        //Changelistener que indica que se debe de hacer cuando se cambie de pestaña
        changeListener = e -> {
            Component vista = jTabbedPane.getComponentAt(jTabbedPane.getSelectedIndex());
            //Utilizando el operador instanceof, selecciona la operación a realizar según el tipo de vista
            if (vista instanceof VistaPracticas) {
                controlador.mostrarPracticas();
                setBounds(100, 100, 1200, 400);
                setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            } else if (vista instanceof VistaTutores) {
                controlador.mostrarTutores();
                setBounds(100, 100, 700, 400);
                setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            } else if (vista instanceof VistaEmpresa) {
                controlador.mostrarEmpresas();
                setBounds(100, 100, 1200, 400);
                setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            } else if (vista instanceof VistaGrupos) {
                controlador.mostrarGrupos();
                setBounds(100, 100, 700, 400);
                setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            } else if (vista instanceof VistaAlumnos) {
                controlador.mostrarListaAlumnos();
                setBounds(100, 100, 700, 400);
                setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            } else if (vista instanceof VistaDirectores) {
                controlador.mostrarDirectores();
                setBounds(100, 100, 700, 400);
                setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            } else if (vista instanceof VistaPrincipalDirector) {
                setBounds(100, 100, 700, 400);
                setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            }else if (vista instanceof VistaPrincipalTutor) {
                setBounds(100, 100, 700, 400);
                setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
            }
        };
    }

    /**
     * Método para añadir las pestañas al contendor
     *
     * @param vistas ArrayList de ListasVistas que se añadirán al contenedor
     */
    public void cargarPestanas(ArrayList<ListaVistas> vistas) {
        //Elimina el changeListener antes de modificar las pestañas para evitar errores
        jTabbedPane.removeChangeListener(changeListener);
        //Elimina las pestañas que pueda haber
        jTabbedPane.removeAll();
        //Bucle que añade las vistas
        for (ListaVistas vista : vistas) {
            jTabbedPane.addTab(vista.getTitulo(), (Component) vista.getVista());
        }
        //Vuelve a añadir el changeListener
        jTabbedPane.addChangeListener(changeListener);

    }


    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

}
