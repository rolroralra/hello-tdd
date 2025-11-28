package com.example.tdd.assertion.customize;

import com.example.tdd.assertion.Person;
import org.assertj.core.api.AbstractAssert;

/**
 * @see <a href=https://www.baeldung.com/assertj-custom-assertion>Assertj Custom Assertion (baeldung)</a>
 */
class PersonAssert extends AbstractAssert<PersonAssert, Person> {

    public PersonAssert(Person actual) {
        super(actual, PersonAssert.class);
    }

    // assertion methods described later

    public static PersonAssert assertThat(Person actual) {
        return new PersonAssert(actual);
    }

    public PersonAssert hasFullName(String fullName) {
        isNotNull();
        if (!actual.getFullName().equals(fullName)) {
            failWithMessage("Expected person to have full name %s but was %s",
                fullName, actual.getFullName());
        }
        return this;
    }

    public PersonAssert isAdult() {
        isNotNull();
        if (actual.getAge() < 18) {
            failWithMessage("Expected person to be adult");
        }
        return this;
    }

    public PersonAssert hasNickName(String nickName) {
        isNotNull();
        if (!actual.getNickNames().contains(nickName)) {
            failWithMessage("Expected person to have nickname %s",
                nickName);
        }
        return this;
    }

}