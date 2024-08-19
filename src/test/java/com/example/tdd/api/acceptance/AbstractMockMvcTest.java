package com.example.tdd.api.acceptance;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
public abstract class AbstractMockMvcTest {
    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    @BeforeEach
    protected void setUp(WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
                .operationPreprocessors()
                .withRequestDefaults(prettyPrint(), modifyHeaders().remove(HttpHeaders.AUTHORIZATION))
                .withResponseDefaults(
                    prettyPrint(),
                    modifyHeaders()
                        .remove(HttpHeaders.VARY)
                        .remove(HttpHeaders.CACHE_CONTROL)
                        .remove(HttpHeaders.PRAGMA)
                        .remove(HttpHeaders.EXPIRES)
                        .remove(HttpHeaders.TRANSFER_ENCODING)
                        .remove(HttpHeaders.DATE)
                        .remove(HttpHeaders.CONNECTION)
                ))
            .build();
    }

    protected ResultActions perform(MockHttpServletRequestBuilder requestBuilder, Snippet... snippets) throws Exception {
        return this.mockMvc.perform(requestBuilder
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(document(apiIdentifier(), snippets));
    }

    protected String apiIdentifier() {
        return identifier() + "/{method-name}";
    }

    protected abstract String identifier();
}
