package view;

import controller.Controlador;
import model.Modelo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaPrincipalTutor extends JFrame implements Vista {

    private JPanel contentPane;

    private Controlador controlador;
    private Modelo modelo;

    private JLabel lblBienvenido;
    private JLabel lblNumeroAsignados;
    private JLabel lblNumeroPorAsignar;
    private JTable table;
    private JComboBox cmbGrupos;


    public VistaPrincipalTutor() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Gestión de Prácticas CFGS - Universidad Europea de Madrid");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipalTutor.class.getResource("/img/uem.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 701, 401);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        cmbGrupos = new JComboBox();
        cmbGrupos.addActionListener(e -> controlador.cambiarGrupo());

        JLabel lblGrupo = new JLabel("Grupo:");

        JButton btnConfig = new JButton("Configuración");
        btnConfig.addActionListener(e -> controlador.mostarConfiguracion());

        lblBienvenido = new JLabel("Bienvenido ");

        JLabel lblAsignados = new JLabel("Alumnos con prácticas asignadas: ");

        lblNumeroAsignados = new JLabel("3");
        lblNumeroAsignados.setForeground(new Color(50, 205, 50));

        JLabel lblPorAsignar = new JLabel("Alumnos con prácticas por asignar:");

        lblNumeroPorAsignar = new JLabel("1");
        lblNumeroPorAsignar.setForeground(Color.RED);

        JButton btnAsignarAlumnos = new JButton("Editar Alumnos");
        btnAsignarAlumnos.addActionListener(e -> controlador.mostrarListaAlumnos());

        JLabel lblOpciones = new JLabel("Opciones");

        JButton btnAsignarPrcticas = new JButton("Detalle Prácticas");
        btnAsignarPrcticas.addActionListener(e -> controlador.mostrarPracticas());

        JButton btnCerrarSesin = new JButton("Cerrar Sesión");
        btnCerrarSesin.addActionListener(e -> controlador.cerrarSesion());

        JScrollPane scrollPane = new JScrollPane();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblAsignados, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblNumeroAsignados, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(478, Short.MAX_VALUE))
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnAsignarAlumnos)
                                                .addGap(18)
                                                .addComponent(btnAsignarPrcticas))
                                        .addComponent(lblOpciones)
                                        .addComponent(btnCerrarSesin))
                                .addContainerGap(429, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addComponent(lblPorAsignar, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addGap(345)
                                                                        .addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(lblNumeroPorAsignar, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addComponent(lblGrupo)
                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                        .addComponent(cmbGrupos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18)
                                                        .addComponent(lblBienvenido))))
                                .addGap(28))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblGrupo)
                                        .addComponent(cmbGrupos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblBienvenido)
                                        .addComponent(btnConfig))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblAsignados)
                                        .addComponent(lblNumeroAsignados))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblPorAsignar)
                                        .addComponent(lblNumeroPorAsignar))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblOpciones)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAsignarAlumnos)
                                        .addComponent(btnAsignarPrcticas))
                                .addGap(18)
                                .addComponent(btnCerrarSesin)
                                .addContainerGap())
        );

        table = new JTable();
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setLblBienvenido() {
        lblBienvenido.setText("Bienvenido " + modelo.getNombreUsuarioFormal());
    }

    public JLabel getLblNumeroAsignados() {
        return lblNumeroAsignados;
    }

    public JLabel getLblNumeroPorAsignar() {
        return lblNumeroPorAsignar;
    }

    public void cargarGrupos() {
        cmbGrupos.setModel(modelo.getModeloGrupos());
    }

    public void cargarTabla() {
        lblNumeroAsignados.setText(Integer.toString(modelo.getNumeroAsignados()));
        lblNumeroPorAsignar.setText(Integer.toString(modelo.getNumeroPorAsignar()));
        table.setModel(modelo.getTablaPracticasTutor());
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Modelo.ComboItem getCmbGrupos() {
        return (Modelo.ComboItem) cmbGrupos.getSelectedItem();
    }
}
