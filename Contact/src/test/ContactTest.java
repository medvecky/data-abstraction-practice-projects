package test;

import model.Contact;
import model.ContactType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {
    Contact testFriend;
    Contact testFamily;
    Contact testWork;

    @BeforeEach
    void setUp() {
        testFriend = new Contact("Test friend", "333555", ContactType.FRIEND);
        testFamily = new Contact("Test family", "777888", ContactType.FAMILY);
        testWork = new Contact("Test work", "999555", ContactType.WORK);
    }

    @Test
    public void testGetters() {
        assertEquals("Test friend", testFriend.getName());
        assertEquals("333555", testFriend.getNumber());
        assertEquals(ContactType.FRIEND, testFriend.getType());
    }

    @Test
    void call() {
        testFriend.call("24/11/2019");
        assertEquals(1, testFriend.getCallLog().size());
        assertEquals("24/11/2019", testFriend.getCallLog().get(0));
        testFriend.call("25/11/2019");
        assertEquals(2, testFriend.getCallLog().size());
        assertEquals("25/11/2019", testFriend.getCallLog().get(1));
    }

    @Test
    void generateResponse() {
        assertEquals("I'll get back to you at work.", testWork.generateResponse());
        assertEquals("See you at home.", testFamily.generateResponse());
        assertEquals("Meet you at the cafe.", testFriend.generateResponse());

    }
}