package com.example.tdd.assertion.customize;

import com.example.tdd.assertion.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @see <a href=https://www.baeldung.com/assertj-custom-assertion>Assertj Custom Assertion (baeldung)</a>
 */
class CustomAssertionTest {

    @Test
    void whenPersonNameMatches_thenCorrect() {
        Person person = new Person("John Doe", 20);
        PersonAssert.assertThat(person)
            .hasFullName("John Doe")
            .isAdult();
    }

    @Test
    @Disabled("This test would be failed")
    void whenPersonAgeLessThanEighteen_thenNotAdult() {
        Person person = new Person("Jane Roe", 16);

        // assertion fails
        PersonAssert.assertThat(person).isAdult();
    }

    @Test
    void whenPersonDoesNotHaveAMatchingNickname_thenIncorrect() {
        Person person = new Person("John Doe", 20);
        person.addNickname("Nick");

        PersonAssert.assertThat(person)
            .isAdult()
            .hasNickName("Nick");
    }
}


