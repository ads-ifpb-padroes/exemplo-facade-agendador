package br.edu.ifpb.padroes.cliente;

import br.edu.ifpb.padroes.job.HelloWorldJob;
import br.edu.ifpb.padroes.scheduler.Agendador;
import br.edu.ifpb.padroes.scheduler.AgendadorQuartzFacade;
import br.edu.ifpb.padroes.scheduler.Tarefa;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe de teste
 * 
 * @author diogo.moreira
 *
 */
public class Loader {

	public static void main(String[] args) {
		Agendador agendador = new AgendadorQuartzFacade();
		agendador.agendarTarefaLoop(HelloWorldJob.class, 5);
	}

}
