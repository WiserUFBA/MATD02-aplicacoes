/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import model.Lampada;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author lucasbulcao
 */
public class LampadaCliente {

    private static final int HTTP_COD_SUCESSO = 200;

    public Lampada getLampada(int id) throws JAXBException, MalformedURLException {
        Lampada lamp = null;
        try {
            URL url = new URL("http://localhost:8080/REST-Lamps/devices/lamp/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                throw new RuntimeException("HTTP error code : " + con.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
            JAXBContext jaxbContext = JAXBContext.newInstance(Lampada.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            lamp = (Lampada) jaxbUnmarshaller.unmarshal(br);
            System.out.println("------ Dados da Lampada -------- \n");
            System.out.println("ID da lampada : " + lamp.getId());
            System.out.println("Estado da Lampada : " + lamp.getState());

            con.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lamp;
    }

    public void setLampada(Lampada lamp) throws UnsupportedEncodingException, IOException {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/REST-Lamps/devices/lamp/"+lamp.getId());
        List nameValuePairs = new ArrayList(1);
        nameValuePairs.add(new BasicNameValuePair("status", Integer.toString(lamp.getState())));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }

}
