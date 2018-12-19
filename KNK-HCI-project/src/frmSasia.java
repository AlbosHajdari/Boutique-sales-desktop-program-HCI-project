import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class frmSasia extends JFrame {

	private JPanel contentPane;
	private Connection conn = null;
	private ResultSet res = null;
	private PreparedStatement pst = null;
	private JButton btnRuaj;
	private int check = 0;
	private JLabel lblPrRi;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtSasVj;
	private JTextField txtSasRi;
	private JLabel lblPrVj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSasia frame = new frmSasia();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmSasia() {
		frmKtheProduktin.aMeKthy = false;
		setTitle("Besa Commerce");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmSasia.class.getResource("/imgs/logo1icon1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 627);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel label_1 = new JLabel("");
		label_1.setBackground(new Color(0, 0, 51));
		label_1.setOpaque(true);
		label_1.setBounds(0, 169, 469, 3);
		contentPane.add(label_1);
		
		btnRuaj = new JButton("Vazhdo");
		btnRuaj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try
					{
						check = 0;
						validimi();
						if(check==0)
						{
							if(Double.parseDouble(txtSasRi.getText())> frmKtheProduktin.sasiaAktuale)
							{
								if(Gjuhesia.gjuha.equals("alb"))
								{
									JOptionPane.showMessageDialog(null,"Sasia per kthim duhet te jete me e vogel!");
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Amount for return should be less than actual amount!");
										
								}
							}
							else
							{
								frmKtheProduktin.sasiaPerKthim = Double.parseDouble(txtSasRi.getText());
								frmKtheProduktin.KtheProduktet();
								dispose();
							}
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		btnRuaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					check = 0;
					validimi();
					if(check==0)
					{
						if(Double.parseDouble(txtSasRi.getText())> frmKtheProduktin.sasiaAktuale)
						{
							if(Gjuhesia.gjuha.equals("alb"))
							{
								JOptionPane.showMessageDialog(null,"Sasia per kthim duhet te jete me e vogel!");
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Amount for return should be less than actual amount!");
									
							}
						}
						else
						{
							frmKtheProduktin.sasiaPerKthim = Double.parseDouble(txtSasRi.getText());
							frmKtheProduktin.KtheProduktet();
							dispose();
						}
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnRuaj.setForeground(new Color(0, 0, 51));
		btnRuaj.setFont(new Font("Arial", Font.BOLD, 15));
		btnRuaj.setBackground(new Color(255, 255, 255));
		btnRuaj.setIcon(new ImageIcon(frmSasia.class.getResource("/imgs/if_save_46830.png")));
		btnRuaj.setBounds(138, 445, 199, 47);
		contentPane.add(btnRuaj);
		
		JRadioButton rdbtnAlb = new JRadioButton("");
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Gjuhesia.gjuha = "alb";
				gjuhesia();
				
			}
		});
		buttonGroup.add(rdbtnAlb);
		rdbtnAlb.setSelected(true);
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setIcon(new ImageIcon(frmSasia.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setBounds(398, 9, 33, 30);
		contentPane.add(rdbtnAlb);
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Gjuhesia.gjuha = "eng";
				gjuhesia();
				
			}
		});
		buttonGroup.add(radioButton);
		radioButton.setIcon(new ImageIcon(frmSasia.class.getResource("/imgs/eng.png")));
		radioButton.setOpaque(false);
		radioButton.setBounds(428, 9, 33, 30);
		contentPane.add(radioButton);
		
		JLabel label_2 = new JLabel("");
		label_2.setBackground(new Color(153, 204, 255));
		label_2.setOpaque(true);
		label_2.setBounds(0, 505, 469, 95);
		contentPane.add(label_2);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmSasia.class.getResource("/imgs/puneLogo1.png")));
		label.setBounds(0, 0, 469, 181);
		contentPane.add(label);
		
		
		txtSasVj = new JTextField();
		txtSasVj.setText((String) null);
		txtSasVj.setOpaque(false);
		txtSasVj.setHorizontalAlignment(SwingConstants.CENTER);
		txtSasVj.setFont(new Font("Arial", Font.BOLD, 15));
		txtSasVj.setEnabled(false);
		txtSasVj.setColumns(10);
		txtSasVj.setBorder(null);
		txtSasVj.setBounds(200, 239, 214, 42);
		contentPane.add(txtSasVj);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(200, 278, 224, 3);
		contentPane.add(separator_3);
		
		lblPrVj = new JLabel("Sasia aktuale:");
		lblPrVj.setForeground(new Color(0, 0, 51));
		lblPrVj.setFont(new Font("Arial", Font.BOLD, 15));
		lblPrVj.setBounds(12, 239, 176, 42);
		contentPane.add(lblPrVj);
		
		txtSasRi = new JTextField();
		txtSasRi.setOpaque(false);
		txtSasRi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSasRi.setForeground(new Color(0, 0, 51));
		txtSasRi.setFont(new Font("Arial", Font.BOLD, 15));
		txtSasRi.setColumns(10);
		txtSasRi.setBorder(null);
		txtSasRi.setBounds(200, 334, 214, 42);
		contentPane.add(txtSasRi);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(200, 373, 224, 3);
		contentPane.add(separator_4);
		
		lblPrRi = new JLabel("Sasia per kthim:");
		lblPrRi.setForeground(new Color(0, 0, 51));
		lblPrRi.setFont(new Font("Arial", Font.BOLD, 15));
		lblPrRi.setBounds(12, 334, 176, 42);
		contentPane.add(lblPrRi);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(frmSasia.class.getResource("/imgs/if_home_309062.png")));
		label_8.setBounds(416, 239, 41, 42);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(frmSasia.class.getResource("/imgs/if_home_309062.png")));
		label_9.setBounds(416, 334, 41, 42);
		contentPane.add(label_9);
		gjuhesia();
		txtSasVj.setText(String.valueOf(frmKtheProduktin.sasiaAktuale));
	}
	
	private void validimi()
	{
		try
		{
			String patterna = "^[0-9]+[\\.]?[0-9]*$";
			Pattern.compile(patterna);
			
			
			if(txtSasRi.getText().equals(""))
			{
				check++;
				if(Gjuhesia.gjuha.equals("alb"))
					JOptionPane.showMessageDialog(null, "Sasia nuk duhet të jetë e zbrazët!");
				else
					JOptionPane.showMessageDialog(null, "Please fill amount field!");
			}
			else if(!Pattern.matches(patterna, txtSasRi.getText()))
			{
				check++;
				if(Gjuhesia.gjuha.equals("alb"))
					JOptionPane.showMessageDialog(null, "Shkruaj nje sasi valide!");
				else
					JOptionPane.showMessageDialog(null, "Write a valid amount!");
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	private void gjuhesia()
	{
		if(Gjuhesia.gjuha.equals("alb"))
		{
			
			btnRuaj.setText("Vazhdo");
			lblPrVj.setText("Sasia aktuale:");
			lblPrRi.setText("Sasia per kthim:");
		}
		else
		{
			btnRuaj.setText("Continue");
			lblPrVj.setText("Actual amount:");
			lblPrRi.setText("Amount for return:");			
		}
	}
}
