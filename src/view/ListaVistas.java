package view;

/**
 * Clase para contener vistas junto a su título para su uso en JTabbedPane
 */
public class ListaVistas {

    private String titulo;
    private Vista vista;

    public ListaVistas(String titulo, Vista vista) {
        this.titulo = titulo;
        this.vista = vista;
    }

    public String getTitulo() {
        return titulo;
    }

    public Vista getVista() {
        return vista;
    }
}
