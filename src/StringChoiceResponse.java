/**
 * Created by Kirkland on 10/27/17.
 */
public class StringChoiceResponse extends ChoiceResponse {



    @Override
    public Object getResponse() {
        return super.getResponse();
    }

    @Override
    public void setResponse(Object response) {
        String string = (String) response;
        super.setResponse(string);
    }

    @Override
    public void display() {
        String string = (String) getResponse();
        System.out.println(string);
    }

    @Override
    public boolean equals(Object obj) {
        StringChoiceResponse responseObj = (StringChoiceResponse)obj;

        String response1 = (String)getResponse();
        String response2 = (String)responseObj.getResponse();

        return response1.equals(response2);


    }
}
