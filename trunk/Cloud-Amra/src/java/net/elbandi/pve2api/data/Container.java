/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.elbandi.pve2api.data;

import java.util.HashMap;
import java.util.Map;
import net.elbandi.pve2api.Pve2Api;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author metbill
 */
public class Container {
    
    private String ostemplate;
    private String storage;
    private String vmid;
    private String cpus;
    private String disk;
    private String hostname;
    private String ip_address;
    private String memory;
    private String password;
    private String status;

    // Création
    public Container(String ostemplate, String vmid, String cpus, String disk, String hostname, String memory, String password) {
        this.ostemplate = ostemplate;
        this.vmid = vmid;
        this.cpus = cpus;
        this.disk = disk;
        this.hostname = hostname;
        this.memory = memory;
        this.password = password;
    }
    
    //Lecture
    public Container(JSONObject data) throws JSONException {
		cpus = data.getString("cpus");
		disk = data.getString("maxdisk");
		hostname = data.getString("name");
		memory = data.getString("maxmem");
		ip_address = data.getString("ip");
                status = data.getString("status");
    }

    public Pve2Api.PveParams getCreateParams() {
		return new Pve2Api.PveParams("ostemplate", ostemplate)
				.Add("storage", storage).Add("vmid", vmid).Add("cpus", cpus)
                                .Add("disk", disk).Add("hostname", hostname).Add("ip_address",ip_address)
                                .Add("memory", memory).Add("password", password);
    }
    
    public Pve2Api.PveParams getUpdateParams() {
		return new Pve2Api.PveParams("cpus", cpus)
                                .Add("disk", disk).Add("memory", memory);
                                
    }
    
    @Override
    public String toString(){
        
        return "cpus="+this.cpus+"\n"+
                "disk="+this.disk+"\n"+"hostname="+this.hostname+"\n"+
                "memory="+this.memory+"\n"+"status="+this.status+"\n"+
                "ip_address="+this.ip_address;   
    }
           

    public String getOstemplate() {
        return ostemplate;
    }

    public void setOstemplate(String ostemplate) {
        this.ostemplate = ostemplate;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getVmid() {
        return vmid;
    }

    public void setVmid(String vmid) {
        this.vmid = vmid;
    }

    public String getCpus() {
        return cpus;
    }

    public void setCpus(String cpus) {
        this.cpus = cpus;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
