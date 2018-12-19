import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class frmPerKthim extends JFrame {

	private JPanel contentPane;
	static double borxhi = 0;
	private JTextField txtPranim;
	private JTextField txtPagesa;
	private JTextField txtKthim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPerKthim frame = new frmPerKthim();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmPerKthim() {
		setTitle("Besa Commerc");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 672, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblParatEPranuara = new JLabel("Parat e pranuara:");
		lblParatEPranuara.setBounds(75, 271, 146, 39);
		contentPane.add(lblParatEPranuara);
		
		txtPranim = new JTextField();
		
		txtPranim.setOpaque(false);
		txtPranim.setBorder(null);
		txtPranim.setBounds(233, 271, 238, 39);
		contentPane.add(txtPranim);
		txtPranim.setColumns(10);
		
		JLabel lblPerPages = new JLabel("Per pages:");
		lblPerPages.setBounds(75, 166, 146, 39);
		contentPane.add(lblPerPages);
		
		txtPagesa = new JTextField();
		txtPagesa.setOpaque(false);
		txtPagesa.setBorder(null);
		txtPagesa.setColumns(10);
		txtPagesa.setBounds(233, 166, 238, 39);
		contentPane.add(txtPagesa);
		
		JLabel lblPerKthim = new JLabel("Per kthim:");
		lblPerKthim.setBounds(75, 370, 146, 39);
		contentPane.add(lblPerKthim);
		
		txtKthim = new JTextField();
		txtKthim.setBorder(null);
		txtKthim.setOpaque(false);
		txtKthim.setColumns(10);
		txtKthim.setBounds(233, 370, 238, 39);
		contentPane.add(txtKthim);
		txtPagesa.setText(String.format("%.2f €", borxhi));
		
		JButton btnNewButton = new JButton("Perfundo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(148, 466, 255, 48);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(233, 203, 238, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(233, 308, 238, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(233, 407, 238, 2);
		contentPane.add(separator_2);
		
		txtPranim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try
				{
					String pr = txtPranim.getText();
					double pr1 = Double.parseDouble(pr);
					txtPranim.setText(String.format("%.2f €", pr1));
					double pranim = 0;
					String txt = txtPranim.getText().replaceAll("[€\\s]", "");
					
					pranim = Double.parseDouble(txt);
					double perKthim = borxhi - pranim;
					txtKthim.setText(String.format("%.2f €", perKthim));
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 654, 26);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mnFile.add(mntmExit);
	}
}
