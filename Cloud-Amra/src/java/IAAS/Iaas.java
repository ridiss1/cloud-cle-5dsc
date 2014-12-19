/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IAAS;

import net.elbandi.pve2api.Pve2Api;

/**
 *
 * @author metbill
 */
public class Iaas {
    
    private Pve2Api pve;
    
    public  Iaas(){
        // Connexion au serveur Proxmox
        pve = new Pve2Api("10.201.2.5","root","pam","iaas2014");
    }
    
}
