package Agentes.Monstros;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lokux
 */
public class Monstro_Medio extends Agent {

    int posX = 0;
    int vida = 10;
    int ataque = 3;
    int velocidade = 2;

    @Override
    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 1000) {

            @Override
            protected void onTick() {
                if (posX < 30) {
                    posX += velocidade;

                } else {
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("Muralha", AID.ISLOCALNAME));
                    msg.setLanguage("Português");
                    msg.setOntology("Ataque");
                    msg.setContent(String.valueOf(ataque));
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
                    if (vida <= 0) {
                        doDelete();
                    }
                }
                block();
            }
        });
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msga = myAgent.receive();
                if (msga != null && msga.getOntology().equalsIgnoreCase("posX")) {
                    ACLMessage reply = msga.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent(String.valueOf(posX));
                    myAgent.send(reply);
                }
                block();
            }
        });

    }

}
