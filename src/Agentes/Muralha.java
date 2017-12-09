/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lokux
 */
public class Muralha extends Agent {

    int vida = 100;

    @Override
    protected void setup() {

        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msga = myAgent.receive();
                if (msga != null && msga.getOntology().equalsIgnoreCase("Ataque")) {
                    String content = msga.getContent();
                    vida -= Integer.parseInt(content);
                    System.out.println("Vida: " + vida);
                    if (vida <= 0) {
                        System.out.println("Muralha morreu");
                        Codec codec = new SLCodec();
                        Ontology jmo = JADEManagementOntology.getInstance();
                        getContentManager().registerLanguage(codec);
                        getContentManager().registerOntology(jmo);
                        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                        msg.addReceiver(getAMS());
                        msg.setLanguage(codec.getName());
                        msg.setOntology(jmo.getName());
                        try {
                            getContentManager().fillContent(msg, new Action(getAID(), new ShutdownPlatform()));
                            send(msg);
                            doDelete();
                        } catch (Codec.CodecException | OntologyException e) {
                        }
                    }
                }
                block();
            }
        });

    }

}
