package pl.sda.racing;

import org.junit.jupiter.api.Test;
import pl.sda.racing.importer.RaceDataReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReaderFileTest {
    @Test
    void shouldAsPigeon() throws IOException {
//given
        RaceDataReader readerFile = new RaceDataReader("src/test/resources/onePigeon.csv");
//when
        List<Pigeon> pigeons = readerFile.getAllPigeons();
//then
        assertEquals(1, pigeons.size());
        Pigeon pigeon = pigeons.get(0);
        assertEquals("Kot", pigeon.getName());
        assertEquals("Texas Outlaws", pigeon.getOwner());
        assertEquals("19633-AU15-FOYS", pigeon.getBirdId());
    }
}