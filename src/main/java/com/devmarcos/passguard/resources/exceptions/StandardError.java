package com.devmarcos.passguard.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StandardError implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "dd-MM-yyyy'T'HH:mm:ss",
                timezone = "GMT" )
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public static class Builder {

        private Instant timestamp;
        private Integer status;
        private String error;
        private String message;
        private String path;

        public Builder setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        public Builder setStatus(Integer status) {
            this.status = status;
            return this;
        }
        public Builder setError(String error) {
            this.error = error;
            return this;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public StandardError build() {
            return new StandardError(this);
        }
        
    }

    public StandardError(Builder builder) {
        timestamp = builder.timestamp;
        status = builder.status;
        error = builder.error;
        message = builder.message;
        path = builder.path;
    }

}
