package com.gurukulams.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gurukulams.core.model.Board;
import com.gurukulams.core.model.Book;
import com.gurukulams.core.model.Grade;
import com.gurukulams.core.model.Question;
import com.gurukulams.core.model.QuestionType;
import com.gurukulams.core.model.Subject;
import com.gurukulams.core.service.BoardService;
import com.gurukulams.core.service.BookService;
import com.gurukulams.core.service.GradeService;
import com.gurukulams.core.service.QuestionService;
import com.gurukulams.core.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BoardMaker {

    /**
     * Board Service.
     */
    @Autowired
    private BoardService boardService;
    /**
     * Grade Service.
     */
    @Autowired
    private GradeService gradeService;
    /**
     * Subjec Service.
     */
    @Autowired
    private SubjectService subjectService;
    /**
     * Book Service.
     */
    @Autowired
    private BookService bookService;

    /**
     * Question Service.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Json Mapper.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Seed Folder.
     */
    @Value("${app.seed.folder:src/main/resources/data}")
    private String seedFolder;

    /**
     * Create All Boards.
     * @param userName
     */
    public void createAllBoards(final String userName) {

        if (seedFolder != null) {
            boardService.deleteAll();

            List<File> boardFiles = List.of(
                    Objects.requireNonNull(new File(seedFolder, "boards")
                            .listFiles((dir, name)
                                    -> name.endsWith(".json")
                                    && !name.contains("-"))));

            for (File boardFile : boardFiles) {
                Board createdBoard = createBoard(userName, boardFile);
                List<Grade> grades =
                        createGrades(userName, boardFile, createdBoard);
                List<Subject> subjects =
                        createSubjects(userName, boardFile, createdBoard);
                List<Book> books =
                        createBooks(userName, boardFile, createdBoard);

                grades.forEach(grade -> {


                    boardService.attachGrade(userName,
                            createdBoard.id(), grade.id());

                    subjects.forEach(subject -> {
                        boardService.attachSubject(userName,
                                createdBoard.id(), grade.id(),
                                subject.id());

                        books.forEach(book -> {
                            if (book.title().toLowerCase().contains(
                                    subject.title().toLowerCase())) {
                                boardService.attachBook(userName,
                                        createdBoard.id(), grade.id(),
                                        subject.id(), book.id());
                            }
                        });

                    });

                });

            }
        }


    }

    private Board createBoard(final String userName,
                              final File boardFile) {
        final Board board = getObject(boardFile, Board.class);

        final String nameOfBoard = boardFile.getName()
                .replaceFirst(".json", "");

        final Board createdBoard = boardService.create(userName,
                null, board);

        List<File> boardLocalizedFiles = List.of(
                Objects.requireNonNull(boardFile.getParentFile()
                        .listFiles((dir, name) -> name.endsWith(".json")
                                && name.contains(nameOfBoard + "-"))));

        boardLocalizedFiles.forEach(boardLocalizedFile -> {
            Locale locale = new Locale(boardLocalizedFile.getName()
                    .replaceFirst(nameOfBoard + "-", "")
                    .replaceFirst(".json", ""));
            final Board boardLocalized =
                    getObject(boardLocalizedFile, Board.class);
            boardService.update(createdBoard.id(),
                    userName, locale, boardLocalized);
        });



        return createdBoard;
    }

    private List<Grade> createGrades(final String userName,
                             final File boardFile,
                             final Board createdBoard) {
        List<Grade> grades = new ArrayList<>();
        List<File> gradeFiles = List.of(
                Objects.requireNonNull(
                        new File(boardFile.getParentFile(), "grades")
                        .listFiles((dir, name) -> name.endsWith(".json")
                                && !name.contains("-"))));

        gradeFiles.forEach(gradeFile -> {
            grades.add(createGrade(userName, boardFile,
                    createdBoard, gradeFile));
        });
        return grades;
    }

    private Grade createGrade(final String userName,
                              final File boardFile,
                              final Board createdBoard,
                              final File gradeFile) {
        final Grade grade = getObject(gradeFile, Grade.class);

        final String nameOfGrade = gradeFile.getName()
                .replaceFirst(".json", "");

        final Grade createdGrade = gradeService.create(userName,
                null, grade);

        List<File> gradeLocalizedFiles = List.of(
                Objects.requireNonNull(gradeFile.getParentFile()
                        .listFiles((dir, name) -> name.endsWith(".json")
                                && name.contains(nameOfGrade + "-"))));

        gradeLocalizedFiles.forEach(gradeLocalizedFile -> {
            Locale locale = new Locale(gradeLocalizedFile.getName()
                    .replaceFirst(nameOfGrade + "-", "")
                    .replaceFirst(".json", ""));
            final Grade gradeLocalized =
                    getObject(gradeLocalizedFile, Grade.class);
            gradeService.update(createdGrade.id(),
                    userName, locale, gradeLocalized);
        });



        return createdGrade;
    }



    private List<Subject> createSubjects(final String userName,
                                final File boardFile,
                                final Board createdBoard) {
        List<Subject> subjects = new ArrayList<>();
        List<File> subjectFiles = List.of(
                Objects.requireNonNull(
                        new File(boardFile.getParentFile(), "subjects")
                        .listFiles((dir, name) -> name.endsWith(".json")
                                && !name.contains("-"))));

        subjectFiles.forEach(subjectFile -> {
            subjects.add(createSubject(userName, createdBoard, subjectFile));
        });
        return subjects;
    }

    private Subject createSubject(final String userName,
                                  final Board createdBoard,
                                  final File subjectFile) {
        final Subject subject = getObject(subjectFile, Subject.class);

        final String nameOfSubject = subjectFile.getName()
                .replaceFirst(".json", "");

        final Subject createdSubject =
                subjectService.create(userName, null, subject);

        List<File> subjectLocalizedFiles = List.of(
                Objects.requireNonNull(subjectFile.getParentFile()
                        .listFiles((dir, name) -> name.endsWith(".json")
                                && name.contains(nameOfSubject + "-"))));

        subjectLocalizedFiles.forEach(subjectLocalizedFile -> {
            Locale locale = new Locale(subjectLocalizedFile.getName()
                    .replaceFirst(nameOfSubject + "-", "")
                    .replaceFirst(".json", ""));
            final Subject subjectLocalized =
                    getObject(subjectLocalizedFile, Subject.class);
            subjectService.update(createdSubject.id(),
                    userName, locale, subjectLocalized);
        });

        return createdSubject;
    }

    private List<Book> createBooks(final String userName,
                             final File boardFile,
                             final Board createdBoard) {
        List<Book> books = new ArrayList<>();
        List<File> bookFiles = List.of(
                Objects.requireNonNull(
                        new File(boardFile.getParentFile(), "books")
                        .listFiles((dir, name) -> name.endsWith(".json")
                                && !name.contains("-"))));

        bookFiles.forEach(bookFile -> {
            books.add(createBook(userName, createdBoard, bookFile));
        });
        return books;
    }

    private Book createBook(final String userName,
                            final Board createdBoard,
                            final File bookFile) {
        final Book book = getObject(bookFile, Book.class);

        final String nameOfBook = bookFile.getName()
                .replaceFirst(".json", "");

        final Book createdBook = bookService.create(userName, null, book);

        List<File> bookLocalizedFiles = List.of(
                Objects.requireNonNull(bookFile.getParentFile()
                        .listFiles((dir, name) -> name.endsWith(".json")
                                && name.contains(nameOfBook + "-"))));

        bookLocalizedFiles.forEach(bookLocalizedFile -> {
            Locale locale = new Locale(bookLocalizedFile.getName()
                    .replaceFirst(nameOfBook + "-", "")
                    .replaceFirst(".json", ""));
            final Book bookLocalized = getObject(bookLocalizedFile, Book.class);
            bookService.update(createdBook.id(),
                    userName, locale, bookLocalized);
        });

        return createdBook;
    }

    /**
     * Create All Questions.
     * @param userName
     */
    public void createAllQuestions(final String userName) throws IOException {

        if (seedFolder != null) {
            questionService.delete();
            File questionsFolder = new File(seedFolder, "questions");
            Files.find(Path.of(questionsFolder.getPath()),
                            Integer.MAX_VALUE,
                            (filePath, fileAttr)
                            -> fileAttr.isRegularFile()
                            && !filePath.toFile().getName().contains("-"))
                    .forEach(path -> {
                        try {
                            createQuestion(userName, path.toFile());
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

    }

    private Question createQuestion(final String userName,
                               final File questionFile)
            throws JsonProcessingException {
        Question question = getObject(questionFile, Question.class);
        final String nameOfQuestion = questionFile.getName()
                .replaceFirst(".json", "");
        String thePath = questionFile.getPath().split("/questions/")[1];
        List<String> tokens = new ArrayList<>(List.of(thePath.split("/")));
        String bookName = tokens.remove(0);
        tokens.remove(tokens.size() - 1);
        String chapterPath = tokens.stream().collect(Collectors.joining("/"));
        Question createdQuestion = questionService.create(
                bookName, QuestionType.CHOOSE_THE_BEST,
                null, question, userName, chapterPath).get();

        List<File> questionLocalizedFiles = List.of(
                Objects.requireNonNull(questionFile.getParentFile()
                        .listFiles((dir, name) -> name.endsWith(".json")
                                && name.contains(nameOfQuestion + "-"))));

        questionLocalizedFiles.forEach(questionLocalizedFile -> {
            Locale locale = new Locale(questionLocalizedFile.getName()
                    .replaceFirst(nameOfQuestion + "-", "")
                    .replaceFirst(".json", ""));
            final Question questionLocalized =
                    getObject(questionLocalizedFile, Question.class);
            questionLocalized.setId(createdQuestion.getId());
            for (int i = 0; i < createdQuestion.getChoices().size(); i++) {
                questionLocalized.getChoices().get(i)
                        .setId(createdQuestion.getChoices().get(i).getId());
            }
            try {
                questionService.update(
                        bookName, QuestionType.CHOOSE_THE_BEST,
                        createdQuestion.getId(), locale, questionLocalized,
                        chapterPath).get();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        return createdQuestion;
    }


    private <T> T getObject(final File jsonFile, final Class<T> type) {
        T t;
        try {
            t = objectMapper.readValue(jsonFile, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}
