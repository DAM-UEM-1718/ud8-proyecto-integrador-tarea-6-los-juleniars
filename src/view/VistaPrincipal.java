package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VistaPrincipal extends JFrame implements Vista {

    private Modelo modelo;
    private Controlador controlador;

    private JTabbedPane jTabbedPane;

    public VistaPrincipal(HashMap<String, Vista> vistas) {
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipalAdministrativo.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        for (String vista : vistas.keySet()) {
            jTabbedPane.addTab(vista, (Component) vistas.get(vista));
        }
        jTabbedPane.addChangeListener(e -> {
            if (jTabbedPane.getTabComponentAt(jTabbedPane.getSelectedIndex()) instanceof VistaPracticas) {
                controlador.mostrarPracticas();
            }
        });
        getContentPane().add(jTabbedPane);
    }


    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

}
