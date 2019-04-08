package cybernet.lunchapp.local.lunchapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintStream;
import java.io.InputStream;
import java.net.Socket;

public class ServerConnection {

    public JSONArray sendRequest (JSONObject request){
        JSONArray relevantPeople;
        try{
            Socket serverconnection = new Socket("192.168.137.1", 27015);
            PrintStream toServer = new PrintStream(serverconnection.getOutputStream());
            //toServer.print("{\"username\":\"johan\", \"password\":\"sala\", \"type\":\"2\"}");
            toServer.print(request.toString());
            String serverResponse = readInputStream(serverconnection.getInputStream());
            Log.d("server response", serverResponse);
            try {
                relevantPeople = new JSONArray(serverResponse);
                serverconnection.close();
                return relevantPeople;
            }
            catch(org.json.JSONException exc){
                Log.d("err creating jsonArr: ", exc.getMessage());
            }

            serverconnection.close();

        }
        catch(java.io.IOException exc){
            Log.d("network error", exc.getMessage());
        }
        return null;
    }

    private String readInputStream(InputStream inStream){
        Log.d("program at", "readInputStream");
        StringBuilder outString = new StringBuilder();
        Boolean bytesToRead = true;
        do {
            try {
                int strByte = inStream.read();
                Log.d("instream read", String.valueOf((char)strByte));
                if(strByte == 0 ){
                    bytesToRead = false;
                    Log.d("readInputStream:", "end of stream");
                }
                else{
                    outString.append((char)strByte);
                }
            }
            catch(java.io.IOException exc){
                bytesToRead = false;
                Log.d("error reading stream", exc.getMessage());
            }
        }
        while(bytesToRead);
        Log.d("stream read result:",outString.toString());
        return outString.toString();
    }
}
