package pl.sda.racing.importer;

import pl.sda.racing.Pigeon;
import pl.sda.racing.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;

public class RaceDataReader {

    private final String filePath;

    public RaceDataReader(String filePath) {

        this.filePath = filePath;
    }

    public List<Pigeon> getAllPigeons() throws IOException {
        LinkedList<Pigeon> pigeonLinkedList = Files.readAllLines(Paths.get(filePath)).stream()
                .map(line -> line.split(","))
                .map(this::asPigeon)
                .collect(Collector.of(() -> new LinkedList<Pigeon>(),
                        LinkedList::add,
                        (list1, list2) -> {
                            LinkedList<Pigeon> objects = new LinkedList<>();
                            objects.addAll(list1);
                            objects.addAll(list2);
                            return objects;
                        }));
        pigeonLinkedList.removeFirst();
        return pigeonLinkedList;
    }


    private Pigeon asPigeon(String[] splitLine) {
        return Pigeon.builder().birdId(splitLine[2]).name(splitLine[3]).owner(splitLine[1]).build();
    }

    public List<ResultDTO> readListOfResults() {

    }


    private ResultDTO asResult(String[] splitLine) {
        asPigeon(splitLine);
        return ResultDTO.builder().identifier(splitLine[2]).time(Duration.parse()splitLine[7]).build();
    }
}
