package Interface;

import InterfaceGrafica.Board;
import InterfaceGrafica.BoardGui;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AI2MAS {
    
    
  

    public static void main(String[] args) throws StaleProxyException, InterruptedException, ControllerException, FileNotFoundException, IOException {
        //Object[] Agentargs = null;
         
        ContainerManager.getInstance().instantiateAgent("Muralha", Agentes.Muralha.class.getName(), 0);
        for (int i = 1; i < 6; i++) {
            ContainerManager.getInstance().instantiateAgent("Torre_Media" + ContainerManager.getInstance().getConatinername(i), Agentes.Torre_Media.class.getName(), i);
        }
        ContainerManager.getInstance().instantiateAgent("Rei_Demonio", Agentes.Rei_Demonio.class.getName(), 0);

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
