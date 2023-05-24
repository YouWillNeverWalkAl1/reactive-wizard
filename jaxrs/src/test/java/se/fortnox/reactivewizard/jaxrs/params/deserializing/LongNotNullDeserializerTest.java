package se.fortnox.reactivewizard.jaxrs.params.deserializing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class LongNotNullDeserializerTest {
    private final static Deserializer<Long> DESERIALIZER = new LongNotNullDeserializer();

    @Test
    void shouldDeserialize() throws DeserializerException {
        assertThat(DESERIALIZER.deserialize("5")).isEqualTo(5L);
        assertThat(DESERIALIZER.deserialize(String.valueOf(Long.MIN_VALUE))).isEqualTo(Long.MIN_VALUE);
        assertThat(DESERIALIZER.deserialize(String.valueOf(Long.MAX_VALUE))).isEqualTo(Long.MAX_VALUE);
    }

    @Test
    void shouldThrowExceptionForNull() {
        try {
            DESERIALIZER.deserialize(null);
            fail("Expected exception, but none was thrown");
        } catch (Exception exception) {
            assertThat(exception).isInstanceOf(DeserializerException.class);
            assertThat(exception.getMessage()).isEqualTo("invalid.long");
        }
    }

    @Test
    void shouldThrowDeserializerExceptionForUnparsableStrings() {
        try {
            DESERIALIZER.deserialize("not a recognized value");
            fail("Expected exception, but none was thrown");
        } catch (Exception exception) {
            assertThat(exception).isInstanceOf(DeserializerException.class);
            assertThat(exception.getMessage()).isEqualTo("invalid.long");
        }
    }
}
