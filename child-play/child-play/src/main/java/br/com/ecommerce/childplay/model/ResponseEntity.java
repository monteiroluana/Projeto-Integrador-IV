package br.com.ecommerce.childplay.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(content = JsonInclude.Include.NON_NULL, value = JsonInclude.Include.NON_DEFAULT)
public class ResponseEntity implements Serializable {

    public static class Response {

        private static final long serialVersionUID = -5393192543608035163L;
        private long id;
        private long epochTime;
        private int returnId;
        private String returnMsg;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getEpochTime() {
            return epochTime;
        }

        public void setEpochTime(long epochTime) {
            this.epochTime = epochTime;
        }

        public int getReturnId() {
            return returnId;
        }

        public void setReturnId(int returnId) {
            this.returnId = returnId;
        }

        public String getReturnMsg() {
            return returnMsg;
        }

        public void setReturnMsg(String returnMsg) {
            this.returnMsg = returnMsg;
        }

        public Response() {
        }

        public Response(long epochTime, int returnId, String returnMsg) {
            this.epochTime = epochTime;
            this.returnId = returnId;
            this.returnMsg = returnMsg;
        }

        public static Response createSuccess() {
            return new Response(new Date().getTime(), 0, "Success.");
        }

        public static Response createUnknownError() {
            return new Response(new Date().getTime(), 99, "Unknown error.");
        }

        public static Response createError(Exception e) {
            return new Response(new Date().getTime(), 99, e.getMessage());
        }
    }

    private Object data;
    private Response response;

    public ResponseEntity() {
    }

    private ResponseEntity(Response response) {
        this.response = response;
    }

    public ResponseEntity(long epochTime, int returnId, String returnMsg) {
        this.response = new Response(epochTime, returnId, returnMsg);
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
