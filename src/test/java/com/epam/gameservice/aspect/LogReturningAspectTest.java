package com.epam.gameservice.aspect;

import com.epam.gameservice.annotation.LogReturning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AspectTestConfig.class)
@ExtendWith(OutputCaptureExtension.class)
class LogReturningAspectTest {

    @Autowired
    private Math math;

    @Test
    void shouldLogReturnValue(CapturedOutput output) {
        int result = math.sum(1, 2);

        assertThat(result).isEqualTo(3);
        assertThat(output).contains("Method: {sum} with parameters: {a=1, b=2} returned: 3");
    }

    @Component
    public static class Math {

        @LogReturning()
        public int sum(int a, int b) {
            return a + b;
        }
    }
}