import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class frmForgotPass extends JFrame {

	private JPanel contentPane;
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTextField txtNrPersonal;
	private JTextField txtEmail;
	private JDateChooser dcDataLindjes;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblEmail;
	private JLabel txtDataLindjes;
	private JLabel lblNumriPersonal;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmForgotPass frame = new frmForgotPass();
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
	public frmForgotPass() {
		setTitle("Besa Commerce");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmForgotPass.class.getResource("/imgs/logo1icon1.png")));
		conn = connectionClass.connectDb();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 678);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNumriPersonal = new JLabel();
		lblNumriPersonal.setForeground(new Color(0, 0, 51));
		lblNumriPersonal.setFont(new Font("Arial", Font.BOLD, 16));
		lblNumriPersonal.setBounds(22, 231, 166, 39);
		contentPane.add(lblNumriPersonal);
		
		txtNrPersonal = new JTextField();
		txtNrPersonal.setFont(new Font("Arial", Font.BOLD, 16));
		txtNrPersonal.setBorder(null);
		txtNrPersonal.setOpaque(false);
		txtNrPersonal.setBounds(185, 231, 225, 36);
		contentPane.add(txtNrPersonal);
		txtNrPersonal.setColumns(10);
		
		txtDataLindjes = new JLabel();
		txtDataLindjes.setForeground(new Color(0, 0, 51));
		txtDataLindjes.setFont(new Font("Arial", Font.BOLD, 16));
		txtDataLindjes.setBounds(22, 316, 166, 42);
		contentPane.add(txtDataLindjes);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.BOLD, 16));
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
						fjalekalimi();
					else
						fjalekalimi();
				}
			}
		});
		txtEmail.setBorder(null);
		txtEmail.setOpaque(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(185, 396, 225, 39);
		contentPane.add(txtEmail);
		
		lblEmail = new JLabel();
		lblEmail.setForeground(new Color(0, 0, 51));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
		lblEmail.setBounds(22, 396, 166, 42);
		contentPane.add(lblEmail);
		
		dcDataLindjes = new JDateChooser();
		dcDataLindjes.setDateFormatString("dd/MM/yyyy");
		dcDataLindjes.setBounds(185, 316, 254, 39);
		contentPane.add(dcDataLindjes);
		
		JButton btnKrijoFjalekaliminE = new JButton();
		btnKrijoFjalekaliminE.setForeground(new Color(255, 255, 255));
		btnKrijoFjalekaliminE.setFont(new Font("Arial", Font.BOLD, 17));
		btnKrijoFjalekaliminE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
				{
					boolean valid = validimi();
					if(valid == false)
					{
						if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
							JOptionPane.showMessageDialog(null, "Plotësoni fushat e zbrazeta!");
						else
							JOptionPane.showMessageDialog(null, "Fill empty fields!");
					}
					else
					{
						if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
							fjalekalimi();
						else
							fjalekalimi();
					}
				}
			}
		});
		btnKrijoFjalekaliminE.setBackground(new Color(135, 206, 255));
		btnKrijoFjalekaliminE.setBorder(null);
		btnKrijoFjalekaliminE.setIcon(new ImageIcon(frmForgotPass.class.getResource("/imgs/login1.png")));
		btnKrijoFjalekaliminE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean valid = validimi();
				if(valid == false)
				{
					if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
						JOptionPane.showMessageDialog(null, "Plotësoni fushat e zbrazeta!");
					else
						JOptionPane.showMessageDialog(null, "Fill empty fields!");
				}
				else
				{
					if(Gjuhesia.gjuha.equalsIgnoreCase("alb"))
						fjalekalimi();
					else
						fjalekalimi();
				}
				
			}
		});
		btnKrijoFjalekaliminE.setBounds(134, 495, 225, 45);
		contentPane.add(btnKrijoFjalekaliminE);
		
		JRadioButton rdbtnAlb = new JRadioButton("");
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Gjuhesia.gjuha = "alb";
				txtDataLindjes.setText("Data e lindjes:");
				btnKrijoFjalekaliminE.setText("Krijo fjalekalimin e ri");
				lblEmail.setText("Email:");
				lblNumriPersonal.setText("Numri Personal:");
			}
		});
		rdbtnAlb.setIcon(new ImageIcon(frmForgotPass.class.getResource("/imgs/alb.png")));
		buttonGroup.add(rdbtnAlb);
		rdbtnAlb.setBounds(427, 9, 33, 25);
		contentPane.add(rdbtnAlb);
		
		JRadioButton rdbtnEng = new JRadioButton("");
		rdbtnEng.setOpaque(false);
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Gjuhesia.gjuha="eng";
				txtDataLindjes.setText("Birth date:");
				btnKrijoFjalekaliminE.setText("Create new password");
				lblEmail.setText("Email:");
				lblNumriPersonal.setText("Personal number:");
			
			}
		});
		rdbtnEng.setIcon(new ImageIcon(frmForgotPass.class.getResource("/imgs/eng.png")));
		buttonGroup.add(rdbtnEng);
		rdbtnEng.setBounds(453, 9, 33, 25);
		contentPane.add(rdbtnEng);
		
		separator = new JSeparator();
		separator.setBounds(185, 268, 254, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(185, 433, 254, 2);
		contentPane.add(separator_1);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(frmForgotPass.class.getResource("/imgs/puneLogo1.png")));
		label.setBounds(12, 0, 474, 167);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(new Color(135, 206, 255));
		label_1.setBounds(0, 553, 494, 145);
		contentPane.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmForgotPass.class.getResource("/imgs/email1.png")));
		label_2.setBounds(411, 409, 33, 26);
		contentPane.add(label_2);
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(frmForgotPass.class.getResource("/imgs/id.png")));
		label_3.setBounds(411, 241, 33, 26);
		contentPane.add(label_3);
		if(Gjuhesia.gjuha.equals("alb"))
		{
			txtDataLindjes.setText("Data e lindjes:");
			btnKrijoFjalekaliminE.setText("Krijo fjalekalimin e ri");
			lblEmail.setText("Email:");
			lblNumriPersonal.setText("Numri Personal:");
		}
		else
		{
			txtDataLindjes.setText("Birth date:");
			btnKrijoFjalekaliminE.setText("Create new password");
			lblEmail.setText("Email:");
			lblNumriPersonal.setText("Personal number:");
		}
	}
	public void fjalekalimi()
	{
		try
		{
			
			String data = dcDataLindjes.getDateFormatString();
			Date dt = dcDataLindjes.getDate();
			String dt1 = new SimpleDateFormat(data).format(dt);
			String[] dt2 = dt1.split("/");
			String data1 = dt2[2]+"-"+dt2[1]+"-"+dt2[0];
			String saltEm ="",dtDbLindjes = "",emailDb="",salti ="";
			String sql = "select email,dtLindjes,salt,nrSerik from tblStafi where nrPersonal = '"+txtNrPersonal.getText()+"'";
			pst = conn.prepareStatement(sql);
			res= pst.executeQuery();
			while(res.next())
			{
				salti = res.getString("salt");
				emailDb = res.getString("email");
				saltEm = res.getString("nrSerik");
				dtDbLindjes = res.getString("dtLindjes");
			}
			pst.close();
			String email = salti +txtEmail.getText()+saltEm;
			email = Encryption.SHA1(email);
			if(email.equalsIgnoreCase(emailDb) && dtDbLindjes.equals(data1))
			{
				frmRecoverPass obj = new frmRecoverPass();
				obj.setNrPersonal(txtNrPersonal.getText());
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				dispose();
			}
			else
			{
				if(Gjuhesia.gjuha.equals("alb"))
					JOptionPane.showMessageDialog(null, "Të dhënat nuk përputhen!");
				else
					JOptionPane.showMessageDialog(null, "The data do not match!");
				txtNrPersonal.setText("");
				
				txtEmail.setText("");
				txtNrPersonal.requestFocus();
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Something bad happened frmForgotPass! "+ex.getMessage());
		}
	}
	private boolean validimi()
	{
		int counter = 0;
		if(txtNrPersonal.getText().equals(""))
		{
			counter++;
			
		}
		
		if(txtEmail.getText().equals(""))
			counter++;
		if(counter != 0 )
			return false;
		JOptionPane.showMessageDialog(null, txtDataLindjes.getText());
		return true;
	}
}
