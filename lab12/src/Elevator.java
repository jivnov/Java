public class Elevator {
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    static void makeExternalCall(){
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(4, false));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void makeInternalCall(){
        try {
            internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void init(){
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        makeExternalCall();
        Thread.sleep(100);
        makeInternalCall();
    }
}