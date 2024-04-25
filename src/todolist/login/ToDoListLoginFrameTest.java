package todolist.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import todolist.login.dao.ToDoListLoginDao;

public class ToDoListLoginFrameTest {

    @Test
    public void testLoginWithCorrectCredentials() {
        // Given
        String validId = "testUser";
        String validPassword = "testPassword";

        // Mocking the ToDoListLoginDao
        ToDoListLoginDao loginDaoMock = mock(ToDoListLoginDao.class);
        when(loginDaoMock.login(validId, validPassword)).thenReturn(true);

        // Creating the login frame
        ToDoListLoginFrame loginFrame = new ToDoListLoginFrame();
        loginFrame.setVisible(false); // Set to invisible for testing
        loginFrame.setLoginDao(loginDaoMock); // Injecting the mock loginDao

        // When
        loginFrame.getT1().setText(validId);
        loginFrame.getT2().setText(validPassword);
        loginFrame.loginCheck();

        // Then
        assertTrue(loginFrame.loginSuccessFlag, "로그인이 성공해야 함");
    }

    @Test
    public void testLoginWithIncorrectCredentials() {
        // Given
        String invalidId = "invalidUser";
        String invalidPassword = "invalidPassword";

        // Mocking the ToDoListLoginDao
        ToDoListLoginDao loginDaoMock = mock(ToDoListLoginDao.class);
        when(loginDaoMock.login(invalidId, invalidPassword)).thenReturn(false);

        // Creating the login frame
        ToDoListLoginFrame loginFrame = new ToDoListLoginFrame();
        loginFrame.setVisible(false); // Set to invisible for testing
        loginFrame.setLoginDao(loginDaoMock); // Injecting the mock loginDao

        // When
        loginFrame.getT1().setText(invalidId);
        loginFrame.getT2().setText(invalidPassword);
        loginFrame.loginCheck();

        // Then
        assertFalse(loginFrame.loginSuccessFlag, "로그인이 실패해야 함");
    }
}