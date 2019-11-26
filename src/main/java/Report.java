import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class Report {

    public static void main(String[] args) throws FileNotFoundException {
        List<Movie> movies = readFileLines("movies.txt").stream()
                .skip(1)
                .map(line -> line.split(","))
                .map(Report::getMovie)
                .collect(Collectors.toList());

        List<User> users = readFileLines("users.txt").stream()
                .skip(1)
                .map(line -> line.split(","))
                .map(Report::getUser)
                .collect(Collectors.toList());

        printReport(users, movies);

    }

    private static User getUser(String[] userLine) {
        long id = Long.parseLong(userLine[0].trim());
        String firstName = userLine[1];
        String lastName = userLine[2];
        String email = userLine[3];

        return new User(id, firstName, lastName, email);
    }

    private static Movie getMovie(String[] movieLine) {
        long id = Long.parseLong(movieLine[0].trim());
        String title = movieLine[1].trim();
        int length = Integer.parseInt(movieLine[2].trim());
        long userId = Long.parseLong(movieLine[3].trim());

        return new Movie(id, title, length, userId);
    }

    private static List<String> readFileLines(String filename) throws FileNotFoundException {
        List<String> fileLines = new ArrayList<>();

        String path = Paths.get(".").toAbsolutePath().normalize().toString();

        File file = new File(path + "\\src\\main\\resources\\" + filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            fileLines.add(scanner.nextLine());
        }
        return fileLines;
    }

    private static void printReport(List<User> users, List<Movie> movies) {
        Map<Long, List<Movie>> report = movies.stream()
                .collect(Collectors.groupingBy(Movie::getUserId));

        for (Map.Entry<Long, List<Movie>> entry : report.entrySet()) {
            Long key = entry.getKey();
            List<Movie> values = entry.getValue();
            for (User user : users) {
                if (user.getUserId() == key) {
                    System.out.println("Collection of " + user.getFirstName() + user.getLastName() + ":");
                    values.stream().forEach(movie -> System.out.println(movie.getTitle()));
                }
            }
            System.out.println();
        }
    }
}

