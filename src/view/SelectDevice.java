package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class SelectDevice {

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public SelectDevice() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1234, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnRcu = new JButton("RCU 500");
		btnRcu.setBounds(343, 59, 89, 23);
		frame.getContentPane().add(btnRcu);
		
		JButton btnRcu_1 = new JButton("RCU 501");
		btnRcu_1.setBounds(343, 136, 89, 23);
		frame.getContentPane().add(btnRcu_1);
		
		JButton btnRcu_2 = new JButton("RCU 502");
		btnRcu_2.setBounds(343, 237, 89, 23);
		frame.getContentPane().add(btnRcu_2);
	}
}
