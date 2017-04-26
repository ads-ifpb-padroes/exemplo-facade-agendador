package br.edu.ifpb.padroes.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.edu.ifpb.padroes.scheduler.Tarefa;

/**
 * Tarefa simples a ser agendada. Aqui temos um acoplamento com o framework
 * Quartz pelo fato de implementar uma interface que o mesmo provê. Seria
 * possível também desacoplar estas tarefas do framework Quartz criando novas
 * interfaces. Pesquise como fazer isto
 * 
 * @author diogo.moreira
 *
 */
public class HelloWorldJob implements Tarefa {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Hello World! " + new Date());
	}

}