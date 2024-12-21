import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Building{
    private final int floors;
    private final Elevator[] elevators;
    private final BlockingQueue<Request> requestQueue;
    public Building(int floors,int numberOfElevators){
        this.floors=floors;
        this.requestQueue=new LinkedBlockingQueue<>();
        this.elevators=new Elevator[numberOfElevators];
        for(int i=0;i<numberOfElevators;++i){
            elevators[i]=new Elevator(i+1,floors,requestQueue);
        }
    }

    public void addRequest(Request request){
        requestQueue.add(request);
    }

    public void startElevators(){
        for(Elevator elevator : elevators)
            new Thread(elevator).start();
    }

    public int getFloors(){
        return floors;
    }
}
