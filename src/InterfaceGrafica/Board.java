package InterfaceGrafica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Agentes.Monster;


public class Board
{

    public List<List<Cell>> board;
    public List<List<Cell>> previousBoard;

    
    // --- Ctors
    
    public Board(String filename) throws FileNotFoundException
    {
        loadBoardFromFile(filename);
    }
    
    

    public synchronized List<Monster> getMonsters()
    {
        List<Monster> mons = new ArrayList<>();
        
        board.forEach((row) ->
        {
            row
                .stream()
                .filter(cell -> cell instanceof MonsterCell)
                .forEach(cell ->
            {
                Monster mon = ((MonsterCell) cell).getAgent();
                if (!mons.contains(mon))
                {
                    mons.add(mon);
                }
            });
        });
        
        return mons;
    }
    

    

    public synchronized void removeAgentCell(Cell agentCell)
    {
        removeAgentCell(agentCell, false);
    }
    
    public synchronized void removeAgentCell(Cell agentCell, boolean fromCurrentBoard)
    {
        Cell previousCell = getCell(fromCurrentBoard ? board : previousBoard, agentCell.getPosition());
        setCell(previousCell);
    }
    
    
    // --- Board general public methods
    
    public synchronized Cell getCell(Coord2D position)
    {
        return getCell(board, position);
    }
    
    public synchronized void setCell(Cell cell)
    {
        setCell(board, cell);
    }
    
    public synchronized void moveCell(Cell cell, Coord2D destination)
    {
        moveCell(cell, destination, false);
    }
    
    public synchronized void moveCell(Cell cell, Coord2D destination, boolean modifyBoard)
    {
        Cell nextCell = getCell(destination);
        if (cell instanceof MonsterCell && nextCell instanceof MonsterCell)
        {
            return;
        }
        
        // Gets the cell that was on the current position
        Cell previousCell = getCell(previousBoard, cell.getPosition());
        setCell(previousCell);

        // If board should be modified...
        if (modifyBoard)
        {
            // ... and the previous was...
            switch (previousCell.getType())
            {
                // ... a simple or a powerup collectible, removes it
                case DOT:
                case POWERUP:
                    Cell emptyCell = new Cell(previousCell.getPosition(), CellType.EMPTY);
                    setCell(emptyCell);
                    setCell(previousBoard, emptyCell);
                    break;
            }
        }

        // Updates the new cell with its destination
        cell.setPosition(destination);
        setCell(cell);
    }
    
    public synchronized int countRows()
    {
        return board.size();
    }
    
    public synchronized int countColumns()
    {
        return board.get(0).size();
    }
    
    
    public synchronized void print()
    {
        
        
        board.forEach((row) ->
        {
            row.forEach((cell) ->
            {
                System.out.print(cell + " ");
            });
            
            System.out.println("");
        });
    }
    
    
    // --- Private methods
    
    private void loadBoardFromFile(String file) throws FileNotFoundException
    {
        try (Scanner scanner = new Scanner(new File(file)))
        {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            
            board = new ArrayList<>();
            previousBoard = new ArrayList<>();
            
            for (int i = 0; i < rows; ++i)
            {
                board.add(i, new ArrayList<>());
                previousBoard.add(i, new ArrayList<>());
                String buff = scanner.next();
                
                for (int j = 0; j < columns; ++j)
                {
                    board.get(i).add(j, new Cell(new Coord2D(i, j), CellType.EMPTY));
                    previousBoard.get(i).add(j, new Cell(new Coord2D(i, j), CellType.EMPTY));
                    
                    for (CellType cellType : CellType.values())
                    {
                        if (cellType.getValue() == buff.charAt(j))
                        {
                            board.get(i).get(j).setType(cellType);
                            previousBoard.get(i).get(j).setType(cellType);
                        }
                    }
                }
            }
        }
    }
    
    private synchronized Cell getCell(List<List<Cell>> board, Coord2D position)
    {
        return board.get(position.x).get(position.y);
    }
    
    public void setCell(List<List<Cell>> board, Cell cell)
    {
        board.get(cell.getPosition().x).set(cell.getPosition().y, cell);
    }

}