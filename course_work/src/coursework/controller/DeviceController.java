package coursework.controller;

import coursework.models.Report;
import coursework.service.Device;
import coursework.Main;

import java.util.ArrayList;

public class DeviceController
{
    private final int devicesNumber;
    private final ArrayList<Device> devices = new ArrayList<>();
    private final BufferController bufferController;

    public DeviceController(int devicesNumber, BufferController bufferController, double alpha, double beta, Report report)
    {
        this.devicesNumber = devicesNumber;
        for(int i = 0; i < devicesNumber; i++)
            devices.add(new Device(i, alpha, beta, report));
        this.bufferController = bufferController;
    }

    public void dispatchRequest()
    {
        for(int i = 0; i < devicesNumber; i++)
            if (devices.get(i).isAvailable())
                if (bufferController.bufferIsEmpty())
                    devices.get(i).addRequest(bufferController.getRequestFromBuffer(), Main.simulatingTime);
    }

    public void solveRequest(double time)
    {
        double minTime;
        boolean continueS = true;
        while(continueS)
        {
            int counter = 0;
            minTime = time;
            for (Device device: devices)
                if (device.getRequest() != null && device.getOutRequestTime() < minTime) {
                    minTime = device.getOutRequestTime();
                    counter++;
                }
            for (Device device: devices)
                if (device.getRequest() != null && device.getOutRequestTime() == minTime)
                    device.deleteRequest();
            if (counter == 0)
                continueS = false;
        }

    }

    public boolean workIsDone()
    {
        for (Device device : devices) if (!device.isAvailable()) return false;
        return true;
    }

    public ArrayList<Device> getDevices()
    {
        return devices;
    }
}
