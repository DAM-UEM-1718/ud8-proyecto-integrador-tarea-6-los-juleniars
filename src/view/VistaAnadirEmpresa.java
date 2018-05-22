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

public class VistaAnadirEmpresa extends JFrame implements Vista {

    private JPanel contentPane;
    private JTextField txtFirma;
    private JTextField txtConvenio;
    private JTextField txtNombre;
    private JTextField textField_2;

    private Controlador controlador;

    public VistaAnadirEmpresa() {
        setTitle("Añadir Empresa");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAnadirEmpresa.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 326, 265);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JLabel lblCdigoDeEmpresa = new JLabel("F. Firma");

        JLabel lblNmeroDeConvenio = new JLabel("Número de Convenio");

        JLabel lblNombre = new JLabel("Nombre");

        JLabel lblNif = new JLabel("NIF");

        txtFirma = new JTextField();
        txtFirma.setColumns(10);

        JButton btnAnadir = new JButton("Añadir Empresa");
        btnAnadir.addActionListener(e -> controlador.anadirEmpresa());

        txtConvenio = new JTextField();
        txtConvenio.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblCdigoDeEmpresa)
                                        .addComponent(lblNmeroDeConvenio)
                                        .addComponent(lblNombre)
                                        .addComponent(lblNif))
                                .addGap(51)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(txtConvenio)
                                                .addComponent(txtFirma, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                                .addContainerGap(64, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(227, Short.MAX_VALUE)
                                .addComponent(btnAnadir)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblCdigoDeEmpresa)
                                        .addComponent(txtFirma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNmeroDeConvenio)
                                        .addComponent(txtConvenio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNombre)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNif)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(btnAnadir)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
