package Agentes.Monstros;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lokux
 */
public class Monstro_Pequeno extends Agent {

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
                    msg.addReceiver(new AID("Torre_Media", AID.ISLOCALNAME));
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
                    msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("Torre_Media", AID.ISLOCALNAME));
                    msg.setLanguage("Português");
                    msg.setOntology("Morri");
                    myAgent.send(msg);
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
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        msg.addReceiver(new AID("Torre_Media", AID.ISLOCALNAME));
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
