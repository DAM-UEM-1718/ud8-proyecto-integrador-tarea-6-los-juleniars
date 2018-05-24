package view;

import controller.Controlador;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaAnadirAdministrativo extends JFrame implements Vista {

    private Controlador controlador;

    private JPanel contentPane;
    private JTextField txtMail;

    private JTextField txtExpediente;

    public VistaAnadirAdministrativo() {
        setTitle("Añadir Director");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAnadirTutor.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JLabel lblMail = new JLabel("Mail");
        txtExpediente = new JTextField();
        txtExpediente.setColumns(10);

        JLabel lblNExpediente = new JLabel("N. Expediente");
        txtMail = new JTextField();
        txtMail.setColumns(10);


        JButton btnAadir = new JButton("Añadir");
        btnAadir.addActionListener(e -> {
            controlador.anadirAdministrativo();
            controlador.mostrarTutores();
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(82)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNExpediente)
                                        .addComponent(lblMail))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnAadir)
                                        .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtExpediente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(167, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(42)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNExpediente)
                                        .addComponent(txtExpediente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblMail)
                                        .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnAadir)
                                .addContainerGap(117, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }


    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public JTextField getTxtMail() {
        return txtMail;
    }

    public JTextField getTxtExpediente() {
        return txtExpediente;
    }
}
