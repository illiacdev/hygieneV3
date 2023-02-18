package kr.co.has.hygiene.back_end.graphql;

import graphql.language.StringValue;
import graphql.schema.*;
import graphql.schema.idl.RuntimeWiring;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@Configuration
public class GraphQlConfig {
    static GraphQLScalarType typeLocalDate = GraphQLScalarType.newScalar()
            .name("LocalDate")
            .coercing(new Coercing<LocalDate, String>() {

                @Override
                public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                    LocalDate localDate = (LocalDate) dataFetcherResult;
                    String format = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                    String format = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//                    return format;
                    return format;
                }

                @Override
                public @NotNull LocalDate parseValue(@NotNull Object input) throws CoercingParseValueException {
                    LocalDate parse = LocalDate.parse((String) input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return parse;
                }

                @Override
                public @NotNull
                LocalDate parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                    LocalDate parse = LocalDate.parse(((StringValue) input).getValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return parse;
                }
            })
            .build();

    static GraphQLScalarType typeLocalDateTime = GraphQLScalarType.newScalar()
            .name("LocalDateTime")
            .coercing(new Coercing<LocalDateTime, String>() {

                @Override
                public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                    LocalDateTime localDateTime = (LocalDateTime) dataFetcherResult;
//                    String format = localDateTime.toString();
                    String format = localDateTime.format(ISO_LOCAL_DATE_TIME);
//                    String format = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//                    return format;
                    return format;
                }

                @Override
                public @NotNull LocalDateTime parseValue(@NotNull Object input) throws CoercingParseValueException {
                    LocalDateTime parse = LocalDateTime.parse((String) input, ISO_LOCAL_DATE_TIME);
//                    LocalDateTime parse = LocalDateTime.now();
                    return parse;
                }

                @Override
                public @NotNull
                LocalDateTime parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
//                    LocalDate parse = LocalDate.parse(((StringValue) input).getValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDateTime parse = LocalDateTime.now();
                    return parse;
                }
            })
            .build();

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> {
            RuntimeWiring.Builder wiringBuilder1 = wiringBuilder;
//            wiringBuilder1.scalar(ExtendedScalars.DateTime)
            wiringBuilder1.scalar(typeLocalDate)
                    .build();
            wiringBuilder1.scalar(typeLocalDateTime).build();
        };
    }
}
