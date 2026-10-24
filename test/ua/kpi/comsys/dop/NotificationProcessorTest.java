package ua.kpi.comsys.dop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationProcessorTest {

    private NotificationProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new NotificationProcessor();
    }

    @Test
    @DisplayName("TextMsg is formatted according to the template")
    void processTextMsg() {
        String expected = """
                Notification: TEXT
                From: Alice
                Message: Hello, world!
                """;

        assertEquals(expected, processor.process(new TextMsg("Alice", "Hello, world!")));
    }

    @Test
    @DisplayName("ImageMsg is formatted according to the template")
    void processImageMsg() {
        String expected = """
                Notification: IMAGE
                From: Bob
                URL: https://example.com/cat.png (512 KB)
                """;

        assertEquals(expected,
                processor.process(new ImageMsg("Bob", "https://example.com/cat.png", 512)));
    }

    @Test
    @DisplayName("AudioMsg is formatted according to the template")
    void processAudioMsg() {
        String expected = """
                Notification: AUDIO
                From: Carol
                Duration: 45 seconds
                """;

        assertEquals(expected, processor.process(new AudioMsg("Carol", 45)));
    }

    @Test
    @DisplayName("Text containing '%' is not treated as a format specifier")
    void processTextWithPercentSign() {
        String expected = """
                Notification: TEXT
                From: Alice
                Message: 100% done
                """;

        assertEquals(expected, processor.process(new TextMsg("Alice", "100% done")));
    }

    @Test
    @DisplayName("Null notification throws IllegalArgumentException with exact message")
    void processNullThrows() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> processor.process(null));

        assertEquals("Notification cannot be null", ex.getMessage());
    }
}