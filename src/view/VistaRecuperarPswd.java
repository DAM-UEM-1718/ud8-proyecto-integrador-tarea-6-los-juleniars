package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Vista que permite cambiar la contraseÃ±a
 */
public class VistaRecuperarPswd extends JDialog implements Vista {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblNombreUsuario;
    private JTextField txtNombreUsuario;
    private Controlador controlador;
    private JButton okButton;


    public VistaRecuperarPswd() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Recuperar Contraseña");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaRecuperarPswd.class.getResource("/img/uem.png")));
        setBounds(100, 100, 450, 199);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            lblNombreUsuario = new JLabel("Nombre de usuario");
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setColumns(10);
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addGap(62)
                                .addComponent(lblNombreUsuario)
                                .addGap(34)
                                .addComponent(txtNombreUsuario, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(58, Short.MAX_VALUE))
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addGap(47)
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNombreUsuario)
                                        .addComponent(txtNombreUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(56, Short.MAX_VALUE))
        );
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("Enviar");
                okButton.addActionListener(e -> controlador.recuperarContrasena());
                okButton.setActionCommand("OK");
                okButton.setEnabled(false);
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(e -> setVisible(false));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        txtNombreUsuario.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

        });
    }

    private void changed() {
        if (txtNombreUsuario.getText().equals("")) {
            okButton.setEnabled(false);
        } else {
            okButton.setEnabled(true);
        }
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }


    public String getTxtNombreUsuario() {
        return txtNombreUsuario.getText();
    }
}
