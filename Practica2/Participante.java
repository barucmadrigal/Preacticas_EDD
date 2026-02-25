public class Participante{

	public static int length;
	public static int indiceDeVictoria;
	String nombre; 
	int numeroDeMedallas; 
	int numeroDeParticipaciones; 
	public Cinta cinta;

	public Participante(String nombre, int m, int p, Cinta cinta){
		this.nombre = nombre; 
		this.numeroDeMedallas = m;
		this.numeroDeParticipaciones = p;
		this.cinta = cinta; 

	}

	public String getNombre(){
		return nombre; 	
	}

	public int getnumeroDeMedallas(){
		return numeroDeMedallas; 
	}

	public int getnumeroDeParticipaciones(){
		return numeroDeParticipaciones;
	}

	public Cinta getCinta(){
		return cinta; 
	}

	public double indiceDeVictoria() {
    if (this.numeroDeParticipaciones == 0) {
        return 0.0; 
    }
    
    return this.numeroDeMedallas / this.numeroDeParticipaciones;
}
}
