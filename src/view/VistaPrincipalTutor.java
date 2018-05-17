package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Controlador;

import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaPrincipalTutor extends JFrame implements Vista {

    private JPanel contentPane;
    private Controlador controlador;

    private JLabel lblBienvenido;


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

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"2º DAM 17-18", "2º ASIR 17-18"}));

        JLabel lblGrupo = new JLabel("Grupo:");

        JButton btnConfig = new JButton("Configuración");
        btnConfig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.mostarConfiguracion();
            }
        });

        lblBienvenido = new JLabel("Bienvenido ");

        JLabel lblAsignados = new JLabel("Alumnos con prácticas asignadas: ");

        JLabel lblNumeroAsignados = new JLabel("3");
        lblNumeroAsignados.setForeground(new Color(50, 205, 50));

        JLabel lblPorAsignar = new JLabel("Alumnos con prácticas por asignar:");

        JLabel lblNumeroPorAsignar = new JLabel("1");
        lblNumeroPorAsignar.setForeground(Color.RED);

        JScrollPane scrAsignados = new JScrollPane();

        JScrollPane scrPorAsignar = new JScrollPane();

        JButton btnAsignarAlumnos = new JButton("Editar Alumnos");
        btnAsignarAlumnos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarListaAlumnos();
            }
        });

        JLabel lblOpciones = new JLabel("Opciones");

        JButton btnAsignarPrcticas = new JButton("Asignar Prácticas");
        btnAsignarPrcticas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarAsignarPracticas();
            }
        });

        JButton btnCerrarSesin = new JButton("Cerrar Sesión");
        btnCerrarSesin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.cerrarSesion();
            }
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(btnAsignarAlumnos)
                                                                .addGap(18)
                                                                .addComponent(btnAsignarPrcticas))
                                                        .addComponent(lblOpciones)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                .addComponent(lblAsignados, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(6)
                                                                                .addComponent(lblNumeroAsignados, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(scrAsignados, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(46)
                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(scrPorAsignar, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                                .addComponent(lblPorAsignar, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(6)
                                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                        .addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(lblNumeroPorAsignar, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addComponent(lblGrupo)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(lblBienvenido)))
                                                .addGap(28))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnCerrarSesin)
                                                .addContainerGap())))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblGrupo)
                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblBienvenido)
                                        .addComponent(btnConfig))
                                .addGap(34)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblAsignados)
                                                        .addComponent(lblNumeroAsignados))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(scrAsignados, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblPorAsignar)
                                                        .addComponent(lblNumeroPorAsignar))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(scrPorAsignar, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblOpciones)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAsignarAlumnos)
                                        .addComponent(btnAsignarPrcticas))
                                .addGap(18)
                                .addComponent(btnCerrarSesin)
                                .addGap(118))
        );

        JList lstPorAsignar = new JList();
        lstPorAsignar.setModel(new AbstractListModel() {
            String[] values = new String[]{"Julen Bujanda"};

            public int getSize() {
                return values.length;
            }

            public Object getElementAt(int index) {
                return values[index];
            }
        });
        scrPorAsignar.setViewportView(lstPorAsignar);

        JList lstAsignados = new JList();
        lstAsignados.setModel(new AbstractListModel() {
            String[] values = new String[]{"Iván Hernández", "Andrés Murillas", "Víctor Jiménez"};

            public int getSize() {
                return values.length;
            }

            public Object getElementAt(int index) {
                return values[index];
            }
        });
        scrAsignados.setViewportView(lstAsignados);
        contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public JLabel getLblBienvenido() {
        return lblBienvenido;
    }
}
