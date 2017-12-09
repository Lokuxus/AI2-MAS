package Agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author lokux
 */
public class Torre_Media extends Agent {

    AID primeiro = null;
    int posPrimeiro = 0;
    int ataque = 2;

    @Override
    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 500) {

            @Override
            protected void onTick() {
                if (primeiro != null) {
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(primeiro);
                    msg.setLanguage("Português");
                    msg.setOntology("Ataque");
                    msg.setContent(String.valueOf(ataque));
                    myAgent.send(msg);
                }
            }
        });

        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msga = myAgent.receive();
                if (msga != null && msga.getOntology().equalsIgnoreCase("posX")) {
                    if (posPrimeiro < Integer.parseInt(msga.getContent())) {
                        posPrimeiro = Integer.parseInt(msga.getContent());
                        primeiro = msga.getSender();
                    }
                } else if (msga != null && msga.getOntology().equalsIgnoreCase("Morri")) {
                    posPrimeiro = 0;
                    primeiro = null;
                }
                block();
            }
        });


        /*
         addBehaviour(new TickerBehaviour(this, 100) {

         @Override
         protected void onTick() {
         String name = null;
         AgentContainer c = getContainerController();
         ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
         for (int i = 0; i < 100; i++) {
         try {
         name = c.getAgent("Monstro" + i).getName();
         if (name != null) {
         msg.addReceiver(new AID(name, AID.ISLOCALNAME));
         }
         } catch (ControllerException ex) {
         //                 Logger.getLogger(Torre_Media.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         msg.setLanguage("Português");
         msg.setOntology("posX");
         msg.setContent("A");
         myAgent.send(msg);
         }
         });
         */
    }

}
