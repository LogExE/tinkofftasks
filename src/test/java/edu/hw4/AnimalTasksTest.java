package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTasksTest {
    @Test
    void testSortByHeight() {
        List<Animal> given = List.of(
            new Animal("Arkadii", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Meepo", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true),
            new Animal("Shlyopa", Animal.Type.CAT, Animal.Sex.M, 3, 80, 12, true)
        );
        List<Animal> expected = List.of(
            new Animal("Arkadii", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Shlyopa", Animal.Type.CAT, Animal.Sex.M, 3, 80, 12, true),
            new Animal("Meepo", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true)
        );
        assertEquals(expected, AnimalTasks.sortedByHeight(given));
    }

    @Test
    void testSortedKthByWeights() {
        List<Animal> given = List.of(
            new Animal("Arkadii", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Meepo", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true),
            new Animal("Shlyopa", Animal.Type.CAT, Animal.Sex.M, 3, 80, 12, true)
        );
        List<Animal> expected = List.of(
            new Animal("Shlyopa", Animal.Type.CAT, Animal.Sex.M, 3, 80, 12, true),
            new Animal("Meepo", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true)
        );
        assertEquals(expected, AnimalTasks.sortedKthByWeight(given, 2));
    }

    @Test
    void testTypeCounts() {
        List<Animal> given = List.of(
            new Animal("Shlyopa", Animal.Type.CAT, Animal.Sex.M, 3, 80, 12, true),
            new Animal("Arkadii", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Meepo", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true),
            new Animal("Vasya", Animal.Type.CAT, Animal.Sex.M, 2, 76, 10, false)
        );
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.SPIDER, 1,
            Animal.Type.DOG, 1,
            Animal.Type.CAT, 2
        );
        assertEquals(expected, AnimalTasks.typeCounts(given));
    }

    @Test
    void testLongestName() {
        List<Animal> given = List.of(
            new Animal("Meepo", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 4, 1, false),
            new Animal("Arkadii", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Vasya", Animal.Type.CAT, Animal.Sex.M, 2, 76, 10, false)
        );
        Animal expected = given.get(2);
        assertEquals(expected, AnimalTasks.getWithLongestName(given));
    }

    @Test
    void testMostFrequentSex() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Shurik", Animal.Type.BIRD, Animal.Sex.F, 2, 5, 1, false),
            new Animal("Musya", Animal.Type.CAT, Animal.Sex.F, 1, 25, 8, false)
        );
        assertEquals(Animal.Sex.F, AnimalTasks.mostFrequentSex(given));
    }

    @Test
    void testHeaviestByType() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Zhuchka", Animal.Type.DOG, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false)
        );
        Map<Animal.Type, Animal> expected = Map.of(
            Animal.Type.SPIDER, given.get(0),
            Animal.Type.DOG, given.get(3)
        );
        assertEquals(expected, AnimalTasks.heaviestByType(given));
    }

    @Test
    void testKthOldest() {
        List<Animal> given = List.of(
            new Animal("Shlyopa", Animal.Type.CAT, Animal.Sex.M, 3, 80, 12, true),
            new Animal("Meepo", Animal.Type.DOG, Animal.Sex.M, 2, 85, 10, true),
            new Animal("Arkadii", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Vasya", Animal.Type.CAT, Animal.Sex.M, 2, 76, 10, false)
        );
        Animal expected = given.get(0);
        assertEquals(expected, AnimalTasks.kthOldest(given, 2));
    }

    @Test
    void testHeaviestUnderK() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Zhuchka", Animal.Type.DOG, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false)
        );
        Optional<Animal> expected = Optional.of(given.get(1));
        assertEquals(expected, AnimalTasks.heaviestUnderKCentimeters(given, 100));
    }

    @Test
    void testTotalPaws() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 4, 1, false)
        );
        assertEquals(24, AnimalTasks.pawsTotal(given));
    }

    @Test
    void testPawsAgeDiffer() {
        List<Animal> given = List.of(
            new Animal("test1", Animal.Type.CAT, Animal.Sex.M, 4, 60, 10, false),
            new Animal("test2", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, true),
            new Animal("test3", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false)
        );
        assertEquals(1, AnimalTasks.pawsAndAgeDiffer(given).size());
    }

    @Test
    void testBiteAndTall() {
        List<Animal> given = List.of(
            new Animal("Meepo", Animal.Type.DOG, Animal.Sex.M, 2, 123, 15, true),
            new Animal("Arkadii", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true),
            new Animal("Vitalii", Animal.Type.DOG, Animal.Sex.M, 4, 125, 16, true),
            new Animal("Vasily", Animal.Type.DOG, Animal.Sex.M, 3, 130, 10, false)
        );
        assertEquals(2, AnimalTasks.bitesAndTall(given).size());
    }

    @Test
    void testCountWeightGTHeight() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 4, 1, false)
        );
        assertEquals(1, AnimalTasks.countWeightGTHeight(given));
    }

    @Test
    void testNamesTwoWords() {
        List<Animal> given = List.of(
            new Animal("Real Pavuk Akakii", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik Chayka", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Terentyev Sidor Pavlovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo Ryba Iz Multikov", Animal.Type.FISH, Animal.Sex.M, 3, 4, 1, false)
        );
        assertEquals(3, AnimalTasks.namesMoreThanTwoWords(given).size());
    }

    @Test
    void testTallDogs() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 4, 1, false)
        );
        assertTrue(AnimalTasks.containsDogTallerThanK(given, 119));
    }

    @Test
    void testSumWeightsByType() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo Fake", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 1, false)
        );
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.SPIDER, 3,
            Animal.Type.DOG, 20
        );
        assertEquals(expected, AnimalTasks.weightTotalByTypeConstrained(given, 3, 3));
    }

    @Test
    void testSortedTSN() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo Fake", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 1, false)
        );
        List<Animal> expected = List.of(
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo Fake", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 1, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false)
        );
        assertEquals(expected, AnimalTasks.sortedTypeSexName(given));
    }

    @Test
    void testSpidersDogsCompetitionNotEnoughInfo() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, false),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo Fake", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 1, false)
        );
        assertFalse(AnimalTasks.spidersBiteMoreThanDogs(given));
    }

    @Test
    void testSpidersDogsCompetitionGood() {
        List<Animal> given = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, true),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Sidorovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, true),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, true),
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false)
        );
        assertTrue(AnimalTasks.spidersBiteMoreThanDogs(given));
    }

    @Test
    void testHeaviestFish() {
        List<Animal> given1 = List.of(
            new Animal("Pavuk", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 2, true),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Sidorovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, true),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, 2, 5, 1, true)
        );
        List<Animal> given2 = List.of(
            new Animal("test1", Animal.Type.CAT, Animal.Sex.M, 4, 60, 10, false),
            new Animal("test2", Animal.Type.FISH, Animal.Sex.M, 1, 5, 3, true),
            new Animal("test3", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false)
        );
        List<Animal> given3 = List.of(
            new Animal("Petrovich", Animal.Type.DOG, Animal.Sex.M, 3, 120, 20, false),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 4, 1, false)
        );
        assertEquals("test2", AnimalTasks.heaviestFish(given1, given2, given3).name());
    }

    @Test
    void testIncorrectAnimals() {
        List<Animal> given = List.of(
            new Animal("", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, -2, true),
            new Animal("Cat", null, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Sidorovich", Animal.Type.DOG, null, 3, 0, 20, true),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, -1, 5, 1, true)
        );
        Map<String, Set<ValidationError>> expected = Map.of(
            "", Set.of(
                new ValidationError("name", "is empty"),
                new ValidationError("weight", "is not positive")
            ),
            "Cat", Set.of(
                new ValidationError("type", "is null")
            ),
            "Sidorovich", Set.of(
                new ValidationError("sex", "is null"),
                new ValidationError("height", "is not positive")
            ),
            "Shurik", Set.of(
                new ValidationError("age", "is negative")
            )
        );
        assertEquals(expected, AnimalTasks.incorrectlySpecified(given));
    }

    // вообще тут не очень получается тестить т.к. в сете нет порядка
    // => строки с числом ошибок > 1 получаются не всегда те
    @Test
    void testIncorrectAnimalsPretty() {
        List<Animal> given = List.of(
            new Animal("", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, -2, true),
            new Animal("Cat", null, Animal.Sex.F, 2, 50, 8, false),
            new Animal("Sidorovich", Animal.Type.DOG, null, 3, 0, 20, true),
            new Animal("Shurik", Animal.Type.SPIDER, Animal.Sex.M, -1, 5, 1, true)
        );
        Map<String, String> expected = Map.of(
            "", "weight, name",
            "Cat", "type",
            "Sidorovich", "height, sex",
            "Shurik", "age"
        );
        assertEquals(expected, AnimalTasks.incorrectlySpecifiedPretty(given));
    }
}
