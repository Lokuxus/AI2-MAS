package InterfaceGrafica;

import Agentes.Monster;

public class MonsterCell extends Cell
{

    private final Monster agent;

    // --- Ctors
    
    private MonsterCell(Monster agent, Coord2D position, CellType type)
    {
        super(position, type);
        this.agent = agent;
    }
    
    public MonsterCell(Monster agent, Cell house)
    {
        this(agent, house.getPosition(), CellType.MONSTER);
    }

    
    // --- Getters and setters
    
    public Monster getAgent()
    {
        return agent;
    }
    
}
