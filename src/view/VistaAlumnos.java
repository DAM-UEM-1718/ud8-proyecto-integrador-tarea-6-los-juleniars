package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;

import controller.Controlador;
import model.Modelo;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaAlumnos extends JFrame implements Vista {


    private Controlador controlador;
    private Modelo modelo;

    private JPanel contentPane;
    private JTable table;
    private JTextField txtMatricula;
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

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {

        });

        lblTitulo = new JLabel("Editar Alumnos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

        btnAsignarEmpresa = new JButton("Asignar Empresa");
        btnAsignarEmpresa.addActionListener(e -> {

        });

        btnAadirAlumno = new JButton("Añadir Alumno");
        btnAadirAlumno.addActionListener(e -> {

        });

        btnModificarAlumno = new JButton("Modificar Alumno");
        btnModificarAlumno.addActionListener(e -> {

        });

        btnEliminarAlumno = new JButton("Eliminar Alumno");
        btnEliminarAlumno.addActionListener(e -> {

        });

        btnMostrarAlmunosSin = new JButton("Mostrar Almunos sin Asignar");
        btnMostrarAlmunosSin.addActionListener(e -> {

        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> controlador.cerrarAlumnos());
        
        JComboBox comboBox = new JComboBox();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblTitulo)
        						.addComponent(txtMatricula, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
        							.addComponent(btnVolver))
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGap(18)
        							.addComponent(btnBuscar))))
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
        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    public void cargarTabla() {
        table.setModel(modelo.getTablaAlumnos());
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
