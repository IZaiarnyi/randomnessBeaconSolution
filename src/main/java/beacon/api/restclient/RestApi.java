package beacon.api.restclient;

import beacon.api.entity.Record;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.*;

/**
 * Interface of REST webservice.
 *
 * Created by Yupa on 24.02.2016.
 */
public interface RestApi {
    @GET("/rest/record/last")
    public Call<Record> getLastRecord();

    @GET("/rest/record/{timestamp}")
    public Call<Record> getRecordByTimeStamp(@Path("timestamp") String timestamp);


}
