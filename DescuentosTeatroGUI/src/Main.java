package descuentosteatrogui;

public class Main {
    public static void main(String[] args) {
        // Ejecutar en hilo de interfaz
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DescuentosTeatroFrame().setVisible(true);
            }
        });
    }
}
