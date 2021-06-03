package yeahbutstill.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yeahbutstill.database.entity.Comment;
import yeahbutstill.database.repository.CommentRepository;
import yeahbutstill.database.repository.CommentRepositoryImpl;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("dani@test.com", "masih ada min buahnya?");
        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(48008);
        Assertions.assertNotNull(comment);
        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment notFound = commentRepository.findById(1_000_000);
        Assertions.assertNull(notFound);
    }

    @Test
    void testFindAll() {
        List<Comment> comment = commentRepository.findAll();
        System.out.println(comment.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> comments = commentRepository.findByEmail("dani@test.com");
        System.out.println(comments.size());
    }
}
