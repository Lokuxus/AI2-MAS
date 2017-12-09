package Interface;

import InterfaceGrafica.Board;
import InterfaceGrafica.CellType;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.io.FileNotFoundException;

public class ContainerManager {

    private Inicial inicio = new Inicial();
    private static ContainerManager instance = null;
    private final ContainerController[] containerController = new ContainerController[6];

    public ContainerManager() {
        init();
    }

    /**
     *
     * @return
     */
    public static ContainerManager getInstance() {
        if (null == instance) {
            instance = new ContainerManager();
        }
        return instance;
    }
    
    public Board loadBoard(String filename) throws FileNotFoundException
    {
         Board board = new Board(getClass().getResource("/InterfaceGrafica/" + filename).getPath());  
         return board;
    }

    private void init() {
        // Gets JADE runtime interface
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        runtime.setCloseVM(true);
        
        // Creates a profile
        Profile profile = new ProfileImpl(true);
        profile.setParameter(Profile.CONTAINER_NAME, "Tower Defense");
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.GUI, "true");

        // Creates a main container
        containerController[0] = runtime.createMainContainer(profile);

        //containerController = runtime.createAgentContainer(profile);
        for (int i = 0; i < 5; i++) {
            profile = new ProfileImpl(true);
            profile.setParameter(Profile.CONTAINER_NAME, "caminho_" + (i + 1));
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            profile.setParameter(Profile.GUI, "true");
            containerController[i + 1] = runtime.createAgentContainer(profile);
        }

    }

    public AgentController instantiateAgent(String name, String className, Object[] args, int i) throws StaleProxyException {

        AgentController agentController = containerController[i].createNewAgent(name, className, args);
        agentController.start();
        return agentController;
    }

    public AgentController instantiateAgent(String name, String className, int i) throws StaleProxyException {
        return instantiateAgent(name, className, new Object[0], i);
    }

    public AgentController instantiateAgentMonster(String name, String className) throws StaleProxyException {
        int t = (int) (Math.random() * 100) % 5 + 1;
        inicio.board.board.board.get(0).get(0).setType(CellType.MONSTER);
        inicio.board.board.previousBoard.get(0).get(0).setType(CellType.MONSTER);
        return instantiateAgent(name, className, new Object[0], t);
    }

    public String getConatinername(int container) throws ControllerException {
        return containerController[container].getContainerName();
    }

}
