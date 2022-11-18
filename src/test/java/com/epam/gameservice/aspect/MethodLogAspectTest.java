package com.epam.gameservice.aspect;

import com.epam.gameservice.annotation.MethodLog;
import com.epam.gameservice.tags.Spring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Spring
@SpringBootTest(classes = AspectTestConfig.class)
@ExtendWith(OutputCaptureExtension.class)
class MethodLogAspectTest {

    @Autowired
    private Math math;

    @Test
    void shouldLogReturnValueWithParameters(CapturedOutput output) {
        int result = math.fullLog(1, 2);

        assertThat(result).isEqualTo(3);
        assertThat(output)
                .contains("Method: 'fullLog' was called with parameters: a=1, b=2")
                .contains("Method: 'fullLog' returned: 3");
    }

    @Test
    void shouldLogParameters(CapturedOutput output) {
        int result = math.inputLog(1, 2);

        assertThat(result).isEqualTo(3);
        assertThat(output)
                .contains("Method: 'inputLog' was called with parameters: a=1, b=2")
                .doesNotContain("Method: 'inputLog' returned: 3");
    }

    @Test
    void shouldLogReturnedValue(CapturedOutput output) {
        int result = math.outputLog(1, 2);

        assertThat(result).isEqualTo(3);
        assertThat(output)
                .contains("Method: 'outputLog' returned: 3")
                .doesNotContain("Method: 'outputLog' was called with parameters: a=1, b=2");
    }

    @Component
    public static class Math {

        @MethodLog
        public int fullLog(int a, int b) {
            return a + b;
        }

        @MethodLog(logOutput = false)
        public int inputLog(int a, int b) {
            return a + b;
        }

        @MethodLog(logInput = false)
        public int outputLog(int a, int b) {
            return a + b;
        }
    }
}