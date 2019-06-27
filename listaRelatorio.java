import java.util.ArrayList;

public class listaRelatorio {
	
	int nProcesso = 0;
	int contCicloExe = 0, somaTamanhoOcupado=0;
	int contaTempo = 0;

	ArrayList<processo> processosNaMemoria = new ArrayList<processo>();
	ArrayList<processo> filaProcessos = new ArrayList<processo>();
	ArrayList<processo> encerrados = new ArrayList<processo>();
	
	public listaRelatorio(int nProcesso,int tempo,int contCicloExe,ArrayList<processo>processosNaMemoria,ArrayList<processo>filaProcessos,ArrayList<processo>encerrados) {
		System.out.println('\n');
		

		System.out.println(contCicloExe+ " Ciclos de memoria"+" #######################################################################");//mostra o ciclo atual 
		
		System.out.println("\n");


		System.out.println("PROCESSOS NA MEMÓRIA "+" ========================================================");
		System.out.println("\n");
		System.out.println("Total de processos na memoria:" + processosNaMemoria.size());
		System.out.println("\n");
		if(processosNaMemoria.size()==0) {
			System.out.println(" Nenhum processo na memoria! ");
			
		}
		else {
			
			for(int i=0; i< processosNaMemoria.size();i++) {//lista os processos na memoria
				System.out.println("PID:"+processosNaMemoria.get(i).getpid()+"--"+"Tempo de execução:"+processosNaMemoria.get(i).getdecrementaTempoExe()+"--"+"Tamanho do Processo:"+processosNaMemoria.get(i).gettamanho()+" bytes");
				somaTamanhoOcupado=somaTamanhoOcupado+processosNaMemoria.get(i).gettamanho();

			}
			
		}
		
		System.out.println("===============================================================================");
		
		System.out.println("\n");
		
		System.out.println("\n");

		System.out.println("FILA DE PROCESSOS AGURADANDO LIBERAR A MEMÓRIA "+" *******************************");
		System.out.println("\n");

		
		if(filaProcessos.size()==0) {
			System.out.println(" Fila vazia! ");
			
		}
		else {
			
			for(int i=0; i< filaProcessos.size();i++) {//lista os processos que estao esperando liberar espaço na memoria
				System.out.println("PID:"+filaProcessos.get(i).getpid()+"--"+"Tamanho do Processo:"+filaProcessos.get(i).gettamanho()+" bytes"+"--"+"Tempo de espera antes de conseguir ser alocado na memooria:"+filaProcessos.get(i).gettempoEsperaFila()+" Ciclos");
			}
		}
		
		
		System.out.println("*******************************************************************************");

		
		System.out.println("\n");
						
		
		System.out.println("FILA DE ENCERRADOS "+" XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("\n");

		if(encerrados.size()==0) {
			System.out.println("Lista vazia! ");
			
		}
		else {
			
			for(int i=0; i< encerrados.size();i++) {//lista os processos na lista de destruidos
				System.out.println("PID:"+encerrados.get(i).getpid()+"--"+"Tempo total de execução:"+encerrados.get(i).tempoExe+"--"+"Tamanho do Processo:"+encerrados.get(i).gettamanho()+" bytes" );
			}

			
		}
		
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("\n");

		System.out.println("Quantidade Total de Processos:" + nProcesso);
		
		
		System.out.println("Percentual de memoria ocupada:" + somaTamanhoOcupado+ " bytes");
		
		System.out.println("Percentual de memoria livre:" + (5000-somaTamanhoOcupado)+" bytes");
		
		

	}

}
