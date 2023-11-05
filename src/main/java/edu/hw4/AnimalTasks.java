package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimalTasks {
    private AnimalTasks() {

    }

    public static List<Animal> sortedByHeight(Collection<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::height)).toList();
    }

    public static List<Animal> sortedKthByWeight(Collection<Animal> animals, int k) {
        Comparator<Animal> comp = Comparator.comparing(Animal::weight).reversed();
        return animals.stream().sorted(comp).limit(k).toList();
    }

    public static Map<Animal.Type, Integer> typeCounts(Collection<Animal> animals) {
        var countCollector = Collectors.collectingAndThen(Collectors.counting(), Long::intValue);
        return animals.stream().collect(Collectors.groupingBy(Animal::type, countCollector));
    }

    public static Animal getWithLongestName(Collection<Animal> animals) {
        return animals.stream().max(Comparator.comparing(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex mostFrequentSex(Collection<Animal> animals) {
        Map<Animal.Sex, Long> stc = animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        return stc.entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getKey).orElse(null);
    }

    public static Map<Animal.Type, Animal> heaviestByType(Collection<Animal> animals) {
        Comparator<Animal> compByWeight = Comparator.comparing(Animal::weight);
        return animals.stream()
            .collect(Collectors.toMap(Animal::type, Function.identity(), BinaryOperator.maxBy(compByWeight)));
    }

    public static Animal kthOldest(Collection<Animal> animals, int k) {
        Comparator<Animal> compByAgeReversed = Comparator.comparing(Animal::age).reversed();
        return animals.stream().sorted(compByAgeReversed).skip(k - 1).findFirst().orElse(null);
    }

    public static Optional<Animal> heaviestUnderKCentimeters(Collection<Animal> animals, int k) {
        return animals.stream().filter(a -> a.height() < k).max(Comparator.comparing(Animal::weight));
    }

    public static Integer pawsTotal(Collection<Animal> animals) {
        return animals.stream().map(Animal::paws).reduce(0, Integer::sum);
    }

    public static List<Animal> pawsAndAgeDiffer(Collection<Animal> animals) {
        return animals.stream().filter(a -> a.age() != a.paws()).toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> bitesAndTall(Collection<Animal> animals) {
        return animals.stream().filter(a -> a.bites() && a.height() > 100).toList();
    }

    public static Integer countWeightGTHeight(Collection<Animal> animals) {
        return (int) (animals.stream().filter(a -> a.weight() > a.height()).count());
    }

    public static List<Animal> namesMoreThanTwoWords(Collection<Animal> animals) {
        return animals.stream().filter(a -> a.name().split(" ").length > 2).toList();
    }

    public static Boolean containsDogTallerThanK(Collection<Animal> animals, int k) {
        return animals.stream().anyMatch(a -> a.type() == Animal.Type.DOG && a.height() > k);
    }

    public static Map<Animal.Type, Integer> weightTotalByTypeConstrained(Collection<Animal> animals, int k, int l) {
        var sumCollector = Collectors.summingInt(Animal::weight);
        return animals.stream().filter(a -> a.weight() >= k && a.weight() <= l)
            .collect(Collectors.groupingBy(Animal::type, sumCollector));
    }

    public static List<Animal> sortedTypeSexName(Collection<Animal> animals) {
        Comparator<Animal> comp =
            Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name);
        return animals.stream().sorted(comp).toList();
    }

    public static Boolean spidersBiteMoreThanDogs(Collection<Animal> animals) {
        Map<Animal.Type, Integer> mapTypes = Map.of(
            Animal.Type.SPIDER, 1,
            Animal.Type.DOG, -1
        );
        boolean enoughInfo =
            animals.stream().anyMatch(a -> a.type() == Animal.Type.SPIDER)
                && animals.stream().anyMatch(a -> a.type() == Animal.Type.DOG);
        return enoughInfo && animals.stream().filter(Animal::bites).map(a -> mapTypes.getOrDefault(a.type(), 0))
            .reduce(0, Integer::sum) > 0;
    }

    public static Animal heaviestFish(Collection<Animal>... cols) {
        Stream<Animal> combined = Stream.of();
        for (Collection<Animal> col : cols) {
            combined = Stream.concat(combined, col.stream());
        }
        return combined.filter(a -> a.type() == Animal.Type.FISH).max(Comparator.comparing(Animal::weight))
            .orElse(null);
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static Map<String, Set<ValidationError>> incorrectlySpecified(Collection<Animal> animals) {
        Map<String, Set<ValidationError>> res = new HashMap<>();
        for (Animal a : animals) {
            Set<ValidationError> curSet = new HashSet<>();
            switch (a.name()) {
                case null -> curSet.add(new ValidationError("name", "is null"));
                case "" -> curSet.add(new ValidationError("name", "is empty"));
                default -> {
                }
            }
            if (a.type() == null) {
                curSet.add(new ValidationError("type", "is null"));
            }
            if (a.sex() == null) {
                curSet.add(new ValidationError("sex", "is null"));
            }
            if (a.age() < 0) {
                curSet.add(new ValidationError("age", "is negative"));
            }
            if (a.height() <= 0) {
                curSet.add(new ValidationError("height", "is not positive"));
            }
            if (a.weight() <= 0) {
                curSet.add(new ValidationError("weight", "is not positive"));
            }
            if (a.name() != null && !curSet.isEmpty()) {
                res.put(a.name(), curSet);
            }
        }
        return res;
    }

    public static Map<String, String> incorrectlySpecifiedPretty(Collection<Animal> animals) {
        Map<String, Set<ValidationError>> res = incorrectlySpecified(animals);
        return res.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream().map(ValidationError::field).collect(Collectors.joining(", "))
            ));
    }
}
