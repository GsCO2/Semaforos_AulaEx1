package view;

import java.util.concurrent.Semaphore;

import controller.CavaleiroController;

public class Main {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(4);
		Semaphore mutextocha = new Semaphore(1);
		Semaphore mutexpedra = new Semaphore(1);
		
		for(int i = 0; i < 4; i++) {
			Thread t = new CavaleiroController(semaforo, i + 1, mutextocha, mutexpedra);
			t.start();
		}
	}
	                           
}
