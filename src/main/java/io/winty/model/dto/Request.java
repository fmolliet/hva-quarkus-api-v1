package io.winty.model.dto;

import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Request {
    
    private String id;
    private String name;
    
    public Request(){}
    
    public Request(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
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
