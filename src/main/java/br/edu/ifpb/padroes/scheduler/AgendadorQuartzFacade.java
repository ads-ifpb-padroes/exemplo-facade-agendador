package br.edu.ifpb.padroes.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.concurrent.atomic.AtomicInteger;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fachada para isolar o conhecimento do framework Quartz em uma classe única.
 * Perceba que tudo que é necessário para iniciar uma tarefa foi simplificado
 * através do método {@link #agendarTarefaLoop(Class, int)} para que,
 * independentemente do framework que estejamos usando, a interface para agendar
 * tarefas seja simples e direta
 * 
 * @author diogo.moreira
 *
 */
public class AgendadorQuartzFacade implements Agendador {

	private Scheduler scheduler;
	private String groupName = "SchedulerFacadeQuartz";
	private AtomicInteger sequence = new AtomicInteger();

	private Logger logger = LoggerFactory
			.getLogger(AgendadorQuartzFacade.class);

	public AgendadorQuartzFacade() {
		initScheduler();
	}

	private void initScheduler() {
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		try {
			scheduler = schedFact.getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			logger.error("Problema ao inicializar agendador de tarefas!");
			e.printStackTrace();
		}
	}

	/**
	 * Agenda uma tarefa para ser repetida a cada N segundos
	 * 
	 * @param tarefa
	 *            A classe que implementa a tarefa
	 * @param segundos
	 *            Tempo entre as execuções
	 */
	public void agendarTarefaLoop(Class<? extends Tarefa> tarefa, int segundos) {
		int nextSequence = sequence.incrementAndGet();
		String identity = tarefa.getClass().getSimpleName();

		JobDetail job = newJob(tarefa).withIdentity(identity + nextSequence,
				this.groupName).build();

		Trigger trigger = newTrigger()
				.withIdentity(identity + nextSequence + "Trigger",
						this.groupName)
				.startNow()
				.withSchedule(
						simpleSchedule().withIntervalInSeconds(segundos)
								.repeatForever()).build();

		try {
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logger.error("Problema ao agendar tarefas!");
			e.printStackTrace();
		}
	}
}
