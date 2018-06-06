package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

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
        changeListener= e -> {
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
            } else if (vista instanceof VistaDirectores){
                controlador.mostrarPersonal();
            }
        };
    }

    public void cargarPestanas(ArrayList<ListaVistas> vistas) {
        jTabbedPane.removeChangeListener(changeListener);
        jTabbedPane.removeAll();
        for (ListaVistas vista : vistas) {
            jTabbedPane.addTab(vista.getTitulo(), (Component) vista.getVista());
        }
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
