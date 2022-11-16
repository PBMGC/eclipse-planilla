package gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class s extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JPanel contentPane, pnlRegistros;
	JTable tblRegistros;
	JTextField txtCodigo, txtDetalle;
	ArrayList<String> aIDCargo;
	
	dao.daoCargo daoCargo = new dao.daoCargo();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					s frame = new s();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public s() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		pnlRegistros = new JPanel();
		pnlRegistros.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlRegistros.setBounds(0,50,200,547);
		pnlRegistros.setLayout(null);
		contentPane.add(pnlRegistros);
		
		tblRegistros = new JTable();
		tblRegistros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				verRegistro( tblRegistros.getSelectedRow() );
			}
		});
		pnlRegistros.add(tblRegistros);
		
		JPanel pnlRegistro = new JPanel();
		pnlRegistro.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlRegistro.setBounds(201,50,698,547);
		pnlRegistro.setLayout(null);
		contentPane.add(pnlRegistro);
		
		JLabel lblCodigo = new JLabel("C�digo :");
		lblCodigo.setBounds(20,200,80,30);
		pnlRegistro.add(lblCodigo);
		
		JLabel lblDetalle = new JLabel("Detalle :");
		lblDetalle.setBounds(20,240,80,30);
		pnlRegistro.add(lblDetalle);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(100,200,200,30);
		pnlRegistro.add(txtCodigo);
		
		txtDetalle = new JTextField();
		txtDetalle.setBounds(100,240,200,30);
		pnlRegistro.add(txtDetalle);
		
		addWindowListener(new WindowAdapter() { @Override public void windowOpened(WindowEvent e) { frm_windowOpened(); } });
		tblRegistros.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { verRegistro( tblRegistros.getSelectedRow() ); } });
	}

	protected void frm_windowOpened() {
		tblRegistros.setModel( daoCargo.getCargos( aIDCargo ) );
		tblRegistros.setRowHeight(30);
		tblRegistros.setBounds(3,51,pnlRegistros.getWidth() - 5, tblRegistros.getRowCount() > 15 ? pnlRegistros.getHeight() - 55 : 30 * tblRegistros.getRowCount() );
	}

	protected void verRegistro(int selectedRow) {
	//	txtCodigo.setText( aIDCargo.get( selectedRow ) );
		txtDetalle.setText( tblRegistros.getValueAt( selectedRow, 0 ).toString() );
	}

}
