package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controlador;

import java.awt.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VistaSuperUsuario extends JFrame implements Vista {

    private JPanel contentPane;
    private Controlador controlador;

    public VistaSuperUsuario() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaSuperUsuario.class.getResource("/img/uem.png")));
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JButton btnNewButton = new JButton("Editar Alumnos");
        btnNewButton.addActionListener(e -> controlador.mostrarListaAlumnos());

        JLabel lblPanelDeAdministracin = new JLabel("Panel de Administración");
        lblPanelDeAdministracin.setFont(new Font("Tahoma", Font.BOLD, 14));

        JButton btnEditarTutores = new JButton("Editar Tutores");
        btnEditarTutores.addActionListener(e -> controlador.mostrarTutores());

        JButton btnEditarPersonalAdministraivo = new JButton("Editar Personal Administrativo");
        btnEditarPersonalAdministraivo.addActionListener(e -> controlador.mostrarDirectores());

        JButton btnCerrarSesin = new JButton("Cerrar Sesión");
        btnCerrarSesin.addActionListener(e -> controlador.cerrarSesion());
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblPanelDeAdministracin)
                                        .addComponent(btnNewButton)
                                        .addComponent(btnEditarTutores)
                                        .addComponent(btnEditarPersonalAdministraivo))
                                .addContainerGap(243, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(325, Short.MAX_VALUE)
                                .addComponent(btnCerrarSesin)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblPanelDeAdministracin)
                                .addGap(18)
                                .addComponent(btnNewButton)
                                .addGap(18)
                                .addComponent(btnEditarTutores)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnEditarPersonalAdministraivo)
                                .addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                                .addComponent(btnCerrarSesin)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
