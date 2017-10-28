package com.KirklandKoncepts;

/**
 * Created by Kirkland on 10/27/17.
 */
public class StringResponse extends Response {

    @Override
    public Object getResponse() {
        return super.getResponse();
    }

    @Override
    public void setResponse(Object response) {
        String string = (String) response;
        super.setResponse(string);
    }
}
