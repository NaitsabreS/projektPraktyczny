package pl.sda.racing.importer;

import pl.sda.racing.Pigeon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RaceDataReader {

    private final String filePath;

    public RaceDataReader(String filePath) {

        this.filePath = filePath;
    }

    public List<Pigeon> getAllPigeons() throws IOException {
        return getListWithoutFirstRow(this::asPigeon);
    }

    private List<String> readFileAsLines() throws IOException {
        List<String> stringLinkedList = Files.readAllLines(Paths.get(filePath));
        return stringLinkedList;
    }
    private List<String> withoutFirstLine(List<String> stringLinkedList) {
        if (stringLinkedList.isEmpty()) {
            return Collections.emptyList();
        }
        return stringLinkedList.subList(1, stringLinkedList.size());
    }
    private <T> List<T> getListWithoutFirstRow(Function<String[], T> function) throws IOException {
    List<T> tLinkedList = withoutFirstLine(readFileAsLines())
            .stream()
            .map(line -> line.split(","))
            .map(function)
            .collect(Collectors.toList());
    return tLinkedList;
}
    private Pigeon asPigeon(String[] splitLine) {
        return Pigeon.builder()
                .birdId(splitLine[2])
                .name(splitLine[3])
                .owner(splitLine[1])
                .build();
    }

    public List<ResultDTO> readListOfResults() throws IOException {
        return getListWithoutFirstRow(this::asResult);
    }

    private Duration parseStringIntoDuration(String splitline) {
        LocalTime splitLineParsedIntoLocalTime = LocalTime.parse(splitline, DateTimeFormatter.ofPattern("H:mm:ss"));
        return Duration.between(LocalTime.MIN, splitLineParsedIntoLocalTime);
    }

    private ResultDTO asResult(String[] splitLine) {
        return ResultDTO.builder()
                .identifier(splitLine[2])
                .time(parseStringIntoDuration(splitLine[9]))
                .build();
    }
}
