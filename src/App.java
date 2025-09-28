import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        // Test the topKFrequent function
        String[] words = { "java", "python", "java", "golang", "java", "python" };
        int k = 2;

        List<String> result = topKFrequent(words, k);
        System.out.println("Input: words = " + Arrays.toString(words) + ", k = " + k);
        System.out.println("Output: " + result);

        System.out.println();

        // Test JSON validation
        Map<String, Object> validJson = Map.of("user", Map.of("name", "A", "age", 30));

        // Invalid: non-string key
        Map<Integer, Object> invalidJson = Map.of(123, "invalid key");

        // Valid nested structure
        List<Object> validList = Arrays.asList("java", 123, true, null);

        System.out.println("Valid JSON object: " + isValidJson(validJson));
        System.out.println("Invalid JSON (non-string key): " + isValidJson(invalidJson));
        System.out.println("Valid JSON array: " + isValidJson(validList));

        System.out.println();

        // Test Mahasiswa class
        Mahasiswa student = new Mahasiswa();
        student.setData("John Doe", "12345", 85.5);
        System.out.println("Student: " + student.nama + ", NIM: " + student.nim + ", Nilai: " + student.nilai);
        System.out.println("Status: " + (student.isLulus() ? "Lulus" : "Tidak Lulus"));

        System.out.println();

        // Test countWords function
        String text = "This is a test. This is only a test";
        Map<String, Integer> wordCount = countWords(text);
        System.out.println("Text: \"" + text + "\"");
        System.out.println("Word count: " + wordCount);
    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();

        // Count frequencies
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        // Sort by frequency (desc) then alphabetically (asc)
        return freq.entrySet().stream()
                .sorted((a, b) -> {
                    int freqCompare = b.getValue().compareTo(a.getValue());
                    return freqCompare != 0 ? freqCompare : a.getKey().compareTo(b.getKey());
                })
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static boolean isValidJson(Object input) {
        if (input == null)
            return true;

        if (input instanceof String || input instanceof Number || input instanceof Boolean) {
            return true;
        }

        if (input instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) input;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                // Key must be String
                if (!(entry.getKey() instanceof String)) {
                    return false;
                }
                // Recursively validate value
                if (!isValidJson(entry.getValue())) {
                    return false;
                }
            }
            return true;
        }

        if (input instanceof List) {
            List<?> list = (List<?>) input;
            for (Object item : list) {
                if (!isValidJson(item)) {
                    return false;
                }
            }
            return true;
        }

        return false; // Invalid type
    }

    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCount = new HashMap<>();

        // Split by whitespace and punctuation, convert to lowercase
        String[] words = text.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        return wordCount;
    }
}
