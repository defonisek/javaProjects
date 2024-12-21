import java.util.Random;

public class RequestGenerator implements Runnable{
    private final Building building;
    private final Random random = new Random();

    public RequestGenerator(Building building){
        this.building=building;
    }

    @Override
    public void run(){
        while(true){
            try{
                generateRequest();
                Thread.sleep(2000); // генерация запросов каждые 2 секунды
            } 
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void generateRequest(){
        int startFloor=random.nextInt(building.getFloors())+1;
        int destinationFloor;
        do{
            destinationFloor=random.nextInt(building.getFloors())+1;
        }while(destinationFloor==startFloor);
        Request request=new Request(startFloor,destinationFloor);
        building.addRequest(request);
        System.out.printf("New request: from floor %d to floor %d%n",startFloor,destinationFloor);
    }
}
