package shapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test 
    public void squareGetX() {
        Square square = new Square();
        assertEquals(20, square.getX());
        square.tick();
        assertEquals(21, square.getX());
    }
}
