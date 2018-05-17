package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import controller.Controlador;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaPracticas extends JFrame implements Vista {

    private JPanel contentPane;
    private JTable table;
    private JTextField txtMatricula;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtDNI;
    private JTextField txtEmpresa;
    private Controlador controlador;


    public VistaPracticas() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAlumnos.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 693, 392);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JScrollPane scrollPane = new JScrollPane();

        txtMatricula = new JTextField();
        txtMatricula.setToolTipText("");
        txtMatricula.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setColumns(10);

        txtApellidos = new JTextField();
        txtApellidos.setColumns(10);

        txtDNI = new JTextField();
        txtDNI.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JLabel lblTitulo = new JLabel("Prácticas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        txtEmpresa = new JTextField();
        txtEmpresa.setColumns(10);

        JButton btnAsignar = new JButton("Asignar Prácticas");
        btnAsignar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarAsignarPracticas();
            }
        });

        JButton btnModificar = new JButton("Modificar Prácticas");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnEliminarPracticas = new JButton("Eliminar Prácticas");
        btnEliminarPracticas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.cerrarPracticas();
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(txtMatricula)
                                                        .addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(txtEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                                                .addComponent(btnBuscar))
                                                        .addComponent(btnVolver)))
                                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAsignar)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnModificar)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminarPracticas)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(23)
                                                .addComponent(lblTitulo))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnVolver)))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmpresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAsignar)
                                        .addComponent(btnModificar)
                                        .addComponent(btnEliminarPracticas))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {"Iv\u00E1n Hern\u00E1ndez", "Apple Espa\u00F1a S.A.", "01/03/2018 - 01/06-2018"},
                        {"V\u00EDctorJim\u00E9nez", "Facebook Inc.", "01/03/2018 - 01/06-2018"},
                        {"Andr\u00E9s Murillas", "Twitter Inc.", "01/03/2018 - 01/06-2018"},
                },
                new String[]{
                        "Alumnos", "Empresa", "Periodo"
                }
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(91);
        table.getColumnModel().getColumn(2).setPreferredWidth(115);
        table.getColumnModel().getColumn(2).setMinWidth(125);
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
