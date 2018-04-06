import static org.hamcrest.MatcherAssert.*; // 이 안의 static 메소드들을 자유롭게 이용가능
import static org.hamcrest.CoreMatchers.*;

import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;


public class UserDaoTest {

    private UserDao userDao;
//    private UserDao hallaUserDao;
    private DaoFactory daoFactory;

    @Before
    public void setup(){
        daoFactory = new DaoFactory();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test //실행
    public void get() throws SQLException, ClassNotFoundException{ // 예외처리를 잘 몰라 알아서 하게끔 throw 시킴
        int id = 1;
        User user = userDao.get(id);
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("이오주"));
        assertThat(user.getPassword(), is("1234"));
    }
    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("hulk");
        user.setPassword("1111");
        Integer id =  userDao.insert(user);

        User insertedUser = userDao.get(id);
        assertThat(insertedUser.getId(), is(id));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

//    @Test //실행
//    public void hallaget() throws SQLException, ClassNotFoundException{ // 예외처리를 잘 몰라 알아서 하게끔 throw 시킴
//        int id = 1;
//        User user = hallaUserDao.get(id);
//        assertThat(user.getId(), is(1));
//        assertThat(user.getName(), is("이오주"));
//        assertThat(user.getPassword(), is("1234"));
//    }
//    @Test
//    public void hallaadd() throws SQLException, ClassNotFoundException {
//        User user = new User();
//        user.setName("hulk");
//        user.setPassword("1111");
//        Integer id =  hallaUserDao.insert(user);
//
//        User insertedUser = hallaUserDao.get(id);
//        assertThat(insertedUser.getId(), is(id));
//        assertThat(insertedUser.getName(), is(user.getName()));
//        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//    }

}
