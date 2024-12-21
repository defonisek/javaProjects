import java.util.*;
import java.util.concurrent.BlockingQueue;

public class Elevator implements Runnable{
    private final int id;
    private int currentFloor=1;
    private boolean movingUp=true;
    private final List<Request> passengers=new ArrayList<>();
    private final BlockingQueue<Request> requestQueue;
    private final PriorityQueue<Request> plannedStops=new PriorityQueue<>(Comparator.comparingInt(req->Math.abs(currentFloor-req.getStartFloor())));
    private int lastFloor=-1;

    public Elevator(int id,int totalFloors,BlockingQueue<Request> requestQueue){
        this.id=id;
        this.requestQueue=requestQueue;
    }

    @Override
    public void run(){
        while(true){
            try{
                handleRequests();
                Thread.sleep(1000);
            } 
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void handleRequests(){
        // убираем пассажиров
        passengers.removeIf(request->{
            if(request.getDestinationFloor()==currentFloor){
                log(String.format("Passenger dropped off at floor %d",currentFloor));
                return true;
            }
            return false;
        });
        // проверяем, нужно ли подбирать еще пассажиров
        synchronized (requestQueue){
            Iterator<Request> iterator=requestQueue.iterator();
            while(iterator.hasNext()){
                Request request=iterator.next();
                if(canPickUp(request)){
                    plannedStops.add(request);
                    iterator.remove();
                    log(String.format("Scheduled stop to pick up passenger at floor %d going to floor %d",request.getStartFloor(),request.getDestinationFloor()));
                }
            }
        }
        // подобрать пассажиров на текущем этаже
        plannedStops.removeIf(request->{
            if(request.getStartFloor()==currentFloor){
                passengers.add(request);
                log(String.format("Picked up passenger at floor %d going to floor %d",request.getStartFloor(),request.getDestinationFloor()));
                return true;
            }
            return false;
        });
        // к следующей плановой остановке
        if(!plannedStops.isEmpty()){
            Request nextStop=plannedStops.peek();
            if(nextStop.getStartFloor()>currentFloor){
                movingUp=true;
                currentFloor++;
            } 
            else if(nextStop.getStartFloor()<currentFloor){
                movingUp=false;
                currentFloor--;
            } 
            else
                plannedStops.poll();
        } 
        else if(!passengers.isEmpty()){
            // едем на необходимые пассажирам этажи
            int destination=passengers.get(0).getDestinationFloor();
            if(destination>currentFloor){
                movingUp=true;
                currentFloor++;
            } 
            else if(destination<currentFloor){
                movingUp=false;
                currentFloor--;
            }
        }
        if(currentFloor!=lastFloor){
            log(String.format("Moved to floor %d",currentFloor));
            lastFloor=currentFloor;
        }
    }

    private boolean canPickUp(Request request){
        // проверка, можно ли подхватить пассажиров
        if(passengers.isEmpty())
            return true;
        int firstDestination=passengers.get(0).getDestinationFloor();
        if(movingUp){
            return currentFloor <= request.getStartFloor() && request.getStartFloor() <= firstDestination;
        } 
        else{
            return currentFloor >= request.getStartFloor() && request.getStartFloor() >= firstDestination;
        }
    }

    private void log(String message){
        System.out.printf("Elevator %d: %s%n",id,message);
    }
}