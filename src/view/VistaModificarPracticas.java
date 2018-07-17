package view;

import com.toedter.calendar.JDateChooser;
import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Date;

/**
 * Clase que permite modificar las prÃ¡cticas
 *
 * @author Los Juleniars
 */
public class VistaModificarPracticas extends JDialog implements Vista {

    private final JPanel contentPanel = new JPanel();
    private Controlador controlador;
    private Modelo modelo;
    private JTextField txtTutorEmpresa;
    private JTextField txtHorario;
    private JTextField txtLocalizacion;
    private JTextField txtEstado;
    private JCheckBox chckbxErasmus;
    private JButton btnOK;
    private JLabel lblAlumno;
    private JLabel lblEmpresa;
    private JDateChooser dateInicio;
    private JDateChooser dateFin;


    private int numMat;
    private int numConv;
    private int fila;

    public VistaModificarPracticas() {
        setTitle("Modificar Prácticas");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAsignarPracticas.class.getResource("/img/uem.png")));
        setBounds(100, 100, 450, 236);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        lblAlumno = new JLabel("Alumno:");

        lblEmpresa = new JLabel("Empresa:");

        JLabel lblInicio = new JLabel("Fecha Inicio:");

        JLabel lblFin = new JLabel("Fecha Fin:");

        JLabel lblTutorEmpresa = new JLabel("Tutor Empresa:");

        txtTutorEmpresa = new JTextField();
        txtTutorEmpresa.setColumns(10);

        JLabel lblHorario = new JLabel("Horario:");

        txtHorario = new JTextField();
        txtHorario.setColumns(10);

        JLabel lblLocalizacion = new JLabel("Localización");

        txtLocalizacion = new JTextField();
        txtLocalizacion.setColumns(10);

        chckbxErasmus = new JCheckBox("Erasmus");

        JLabel lblEstado = new JLabel("Estado:");

        txtEstado = new JTextField();
        txtEstado.setColumns(10);

        dateInicio = new JDateChooser();
        dateFin = new JDateChooser();

        dateInicio.setDate(new Date());
        dateFin.setDate(new Date());

        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addComponent(lblAlumno)
                                                .addPreferredGap(ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                                                .addComponent(lblEmpresa)
                                                .addGap(156))
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addGroup(gl_contentPanel.createSequentialGroup()
                                                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(lblTutorEmpresa)
                                                                .addComponent(lblHorario, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
                                                        .addGap(12))
                                                .addGap(0)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(txtHorario, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtTutorEmpresa, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblFin, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(dateInicio, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(dateFin, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                                .addGap(29))
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addComponent(lblLocalizacion)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                                .addComponent(txtLocalizacion, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                                                                .addComponent(chckbxErasmus))
                                                                        .addComponent(txtEstado, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
                                                        .addComponent(lblEstado))
                                                .addGap(148))))
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblAlumno)
                                        .addComponent(lblEmpresa))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblTutorEmpresa)
                                                        .addComponent(txtTutorEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(6)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(txtHorario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblHorario)))
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addGap(6)
                                                                .addComponent(lblInicio))
                                                        .addComponent(dateInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(gl_contentPanel.createSequentialGroup()
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(lblFin)
                                                                .addComponent(dateFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18)
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblLocalizacion)
                                        .addComponent(txtLocalizacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chckbxErasmus))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblEstado)
                                        .addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(127))
        );
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnOK = new JButton("Modificar");
                btnOK.addActionListener(e -> controlador.modificarPracticas());
                btnOK.setActionCommand("OK");
                btnOK.setEnabled(false);
                buttonPane.add(btnOK);
                getRootPane().setDefaultButton(btnOK);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(e -> controlador.cerrarModificarPracticas());
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

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
        txtEstado.getDocument().addDocumentListener(documentListener);
        txtHorario.getDocument().addDocumentListener(documentListener);
        txtLocalizacion.getDocument().addDocumentListener(documentListener);
        txtTutorEmpresa.getDocument().addDocumentListener(documentListener);
    }

    public void changed() {
        if (txtEstado.getText().equals("") || dateFin.getDate() == null || txtHorario.getText().equals("") || dateInicio.getDate() == null || txtLocalizacion.getText().equals("") || txtLocalizacion.getText().equals("")) {
            btnOK.setEnabled(false);
        } else {
            btnOK.setEnabled(true);
        }
    }

    public void cargarDatos(int numMat, int numConv, String nombreAlumno, String nombreEmpresa, String tutorEmpresa, Date fechaInicio, Date fechaFin, String horario, String localizacion, boolean erasmus, String estado, int fila) {
        this.numMat = numMat;
        this.numConv = numConv;
        this.fila = fila;
        lblAlumno.setText("Alumno: " + nombreAlumno);
        lblEmpresa.setText("Empresa: " + nombreEmpresa);
        txtTutorEmpresa.setText(tutorEmpresa);
        dateInicio.setDate(fechaInicio);
        dateFin.setDate(fechaFin);
        txtHorario.setText(horario);
        txtLocalizacion.setText(localizacion);
        chckbxErasmus.setSelected(erasmus);
        txtEstado.setText(estado);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Date getDateInicio() {
        return dateInicio.getDate();
    }

    public Date getDateFin() {
        return dateFin.getDate();
    }

    public String getTxtTutorEmpresa() {
        return txtTutorEmpresa.getText();
    }

    public String getTxtHorario() {
        return txtHorario.getText();
    }

    public String getTxtLocalizacion() {
        return txtLocalizacion.getText();
    }

    public String getTxtEstado() {
        return txtEstado.getText();
    }

    public boolean getChckbxErasmus() {
        return chckbxErasmus.isSelected();
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public int getNumMat() {
        return numMat;
    }

    public int getNumConv() {
        return numConv;
    }

    public int getFila() {
        return fila;
    }
}
