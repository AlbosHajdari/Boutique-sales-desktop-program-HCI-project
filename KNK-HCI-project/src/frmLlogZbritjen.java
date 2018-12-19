import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmLlogZbritjen extends JFrame {

	private JPanel contentPane;
	private JTextField txtCmimi;
	private JTextField txtCmimiZbritje;
	private JTextField txtPerqindja;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblmimi ;
	private JLabel lblmimiMeZbritje;
	private JLabel lblZbritjaperqindje;
	private JButton btnNewButton;
	private int counter = 0;
	private JLabel lblcmimiZb;
	private JLabel lblcmimi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLlogZbritjen frame = new frmLlogZbritjen();
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
	public frmLlogZbritjen() {
		setFont(null);
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmLlogZbritjen.class.getResource("/imgs/logo1icon1.png")));
		setTitle("Besa Commerce");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblmimi = new JLabel("\u00C7mimi:");
		lblmimi.setForeground(new Color(0, 0, 51));
		lblmimi.setFont(new Font("Roboto Black", lblmimi.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 16));
		lblmimi.setBounds(28, 202, 170, 33);
		contentPane.add(lblmimi);
		
		lblmimiMeZbritje = new JLabel("\u00C7mimi me zbritje:");
		lblmimiMeZbritje.setForeground(new Color(0, 0, 51));
		lblmimiMeZbritje.setFont(new Font("Roboto Black", lblmimi.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 16));
		lblmimiMeZbritje.setBounds(28, 281, 170, 33);
		contentPane.add(lblmimiMeZbritje);
		
		txtCmimi = new JTextField();
		txtCmimi.setOpaque(false);
		txtCmimi.setBorder(null);
		txtCmimi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCmimi.setForeground(new Color(0, 0, 51));
		txtCmimi.setFont(new Font("Roboto Black", lblmimi.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 16));
		txtCmimi.setBounds(210, 194, 226, 41);
		contentPane.add(txtCmimi);
		txtCmimi.setColumns(10);
		
		txtCmimiZbritje = new JTextField();
		txtCmimiZbritje.setOpaque(false);
		txtCmimiZbritje.setBorder(null);
		txtCmimiZbritje.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCmimiZbritje.setForeground(new Color(0, 0, 51));
		txtCmimiZbritje.setFont(new Font("Roboto Black", lblmimi.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 16));
		txtCmimiZbritje.setColumns(10);
		txtCmimiZbritje.setBounds(210, 273, 226, 41);
		contentPane.add(txtCmimiZbritje);
		
		lblZbritjaperqindje = new JLabel("Zbritja (Perqindje):");
		lblZbritjaperqindje.setForeground(new Color(0, 0, 51));
		lblZbritjaperqindje.setFont(new Font("Roboto Black", lblmimi.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 16));
		lblZbritjaperqindje.setBounds(28, 358, 170, 33);
		contentPane.add(lblZbritjaperqindje);
		
		txtPerqindja = new JTextField();
		txtPerqindja.setEditable(false);
		txtPerqindja.setOpaque(false);
		txtPerqindja.setBorder(null);
		txtPerqindja.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPerqindja.setForeground(new Color(0, 0, 51));
		txtPerqindja.setFont(new Font("Roboto Black", lblmimi.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 16));
		txtPerqindja.setColumns(10);
		txtPerqindja.setBounds(210, 350, 226, 41);
		contentPane.add(txtPerqindja);
		
		btnNewButton = new JButton("Llogarite Perqindjen");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evn) {
				if(evn.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try
					{
						lblcmimi.setText("");
						lblcmimiZb.setText("");
						counter = 0;
						validimi();
						if(counter != 0)
						{
							if(Gjuhesia.gjuha.equals("alb"))
								JOptionPane.showMessageDialog(null, "Keni gabime ne formen tuaj!");
							else
								JOptionPane.showMessageDialog(null, "You have errors in your form!");
						}
						else
							llogPerqindjen();
							
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		btnNewButton.setBackground(new Color(153, 204, 255));
		btnNewButton.setBorder(null);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Roboto Black", lblmimi.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					lblcmimi.setText("");
					lblcmimiZb.setText("");
					counter = 0;
					validimi();
					if(counter != 0)
					{
						if(Gjuhesia.gjuha.equals("alb"))
							JOptionPane.showMessageDialog(null, "Keni gabime ne formen tuaj!");
						else
							JOptionPane.showMessageDialog(null, "You have errors in your form!");
					}
					else
						llogPerqindjen();
						
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnNewButton.setBounds(130, 421, 219, 41);
		contentPane.add(btnNewButton);
		
		JRadioButton rdbtnAlb = new JRadioButton("");
		rdbtnAlb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				lblmimi.setText("Çmimi");
				lblmimiMeZbritje.setText("Çmimi me zbritje");
				lblZbritjaperqindje.setText("Zbritja (Perqindje)");
				btnNewButton.setText("Llogarite Perqindjen");
				Gjuhesia.gjuha = "alb";
			}
		});
		rdbtnAlb.setSelected(true);
		buttonGroup.add(rdbtnAlb);
		rdbtnAlb.setOpaque(false);
		rdbtnAlb.setIcon(new ImageIcon(frmLlogZbritjen.class.getResource("/imgs/alb.png")));
		rdbtnAlb.setBounds(403, 9, 33, 25);
		contentPane.add(rdbtnAlb);
		
		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				lblmimi.setText("Price");
				lblmimiMeZbritje.setText("Price with disc.");
				lblZbritjaperqindje.setText("Disc. (Percentage)");
				btnNewButton.setText("Calculate Percentage");
				Gjuhesia.gjuha = "eng";
			}
		});
		buttonGroup.add(radioButton_1);
		radioButton_1.setIcon(new ImageIcon(frmLlogZbritjen.class.getResource("/imgs/eng.png")));
		radioButton_1.setOpaque(false);
		radioButton_1.setBounds(434, 9, 30, 25);
		contentPane.add(radioButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(210, 233, 226, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(210, 312, 226, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(210, 389, 226, 2);
		contentPane.add(separator_2);
		
		lblcmimi = new JLabel("");
		lblcmimi.setForeground(new Color(255, 0, 0));
		lblcmimi.setFont(new Font("Arial", Font.ITALIC, 10));
		lblcmimi.setBounds(195, 233, 269, 16);
		contentPane.add(lblcmimi);
		
		lblcmimiZb = new JLabel("");
		lblcmimiZb.setForeground(new Color(255, 0, 0));
		lblcmimiZb.setFont(new Font("Arial", Font.ITALIC, 10));
		lblcmimiZb.setBounds(195, 312, 269, 16);
		contentPane.add(lblcmimiZb);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 0, 51));
		lblNewLabel.setBounds(0, 168, 472, 3);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmLlogZbritjen.class.getResource("/imgs/puneLogo1.png")));
		label_1.setBackground(new Color(153, 204, 255));
		label_1.setOpaque(true);
		label_1.setBounds(0, 0, 472, 169);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setOpaque(true);
		label_2.setBackground(new Color(153, 204, 255));
		label_2.setBounds(0, 475, 472, 52);
		contentPane.add(label_2);
		
		JLabel dfasfs = new JLabel("");
		dfasfs.setOpaque(true);
		dfasfs.setBackground(new Color(255, 255, 255));
		dfasfs.setBounds(0, 0, 472, 497);
		contentPane.add(dfasfs);
		if(Gjuhesia.gjuha.equals("alb"))
		{
			lblmimi.setText("Çmimi");
			lblmimiMeZbritje.setText("Çmimi me zbritje");
			lblZbritjaperqindje.setText("Zbritja (Perqindje)");
			btnNewButton.setText("Llogarite Perqindjen");
			
		}
		else
		{
			lblmimi.setText("Price");
			lblmimiMeZbritje.setText("Price with disc.");
			lblZbritjaperqindje.setText("Disc. (Percentage)");
			btnNewButton.setText("Calculate Percentage");
		}
	}
	private void llogPerqindjen()
	{
		try
		{
			double cmimi = Double.parseDouble(txtCmimi.getText());
			double cmimiZbritje = Double.parseDouble(txtCmimiZbritje.getText());
			double zbritja = ((cmimi - cmimiZbritje)/cmimi)*100;
			String perqindja = String.format("%.2f", zbritja);
			perqindja = perqindja + "  %";
			txtPerqindja.setText(perqindja);
			txtCmimi.setText(String.format("%.2f  €", cmimi));
			txtCmimiZbritje.setText(String.format("%.2f  €", cmimiZbritje));
			
			lblcmimi.setText("");
			lblcmimiZb.setText("");
			counter = 0;
	
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
			String patterna = "^[0-9]+[\\.]?[0-9]*$";
			Pattern.compile(patterna);
			String cmimiStr = txtCmimi.getText();
			cmimiStr = cmimiStr.replaceAll("[€\\s]", "");
			String cmimiZbStr = txtCmimiZbritje.getText();
			cmimiZbStr = cmimiZbStr.replaceAll("[€\\s]", "");
			if(txtCmimi.getText().equals(""))
			{
				counter++;
				if(Gjuhesia.gjuha.equals("alb"))
					lblcmimi.setText("Çmimi nuk duhet te jete i zbrazet!");
				else
					lblcmimi.setText("Please fill Price field!");
			}
			if(txtCmimiZbritje.getText().equals(""))
			{
				counter++;
				if(Gjuhesia.gjuha.equals("alb"))
					lblcmimiZb.setText("Çmimi me zbritje nuk duhet te jete i zbrazet!");
				else
					lblcmimiZb.setText("Please fill Price with disc. field!");
				
			}
			if(!Pattern.matches(patterna, cmimiStr))
			{
				counter++;
				if(Gjuhesia.gjuha.equals("alb"))
					lblcmimi.setText("Lejohen vetem numra!");
				else
					lblcmimi.setText("Only numbers are allowed!");
			}
			if(!Pattern.matches(patterna, cmimiZbStr))
			{
				counter++;
				if(Gjuhesia.gjuha.equals("alb"))
					lblcmimiZb.setText("Lejohen vetem numra!");
				else
					lblcmimiZb.setText("Only numbers are allowed!");
			}
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
}
