package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Objects;

public class JpaMain {

    public static void main(String[] args) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.query();
        // persistence.xml name을 인자로 넘김
        // 팩토리는 한 번만 실행
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드간 공유 x
        EntityManager em = emf.createEntityManager();
        // JPA의 모든 변경은 트랜잭션 내에서 실행해야 함
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
 /*           Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");*/

            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();

            System.out.println("===========================");
            // 트랜잭션 단위 커밋
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
