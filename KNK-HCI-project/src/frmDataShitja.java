
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmDataShitja extends JFrame {

	private JPanel contentPane;
	 //static Calendar dataDikur  = new GregorianCalendar(2018,Month.JANUARY,1);
	static java.util.Date dataDikur = new java.util.Date();
    static java.util.Date dataSot = new java.util.Date();
    static String emriIfurnitoritTeMarre = "";
    private static PreparedStatement pst = null;
    private static ResultSet res = null;
    private static Connection conn = null;
    private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmDataShitja frame = new frmDataShitja();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				
					
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
	
	public void fillCombo(){
		try 
		{
			conn = connectionClass.connectDb();
			ArrayList<String> furnitoret = new ArrayList<String>();
			String query ="select emriFurnitorit from tblFurnitoret";
			
			furnitoret.add("");
			pst = conn.prepareStatement(query);
			res = pst.executeQuery();
			
			while(res.next())
			{
				String furnitor = res.getString("emriFurnitorit");
				furnitoret.add(furnitor);
			}
			pst.close();
			comboBox.removeAllItems();
			comboBox.setModel(new DefaultComboBoxModel(furnitoret.toArray()));
			
			 
	
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
		}
		
		
	}
	
	
	
	public frmDataShitja() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmDataShitja.class.getResource("/imgs/logo1icon1.png")));

		setTitle("Besa Commerce");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 606);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, Calendar.JANUARY, 1); //Year, month and day of month
	    dataDikur = cal.getTime();
		dataSot = new java.util.Date();
		
		JLabel lblFurnitori = new JLabel("Furnitori:");
		lblFurnitori.setForeground(new Color(0, 0, 51));
		lblFurnitori.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblFurnitori.setBounds(28, 260, 144, 43);
		contentPane.add(lblFurnitori);
		
		JLabel lblPrej = new JLabel("Prej:");
		lblPrej.setForeground(new Color(0, 0, 51));
		lblPrej.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblPrej.setBounds(28, 340, 144, 45);
		contentPane.add(lblPrej);
		
		JLabel lblDeri = new JLabel("Deri:");
		lblDeri.setForeground(new Color(0, 0, 51));
		lblDeri.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblDeri.setBounds(28, 433, 144, 45);
		contentPane.add(lblDeri);
		
		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 51));
		comboBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		comboBox.setBounds(184, 258, 255, 45);
		contentPane.add(comboBox);
		fillCombo();
		
		JDateChooser dateDeri = new JDateChooser();
		dateDeri.setForeground(new Color(0, 0, 51));
		dateDeri.setDateFormatString("dd/MM/yyyy");
		dateDeri.setBounds(184, 433, 255, 45);
		dateDeri.setDate(dataSot);
		contentPane.add(dateDeri);
		
		JDateChooser datePrej = new JDateChooser();
		datePrej.setForeground(new Color(0, 0, 51));
		datePrej.setDateFormatString("dd/MM/yyyy");
		datePrej.setBounds(184, 340, 255, 45);
		datePrej.setDate(dataDikur);
		
		contentPane.add(datePrej);
		
		
		JButton btnShikoShitjet = new JButton("Shiko blerjet");
		btnShikoShitjet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					frmRaportet Raportet = new frmRaportet();
					
					dataDikur = datePrej.getDate();
					dataSot = dateDeri.getDate();
					emriIfurnitoritTeMarre = comboBox.getSelectedItem().toString();
					dispose();
				}
			}
		});
		btnShikoShitjet.setForeground(new Color(255, 255, 255));
		btnShikoShitjet.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnShikoShitjet.setBackground(new Color(153, 204, 255));
		btnShikoShitjet.setIcon(new ImageIcon(frmDataShitja.class.getResource("/imgs/dollar.png")));
		
		btnShikoShitjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnShikoShitjet.setBackground(Color.BLUE);
				frmRaportet Raportet = new frmRaportet();
				
				dataDikur = datePrej.getDate();
				dataSot = dateDeri.getDate();
				emriIfurnitoritTeMarre = comboBox.getSelectedItem().toString();
				dispose();
			
			}
		});
		btnShikoShitjet.setBounds(127, 513, 219, 45);
		contentPane.add(btnShikoShitjet);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(88, 0, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton rdbtnAlb = new JRadioButton("");
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Gjuhesia.gjuha = "alb";
				lblFurnitori.setText("Furnitori");
				lblPrej.setText("Prej:");
				lblDeri.setText("Deri:");
				btnShikoShitjet.setText("Shiko Blerjet");
				}
		});
		rdbtnAlb.setIcon(new ImageIcon(frmDataShitja.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setSelected(true);
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setBounds(380, 9, 33, 25);
		contentPane.add(rdbtnAlb);
		
		JRadioButton rdbtnEng = new JRadioButton("");
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Gjuhesia.gjuha = "eng";
				lblFurnitori.setText("Supplier");
				lblPrej.setText("From:");
				lblDeri.setText("To:");
				btnShikoShitjet.setText("View Purchases");
			}
		});
		rdbtnEng.setIcon(new ImageIcon(frmDataShitja.class.getResource("/imgs/eng.png")));
		rdbtnEng.setOpaque(false);
		rdbtnEng.setBounds(415, 9, 33, 25);
		contentPane.add(rdbtnEng);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 0, 51));
		lblNewLabel.setBounds(0, 174, 564, 3);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(153, 204, 255));
		label.setOpaque(true);
		label.setIcon(new ImageIcon(frmDataShitja.class.getResource("/imgs/puneLogo1.png")));
		label.setBounds(0, 0, 564, 177);
		contentPane.add(label);
		if(Gjuhesia.gjuha.equals("alb"))
		{
			lblFurnitori.setText("Furnitori");
			lblPrej.setText("Prej:");
			lblDeri.setText("Deri:");
			btnShikoShitjet.setText("Shiko Blerjet");
		}
		else
		{
			lblFurnitori.setText("Supplier");
			lblPrej.setText("From:");
			lblDeri.setText("To:");
			btnShikoShitjet.setText("View Purchases");
		}
	}
}
