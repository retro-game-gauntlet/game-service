package com.epam.gameservice.aspect;

import com.epam.gameservice.annotation.InputMethodLog;
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
class InputMethodLogAspectTest {

    @Autowired
    private Math math;

    @Test
    void shouldLogInputParameters(CapturedOutput output) {
        int result = math.sum(1, 2);

        assertThat(result).isEqualTo(3);
        assertThat(output)
                .contains("Method: 'sum' was called with parameters: a=1, b=2");
    }

    @Component
    public static class Math {

        @InputMethodLog
        public int sum(int a, int b) {
            return a + b;
        }
    }
}