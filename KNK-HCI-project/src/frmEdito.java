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
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class frmEdito extends JFrame {

	private JPanel contentPane;
	protected static String barkodiProduktit = "";
	protected static double cmimiVjeter = 0;
	protected static String emProduktit ="";
	private JTextField txtBarkodi;
	private JTextField txtCmVjeter;
	private JTextField txtCmRi;
	private Connection conn = null;
	private PreparedStatement pst = null;
	private JLabel lblBarkodi;
	private JLabel lblmimiParaprak;
	private JLabel lblmimiIRi;
	private JButton btnRuaj;
	private int check = 0;
	private JLabel lblPrRi;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtPrVj;
	private JTextField txtPrRi;
	private JLabel lblPrVj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmEdito frame = new frmEdito();
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
	public frmEdito() {
		setTitle("Besa Commerce");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmEdito.class.getResource("/imgs/logo1icon1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 816);
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
		
		lblBarkodi = new JLabel("Barkodi:");
		lblBarkodi.setForeground(new Color(0, 0, 51));
		lblBarkodi.setFont(new Font("Arial", Font.BOLD, 15));
		lblBarkodi.setBounds(12, 204, 176, 42);
		contentPane.add(lblBarkodi);
		
		txtBarkodi = new JTextField();
		txtBarkodi.setHorizontalAlignment(SwingConstants.CENTER);
		txtBarkodi.setEnabled(false);
		txtBarkodi.setFont(new Font("Arial", Font.BOLD, 15));
		txtBarkodi.setOpaque(false);
		txtBarkodi.setBorder(null);
		txtBarkodi.setBounds(200, 204, 214, 42);
		contentPane.add(txtBarkodi);
		txtBarkodi.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(200, 243, 224, 3);
		contentPane.add(separator);
		
		txtCmVjeter = new JTextField();
		txtCmVjeter.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCmVjeter.setEnabled(false);
		txtCmVjeter.setFont(new Font("Arial", Font.BOLD, 15));
		txtCmVjeter.setOpaque(false);
		txtCmVjeter.setColumns(10);
		txtCmVjeter.setBorder(null);
		txtCmVjeter.setBounds(200, 464, 214, 42);
		contentPane.add(txtCmVjeter);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(200, 503, 224, 3);
		contentPane.add(separator_1);
		
		lblmimiParaprak = new JLabel("\u00C7mimi paraprak:");
		lblmimiParaprak.setForeground(new Color(0, 0, 51));
		lblmimiParaprak.setFont(new Font("Arial", Font.BOLD, 15));
		lblmimiParaprak.setBounds(12, 464, 176, 42);
		contentPane.add(lblmimiParaprak);
		
		txtCmRi = new JTextField();
		txtCmRi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCmRi.setForeground(new Color(0, 0, 51));
		txtCmRi.setFont(new Font("Arial", Font.BOLD, 15));
		txtCmRi.setOpaque(false);
		txtCmRi.setColumns(10);
		txtCmRi.setBorder(null);
		txtCmRi.setBounds(200, 559, 214, 42);
		contentPane.add(txtCmRi);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(200, 598, 224, 3);
		contentPane.add(separator_2);
		
		lblmimiIRi = new JLabel("\u00C7mimi i ri:");
		lblmimiIRi.setForeground(new Color(0, 0, 51));
		lblmimiIRi.setFont(new Font("Arial", Font.BOLD, 15));
		lblmimiIRi.setBounds(12, 559, 176, 42);
		contentPane.add(lblmimiIRi);
		
		btnRuaj = new JButton("Ruaj ndryshimet");
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
							updatePrice();
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
						updatePrice();
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
		btnRuaj.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/if_save_46830.png")));
		btnRuaj.setBounds(135, 647, 199, 47);
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
		rdbtnAlb.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/alb.png")));
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
		radioButton.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/eng.png")));
		radioButton.setOpaque(false);
		radioButton.setBounds(428, 9, 33, 30);
		contentPane.add(radioButton);
		
		JLabel label_2 = new JLabel("");
		label_2.setBackground(new Color(153, 204, 255));
		label_2.setOpaque(true);
		label_2.setBounds(0, 697, 469, 95);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/barcode.png")));
		label_3.setBounds(416, 204, 41, 42);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/euro.png")));
		label_4.setBounds(416, 464, 41, 42);
		contentPane.add(label_4);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/puneLogo1.png")));
		label.setBounds(0, 0, 469, 181);
		contentPane.add(label);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/euro.png")));
		label_5.setBounds(416, 559, 41, 42);
		contentPane.add(label_5);
		
		
		txtPrVj = new JTextField();
		txtPrVj.setText((String) null);
		txtPrVj.setOpaque(false);
		txtPrVj.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrVj.setFont(new Font("Arial", Font.BOLD, 15));
		txtPrVj.setEnabled(false);
		txtPrVj.setColumns(10);
		txtPrVj.setBorder(null);
		txtPrVj.setBounds(200, 285, 214, 42);
		contentPane.add(txtPrVj);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(200, 324, 224, 3);
		contentPane.add(separator_3);
		
		lblPrVj = new JLabel("Emertimi aktual:");
		lblPrVj.setForeground(new Color(0, 0, 51));
		lblPrVj.setFont(new Font("Arial", Font.BOLD, 15));
		lblPrVj.setBounds(12, 285, 176, 42);
		contentPane.add(lblPrVj);
		
		txtPrRi = new JTextField();
		txtPrRi.setOpaque(false);
		txtPrRi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrRi.setForeground(new Color(0, 0, 51));
		txtPrRi.setFont(new Font("Arial", Font.BOLD, 15));
		txtPrRi.setColumns(10);
		txtPrRi.setBorder(null);
		txtPrRi.setBounds(200, 380, 214, 42);
		contentPane.add(txtPrRi);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(200, 419, 224, 3);
		contentPane.add(separator_4);
		
		lblPrRi = new JLabel("Emertimi i ri:");
		lblPrRi.setForeground(new Color(0, 0, 51));
		lblPrRi.setFont(new Font("Arial", Font.BOLD, 15));
		lblPrRi.setBounds(12, 380, 176, 42);
		contentPane.add(lblPrRi);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/if_home_309062.png")));
		label_8.setBounds(416, 285, 41, 42);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon(frmEdito.class.getResource("/imgs/if_home_309062.png")));
		label_9.setBounds(416, 380, 41, 42);
		contentPane.add(label_9);
		gjuhesia();
		txtBarkodi.setText(barkodiProduktit);
		txtCmVjeter.setText(String.format("%.2f", cmimiVjeter));
		txtPrVj.setText(emProduktit);
	}
	private void updatePrice()
	{
		try
		{
			double cmimi = Double.parseDouble(txtCmRi.getText());
			conn = connectionClass.connectDb();
			
			
			if(!barkodiProduktit.equals(""))
			{
				conn = connectionClass.connectDb();
				String sql = "update tblCmimet set cmimiShitjes = "+cmimi+" where produktetId = (select id from tblRegjistrimiMallit where barkodi = '"+barkodiProduktit+"')";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				
				sql = "update tblRegjistrimiMallit set emriProduktit = '"+txtPrRi.getText()+"' where barkodi = '"+barkodiProduktit+"'";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				
				sql = "update tblmalliBlere set emriProduktit = '"+txtPrRi.getText()+"' where barkodi = '"+barkodiProduktit+"'";
				pst = conn.prepareStatement(sql);
				pst.executeUpdate();
				pst.close();
				
				if(Gjuhesia.gjuha.equals("alb"))
				{
					JOptionPane.showMessageDialog(null, "Të dhënat u përditësuan me sukses!");
				}
				else
				{

					JOptionPane.showMessageDialog(null, "Data have been updated successfully!");
				}
				dispose();
			}
			else
			{
				if(Gjuhesia.gjuha.equals("alb"))
				{
					JOptionPane.showMessageDialog(null, "Fillimisht zgjedhni një produkt nga lista e produkteve!");
				}
				else
				{

					JOptionPane.showMessageDialog(null, "First choose a product from product list!");
				}
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	private void validimi()
	{
		try
		{
			String patterna = "^[0-9]+[\\.]?[0-9]*$",patt1 = "^[a-zA-ZëËÇç0-9\\Ø\\.\\/\\-\\s]+$";
			Pattern.compile(patterna);
			if(txtCmRi.equals(""))
			{
				check++;
				if(Gjuhesia.gjuha.equals("alb"))
					JOptionPane.showMessageDialog(null, "Çmimi i ri nuk duhet te jete i zbrazet!");
				else
					JOptionPane.showMessageDialog(null, "Please fill new price field!");
			}
			else if(!Pattern.matches(patterna, txtCmRi.getText()))
			{
				if(Gjuhesia.gjuha.equals("alb"))
					JOptionPane.showMessageDialog(null, "Lejohen vetem numra!");
				else
					JOptionPane.showMessageDialog(null, "Only numbers are allowed!");
			}
			if(txtPrRi.getText().equals(""))
			{
				check++;
				if(Gjuhesia.gjuha.equals("alb"))
					JOptionPane.showMessageDialog(null, "Emertimi nuk duhet te jete i zbrazet!");
				else
					JOptionPane.showMessageDialog(null, "Please fill description field!");
			}
			else if(!Pattern.matches(patt1, txtPrRi.getText()))
			{
				check++;
				if(Gjuhesia.gjuha.equals("alb"))
					JOptionPane.showMessageDialog(null, "Shkruaj nje emertim valid!");
				else
					JOptionPane.showMessageDialog(null, "Write a valid description!");
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
			lblBarkodi.setText("Barkodi:");
			lblmimiParaprak.setText("Çmimi paraprak:");
			lblmimiIRi.setText("Çmimi i ri:");
			btnRuaj.setText("Ruaj ndryshimet");
			lblPrVj.setText("Emertimi aktual:");
			lblPrRi.setText("Emertimi i ri:");
		}
		else
		{
			lblBarkodi.setText("Barcode:");
			lblmimiParaprak.setText("Old price:");
			lblmimiIRi.setText("New price:");
			btnRuaj.setText("Save changes");
			lblPrVj.setText("Old description:");
			lblPrRi.setText("New description:");			
		}
	}
}
