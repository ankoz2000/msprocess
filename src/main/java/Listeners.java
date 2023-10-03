import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class Listeners {

    private final String topicName = "msprocess";

    @Autowired
    private KafkaTemplate<String, TransactionData> kafkaTemplate;

    @KafkaListener(topics = "mscashdesc")
    public void cashDescListener(TransactionData transactionData) {
        translateToTopic(topicName, transactionData);
    }

    public void translateToTopic(String topicName, TransactionData transactionData) {
        kafkaTemplate.send(topicName, "msproces", transactionData);
    }
}
