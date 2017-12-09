package InterfaceGrafica;

import java.awt.Graphics2D;


public class Cell
{

    private CellType type;
    private Coord2D position;
    private int BOARD_CELL_SIZE = Constant.BOARD_CELL_SIZE;

    public Cell(Coord2D position, CellType type)
    {
        this.position = position;
        this.type = type;
    }

    public Coord2D getPosition()
    {
        return position;
    }

    public void setPosition(Coord2D position)
    {
        this.position = position;
    }
    
    public CellType getType()
    {
        return type;
    }

    public void setType(CellType type)
    {
        this.type = type;
    }
    
    public void draw(Graphics2D canvas)
    {
        int x = position.x,
            y = position.y;
                
        canvas.setColor(type.getColor());
        // TODO: Change some stuff to canvas.drawImage(...)
        
        switch (type)
        {
            case DOT:
                int xPos = BOARD_CELL_SIZE * x + (BOARD_CELL_SIZE / 4);
                int yPos = BOARD_CELL_SIZE * y + (BOARD_CELL_SIZE / 4);
                
                canvas.fillOval(
                    yPos,
                    xPos,
                    BOARD_CELL_SIZE / 2, 
                    BOARD_CELL_SIZE / 2   
                );
                
                break;
                
            case MONSTER:
            case PACMAN:
            case POWERUP:
                canvas.fillOval(
                    BOARD_CELL_SIZE * y,
                    BOARD_CELL_SIZE * x, 
                    BOARD_CELL_SIZE, 
                    BOARD_CELL_SIZE    
                );
                
                break;
            default:
                canvas.fillRect(
                    BOARD_CELL_SIZE * y,
                    BOARD_CELL_SIZE * x, 
                    BOARD_CELL_SIZE, 
                    BOARD_CELL_SIZE
                );
        }
    }

    @Override
    public String toString()
    {
        return type.toString();
    }
    
}
