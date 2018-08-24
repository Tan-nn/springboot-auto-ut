package com.example;

import com.example.services.FactoryServices;
import com.example.services.ICharge;
import com.example.util.Const.Zone;
import com.example.util.Const;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by tan on 02/04/2017.
 */
public class UtilTest {
    private enum Input {
        type,
        input,
        expected,
        cardId,
        start,
        end,
        cost,
        normal
    }


    /**
     * !Importance! method execute test case
     *
     * @param filePath file test case input
     * @throws JSONException read json file failure
     */
    public static void executeTestCase(String filePath) throws JSONException {
        String expectedData = getFile(filePath);
        JSONObject jsonObject = new JSONObject(expectedData);

        // get json attribute/object
        String parkingZone = jsonObject.getString(Input.type.toString());
        JSONObject inputJsObj = jsonObject.getJSONObject(Input.input.toString());
        JSONObject expectedJsObj = jsonObject.getJSONObject(Input.expected.toString());
        boolean normal = jsonObject.getBoolean(Input.normal.toString());

        // get child attribute
        String startDateStr = inputJsObj.getString(Input.start.toString());
        String endDateStr = inputJsObj.getString(Input.end.toString());
        String cardId = inputJsObj.getString(Input.cardId.toString());
        double costExpect = expectedJsObj.getDouble(Input.cost.toString());

        DateTime startTime = DateTime.parse(startDateStr, DateTimeFormat.forPattern(Const.DATE_PATTERN));
        DateTime endTime = DateTime.parse(endDateStr, DateTimeFormat.forPattern(Const.DATE_PATTERN));

        ICharge iCharge = FactoryServices.INSTANCES.getParkingService(Zone.valueOf(parkingZone));

        double cost = iCharge.charge(cardId, startTime, endTime);

        if (normal)
            Assert.assertEquals(costExpect, cost, 0.0);
        else
            Assert.assertNotEquals(costExpect, cost, 0.0);
    }

    /**
     * Read file
     *
     * @param filePath file to read
     * @return file content
     */
    private static String getFile(String filePath) {

        StringBuilder result = new StringBuilder();
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            stream.forEach(result::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

}
