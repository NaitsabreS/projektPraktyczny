package pl.sda.racing.importer;

import org.junit.jupiter.api.Test;
import pl.sda.racing.Pigeon;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RaceDataReaderTest {
    @Test
    void shouldReadAllResultsWithoutHeader() {
//given
        String filepath = "src/test/resources/pigeon-racing.csv";
        RaceDataReader raceDataReader = new RaceDataReader(filepath);
//when
        List<ResultDTO> resultDTOList = raceDataReader.readListOfResults();
//then
        assertEquals(400, resultDTOList.size());
    }

    @Test
    void shouldReadEmptyFile() throws IOException {
        //given
        String filepath = "src/test/resources/empty-file.csv";
        RaceDataReader raceDataReader = new RaceDataReader(filepath);
        //when
        List<ResultDTO> resultDTOList = raceDataReader.readListOfResults();
        List<Pigeon> resultPigeonList = raceDataReader.getAllPigeons();
        //then
        assertTrue(resultDTOList.isEmpty());
        assertTrue(resultPigeonList.isEmpty());
    }

    @Test
    void shouldTwoPigeonsTimeBeRelated() {
        //given
        String filepath = "src/test/resources/pigeon-racing.csv";
        RaceDataReader raceDataReader = new RaceDataReader(filepath);

        //when
        List<ResultDTO> resultDTOList = raceDataReader.readListOfResults();
        ResultDTO resultOfFirstPigeon = resultDTOList.get(0);
        ResultDTO resultOfSecondPigeon = resultDTOList.get(1);
        //then
        assertEquals(Duration.ZERO, resultOfFirstPigeon.getTime());
        resultDTOList.subList(1, resultDTOList.size()).forEach(
                result -> assertNotEquals(Duration.ZERO, result.getTime())
        );
    }
}