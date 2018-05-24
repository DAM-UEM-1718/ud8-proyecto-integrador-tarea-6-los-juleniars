package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VistaRegistro extends JFrame implements Vista {

    private JPanel contentPane;
    private Controlador controlador;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JLabel lblContrasea;
    private JLabel lblNif;
    private JLabel lblRegistro;

    public VistaRegistro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        textField = new JTextField();
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        
        JButton btnValidar = new JButton("VALIDAR");
        
        JLabel lblNombreYApellidos = new JLabel("Nombre y Apellidos:");
        
        JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
        
        JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");
        
        lblContrasea = new JLabel("Contraseña:");
        
        lblNif = new JLabel("NIF:");
        
        lblRegistro = new JLabel("REGISTRO");
        lblRegistro.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 20));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblContrasea)
        								.addComponent(lblNombreYApellidos)
        								.addComponent(lblNif)
        								.addGroup(gl_contentPane.createSequentialGroup()
        									.addComponent(lblNombreDeUsuario)
        									.addPreferredGap(ComponentPlacement.UNRELATED)))
        							.addGap(10))
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addComponent(lblCorreoElectronico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addPreferredGap(ComponentPlacement.RELATED)))
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        							.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        							.addContainerGap())
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        							.addContainerGap())
        						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
        								.addComponent(textField_4, Alignment.LEADING)
        								.addComponent(textField_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
        							.addContainerGap())
        						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
        							.addContainerGap())))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(318, Short.MAX_VALUE))))
        		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        			.addGap(173)
        			.addComponent(btnValidar)
        			.addContainerGap(176, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNombreYApellidos))
        			.addGap(7)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNombreDeUsuario))
        			.addGap(9)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblCorreoElectronico))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblContrasea))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNif))
        			.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
        			.addComponent(btnValidar)
        			.addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
