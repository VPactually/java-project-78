package hexlet.test;

import hexlet.code.App;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
public class AppTest {
    @Test
    public void test() {
        assertThat(App.helloWorld()).isEqualTo("Hello World!");
    }
}
