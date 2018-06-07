package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Vista principal del superusuario
 *
 * @author Los Juleniars
 */
public class VistaSuperUsuario extends JPanel implements Vista {

    private Controlador controlador;

    public VistaSuperUsuario() {
        setBounds(100, 100, 450, 300);
        setBorder(new EmptyBorder(5, 5, 5, 5));
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
        GroupLayout gl_contentPane = new GroupLayout(this);
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
        setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
