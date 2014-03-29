package chat;

import junit.framework.Assert;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: yulia
 * Date: 29.03.14
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
public class ChatMessageTest {
    String s, t;
    ChatMessage cm;

    @Before
    public void setUp() {
        s =  "sender";
        t =  "message content";
        cm = new ChatMessage(s, t);
    }

    @Test
    public void testChatMessage() throws Exception {
        Assert.assertEquals(cm.sender, s);
        Assert.assertEquals(cm.text, t);
    }

    @Test
    public void testJson() throws Exception {
        String json = cm.json();
        Assert.assertTrue((isValidJSON(json)));
    }

    public boolean isValidJSON(String input)
    {
        try {
            new JSONParser().parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
