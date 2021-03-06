import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelAgent extends Thread {
    static class InternalCall{
        private final int toFloor;
        InternalCall(int toFloor){
            this.toFloor = toFloor;
        }
    }

    InternalPanelAgent(ElevatorCar elevatorCar){
        this.elevatorCar = elevatorCar;
    }

    BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
    ElevatorCar elevatorCar;

    public void run(){
        for(;;){
            InternalCall ic = null;
            try {
                ic = input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            assert ic != null;
            if(ic.toFloor==elevatorCar.getFloor())
                continue;

            if(elevatorCar.getFloor() < ic.toFloor){
                ElevatorStops.get().setLiftStopUp(ic.toFloor);
            }else{
                ElevatorStops.get().setLiftStopDown(ic.toFloor);
            }
        }
    }
}