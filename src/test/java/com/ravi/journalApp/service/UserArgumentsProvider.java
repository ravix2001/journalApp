package com.ravi.journalApp.service;

import com.ravi.journalApp.entity.User;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider{

    @Override
    public Stream<? extends Arguments> provideArguments(org.junit.jupiter.api.extension.ExtensionContext context) throws Exception {
        return Stream.of(
            //Arguments.of(User.builder().username("ravi").password("ravi").build()),
            Arguments.of(User.builder().username("laxman").password("laxman").build())
        );
    }
}
