package Interface;

import jade.wrapper.StaleProxyException;

public class AI2MAS {

    public static void main(String[] args) throws StaleProxyException, InterruptedException {
        Object[] Agentargs = null;

        // ContainerManager.getInstance().instantiateAgentMonster("Monstro_A", Agentes.Monstros.Monstro_Grande.class.getName());
        // ContainerManager.getInstance().instantiateAgentMonster("Monstro_B", Agentes.Monstros.Monstro_Medio.class.getName());
        //for (int i = 1; i <=5; i++) {
        ContainerManager.getInstance().instantiateAgent("Muralha", Agentes.Monstros.Muralha.class.getName(), 0);
        ContainerManager.getInstance().instantiateAgent("Torre_Media", Agentes.Monstros.Torre_Media.class.getName(), 1);

        //}
        for (int i = 0; i < 100; i++) {
            int porc = (int) ((Math.random() * 100) % 10);
            if (porc < 3) {
                ContainerManager.getInstance().instantiateAgentMonster("Monstro" + i, Agentes.Monstros.Monstro_Grande.class.getName());
            } else if (porc < 6) {
                ContainerManager.getInstance().instantiateAgentMonster("Monstro" + i, Agentes.Monstros.Monstro_Medio.class.getName());
            } else {
                ContainerManager.getInstance().instantiateAgentMonster("Monstro" + i, Agentes.Monstros.Monstro_Pequeno.class.getName());
            }
            Thread.sleep(1000);
        }

    }

}
