package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaContenedorPrincipal extends JFrame implements Vista {

    private Modelo modelo;
    private Controlador controlador;

    private JTabbedPane jTabbedPane;

    public VistaContenedorPrincipal() {
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipalAdministrativo.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        jTabbedPane.addChangeListener(e -> {
            Component vista = jTabbedPane.getComponentAt(jTabbedPane.getSelectedIndex());
            if (vista instanceof VistaPracticas) {
                controlador.mostrarPracticas();
            } else if (vista instanceof VistaTutores) {
                controlador.mostrarTutores();
            } else if (vista instanceof VistaEmpresa) {
                controlador.mostrarEmpresas();
            } else if (vista instanceof VistaGrupos) {
                controlador.mostrarGrupos();
            } else if (vista instanceof VistaAlumnos) {
                controlador.mostrarListaAlumnos();
            }
        });
        getContentPane().add(jTabbedPane);
    }

    public void cargarPestanas(ArrayList<ListaVistas> vistas) {
        jTabbedPane.removeAll();
        for (ListaVistas vista : vistas) {
            jTabbedPane.addTab(vista.getTitulo(), (Component) vista.getVista());
        }

    }


    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

}
