/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes.Monstros;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lokux
 */
public class Monstro_Medio extends Agent {

    int posX = 0;
    int posY = (int) ((Math.random() * 100) % 5);
    int vida = 10;
    int ataque = 3;
    int velocidade = 3;

    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msga = myAgent.receive();
                if (msga != null) {
                    String content = msga.getContent();
                    vida -= Integer.getInteger(content);
                    if (vida <= 0) {
                       doDelete();
                    }
                }

                if (posX < 30) {
                    posX += velocidade;

                } else {
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("Muralha", AID.ISLOCALNAME));
                    msg.setLanguage("Português");
                    msg.setOntology("Ataque");
                    msg.setContent(String.valueOf(ataque));
                    myAgent.send(msg);
                }
            }
        });

    }

}
