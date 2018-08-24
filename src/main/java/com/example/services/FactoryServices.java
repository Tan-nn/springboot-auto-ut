package com.example.services;

import com.example.util.Const.Zone;

/**
 * Created by tan on 01/04/2017.
 */
public enum FactoryServices {
    INSTANCES;

    // must use java Reflected
    public ICharge getParkingService(Zone type) {
        switch (type) {
            case m1:
                return M1Service.getInstance();
            case m2:
                return M2Service.getInstance();
            default:
                return M3Service.getInstance();
        }
    }

}
