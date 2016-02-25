package beacon.api.entity;

/**
 * Created by Yupa on 24.02.2016.
 */
public class Record {
    String version;
    String frequency;
    String timeStamp;
    String seedValue;
    String previousOutputValue;
    String outputValue;
    String signatureValue;
    int statusCode;
    public Record(){}
    public Record( String version, String frequency, String timeStamp, String seedValue, String previousOutputValue,  String outputValue, String signatureValue,  int statusCode) {
        this.version = version;
        this.frequency = frequency;
        this.timeStamp = timeStamp;
        this.seedValue = seedValue;
        this.previousOutputValue = previousOutputValue;
        this.outputValue = outputValue;
        this.signatureValue = signatureValue;
        this.statusCode = statusCode;
    }

    public String getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(String outputValue) {
        this.outputValue = outputValue;
    }

    public String getPreviousOutputValue() {
        return previousOutputValue;
    }

    public void setPreviousOutputValue(String outputValue) {
        this.previousOutputValue = outputValue;
    }

}