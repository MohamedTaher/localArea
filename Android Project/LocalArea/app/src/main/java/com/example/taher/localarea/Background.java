package com.example.taher.localarea;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


/**
 * Created by taher on 2/3/16.
 */
public class Background extends AsyncTask<String, Void, String> {
    Context ctx;
    public String result = "";

    private String ip_address = "192.168.1.3";//write here your ip address before build

    private String signup_url = "http://"+ip_address+"/signup.php";//192.168.1.6
    private String login_url = "http://"+ip_address+"/login.php";
    private String searchID_url = "http://"+ip_address+"/search_id.php";
    private String searchUsername_url = "http://"+ip_address+"/usernameSearch.php";
    private String signout_url = "http://"+ip_address+"/logout.php";
    private String isFriend_url = "http://"+ip_address+"/is_friend.php";
    private String isAddFriend_url = "http://"+ip_address+"/is_addFriend.php";
    private String isRequestedFriend_url = "http://"+ip_address+"/is_requestedFriend.php";
    private String addFriend_url = "http://"+ip_address+"/addFriend.php";
    private String acceptFriend_url = "http://"+ip_address+"/acceptFriend.php";
    private String getMessageThreadsForUser_url = "http://"+ip_address+"/getMessageThreadsForUser.php";


    public Background(Context ctx) {
        super();
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {


        String method = params[0].toString();

        if (method.equals("signup")) {

            try {

                String uname = params[1].toString();
                String upassword = params[2].toString();
                String uemail = params[3].toString();

                // open a connection to the site
                URL url = new URL(signup_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("uname=" + uname);
                ps.print("&upassword=" + upassword);
                ps.print("&uEmail=" + uemail);

                // we have to get the input stream in order to actually send the
                // request
                con.getInputStream();
                // close the print stream
                ps.close();

                return "1";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("login")) {

            try {

                String uname = params[1].toString();
                String upassword = params[2].toString();

                // open a connection to the site
                URL url = new URL(login_url);

                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());

                // send your parameters to your site
                ps.print("uname=" + uname);
                ps.print("&upassword=" + upassword);

                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }

                ps.close();

                result += res;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "1";

        } else if (method.equals("searchByID")) {
            try {
                String uID = params[1];
                URL url = new URL(searchID_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("uid=" + uID);

                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }
                // close the print stream
                ps.close();

                // id = "" + res;
                result = res + "";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("searchName")) {
            try {
                String uname = params[1];

                // open a connection to the site
                URL url = new URL(searchUsername_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("uname=" + uname);

                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }

                // close the print stream
                ps.close();

                // id = "" + res;
                result = res + "";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("logout")) {
            String user_id = params[1].toString();
            try {
                URL url = new URL(signout_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("user_id=" + user_id);

                // we have to get the input stream in order to actually send the
                // request
                con.getInputStream();
                ps.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("isFriend")) {

            try{
                String first_user = (String)params[1];
                String second_user = (String)params[2];

                String link=isFriend_url;
                String data  = URLEncoder.encode("first_user", "UTF-8") + "=" + URLEncoder.encode(first_user, "UTF-8");
                data += "&" + URLEncoder.encode("second_user", "UTF-8") + "=" + URLEncoder.encode(second_user, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                result =  sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

/*
            String src = params[1];
            String des = params[2];
            try {
                // open a connection to the site
                URL url = new URL(isFriend_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("first_user=" + src);
                ps.print("second_user=" + des);
                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }
                // close the print stream
                ps.close();

                // id = "" + res;
                result = res + "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        } else if (method.equals("isAddFriend")) {

            try{
                String first_user = (String)params[1];
                String second_user = (String)params[2];

                String link=isAddFriend_url;
                String data  = URLEncoder.encode("first_user", "UTF-8") + "=" + URLEncoder.encode(first_user, "UTF-8");
                data += "&" + URLEncoder.encode("second_user", "UTF-8") + "=" + URLEncoder.encode(second_user, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                result =  sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
/*
            String src = params[1];
            String des = params[2];
            try {
                // open a connection to the site
                URL url = new URL(isAddFriend_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("first_user=" + src);
                ps.print("second_user=" + des);
                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }
                // close the print stream
                ps.close();

                // id = "" + res;
                result = res + "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        } else if (method.equals("isRequestedFriend")) {

            try{
                String first_user = (String)params[1];
                String second_user = (String)params[2];

                String link=isRequestedFriend_url;
                String data  = URLEncoder.encode("first_user", "UTF-8") + "=" + URLEncoder.encode(first_user, "UTF-8");
                data += "&" + URLEncoder.encode("second_user", "UTF-8") + "=" + URLEncoder.encode(second_user, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                result =  sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

            /*
            String src = params[1];
            String des = params[2];
            try {
                // open a connection to the site
                URL url = new URL(isRequestedFriend_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("first_user=" + src);
                ps.print("second_user=" + des);
                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }
                // close the print stream
                ps.close();

                // id = "" + res;
                result = res + "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        } else if (method.equals("addFriend")) {

            try{
                String first_user = (String)params[1];
                String second_user = (String)params[2];

                String link=addFriend_url;
                String data  = URLEncoder.encode("first_user", "UTF-8") + "=" + URLEncoder.encode(first_user, "UTF-8");
                data += "&" + URLEncoder.encode("second_user", "UTF-8") + "=" + URLEncoder.encode(second_user, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                result =  sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

/*
            String src = params[1];
            String des = params[2];

            try {
                // open a connection to the site
                URL url = new URL(addFriend_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("first_user=" + src);
                ps.print("second_user=" + des);
                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }
                // close the print stream
                ps.close();

                // id = "" + res;
                result = res + "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
/*
            String src = params[1];
            String des = params[2];
            try {
                // open a connection to the site
                URL url = new URL(addFriend_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("source_id=" + src);
                ps.print("des_id=" + des);
                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }
                // close the print stream
                ps.close();

                // id = "" + res;
                result = res + "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        } else if (method.equals("acceptFriend")) {

            try{
                String first_user = (String)params[1];
                String second_user = (String)params[2];

                String link=acceptFriend_url;
                String data  = URLEncoder.encode("first_user", "UTF-8") + "=" + URLEncoder.encode(first_user, "UTF-8");
                data += "&" + URLEncoder.encode("second_user", "UTF-8") + "=" + URLEncoder.encode(second_user, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                result =  sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

            /*String src = params[1];
            String des = params[2];
            try {
                // open a connection to the site
                URL url = new URL(acceptFriend_url);
                URLConnection con = url.openConnection();
                // activate the output
                con.setDoOutput(true);
                PrintStream ps = new PrintStream(con.getOutputStream());
                // send your parameters to your site
                ps.print("source_id=" + src);
                ps.print("des_id=" + des);
                // we have to get the input stream in order to actually send the
                // request
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String line = "", res = "";
                while ((line = input.readLine()) != null) {
                    res += line;
                }
                // close the print stream
                ps.close();

                // id = "" + res;
                result = res + "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        } else if (method == "getMessageThreadsForUser") {
            //get from database the messages that contains this user id in the thread names
            String uID = params[1];
            //1.call the retrieve threads php file and get the response
            //  L> The message format is this [No.Threads]/[No.IDs]/[ID1, ID2, ...]/[No.Msgs]/[Lngth]/[Msg]
            //2.loop on the threads and for each thread replace the ID with the name of the user
            //3.return the result ^_^

            try{
                String link= getMessageThreadsForUser_url;
                String mode = "id";
                String data  = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                data += "&" + URLEncoder.encode("mode", "UTF-8") + "=" + URLEncoder.encode(mode, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                result =  sb.toString();
            }
            catch(Exception e) {
                return new String("Exception: " + e.getMessage());
            }
//            try {
//                URL url = new URL(getMessageThreadsForUser_url);
//                URLConnection con = url.openConnection();
//                con.setDoOutput(true);
//                PrintStream ps = new PrintStream(con.getOutputStream());
//                ps.print("uid=" + uID);
//                ps.print("&mode=id");
//                BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String line = "", res = "";
//                while ((line = input.readLine()) != null) {
//                    res += line;
//                }
//                ps.close();
//                return res;
//            }
//            catch(Exception e){
//                return new String("Exception: " + e.getMessage());
//            }
        }

        return "-1";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(ctx, "background "+ this.result, Toast.LENGTH_LONG).show();

    }
}

/*            try{
                String username = (String)params[1];
                String password = (String)params[2];
                String email = (String)params[3];

                String link=signup_url;
                String data  = URLEncoder.encode("uname", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("upassword", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                data += "&" + URLEncoder.encode("uEmail", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                return sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
*/