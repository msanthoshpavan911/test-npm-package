package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService("alice", "alice@example.com", 30);
    }

    // --- constructor ---

    @Test
    void constructor_setsUserName() {
        assertEquals("alice", userService.getUserName());
    }

    @Test
    void constructor_differentValues() {
        UserService svc = new UserService("bob", "bob@example.com", 25);
        assertEquals("bob", svc.getUserName());
    }

    // --- getUserName ---

    @Test
    void getUserName_returnsConstructorValue() {
        UserService svc = new UserService("carol", "carol@example.com", 22);
        assertEquals("carol", svc.getUserName());
    }

    // --- processUsers ---

    @Test
    void processUsers_emptyList_doesNothing() {
        assertDoesNotThrow(() -> userService.processUsers(Collections.emptyList()));
    }

    @Test
    void processUsers_singleNullEntry_takesNullBranch() {
        List<String> users = Arrays.asList((String) null);
        assertDoesNotThrow(() -> userService.processUsers(users));
    }

    @Test
    void processUsers_validEntries_takesElseBranch() {
        List<String> users = Arrays.asList("alice", "bob");
        assertDoesNotThrow(() -> userService.processUsers(users));
    }

    @Test
    void processUsers_mixedNullAndValid_coversBothBranches() {
        List<String> users = Arrays.asList("alice", null, "bob");
        assertDoesNotThrow(() -> userService.processUsers(users));
    }

    // --- calculateScore ---

    @Test
    void calculateScore_sumsAllFourParams() {
        assertEquals(10, userService.calculateScore(1, 2, 3, 4));
    }

    @Test
    void calculateScore_allZeros_returnsZero() {
        assertEquals(0, userService.calculateScore(0, 0, 0, 0));
    }

    @Test
    void calculateScore_negativeValues() {
        assertEquals(-10, userService.calculateScore(-1, -2, -3, -4));
    }

    // --- isBlank ---

    @Test
    void isBlank_null_returnsTrue() {
        assertTrue(userService.isBlank(null));
    }

    @Test
    void isBlank_emptyString_returnsTrue() {
        assertTrue(userService.isBlank(""));
    }

    @Test
    void isBlank_nonEmptyString_returnsFalse() {
        assertFalse(userService.isBlank("hello"));
    }

    // --- buildUserList (private) via reflection ---

    @Test
    @SuppressWarnings("unchecked")
    void buildUserList_returnsListContainingTestEntry() throws Exception {
        Method method = UserService.class.getDeclaredMethod("buildUserList");
        method.setAccessible(true);
        List<String> result = (List<String>) method.invoke(userService);
        assertEquals(1, result.size());
        assertEquals("test", result.get(0));
    }

    // --- constant ---

    @Test
    void maxUsers_constantIs100() {
        assertEquals(100, UserService.MAX_USERS);
    }
}
