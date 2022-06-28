package io.winty.model.dto;

import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@RegisterForReflection
public class Request {
    
    private String id;
    private String name;
    
    public Request(){}
    
    public Request(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Request)) {
            return false;
        }

        Request other = (Request) obj;

        return Objects.equals(other.name, this.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
