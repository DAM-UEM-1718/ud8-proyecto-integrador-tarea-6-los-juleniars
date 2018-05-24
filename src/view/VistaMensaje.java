package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaMensaje extends JDialog implements Vista {

    private final JPanel contentPanel = new JPanel();
    private Controlador controlador;

    private JLabel lblMensaje;

    public VistaMensaje() {
        setTitle("Alerta");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaMensaje.class.getResource("/img/uem.png")));
        setBounds(100, 100, 450, 135);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        lblMensaje = new JLabel("");
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addGap(41)
                                .addComponent(lblMensaje, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(42, Short.MAX_VALUE))
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblMensaje, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                .addContainerGap())
        );
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Aceptar");
                okButton.addActionListener(e -> controlador.cerrarMensaje());
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;

    }

    public JLabel getLblMensaje() {
        return lblMensaje;
    }
}
