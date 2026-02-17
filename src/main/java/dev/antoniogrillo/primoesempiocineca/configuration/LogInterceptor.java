package dev.antoniogrillo.primoesempiocineca.configuration;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LogInterceptor implements WebGraphQlInterceptor {
    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        return chain.next(request)
                .doOnNext(response->{
                   if(!response.getErrors().isEmpty()){
                       System.out.println("INIZIO ERRORE");
                       //TODO gestisco i log di errore su tutte le chiamate
                       response.getErrors().forEach(System.out::println);
                       System.out.println("SONO UN ERRORE OOOOHHHH");
                   }
                });
    }
}
