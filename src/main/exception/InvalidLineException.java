package main.exception;

public class InvalidLineException extends Exception{
    private final String csvProblem;


    public InvalidLineException(String csvProblem, final String massage){
        super(massage);
        this.csvProblem = csvProblem;
    }

    @Override
    public String toString() {
        return "Invalid line in csv file: {"
                + csvProblem + "}";
    }
}
