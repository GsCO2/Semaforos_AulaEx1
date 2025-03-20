package controller;

import java.util.concurrent.Semaphore;

public class CavaleiroController extends Thread {
	private Semaphore semaforo;
	private int cavaleirotocha;
	private int distpercorrida;
	private final int distanciatotal = 200;
	private final int tocha = 50;
	private final int pedra = 150;
	private Semaphore mutextocha;
	private Semaphore mutexpedra;
	private int numcavaleiro;
	private static boolean pegoutocha = false;
	private static boolean pegoupedra = false;
	private final String[] resultado = {" foi devorado!", " foi devorado!", " foi devorado!", " conseguiu escapar!"};
	private static boolean[] portasocupadas = new boolean[4];
	 
	public CavaleiroController(Semaphore semaforo, int numcavaleiro, Semaphore mutextocha, Semaphore mutexpedra) {
		this.semaforo = semaforo;
		this.numcavaleiro = numcavaleiro;
		this.mutextocha = mutextocha;
		this.mutexpedra = mutexpedra;
		
	}
	
	@Override
	public void run() {
		Corrida();
		ChegadaPorta();
	}
	
	public void Corrida() {
		while(distanciatotal > distpercorrida) {
			int aceleracao = (int)(Math.random() * 2.01) + 2;
			distpercorrida += aceleracao;
			if (distpercorrida >= tocha && !pegoutocha) {
				try {
					mutextocha.acquire();
					if(!pegoutocha) {
						pegoutocha = true;
						aceleracao += 2;
						cavaleirotocha = numcavaleiro;
						System.out.println("O cavaleiro " + numcavaleiro + " pegou a tocha!");
					}
				} catch(InterruptedException e) {
					System.err.println(e.getMessage());
				} finally {
					mutextocha.release();
				}
			}
			if(distpercorrida >= pedra) {
				try {
					mutexpedra.acquire();
					if(!pegoupedra && numcavaleiro != cavaleirotocha) {
						pegoupedra = true;
						aceleracao +=2;
						System.out.println("O cavaleiro " + numcavaleiro + " pegou a rocha!");
					}	
				} catch(InterruptedException e) {
					System.err.println(e.getMessage());
				} finally {
					mutexpedra.release();
				}
			}
			System.out.println("o cavaleiro " + numcavaleiro + " já percorreu " + distpercorrida);
			try {
				sleep(50);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	public void ChegadaPorta() {
		try {
			semaforo.acquire();
			int portaescolhida;
			
			do {
				portaescolhida = (int)(Math.random() * 4);
			} while (portasocupadas [portaescolhida]);
			portasocupadas[portaescolhida] = true;
			System.out.println("O cavaleiro " + numcavaleiro + " escolheu a porta " + portaescolhida + " e " + resultado[portaescolhida]);
		} catch(InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}
}
	
