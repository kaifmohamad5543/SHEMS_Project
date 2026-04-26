package model;

public class DeviceSchedule {
    private Appliance appliance;
    private String startTime;
    private String endTime;

    public DeviceSchedule(Appliance appliance, String startTime, String endTime) {
        this.appliance = appliance;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void showSchedule() {
        System.out.println(
                "Schedule: " + appliance.getName()
                        + " will run from " + startTime
                        + " to " + endTime
        );
    }
}