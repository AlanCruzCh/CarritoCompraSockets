package interfaces;

import javax.swing.*;

public class InterfazCliente extends JFrame{
    public static void main(String[] args) {
        JFrame interfazCliente = new JFrame("Interfaz cliente");
        interfazCliente.setSize(600, 400);
        interfazCliente.getAlignmentX();
        interfazCliente.getAlignmentY();
        JPanel panel = new JPanel();
        interfazCliente.add(panel);
        JLabel ip = new JLabel("Ingrese una Ip");
        panel.add(ip);
        interfazCliente.setVisible(true);
        
    }
    
}
