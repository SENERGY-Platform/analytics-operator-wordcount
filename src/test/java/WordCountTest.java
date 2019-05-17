import org.infai.seits.sepl.operators.Message;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WordCountTest {

    @Test
    public void run() throws Exception{
        WordCount tsc = new WordCount();
        List<Message> messages = TestMessageProvider.getTestMesssagesSet();
        for (Message m : messages) {
            tsc.config(m);
            tsc.run(m);
            JSONObject expected = new JSONObject("{" + m.getMessageString().split("compare\":\\{")[1].split("}")[0] + "}");
            JSONObject actual = new JSONObject("{" + m.getMessageString().split("wordcounts\":\\{")[1].split("}")[0] + "}");
            net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals(expected, actual);

            expected.put("jnp", 33);
            net.javacrumbs.jsonunit.JsonAssert.assertJsonNotEquals(expected, actual);
        }
    }
}
