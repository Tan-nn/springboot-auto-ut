package com.example.controller;

import com.example.Parameter.Park;
import com.example.model.Fee;
import com.example.services.FactoryServices;
import com.example.services.ICharge;
import com.example.util.Const;
import com.example.util.Const.Zone;
import com.example.util.Util;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by tan on 01/04/2017.
 */
@RestController
public class ParkingController {


    /**
     *
     * Charge parking fee
     *
     * @param park RequestBody object
     * @param zoneId parking zone Id
     * @return Parking info
     */
    @RequestMapping(value = "/parking/{zoneId}", method = RequestMethod.POST)
    public Fee chargeFee(@RequestBody @Valid Park park, @PathVariable String zoneId) {
        // if zoneId invalid throw IllegalArgumentException and it's handle in CommonExceptionHandler
        Zone parkingZoneId = Zone.valueOf(zoneId);

        // pare timestamp to Date time, may be change String type
        DateTime startTime = new DateTime(park.getStartTime());
        DateTime endTime = new DateTime(park.getEndTime());

        // validation date
        Util.validateDateBefore(startTime, endTime);

        // pick service
        ICharge m1Service = FactoryServices.INSTANCES.getParkingService(parkingZoneId);
        double cost = m1Service.charge(park.getCardId(), startTime, endTime);

        Fee fee = new Fee();
        fee.setStartTime(startTime.toString(Const.DATE_PATTERN));
        fee.setEndTime(endTime.toString(Const.DATE_PATTERN));
        fee.setCost(cost);

        return fee;
    }

    /**
     *
     * Get parking zone list
     *
     * @return Parking zone list
     */
    @RequestMapping(value = "/parking")
    public Map<String, List<Zone>> getParkingZone() {
        // create map for store key-value of response object
        Map<String, List<Zone>> response = new HashMap<>();
        // put zone array to response
        response.put("zones", Arrays.asList(Zone.values()));

        return response;
    }

}
