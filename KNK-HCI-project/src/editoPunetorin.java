

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class editoPunetorin extends JFrame {

	private JPanel contentPane;
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	private JTextField txtId;
	private JTextField txtEmri;
	private JTextField txtEmail;
	private JLabel lblTel;
	private JTextField txtTel;
	private JLabel lblPozita;
	private JLabel lblPaga;
	private JComboBox cmbPozita;
	private JTextField txtPaga;
	private JTextField txtMbiemri;
	private JLabel lblMbiemri;
	private JLabel label;
	Color ngjyra = new Color(102, 204, 255);
	private JLabel label_1;
	private int nrSerik = 0;
	private String salt = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editoPunetorin frame = new editoPunetorin();
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
	public editoPunetorin() {
		setTitle("Besa Commerce");
		setIconImage(Toolkit.getDefaultToolkit().getImage(editoPunetorin.class.getResource("/imgs/logo1icon1.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				conn = connectionClass.connectDb();
				try {
				
					String sql = "select * from tblstafi where NrPersonal = " + frmStafi.PersonalNr + ";";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();

					while(res.next()) {

						txtId.setText(res.getString("Id"));
						txtEmri.setText(res.getString("Emri"));
				        txtMbiemri.setText(res.getString("Mbiemri"));
						nrSerik = res.getInt("nrSerik");
						salt = res.getString("salt");
						txtEmail.setText(res.getString("email"));
						txtPaga.setText(res.getString("Paga"));
						
						txtTel.setText(res.getString("tel"));
					}
					
					
				}
				catch(Exception ex) {
					
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 614, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setForeground(new Color(0, 0, 51));
		lblId.setFont(new Font("Arial", Font.BOLD, 16));
		lblId.setBounds(10, 23, 93, 49);
		contentPane.add(lblId);
		
		JLabel lblEmri = new JLabel("Emri");
		lblEmri.setForeground(new Color(0, 0, 51));
		lblEmri.setFont(new Font("Arial", Font.BOLD, 16));
		lblEmri.setBounds(10, 103, 93, 46);
		contentPane.add(lblEmri);
		
		txtId = new JTextField();
		txtId.setForeground(new Color(0, 0, 51));
		txtId.setFont(new Font("Arial", Font.BOLD, 16));
		txtId.setBorder(null);
		txtId.setOpaque(false);
		txtId.setEditable(false);
		txtId.setBounds(115, 26, 215, 46);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtEmri = new JTextField();
		txtEmri.setForeground(new Color(0, 0, 51));
		txtEmri.setFont(new Font("Arial", Font.BOLD, 16));
		txtEmri.setBorder(null);
		txtEmri.setOpaque(false);
		txtEmri.setBounds(115, 103, 215, 46);
		contentPane.add(txtEmri);
		txtEmri.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(0, 0, 51));
		lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
		lblEmail.setBounds(10, 271, 93, 49);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(0, 0, 51));
		txtEmail.setFont(new Font("Arial", Font.BOLD, 16));
		txtEmail.setBorder(null);
		txtEmail.setOpaque(false);
		txtEmail.setText("");
		txtEmail.setBounds(115, 271, 215, 49);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblTel = new JLabel("Tel");
		lblTel.setForeground(new Color(0, 0, 51));
		lblTel.setFont(new Font("Arial", Font.BOLD, 16));
		lblTel.setBounds(10, 408, 65, 46);
		contentPane.add(lblTel);
		
		txtTel = new JTextField();
		txtTel.setForeground(new Color(0, 0, 51));
		txtTel.setFont(new Font("Arial", Font.BOLD, 16));
		txtTel.setBorder(null);
		txtTel.setOpaque(false);
		txtTel.setText("");
		txtTel.setColumns(10);
		txtTel.setBounds(74, 408, 256, 46);
		contentPane.add(txtTel);
		
		lblPozita = new JLabel("Pozita");
		lblPozita.setForeground(new Color(0, 0, 51));
		lblPozita.setFont(new Font("Arial", Font.BOLD, 16));
		lblPozita.setBounds(10, 346, 65, 49);
		contentPane.add(lblPozita);
		
		JButton btnPerditesoTeDhenat = new JButton("Perditeso te dhenat");
		btnPerditesoTeDhenat.setForeground(new Color(0, 0, 51));
		btnPerditesoTeDhenat.setBackground(SystemColor.window);
		btnPerditesoTeDhenat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conn = connectionClass.connectDb();
				String emailiRi = salt + txtEmail.getText() + nrSerik;
				String emailiEnkriptuar = Encryption.SHA1(emailiRi);
				String sql = "update  tblstafi\r\n" + 
						"set emri = '" + txtEmri.getText() + "', mbiemri = '" + txtMbiemri.getText() + "', Email = '"+emailiEnkriptuar + "', poziteId = (select id from tblpozita where pershkrimi= '"+cmbPozita.getSelectedItem() + "'), Tel = '"+txtTel.getText() + "', Paga = '" + txtPaga.getText() + "'" + 
						" where id = '" + txtId.getText() + "'";
				try {
					 java.sql.Statement st = conn.createStatement();
				      st.executeUpdate(sql);
					//pst = conn.prepareStatement(sql);
					//res = pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					dispose();
				}
				
			}
		});
		btnPerditesoTeDhenat.setIcon(new ImageIcon(editoPunetorin.class.getResource("/imgs/icons8_Edit_Property_48px.png")));
		btnPerditesoTeDhenat.setFont(new Font("Arial", Font.BOLD, 16));
		btnPerditesoTeDhenat.setBounds(124, 569, 345, 46);
		contentPane.add(btnPerditesoTeDhenat);
		
		lblPaga = new JLabel("Paga");
		lblPaga.setForeground(new Color(0, 0, 51));
		lblPaga.setFont(new Font("Arial", Font.BOLD, 16));
		lblPaga.setBounds(10, 481, 93, 46);
		contentPane.add(lblPaga);
		
		txtPaga = new JTextField();
		txtPaga.setForeground(new Color(0, 0, 51));
		txtPaga.setFont(new Font("Arial", Font.BOLD, 16));
		txtPaga.setBorder(null);
		txtPaga.setOpaque(false);
		txtPaga.setText("");
		txtPaga.setColumns(10);
		txtPaga.setBounds(74, 481, 256, 46);
		contentPane.add(txtPaga);
		
		cmbPozita = new JComboBox();
		cmbPozita.setBounds(84, 346, 246, 49);
		contentPane.add(cmbPozita);
		
		txtMbiemri = new JTextField();
		txtMbiemri.setForeground(new Color(0, 0, 51));
		txtMbiemri.setFont(new Font("Arial", Font.BOLD, 16));
		txtMbiemri.setBorder(null);
		txtMbiemri.setOpaque(false);
		txtMbiemri.setColumns(10);
		txtMbiemri.setBounds(115, 193, 215, 46);
		contentPane.add(txtMbiemri);
		
		lblMbiemri = new JLabel("Mbiemri");
		lblMbiemri.setForeground(new Color(0, 0, 51));
		lblMbiemri.setFont(new Font("Arial", Font.BOLD, 16));
		lblMbiemri.setBounds(10, 193, 93, 49);
		contentPane.add(lblMbiemri);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(editoPunetorin.class.getResource("/imgs/1.png")));
		label.setBounds(342, 43, 246, 237);
		contentPane.add(label);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(74, 237, 256, 2);
		contentPane.add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(74, 70, 256, 2);
		contentPane.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(74, 147, 256, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(74, 316, 256, 4);
		contentPane.add(separator_3);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.LIGHT_GRAY);
		separator_5.setBackground(SystemColor.controlHighlight);
		separator_5.setBounds(74, 453, 256, 1);
		contentPane.add(separator_5);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(74, 525, 256, 2);
		contentPane.add(separator_7);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(ngjyra);
		lblNewLabel.setBounds(0, 176, 596, 452);
		contentPane.add(lblNewLabel);
		
		label_1 = new JLabel("");
		label_1.setBackground(ngjyra);
		label_1.setOpaque(true);
		label_1.setBounds(0, 11, 596, 11);
		contentPane.add(label_1);
		
		JRadioButton rdbtnEng = new JRadioButton("");
		rdbtnEng.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lblId.setText("Id");
				lblEmri.setText("Name");
				lblEmail.setText("Email");
				lblMbiemri.setText("Surname");
				lblPozita.setText("Position");
				lblPaga.setText("Wage");
				lblTel.setText("Tel");
				btnPerditesoTeDhenat.setText("Update");
				
			}
		});
		rdbtnEng.setIcon(new ImageIcon(editoPunetorin.class.getResource("/imgs/eng.png")));
		rdbtnEng.setOpaque(false);
		rdbtnEng.setBounds(555, 21, 33, 25);
		contentPane.add(rdbtnEng);
		
		JRadioButton rdbtnAlb = new JRadioButton("");
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				lblId.setText("Id");
				lblEmri.setText("Emri");
				lblEmail.setText("Email");
				lblMbiemri.setText("Mbiemri");
				lblPozita.setText("Pozita");
				lblPaga.setText("Paga");
				lblTel.setText("Tel");
				btnPerditesoTeDhenat.setText("Perditeso te dhenat");
				
			}
		});
		rdbtnAlb.setIcon(new ImageIcon(editoPunetorin.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setSelected(true);
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setBounds(520, 21, 33, 25);
		contentPane.add(rdbtnAlb);
		fillCombo();
	}
	private void fillCombo()
	{
		try
		{
			ArrayList<String> pozita = new ArrayList<String>();
			pozita.add("");
			conn = connectionClass.connectDb();
			String sql = "select pershkrimi from tblpozita ";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			while(res.next())
			{
				String pozite = res.getString("pershkrimi");
				pozita.add(pozite);
			}
			pst.close();
			cmbPozita.removeAllItems();
			cmbPozita.setModel(new DefaultComboBoxModel(pozita.toArray()));
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
}
