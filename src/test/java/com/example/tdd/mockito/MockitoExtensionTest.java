package com.example.tdd.mockito;

import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoExtensionTest {
    @Mock
    private List<String> mockList;

    @Test
    @DisplayName("")
    void mockitoExtensionTest() {
        mockList.add("one");
        verify(mockList).add("one");
    }
}
