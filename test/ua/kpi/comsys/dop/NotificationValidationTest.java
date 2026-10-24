package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NotificationValidationTest {

    // ---------- TextMsg ----------

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void textMsgInvalidSenderThrows(String sender) {
        assertThrows(IllegalArgumentException.class, () -> new TextMsg(sender, "Hi"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void textMsgInvalidTextThrows(String text) {
        assertThrows(IllegalArgumentException.class, () -> new TextMsg("Alice", text));
    }

    @Test
    void textMsgValidDataCreatesRecord() {
        TextMsg msg = new TextMsg("Alice", "Hi");
        assertEquals("Alice", msg.sender());
        assertEquals("Hi", msg.text());
    }

    // ---------- ImageMsg ----------

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void imageMsgInvalidSenderThrows(String sender) {
        assertThrows(IllegalArgumentException.class,
                () -> new ImageMsg(sender, "https://example.com/a.png", 10));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void imageMsgInvalidUrlThrows(String url) {
        assertThrows(IllegalArgumentException.class, () -> new ImageMsg("Bob", url, 10));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void imageMsgNonPositiveSizeThrows(int sizeKb) {
        assertThrows(IllegalArgumentException.class,
                () -> new ImageMsg("Bob", "https://example.com/a.png", sizeKb));
    }

    @Test
    void imageMsgMinimalValidSizeIsAccepted() {
        assertDoesNotThrow(() -> new ImageMsg("Bob", "https://example.com/a.png", 1));
    }

    // ---------- AudioMsg ----------

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void audioMsgInvalidSenderThrows(String sender) {
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg(sender, 30));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void audioMsgNonPositiveDurationThrows(int duration) {
        assertThrows(IllegalArgumentException.class, () -> new AudioMsg("Carol", duration));
    }

    @Test
    void audioMsgMinimalValidDurationIsAccepted() {
        assertDoesNotThrow(() -> new AudioMsg("Carol", 1));
    }
}
