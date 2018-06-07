package view;

import controller.Controlador;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Vista que permite el inicionde sesión ya acceder al menú de configuración de la base de datos,
 * al menú de recuperar contraseña y al menú de registro
 *
 * @author Los Juleniars
 */
public class VistaLogin extends JFrame implements Vista {

    private Controlador controlador;

    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField pswContrasena;
    private JButton btnIniciarSesin;
    private JButton btnRecuperarContrasea;
    private JButton btnRegistro;
    private JButton btnConfig;
    private JLabel lblUsuario;
    private JLabel lblContrasea;
    private JLabel lblAviso;

    private boolean conectado;


    public VistaLogin() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/img/uem.png")));
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 501, 325);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        lblUsuario = new JLabel("Usuario");

        txtUsuario = new JTextField();
        txtUsuario.setColumns(10);

        lblContrasea = new JLabel("Contraseña");

        pswContrasena = new JPasswordField();

        btnIniciarSesin = new JButton("Iniciar Sesión");
        btnIniciarSesin.setEnabled(false);
        btnIniciarSesin.addActionListener(e -> {
            controlador.iniciarSesion();
            //txtUsuario.setText("");
            //pswContrasena.setText("");
            getRootPane().setDefaultButton(btnIniciarSesin);
        });

        txtUsuario.getDocument().addDocumentListener(new DocumentListener() {
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
        pswContrasena.getDocument().addDocumentListener(new DocumentListener() {
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


        btnRecuperarContrasea = new JButton("Recuperar Contraseña");

        btnRegistro = new JButton("Registro");
        btnRegistro.addActionListener(e -> controlador.mostrarRegistro());

        btnConfig = new JButton("");

        lblAviso = new JLabel("Conectando con la base de datos...");
        lblAviso.setForeground(Color.ORANGE);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(440, Short.MAX_VALUE)
                                .addComponent(btnConfig)
                                .addContainerGap())
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addGap(108)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(btnRegistro)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(lblUsuario)
                                                                .addComponent(lblContrasea))
                                                        .addGap(45)
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                                .addComponent(pswContrasena)
                                                                .addComponent(txtUsuario, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addComponent(btnRecuperarContrasea)
                                                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnIniciarSesin))
                                                .addComponent(lblAviso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(93, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnConfig)
                                .addGap(50)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblUsuario)
                                        .addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblContrasea)
                                        .addComponent(pswContrasena, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblAviso, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addGap(2)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnRecuperarContrasea)
                                        .addComponent(btnIniciarSesin))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnRegistro)
                                .addContainerGap(52, Short.MAX_VALUE))
        );

        BufferedImage icono = null;
        try {
            Image img = ImageIO.read(getClass().getResource("/img/gear.png"));
            btnConfig.setIcon(new ImageIcon(img.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnConfig.setBorder(BorderFactory.createEmptyBorder());
        btnConfig.setContentAreaFilled(false);
        btnConfig.addActionListener(e -> controlador.mostrarConfigFichero());
        btnRecuperarContrasea.addActionListener(e -> controlador.mostrarRecuperarContrasena());
        getRootPane().setDefaultButton(btnIniciarSesin);
        contentPane.setLayout(gl_contentPane);

    }

    public String getTxtUsuario() {
        return txtUsuario.getText();
    }

    public String getPswContrasena() {
        return new String(pswContrasena.getPassword());
    }

    public void sesionIniciada() {
        //JOptionPane.showMessageDialog(null, "Sesión iniciada");
        limpiarCampos();
        controlador.mostrarVistaSesion();
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void contrasenaEnviada() {
        lblAviso.setForeground(Color.GREEN);
        lblAviso.setText("Contraseña enviada.");
    }

    public void errorInicioSesion() {
        lblAviso.setForeground(Color.RED);
        lblAviso.setText("Usuario o contraseña incorrectos.");
    }

    public void errorGenerarContrasena() {
        lblAviso.setForeground(Color.RED);
        lblAviso.setText("Error al enviar la nueva contraseña.");
    }

    public void errorCrearUsuario() {
        lblAviso.setForeground(Color.RED);
        lblAviso.setText("Error al crear el nuevo usuario.");
    }

    public void errorConexion() {
        lblAviso.setForeground(Color.RED);
        lblAviso.setText("No ha sido posible conectarse a la base de datos.");
    }

    private void limpiarCampos() {
        lblAviso.setText("");
        txtUsuario.setText("");
        pswContrasena.setText("");
    }

    private void changed() {
        if (txtUsuario.getText().equals("") || new String(pswContrasena.getPassword()).equals("") || !conectado) {
            btnIniciarSesin.setEnabled(false);
        } else {
            btnIniciarSesin.setEnabled(true);
        }

    }

    public void conectado() {
        lblAviso.setText("");
        conectado = true;
    }

    public void intentosSuperados() {
        Object[] opciones = {"Aceptar"};
        int input = JOptionPane.showOptionDialog(null, "Intentos de inicio de sesión superados. La aplicación se va a cerrar.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        System.exit(0);

    }
}
