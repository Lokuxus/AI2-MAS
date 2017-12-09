package Agentes;

import Interface.ContainerManager;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lokux
 */
public class Rei_Demonio extends Agent {

    int i = 0;

    @Override
    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 1000) {

            @Override
            protected void onTick() {
                if (i < 100) {
                    try {
                        int porc = (int) ((Math.random() * 100) % 9);
                        if (porc < 3) {
                            ContainerManager.getInstance().instantiateAgentMonster("Monstro" + i, Agentes.Monstro_Grande.class.getName());
                        } else if (porc < 6) {
                            ContainerManager.getInstance().instantiateAgentMonster("Monstro" + i, Agentes.Monstro_Medio.class.getName());
                        } else {
                            ContainerManager.getInstance().instantiateAgentMonster("Monstro" + i, Agentes.Monstro_Pequeno.class.getName());
                        }
                    } catch (Exception e) {
                    }
                }
                i++;
            }
        });
    }
}
