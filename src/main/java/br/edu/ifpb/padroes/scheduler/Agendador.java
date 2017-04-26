package br.edu.ifpb.padroes.scheduler;

/**
 * Interface para agendar tarefas na nossa aplicação. Veja que para trocarmos o
 * framework de agendamento de tarefas é simples, precisando apenas criar uma
 * nova implementação de {@link Agendador}
 * 
 * @author diogo.moreira
 *
 */
public interface Agendador {

	void agendarTarefaLoop(Class<? extends Tarefa> tarefa, int segundos);

}
