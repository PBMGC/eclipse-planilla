package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;

import org.w3c.dom.events.Event;

import bean.Empleado;
import dao.daoEmpleado;

import javax.swing.event.CaretEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	JPanel contentPane;
	JLabel imgCerrar;
	JTextField txtUsuario;
	JPasswordField txtPassword;
	JButton btnIniciar, btnCancelar;
	
	bean.Empleado empleado = new bean.Empleado();
	dao.daoEmpleado daoEmpleado = new dao.daoEmpleado();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JPanel pnlTitulo = new JPanel();
		pnlTitulo.setBounds(0,0,400,60);
		pnlTitulo.setBackground( new Color(90, 17, 93) );
		pnlTitulo.setLayout(null);
		contentPane.add( pnlTitulo );
		
		JLabel imgTitulo = new JLabel();
		imgTitulo.setBounds(96,8,208,43);
		imgTitulo.setIcon(new ImageIcon(frmLogin.class.getResource("/gui/img/logo.png")));
		pnlTitulo.add(imgTitulo);
		
		imgCerrar = new JLabel();
		imgCerrar.setBounds(360,14,32,32);
		imgCerrar.setIcon(new ImageIcon(frmLogin.class.getResource("/gui/img/cerrar.png")));
		pnlTitulo.add(imgCerrar);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(120, 120, 80, 30);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(120, 160, 80, 30);
		contentPane.add(lblPassword);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(200, 120, 80, 30);
		txtUsuario.setColumns(8);
		txtUsuario.setMargin(new Insets(2, 5, 2, 5));
		contentPane.add(txtUsuario);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(200, 160, 80, 30);
		txtPassword.setColumns(6);
		txtPassword.setMargin( new Insets(2, 5, 2, 5));
		contentPane.add(txtPassword);

		btnIniciar = new JButton("Iniciar");		
		btnIniciar.setBounds(80, 220, 100, 30);
		btnIniciar.setBackground( new Color(90, 17, 93) );
		btnIniciar.setBorderPainted(false);
		btnIniciar.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		btnIniciar.setEnabled(false);
		btnIniciar.setFocusPainted(false);
		btnIniciar.setForeground( Color.white );
		contentPane.add(btnIniciar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(220, 220, 100, 30);
		btnCancelar.setBackground( new Color(90, 17, 93) );
		btnCancelar.setBorderPainted(false);
		btnCancelar.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		btnCancelar.setFocusPainted(false);
		btnCancelar.setForeground( Color.white );
		contentPane.add(btnCancelar);
		
		this.addWindowListener(new WindowAdapter() { @Override public void windowOpened(WindowEvent e) { form_windowsOpened(); } });
		
		imgCerrar.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { imgCerrar_Clicked(); } });
		
		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override public void focusGained(FocusEvent e) { txt_focusGained(e); }
			@Override public void focusLost(FocusEvent e) { txt_focusLost(e); } } );
		
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override public void focusGained(FocusEvent e) { txt_focusGained(e); }
			@Override public void focusLost(FocusEvent e) { txt_focusLost(e);} } );
		
		btnIniciar.addFocusListener(new FocusAdapter() {
			@Override public void focusGained(FocusEvent e) { btn_focusGained( btnIniciar ); }
			@Override public void focusLost(FocusEvent e) { btn_focusLost( btnIniciar ); } } );

		btnCancelar.addFocusListener(new FocusAdapter() {
			@Override public void focusGained(FocusEvent e) { btn_focusGained( btnCancelar ); }
			@Override public void focusLost(FocusEvent e) { btn_focusLost( btnCancelar ); } } );

		btnIniciar.addMouseListener(new MouseAdapter() { 
			@Override public void mouseEntered(MouseEvent e) { btn_focusGained( btnIniciar );	}
			@Override public void mouseExited(MouseEvent e) { btn_focusLost( btnIniciar );  } });
				
		btnCancelar.addMouseListener(new MouseAdapter() { 
			@Override public void mouseEntered(MouseEvent e) { btn_focusGained( btnCancelar ); }
			@Override public void mouseExited(MouseEvent e) { btn_focusLost( btnCancelar );  } });

		txtUsuario.addKeyListener( new KeyAdapter() { @Override public void keyTyped(KeyEvent e) { txt_keyTyped( e ); } });
		txtPassword.addKeyListener(new KeyAdapter() { @Override public void keyTyped(KeyEvent e) { txt_keyTyped( e ); } });
		
		txtUsuario.addCaretListener(new CaretListener() { public void caretUpdate(CaretEvent e) { txt_caretUpdate(); } });
		txtPassword.addCaretListener(new CaretListener() { public void caretUpdate(CaretEvent e) { txt_caretUpdate(); } });
		
		btnIniciar.addActionListener( new ActionListener() { @Override public void actionPerformed(ActionEvent e) { btnIniciar_click(); } });
		btnCancelar.addActionListener( new ActionListener() { @Override public void actionPerformed(ActionEvent e) { imgCerrar_Clicked(); } });
	}

	protected void form_windowsOpened() {
		InputMap imUsuario = txtUsuario.getInputMap( JComponent.WHEN_FOCUSED );
        imUsuario.put( KeyStroke.getKeyStroke( KeyEvent.VK_V, Event.AT_TARGET ), "null" );
        
        InputMap imPassword = txtPassword.getInputMap( JComponent.WHEN_FOCUSED );
        imPassword.put( KeyStroke.getKeyStroke( KeyEvent.VK_V, Event.AT_TARGET ), "null" );
	}

	protected void txt_caretUpdate() {
		btnIniciar.setEnabled( txtUsuario.getText().length() == txtUsuario.getColumns() && txtPassword.getPassword().length == txtPassword.getColumns() );		
	}

	protected void txt_keyTyped( KeyEvent e ) {
		JTextField txt = (JTextField)  e.getSource();
		if ( !Character.isDigit( e.getKeyChar() ) || txt.getText().length() == txt.getColumns() )
			e.consume();
	}

	protected void txt_focusGained(FocusEvent e) {
		e.getComponent().setBackground( new Color(226,244,252) );
	}

	protected void txt_focusLost(FocusEvent e) {
		e.getComponent().setBackground( Color.white );
	}

	protected void btn_focusGained(JButton btn) {
		btn.setBackground( Color.black );
	}

	protected void btn_focusLost(JButton btn) {
		btn.setBackground( new Color(90,17,93) );
	}

	protected void imgCerrar_Clicked() {
		if ( JOptionPane.showConfirmDialog(this, "¿ Desea salir de la app ?", "Salir", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION )
			System.exit(0);		
	}
	
	protected void btnIniciar_click() {
		empleado.setDni( txtUsuario.getText() );
		empleado.setPassword( new String( txtPassword.getPassword() ) );
		daoEmpleado.Login(empleado);
		
		JOptionPane.showMessageDialog(this, empleado.isValido() ? "Usuario : " + empleado.getNombres() : "Usuario y/o contraseña inválido" );
	}
	
}
