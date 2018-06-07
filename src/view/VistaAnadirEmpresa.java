package view;

import com.toedter.calendar.JDateChooser;
import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Date;

/**
 * Vista de laaplicación que permite añadir empresas
 *
 * @author Los Juleniars
 */
public class VistaAnadirEmpresa extends JFrame implements Vista {

    private JPanel contentPane;
    private JTextField txtConvenio;
    private JTextField txtNombre;

    private Controlador controlador;
    private JTextField txtLocalidad;
    private JTextField txtDireccion;
    private JTextField txtRepresentante;
    private JTextField txtMail;
    private JDateChooser dateFirma;
    private JButton btnAnadir;

    private boolean modificar;

    public VistaAnadirEmpresa() {
        setTitle("Añadir Empresa");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAnadirEmpresa.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 335, 368);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JLabel lblCdigoDeEmpresa = new JLabel("F. Firma");

        JLabel lblNmeroDeConvenio = new JLabel("Número de Convenio");

        JLabel lblNombre = new JLabel("Nombre");

        btnAnadir = new JButton("Añadir Empresa");
        btnAnadir.setEnabled(false);
        btnAnadir.addActionListener(e -> {
            if (!modificar)
                controlador.anadirEmpresa();
            else
                controlador.modificarEmpresa();
        });

        txtConvenio = new JTextField();
        txtConvenio.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);

        dateFirma = new JDateChooser();

        txtLocalidad = new JTextField();
        txtLocalidad.setColumns(10);

        JLabel lblLocalidad = new JLabel("Localidad");

        txtDireccion = new JTextField();
        txtDireccion.setColumns(10);

        JLabel lblDireccin = new JLabel("Dirección");

        txtRepresentante = new JTextField();
        txtRepresentante.setColumns(10);

        JLabel lblRepresentante = new JLabel("Representante");

        txtMail = new JTextField();
        txtMail.setColumns(10);

        JLabel lblMail = new JLabel("Mail");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAnadir)
                                                .addContainerGap())
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblLocalidad)
                                                        .addComponent(lblDireccin)
                                                        .addComponent(lblRepresentante)
                                                        .addComponent(lblMail)
                                                        .addComponent(lblNmeroDeConvenio)
                                                        .addComponent(lblNombre)
                                                        .addComponent(lblCdigoDeEmpresa))
                                                .addGap(46)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(dateFirma, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtNombre, Alignment.LEADING)
                                                        .addComponent(txtLocalidad, Alignment.LEADING, 152, 152, Short.MAX_VALUE)
                                                        .addComponent(txtDireccion, Alignment.LEADING)
                                                        .addComponent(txtRepresentante, Alignment.LEADING)
                                                        .addComponent(txtMail, Alignment.LEADING)
                                                        .addComponent(txtConvenio))
                                                .addGap(15))))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblCdigoDeEmpresa)
                                        .addComponent(dateFirma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(12)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNmeroDeConvenio)
                                        .addComponent(txtConvenio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(19)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombre))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtLocalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblLocalidad))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblDireccin)
                                        .addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtRepresentante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblRepresentante))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMail))
                                .addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(btnAnadir)
                                .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
        DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

        };
        txtNombre.getDocument().addDocumentListener(documentListener);
        txtConvenio.getDocument().addDocumentListener(documentListener);
        txtMail.getDocument().addDocumentListener(documentListener);
        txtDireccion.getDocument().addDocumentListener(documentListener);
        txtLocalidad.getDocument().addDocumentListener(documentListener);
        txtRepresentante.getDocument().addDocumentListener(documentListener);
    }

    private void changed() {
        if (txtNombre.getText().equals("") || txtConvenio.getText().equals("") || txtMail.getText().equals("") || txtDireccion.getText().equals("") || txtLocalidad.getText().equals("") || txtRepresentante.getText().equals("")) {
            btnAnadir.setEnabled(false);
        } else {
            btnAnadir.setEnabled(true);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtConvenio.setText("");
        txtMail.setText("");
        txtDireccion.setText("");
        txtLocalidad.setText("");
        txtRepresentante.setText("");
        dateFirma.setDate(new Date());
    }

    public void setAnadir() {
        limpiarCampos();
        modificar = false;
        setTitle("Añadir Empresa");
        btnAnadir.setText("Añadir");
        txtConvenio.setEditable(true);
    }

    public void setModificar(String nombre, Date fechaFirma, int convenio, String mail, String direccion, String localidad, String representante) {
        setTitle("Modificar Empresa");
        btnAnadir.setText("Modificar");
        modificar = true;
        txtNombre.setText(nombre);
        txtConvenio.setText(Integer.toString(convenio));
        txtMail.setText(mail);
        txtDireccion.setText(direccion);
        txtLocalidad.setText(localidad);
        txtRepresentante.setText(representante);
        dateFirma.setDate(fechaFirma);
        txtConvenio.setEditable(false);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public int getConvenio() {
        return Integer.parseInt(txtConvenio.getText());
    }

    public String getTxtNombre() {
        return txtNombre.getText();
    }

    public String getTxtLocalidad() {
        return txtLocalidad.getText();
    }

    public String getTxtDireccion() {
        return txtDireccion.getText();
    }

    public String getTxtRepresentante() {
        return txtRepresentante.getText();
    }

    public String getTxtMail() {
        return txtMail.getText();
    }

    public Date getFechaFirma() {
        return dateFirma.getDate();
    }
}
