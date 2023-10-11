import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class Listeners {

    private final String topicName = "msprocess";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper om;

    @KafkaListener(topics = "mscashdesc")
    public void cashDescListener(String data) {
        translateToTopic(topicName, data);
    }

    public void translateToTopic(String topicName, String transactionData) {
        kafkaTemplate.send(topicName, "msproces", transactionData);
    }
}
