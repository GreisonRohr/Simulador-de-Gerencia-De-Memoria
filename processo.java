
public class processo {
	
	int pid=0,tamanho=0,tempoExe=0,decrementaTempoExe=0,tempoEsperaFila=0;
	
	public int getpid(){
		return pid;
	}
	
	public void setpid(int pid) {
		this.pid = pid;
	}
	
	
	public int gettamanho() {
		return tamanho;
	}
	
	public void settamanho(int tamanho) {
		this.tamanho=tamanho;
	}
	
	public int gettempoExe(){
		return tempoExe;
	}
	
	public void settempoExe(int tempoExe) {
		this.tempoExe = tempoExe;
	}
	
	
	public int getdecrementaTempoExe() {
		return decrementaTempoExe;
	}
	
	public void setdecrementaTempoExe(int decrementaTempoExe) {
		this.decrementaTempoExe=decrementaTempoExe;
	}
	
	public int gettempoEsperaFila(){
		return tempoEsperaFila;
	}
	
	public void settempoEsperaFila(int tempoEsperaFila) {
		this.tempoEsperaFila = tempoEsperaFila;
	}

}
