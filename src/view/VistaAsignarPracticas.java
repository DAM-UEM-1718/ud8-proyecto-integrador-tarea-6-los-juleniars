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

public class VistaAsignarPracticas extends JDialog implements Vista {

    private final JPanel contentPanel = new JPanel();
    private Controlador controlador;
    private Modelo modelo;

    private JDateChooser dateInicio;
    private JDateChooser dateFin;
    private JTextField txtTutorEmpresa;
    private JTextField txtHorario;
    private JTextField txtLocalizacion;

    private JComboBox cmbAlumno;
    private JComboBox cmbEmpresa;
    private JTextField txtEstado;
    private JCheckBox chckbxErasmus;
    private JButton btnAsignar;

    public VistaAsignarPracticas() {
        setTitle("Asignar Prácticas");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAsignarPracticas.class.getResource("/img/uem.png")));
        setBounds(100, 100, 450, 253);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        cmbAlumno = new JComboBox();

        JLabel lblAlumno = new JLabel("Alumno");

        JLabel lblEmpresa = new JLabel("Empresa");

        cmbEmpresa = new JComboBox();

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
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addComponent(lblAlumno)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(cmbAlumno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addComponent(lblInicio)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(dateInicio, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(dateFin, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                                                .addGap(30)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addComponent(lblHorario, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(txtHorario, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
                                                                .addGroup(gl_contentPanel.createSequentialGroup()
                                                                        .addComponent(lblTutorEmpresa)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(txtTutorEmpresa, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(gl_contentPanel.createSequentialGroup()
                                                                        .addComponent(lblEmpresa)
                                                                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(cmbEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblLocalizacion)
                                                        .addComponent(lblEstado))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addComponent(txtLocalizacion, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(chckbxErasmus))
                                                        .addComponent(txtEstado))))
                                .addContainerGap())
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(cmbAlumno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblAlumno)
                                                        .addComponent(lblEmpresa)
                                                        .addComponent(cmbEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(txtTutorEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(gl_contentPanel.createSequentialGroup()
                                                                        .addGap(6)
                                                                        .addComponent(lblInicio)))
                                                        .addComponent(dateInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addGap(3)
                                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(lblHorario)
                                                                        .addComponent(txtHorario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addGap(3)
                                                                .addComponent(lblFin))
                                                        .addComponent(dateFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addGap(45)
                                                .addComponent(lblTutorEmpresa)))
                                .addGap(10)
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblLocalizacion)
                                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(txtLocalizacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(chckbxErasmus)))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblEstado)
                                        .addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnAsignar = new JButton("Asignar");
                btnAsignar.setEnabled(false);
                btnAsignar.addActionListener(e -> controlador.asignarPracticas());
                btnAsignar.setActionCommand("OK");
                buttonPane.add(btnAsignar);
                getRootPane().setDefaultButton(btnAsignar);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(e -> controlador.cerrarAsignarPracticas());
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

    public void limpiarCampos() {
        cmbAlumno.setSelectedIndex(0);
        cmbEmpresa.setSelectedIndex(0);
        txtLocalizacion.setText("");
        dateInicio.setDate(new Date());
        dateFin.setDate(new Date());
        txtHorario.setText("");
        txtTutorEmpresa.setText("");
        txtEstado.setText("");
        chckbxErasmus.setSelected(false);
    }

    public void changed() {
        if (txtEstado.getText().equals("") || dateFin.getDate() == null || txtHorario.getText().equals("") || dateFin.getDate() == null || txtLocalizacion.getText().equals("") || txtLocalizacion.getText().equals("")) {
            btnAsignar.setEnabled(false);
        } else {
            btnAsignar.setEnabled(true);
        }
    }

    public void cargarCmbs() {
        cmbAlumno.setModel(modelo.getModeloCmbAlumnos());
        cmbEmpresa.setModel(modelo.getModeloCmbEmpresas());
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Modelo.ComboItem getComboItemAlumno() {
        return (Modelo.ComboItem) cmbAlumno.getModel().getSelectedItem();
    }

    public Modelo.ComboItem getComboItemEmpresa() {
        return (Modelo.ComboItem) cmbEmpresa.getModel().getSelectedItem();
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
}
