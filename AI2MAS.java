
package Interface;

import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import MAS.core.*;


public class AI2MAS {

   
    public static void main(String[] args) throws StaleProxyException {
        Object[] Agentargs= null;
        
        ContainerManager.getInstance().instantiateAgentMonster("Monstro_A", Agentes.Monstros.Monstro_Grande.class.getName());
    
    }
    
}
