package com.s.s.siliconsteppe.wifimanager;


//class to search available clients nearby
public class ClientScanResult {

    private String IpAddr;

    private String HWAddr;

    private String Device;

    private boolean isReachable;

    public ClientScanResult(String ipAddress, String hWAddress, String device, boolean isReachable) {
        super();
        IpAddr = ipAddress;
        HWAddr = hWAddress;
        Device = device;
        this.setReachable(isReachable);
    }

    public String getIpAddress() {
        return IpAddr;
    }

    public boolean isReachable() {
        return isReachable;
    }

    public void setReachable(boolean isReachable) {
        this.isReachable = isReachable;
    }
}