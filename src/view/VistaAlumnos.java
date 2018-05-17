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

public class VistaAlumnos extends JFrame implements Vista {

    private JPanel contentPane;
    private JTable table;
    private JTextField txtMatricula;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtDNI;
    private JTextField txtEmpresa;
    private Controlador controlador;
    private JButton btnBuscar;
    private JLabel lblTitulo;
    private JButton btnMostrarAlmunosSin;
    private JButton btnEliminarAlumno;
    private JButton btnModificarAlumno;
    private JButton btnAadirAlumno;
    private JButton btnAsignarEmpresa;


    public VistaAlumnos() {
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
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

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

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        lblTitulo = new JLabel("Editar Alumnos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        txtEmpresa = new JTextField();
        txtEmpresa.setColumns(10);

        btnAsignarEmpresa = new JButton("Asignar Empresa");
        btnAsignarEmpresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnAadirAlumno = new JButton("Añadir Alumno");
        btnAadirAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnModificarAlumno = new JButton("Modificar Alumno");
        btnModificarAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnEliminarAlumno = new JButton("Eliminar Alumno");
        btnEliminarAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnMostrarAlmunosSin = new JButton("Mostrar Almunos sin Asignar");
        btnMostrarAlmunosSin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.cerrarAlumnos();
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
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAadirAlumno)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnModificarAlumno)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnEliminarAlumno)
                                                .addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                                .addComponent(btnMostrarAlmunosSin)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnAsignarEmpresa)))
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
                                        .addComponent(btnAsignarEmpresa)
                                        .addComponent(btnAadirAlumno)
                                        .addComponent(btnModificarAlumno)
                                        .addComponent(btnEliminarAlumno)
                                        .addComponent(btnMostrarAlmunosSin))
                                .addContainerGap())
        );

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        /*table.setModel(new DefaultTableModel(
                new Object[][]{
                        {"265758984", "Julen", "Bujanda Blanco", "70000000A", "Sin Asignar"},
                        {"089568345", "Iv\u00E1n", "Hern\u00E1ndez", "70000001B", "Apple Espa\u00F1a S.A."},
                        {"940385554", "V\u00EDctor", "Jim\u00E9nez", "8000000A", "Facebook Inc."},
                        {"325545232", "Andr\u00E9s", "Murillas", "9000000A", "Twitter Inc."},
                },
                new String[]{
                        "Num. Matr\u00EDcula", "Nombre", "Apellidos", "DNI", "Empresa Asignada"
                }
        ));*/
        //table.getColumnModel().getColumn(0).setPreferredWidth(91);
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public JTable getTable() {
        return table;
    }
}
