package view;

import controller.Controlador;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VistaAsignarPracticas extends JDialog implements Vista {

    private Controlador controlador;

    private final JPanel contentPanel = new JPanel();
    private JTextField txtInicio;
    private JTextField txtFin;
    private JTextField txtTutorEmpresa;
    private JTextField txtHorario;
    private JTextField txtLocalizacion;

    public VistaAsignarPracticas() {
        setTitle("Asignar Prácticas");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAsignarPracticas.class.getResource("/img/uem.png")));
        setBounds(100, 100, 450, 225);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JComboBox cmbAlumno = new JComboBox();
        cmbAlumno.setModel(new DefaultComboBoxModel(new String[]{"Julen Bujanda", "Víctor Jiménez"}));

        JLabel lblAlumno = new JLabel("Alumno");

        JLabel lblEmpresa = new JLabel("Empresa");

        JComboBox cmbEmpresa = new JComboBox();
        cmbEmpresa.setModel(new DefaultComboBoxModel(new String[]{"Apple España S.A.", "Twitter Inc.", "Facebook Inc."}));

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
                                                .addComponent(lblLocalizacion)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(txtLocalizacion, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
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
                                        .addComponent(txtLocalizacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Asignar");
                okButton.addActionListener(e -> controlador.cerrarAsignarPracticas());
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
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
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
