package ru.rdude.rpg.game.utils;

import com.badlogic.gdx.math.RandomXS128;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Functions {

    private static RandomXS128 random = new RandomXS128();

    public static int random(int to) {
        return random.nextInt(to);
    }

    public static int random(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static float random(float min, float max) {
        return (float) ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static double random(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static long random(long min, long max) {
        return random.nextLong(min - max) + min;
    }

    public static float random(float max) {
        return (float) ThreadLocalRandom.current().nextDouble(max);
    }

    public static double random(double max) {
        return ThreadLocalRandom.current().nextDouble(max);
    }

    public static float random(long max) {
        return random.nextLong(max);
    }

    public static float random(String min, String max) {
        return (float) random(Double.parseDouble(min), Double.parseDouble(max));
    }

    public static <T> T random(List<T> list) {
        if (list.size() == 1)
            return list.get(0);
        return list.get(random(0, list.size()));
    }

    public static <T> T random(Set<T> set) {
        return set.stream()
                .skip(random(set.size()))
                .findAny()
                .orElse(null);
    }

    public static <T> T random(T... values) {
        if (values.length < 1) return null;
        return values[random(0, values.length)];
    }

    public static <T> T randomMapKey(Map<T, ?> map) {
        if (map.isEmpty()) return null;
        return random(map.keySet());
    }

    public static <T> T randomMapValue(Map<?, T> map) {
        if (map.isEmpty()) return null;
        return random(map.entrySet()).getValue();
    }

    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    public static <K> K randomWithWeights(Map<K, Double> map) {
        if (map.isEmpty()) return null;
        double sum = map.values().stream().reduce(0d, Double::sum);
        double current = 0d;
        double random = random(0d, sum);
        for (Map.Entry<K, Double> entry : map.entrySet()) {
            current += entry.getValue();
            if (current >= random) return entry.getKey();
        }
        throw new IllegalArgumentException("this exception must be unreachable");
    }

    public static <T> RandomCollector<T> randomCollector() {
        return new RandomCollector<>();
    }

    public static <T> RandomCollectorList<T> randomCollector(int elementsAmount) {
        return new RandomCollectorList<>(elementsAmount);
    }

    public static long generateGuid() {
        try {
            long date = new Date().getTime();
            long randomNumber = random.nextLong();
            randomNumber = randomNumber < 0 ? randomNumber * (-1) : randomNumber;
            String string = date + String.valueOf(randomNumber).substring(12, 17);
            return Long.parseLong(string);
        } catch (StringIndexOutOfBoundsException e) {
            return generateGuid();
        }
    }

    public static String addSlashToString(String string) {
        return string.endsWith("\\") || string.endsWith("/") ? string : string + "\\";
    }

    public static String trimPath(String string) {
        return string.substring(Math.max(string.lastIndexOf("/"), string.lastIndexOf("\\")) + 1);
    }

    public static class RandomCollectorList<T> implements Collector<T, List<T>, List<T>> {

        private final int elementsCount;

        public RandomCollectorList(int elementsCount) {
            this.elementsCount = elementsCount;
        }

        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (a, b) -> {
                a.addAll(b);
                return a;
            };
        }

        @Override
        public Function<List<T>, List<T>> finisher() {
            return in -> {
                List<T> pre = new ArrayList<>(in);
                Collections.shuffle(pre);
                return pre.subList(0, elementsCount);
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(Characteristics.UNORDERED);
        }
    }

    public static class RandomCollector<T> implements Collector<T, List<T>, T> {

        @Override
        public Supplier<List<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<T>, T> accumulator() {
            return List::add;
        }

        @Override
        public BinaryOperator<List<T>> combiner() {
            return (a, b) -> {
                a.addAll(b);
                return a;
            };
        }

        @Override
        public Function<List<T>, T> finisher() {
            return Functions::random;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(Characteristics.UNORDERED);
        }

    }
}
