package br.com.ecommerce.childplay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonInclude(content = JsonInclude.Include.NON_NULL, value = JsonInclude.Include.NON_DEFAULT)
public class ResponseEntity implements Serializable {

    public static class Response {

        private static final long serialVersionUID = -5393192543608035163L;

        private String returnMsg;

        public String getReturnMsg() {
            return returnMsg;
        }

        public void setReturnMsg(String returnMsg) {
            this.returnMsg = returnMsg;
        }

        public Response() {
        }

        public Response(String returnMsg) {
            this.returnMsg = returnMsg;
        }

        public static Response createSuccess() {
            return new Response( "Success.");
        }

        public static Response createUnknownError() {
            return new Response( "Unknown error.");
        }

        public static Response createError(Exception e) {
            return new Response( e.getMessage());
        }
    }

    private Object data;
    private Response response;

    public ResponseEntity() {
    }

    private ResponseEntity(Response response) {
        this.response = response;
    }

    public ResponseEntity( String returnMsg) {
        this.response = new Response(returnMsg);
    }

    public static ResponseEntity createSuccess() {
        return new ResponseEntity(Response.createSuccess());
    }

    public static ResponseEntity createUnknownError() {
        return new ResponseEntity(Response.createUnknownError());
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
