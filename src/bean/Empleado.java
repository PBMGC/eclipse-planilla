package bean;

public class Empleado {
	int id;
	String Nombres, ApellidoPaterno, ApellidoMaterno, Direccion, Dni, Password;
	boolean Valido;
	
	public Empleado() { }
	
	public Empleado(int id, String nombres, String apellidoPaterno, String apellidoMaterno, 
			String direccion, String dni, String password) {
		super();
		this.id = id;
		Nombres = nombres;
		ApellidoPaterno = apellidoPaterno;
		ApellidoMaterno = apellidoMaterno;
		Direccion = direccion;
		Dni = dni;
		Password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombres() {
		return Nombres;
	}

	public void setNombres(String nombres) {
		Nombres = nombres;
	}

	public String getApellidoPaterno() {
		return ApellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		ApellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return ApellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		ApellidoMaterno = apellidoMaterno;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public boolean isValido() {
		return Valido;
	}

	public void setValido(boolean valido) {
		Valido = valido;
	}

	@Override
	public String toString() {
		return  String.format( "%s,%s,%s,%s,%s,%s,%s", id,Nombres,ApellidoPaterno,ApellidoMaterno,Direccion,Dni,Password );				
	}
	
}