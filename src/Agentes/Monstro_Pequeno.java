package Agentes;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ControllerException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lokux
 */
public class Monstro_Pequeno extends Monster {

    int posX = 0;
    int vida = 5;
    int ataque = 1;
    int velocidade = 3;

    @Override
    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 1000) {

            @Override
            protected void onTick() {
                if (posX < 30) {
                    posX += velocidade;
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    try {
                        msg.addReceiver(new AID("Torre_Media" + getContainerController().getContainerName(), AID.ISLOCALNAME));
                    } catch (ControllerException ex) {
                        Logger.getLogger(Monstro_Pequeno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    msg.setLanguage("Português");
                    msg.setOntology("posX");
                    msg.setContent(String.valueOf(posX));
                    myAgent.send(msg);
                } else {
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("Muralha", AID.ISLOCALNAME));
                    msg.setLanguage("Português");
                    msg.setOntology("Ataque");
                    msg.setContent(String.valueOf(ataque));
                    myAgent.send(msg);
                    ACLMessage msga = new ACLMessage(ACLMessage.INFORM);
                    try {
                        msga.addReceiver(new AID("Torre_Media" + getContainerController().getContainerName(), AID.ISLOCALNAME));
                    } catch (ControllerException ex) {
                        Logger.getLogger(Monstro_Pequeno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    msga.setLanguage("Português");
                    msga.setOntology("Morri");
                    myAgent.send(msga);
                    doDelete();
                }
            }
        });

        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msga = myAgent.receive();
                if (msga != null && msga.getOntology().equalsIgnoreCase("Ataque")) {
                    String content = msga.getContent();
                    vida -= Integer.parseInt(content);
                    System.out.println(getName() + " Fui atacado: " + posX + " " + vida);
                    if (vida <= 0) {
						System.out.println(getName()+" Morri");
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        try {
                            msg.addReceiver(new AID("Torre_Media" + getContainerController().getContainerName(), AID.ISLOCALNAME));
                        } catch (ControllerException ex) {
                            Logger.getLogger(Monstro_Pequeno.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        msg.setLanguage("Português");
                        msg.setOntology("Morri");
                        myAgent.send(msg);
                        doDelete();
                    }
                }
                block();
            }
        });

    }

}
