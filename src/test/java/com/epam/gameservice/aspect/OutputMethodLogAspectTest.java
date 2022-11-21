package com.epam.gameservice.aspect;

import com.epam.gameservice.annotation.OutputMethodLog;
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
class OutputMethodLogAspectTest {

    @Autowired
    private Math math;

    @Test
    void shouldLogReturnedValue(CapturedOutput output) {
        int result = math.sum(1, 2);

        assertThat(result).isEqualTo(3);
        assertThat(output)
                .contains("Method: 'sum' returned: 3");
    }

    @Component
    public static class Math {

        @OutputMethodLog
        public int sum(int a, int b) {
            return a + b;
        }
    }
}