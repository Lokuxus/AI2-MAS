package Interface;

import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class AI2MAS {

    public static void main(String[] args) throws StaleProxyException, InterruptedException, ControllerException {
        Object[] Agentargs = null;

        ContainerManager.getInstance().instantiateAgent("Muralha", Agentes.Monstros.Muralha.class.getName(), 0);
        for (int i = 1; i < 6; i++) {
            ContainerManager.getInstance().instantiateAgent("Torre_Media" + ContainerManager.getInstance().getConatinername(i), Agentes.Monstros.Torre_Media.class.getName(), i);
        }

        ContainerManager.getInstance().instantiateAgent("Rei_Demonio", Agentes.Monstros.Rei_Demonio.class.getName(), 0);

        //}
        /*
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
         */
    }

}
