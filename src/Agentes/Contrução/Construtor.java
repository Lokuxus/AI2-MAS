/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes.Contrução;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author 3768210
 */
public class Construtor extends Agent {

    int cooldown = 0;

    String status = "Disponivel";

    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                ACLMessage msga = myAgent.receive();
                if ("Disponivel".equalsIgnoreCase(status)) {
                    
                }else{
                    cooldown-=1;
                    if(cooldown<=0){
                        
                    }
                }

            }
        });
    }

}
