/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes.Monstros;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lokux
 */
public class Monstro_Grande extends Agent {

    int posX = 0;
    int posY = (int) ((Math.random() * 100) % 5);
    int vida = 15;
    int ataque = 5;
    int velocidade = 1;

    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 1000) {

            protected void onTick() {
                if (posX < 30) {
                    posX += velocidade;
                    System.out.println(myAgent.getLocalName()+" Posição: "+posX);

                } else {
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("Muralha", AID.ISLOCALNAME));
                    msg.setLanguage("Português");
                    msg.setOntology("Ataque");
                    msg.setContent(String.valueOf(ataque));
                    myAgent.send(msg);
                    System.out.println("Morri" + msg.toString());
                    doDelete();
                }
            }
        });

        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msga = myAgent.receive();
                if (msga != null) {
                    System.out.println("Entrei não sei porque" + msga.toString());
                    String content = msga.getContent();
                    vida -= Integer.getInteger(content);
                    if (vida <= 0) {
                        doDelete();
                    }
                }
                block();
            }
        });

    }

}
