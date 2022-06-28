package io.winty.routes;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import io.winty.model.dto.Request;

public class Router extends RouteBuilder {
    
    private final Set<Request> data = Collections.synchronizedSet(new LinkedHashSet<>());
    
    public Router() {
        data.add(new Request("1", "teste"));
        data.add(new Request("2", "teste2"));
    }

    @Override
    public void configure() throws Exception {
        
        restConfiguration().bindingMode(RestBindingMode.auto);
        
        rest("/hva/v1")
            .id("REST ROUTER")
            // .get()
            //    from("direct:listUsers")
            //     .to("direct:get")
            .post()
                .type(Request.class)
                .to("direct:mainrouter");
                
        from("direct:listUsers")
            .setBody().constant(data);
        
        from("direct:addUser")
            .process()
                .body(Request.class, data::add)
            .setBody()
                .constant(data);
                
    from("direct:mainrouter")
        .id("MAINROUTER")
        .circuitBreaker()
            .resilience4jConfiguration()
                .timeoutEnabled(true)
                .timeoutDuration(2000)
                .end()
            .log("Resilience processing start: ${threadName}")
            .to("direct:addUser")
            .log("Resilience processing end: ${threadName}")
        .onFallback()
            .to("direct:addUser")
        .end()
        .log("After Resilience ${body}");
            
    }
    
}
