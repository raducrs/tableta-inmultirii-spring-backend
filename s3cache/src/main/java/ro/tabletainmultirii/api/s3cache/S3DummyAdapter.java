package ro.tabletainmultirii.api.s3cache;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class S3DummyAdapter implements S3Adapter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean save(Object key, Object value) {
        try {
            System.out.println("Saving at "+ (key != null ? key.toString() : "null") + " \n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
