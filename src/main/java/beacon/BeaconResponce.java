package beacon;

import beacon.api.entity.Record;
import beacon.api.restclient.RestClient;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Yupa on 26.02.2016.
 */
public class BeaconResponce {
    private String beaconOutput = null;
    private Record record = null;
    private final int beaconInterval= 60000;//millisecs

    public BeaconResponce(Date dateFrom, Date dateTo) throws IOException {
        RestClient.INSTANCE.initialize();
        if ( dateFrom != null && dateTo != null ){
            while ( dateFrom.compareTo(dateTo) <= 0){
                String epochTimeStamp =  String.valueOf(dateFrom.getTime() / 1000L);
                record = RestClient.INSTANCE.getRestApi().getRecordByTimeStamp(epochTimeStamp).execute().body();
                if ( record != null)
                    beaconOutput += record.getOutputValue();
                dateFrom =  new Date(dateFrom.getTime() + beaconInterval);
            }
        }
        else{
            record  = RestClient.INSTANCE.getRestApi().getLastRecord().execute().body();
            beaconOutput = record.getOutputValue();
        }
    }

    public String getOutput(){ return beaconOutput; }
}