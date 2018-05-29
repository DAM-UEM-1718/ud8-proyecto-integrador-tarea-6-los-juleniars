package view;

import controller.Controlador;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class VistaModificarPracticas extends JDialog implements Vista {

    private final JPanel contentPanel = new JPanel();
    private Controlador controlador;
    private JTextField txtInicio;
    private JTextField txtFin;
    private JTextField txtTutorEmpresa;
    private JTextField txtHorario;
    private JTextField txtLocalizacion;

    private JComboBox cmbAlumno;
    private JComboBox cmbEmpresa;
    private JTextField txtEstado;
    private JCheckBox chckbxErasmus;
    private JButton btnOK;

    public VistaModificarPracticas() {
        setTitle("Modificar Prácticas");
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

        txtInicio = new JTextField();
        txtInicio.setColumns(10);

        JLabel lblFin = new JLabel("Fecha Fin:");

        txtFin = new JTextField();
        txtFin.setColumns(10);

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
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(txtInicio, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                                .addComponent(lblFin, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(4)
                                                                .addComponent(txtFin, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(31)
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
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(lblInicio)
                                                                .addComponent(txtInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(txtTutorEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
                                                        .addComponent(txtFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPanel.createSequentialGroup()
                                                .addGap(45)
                                                .addComponent(lblTutorEmpresa)))
                                .addGap(18)
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
                btnOK = new JButton("Modificar");
                btnOK.addActionListener(e -> controlador.asignarPracticas());
                btnOK.setActionCommand("OK");
                btnOK.setEnabled(false);
                buttonPane.add(btnOK);
                getRootPane().setDefaultButton(btnOK);
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
        txtFin.getDocument().addDocumentListener(documentListener);
        txtHorario.getDocument().addDocumentListener(documentListener);
        txtInicio.getDocument().addDocumentListener(documentListener);
        txtLocalizacion.getDocument().addDocumentListener(documentListener);
        txtTutorEmpresa.getDocument().addDocumentListener(documentListener);
    }

    public void changed() {
        if (txtEstado.getText().equals("") || txtFin.getText().equals("") || txtHorario.getText().equals("") || txtInicio.getText().equals("") || txtLocalizacion.getText().equals("") || txtLocalizacion.getText().equals("")) {
            btnOK.setEnabled(false);
        } else {
            btnOK.setEnabled(true);
        }
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public JComboBox getCmbAlumno() {
        return cmbAlumno;
    }

    public JComboBox getCmbEmpresa() {
        return cmbEmpresa;
    }

    public JTextField getTxtInicio() {
        return txtInicio;
    }

    public JTextField getTxtFin() {
        return txtFin;
    }

    public JTextField getTxtTutorEmpresa() {
        return txtTutorEmpresa;
    }

    public JTextField getTxtHorario() {
        return txtHorario;
    }

    public JTextField getTxtLocalizacion() {
        return txtLocalizacion;
    }

    public JTextField getTxtEstado() {
        return txtEstado;
    }

    public JCheckBox getChckbxErasmus() {
        return chckbxErasmus;
    }
}
