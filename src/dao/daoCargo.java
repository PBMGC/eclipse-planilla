package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class daoCargo {

	public DefaultTableModel getCargos( ArrayList<String> aIDCargo) {
		DefaultTableModel modelo = null;
		
		try {	
			FileReader fileReader = new FileReader("Cargos.txt");
			BufferedReader bufferedReader  = new BufferedReader(fileReader);
			
			String linea = null;
			while ( ( linea = bufferedReader.readLine() ) != null ) {
				String[] aLinea = linea.split(",");
				if ( modelo == null ) {
					modelo = new DefaultTableModel(0, 1);
					aIDCargo = new ArrayList<String>();
				}
				aIDCargo.add( aLinea[0] ) ;
				modelo.addRow( new Object[] { aLinea[1] } );
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) { e.printStackTrace(); }

		
		return modelo;
	}
	
}
