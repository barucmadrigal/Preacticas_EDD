public class Cinta{

	String nombre; 
	int jerarquia;
	static final int BLANCO = 1;
	static final int NARANJA = 2; 
	static final int MORADO = 3;
	static final int AZUL = 4; 
	static final int VERDE = 5;

	public Cinta(String nombre, int jerarquia){
		this.nombre = nombre;
		this.jerarquia = jerarquia;
	}

	public void obtenerJerarquia(String nombre){
		switch(nombre){
			case "Kaimua": 
				jerarquia = BLANCO; 
			break;
			case "Moli": 
				jerarquia = NARANJA;
			break; 
			case "Lua":
				jerarquia = MORADO;
			break;
			case "Moana": 
				jerarquia = AZUL; 
			break; 
			case "Ulajui": 
				jerarquia = VERDE; 
			break; 
			default:
				jerarquia = 0; 
			break;
		}
	}

	public int getJerarquia(){
		return jerarquia; 
	}

	public String getNombre(){
		return nombre; 
	}

	public int mayorJerarquia(Cinta cinta, Cinta cinta2){
		if(cinta.getJerarquia() > cinta2.getJerarquia()){
			//System.out.println("La cinta" + cinta.getNombre() + "Tiene mayor gerarquia"); 
			return 1;
		}
		else if(cinta.getJerarquia() < cinta2.getJerarquia()){
			//System.out.println("La cinta" + cinta2.getNombre() + "Tiene mayor gerarquia"); 
			return -1; 
		}
		else if(cinta.getJerarquia() == cinta2.getJerarquia()){
			//System.out.println("Ambas cintas tienen la misma gerarquia"); 
			return 0;
		}
		else{
			return 0;
		}
	}
}
