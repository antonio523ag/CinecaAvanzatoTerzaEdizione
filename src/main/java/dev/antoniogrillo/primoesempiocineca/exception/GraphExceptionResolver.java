package dev.antoniogrillo.primoesempiocineca.exception;

import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class GraphExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable e, DataFetchingEnvironment d){
        if(e instanceof ConstraintViolationException c){
            return GraphQLError.newError()
                    .message(c.getMessage())
                    .errorType(ErrorType.BAD_REQUEST)
                    .build();
        }
        if(e instanceof ResponseStatusException r){
            return GraphQLError.newError()
                    .message(r.getMessage())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        }
        if(e instanceof CustomResponseException c){
            return GraphQLError.newError()
                    .message(c.getMessage())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        }

        return GraphQLError.newError()
                    .message(e.getMessage())
                    .errorType(ErrorType.INTERNAL_ERROR)
                    .build();
    }
}
